import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Utils {

    public static List<List<String>> readExcel(String path) {
        List<List<String>> result = new ArrayList<>();
        try {
            File file = new File(path);
            FileInputStream inputStream = new FileInputStream(file);
            Workbook wb = new XSSFWorkbook(inputStream);
            Sheet sheet = wb.getSheetAt(0);
            int columns = sheet.getRow(0).getPhysicalNumberOfCells();
            for (Row row : sheet) {
                ArrayList<String> rowList = new ArrayList<>();
                for (int i = 0; i < columns; i++) {
                    Cell cell = row.getCell(i);
                    if (cell == null) {
                        rowList.add(null);
                        continue;
                    }
                    cell.setCellType(CellType.STRING);
                    rowList.add(cell.getStringCellValue());
                }
                result.add(rowList);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void createNewFile(List<List<String>> data, String newFilePath) {
        File file = new File(newFilePath);
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream outputStream = null;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            String sheetName = "sheet1";
            Sheet sheet = workbook.createSheet(sheetName);
            for (int i = 0; i < data.size(); i++) {
                Row row = sheet.createRow(i);
                List<String> rowList = data.get(i);
                for (int j = 0; j < rowList.size(); j++) {
                    Cell cell = row.createCell(j);
                    String value = rowList.get(j);
                    if (value != null) {
                        cell.setCellValue(value);
                    }
                }
            }
            outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void handleData(List<List<String>> data) {
        nameMapToService(data);
    }

    /**
     * 名称映射服务
     */
    private static void nameMapToService(List<List<String>> data) {
        final int B = 1;
        final int H = 7;
        HashMap<String, String> map = new HashMap<>();
//        map.put("ftpd", "ftp");
//        map.put("httpd", "http");
//        map.put("smtpd", "smtp");
//        map.put("talnetd", "talnet");
        map.put("pop3", "pop3");
        map.put("pop3d", "pop3");
        map.put("imapd", "imap");

        for (List<String> row : data) {
            String id = row.get(0);
            if (!id.equals("id")) {
                int num = Integer.parseInt(id);
                if (num > 23595) {
                    break;
                }
            }
            for (String key : map.keySet()) {
                if (row.get(B).contains(key)) {
                    row.set(H, map.get(key));
                }
            }
        }
    }

    /**
     * 合并两列
     */
    private static void mergeTwoColumn(List<List<String>> data) {
        boolean has = false;
        for (List<String> rowList : data) {
            if (rowList.get(0).equals("C310")) {
                has = true;
            }
            String connect = "-";
            if (has) {
                connect = "_";
            }
            String value = rowList.get(0) + connect + rowList.get(1);
            rowList.set(0, value);
            rowList.remove(1);
        }
    }
}
