/*
package com.qa.opencart.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    private Workbook workbook;
    private Sheet sheet;

    public ExcelUtil(String filePath, String sheetName) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetName);
            }

        } catch (Exception e) {
            System.out.println("Excel file loading failed: " + filePath);
            e.printStackTrace();
        }
    }

    // Get Row Count
    public int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    // Get Column Count
    public int getColumnCount() {
        return sheet.getRow(0).getPhysicalNumberOfCells();
    }

    // Get Cell Data
    public String getCellData(int rowNum, int colNum) {
        return sheet.getRow(rowNum).getCell(colNum).toString();
    }

    // Get All Data (For DataProvider)
    public Object[][] getSheetData() {

        int rows = getRowCount();
        int cols = getColumnCount();

        Object[][] data = new Object[rows - 1][cols];

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] = getCellData(i, j);
            }
        }

        return data;
    }

    // Write Data to Cell
    public void setCellData(int rowNum, int colNum, String value, String filePath) {

        try {
            sheet.getRow(rowNum).createCell(colNum).setCellValue(value);

            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Close Workbook
    public void closeWorkbook() {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/
