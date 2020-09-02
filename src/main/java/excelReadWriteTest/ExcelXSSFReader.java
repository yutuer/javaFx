package excelReadWriteTest;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description 新的excel读取器
 * @Author zhangfan
 * @Date 2020/8/4 20:25
 * @Version 1.0
 */
public class ExcelXSSFReader
{

    private String fileName;

    /**
     * 新的可以带有flush功能的workbook
     */
    private SXSSFWorkbook sxWorkbook;

    public ExcelXSSFReader(String fileName)
    {
        this.fileName = fileName;
    }

    public void initRead() throws OpenXML4JException, IOException
    {
        try (InputStream is = new FileInputStream(fileName))
        {
            Workbook workbook = WorkbookFactory.create(is);
            if (workbook instanceof XSSFWorkbook)
            {
                sxWorkbook = new SXSSFWorkbook(XSSFWorkbook.class.cast(workbook));
            }
        }
    }

    public SXSSFSheet getSheet()
    {
        return sxWorkbook.getSheetAt(0);
    }
}
