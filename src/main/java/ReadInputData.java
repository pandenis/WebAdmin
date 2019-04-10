import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadInputData {

    public String readExcel(String filePath, String fileName, String sheetName, int rowNumber, int cellNumber) throws IOException {

        File file = new File(filePath + fileName);

        FileInputStream inputStream = new FileInputStream(file);

        Workbook inputWorkbook = null;

        String fileExtensionName = fileName.substring(fileName.indexOf("."));

        if (fileExtensionName.equals(",xlsx")) {
            inputWorkbook = new XSSFWorkbook(inputStream);
        }

        else if (fileExtensionName.equals("xls")) {
            inputWorkbook = new HSSFWorkbook(inputStream);
        }

        Sheet inputSheet = inputWorkbook.getSheet(sheetName);

        int rowCount = inputSheet.getLastRowNum() - inputSheet.getFirstRowNum();


        Row row;
        row = inputSheet.getRow(rowNumber);
        String cellValue = row.getCell(cellNumber).getStringCellValue();

        return cellValue;
    }

}

