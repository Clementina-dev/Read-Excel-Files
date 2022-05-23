package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import static java.lang.System.out;

public class ExcelReader {
    static XSSFSheet sheet;
    static XSSFWorkbook workbook;

    //            create arraylist
    static ArrayList<Object> data = new ArrayList<>();

    public static void excelFiles() {
        try {
//            creating a new file instance
            File file = new File("src/main/resources/ProductsList.xlsx");
//            obtaining byte from the file
            FileInputStream fis = new FileInputStream(file);
//            creating workbook instance that refers to the .xlsx file
            workbook = new XSSFWorkbook(fis);
//            creating a sheet object to retrieve objects
            sheet = workbook.getSheetAt(0);
//            iterating over Excel file
            for (Row row : sheet) {
                //            iterating over each column
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
//                        field that represents string cell type
                        case STRING -> {
                            data.add(cell.getStringCellValue());
                            out.println(cell.getStringCellValue() + "\t\t\t");
                        }
                        case NUMERIC -> {
                            data.add(cell.getNumericCellValue());
//                            out.println(cell.getNumericCellValue() + "\t\t\t");
                            DataFormatter formatter = new DataFormatter();
                            String formatted = formatter.formatCellValue(cell);
                            out.println(formatted);
                        }
                        default -> throw new IllegalStateException("Unexpected value: " + cell.getCellType());
                    }
                }
                out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        out.println(data);
    }
}
