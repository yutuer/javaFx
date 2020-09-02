package asm;

import org.objectweb.asm.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/7/15 14:06
 * @Version 1.0
 */
public class AddField extends ClassVisitor
{

    private String name;
    private int access;
    private String desc;
    private Object value;

    private boolean duplicate;

    public AddField(ClassVisitor cv, String name, int access, String desc, Object value)
    {
        super(Opcodes.ASM5, cv);
        this.name = name;
        this.access = access;
        this.desc = desc;
        this.value = value;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value)
    {
        if (this.name.equals(name))
        {
            duplicate = true;
        }
        return super.visitField(access, name, desc, signature, value);
    }

    @Override
    public void visitEnd()
    {
        if (!duplicate)
        {
            FieldVisitor fv = super.visitField(access, name, desc, null, value);
            if (fv != null)
            {
                fv.visitEnd();
            }
        }
        super.visitEnd();
    }

    public static void main(String[] args) throws Exception
    {
        String output = System.getProperty("user.dir") + "/libjava/output";
        String classDir = System.getProperty("user.dir") + "/libjava/output/MainActivity.class";
        ClassReader classReader = new ClassReader(new FileInputStream(classDir));
        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
        ClassVisitor addField = new AddField(classWriter,
                "field",
                Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL,
                Type.getDescriptor(String.class),
                "value"
        );
        classReader.accept(addField, ClassReader.EXPAND_FRAMES);
        byte[] newClass = classWriter.toByteArray();
        File newFile = new File(output, "MainActivity1.class");
        new FileOutputStream(newFile).write(newClass);
    }
}
