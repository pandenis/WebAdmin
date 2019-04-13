import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadInputData {

    public String readExcel(String filePath, String fileName, int sheetNumber, int rowNumber, int cellNumber) throws IOException {

        File file = new File(filePath + "/" +fileName);

        FileInputStream inputStream = new FileInputStream(file);

        XSSFWorkbook inputWorkbook = new XSSFWorkbook(inputStream);


        XSSFSheet inputSheet = inputWorkbook.getSheetAt(sheetNumber);

        Row row = inputSheet.getRow(rowNumber);
        Cell cell = row.getCell(cellNumber);

        String cellValue = cell.getStringCellValue();

        return cellValue;
    }

}

