package com.boot.test.utils.excel;

import jxl.write.*;
import jxl.write.Alignment;
import jxl.write.Boolean;
import jxl.write.Border;
import jxl.write.BorderLineStyle;
import jxl.write.Colour;
import jxl.write.Number;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tao
 * Date: 13-9-9
 * Time: 下午1:55
 * To change this template use File | Settings | File Templates.
 */
public class ExcelUtils {

    private final static String[] CELL_NAME = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public ExcelUtils() {

    }

    public HSSFWorkbook createExcel(List<ExcelLabel> title, List list, Class c) throws Exception {
        String[] titleName = new String[title.size()];
        HSSFWorkbook workbook = new HSSFWorkbook();// 创建一个Excel文件
        HSSFSheet sheet = workbook.createSheet();// 创建一个Excel的Sheet
        sheet.createFreezePane(7, 0);// 冻结
        HSSFFont headfont = workbook.createFont();
        headfont.setFontName("黑体");
        headfont.setFontHeightInPoints((short) 10);// 字体大小
        headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
        HSSFCellStyle headstyle = workbook.createCellStyle();
        headstyle.setFont(headfont);
        headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中


        HSSFCellStyle headstyle1 = workbook.createCellStyle();
        headstyle1.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
        headstyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        headstyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        HSSFCellStyle headstyle2 = workbook.createCellStyle();
        headstyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        headstyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

        HSSFRow row0 = sheet.createRow(0);
        for (int i = 0; i < title.size(); i++) {
            sheet.setColumnWidth(i, title.get(i).getChineseName().getBytes().length * 2 * 170);
            HSSFCell temp = row0.createCell(i);
            temp.setCellValue(new HSSFRichTextString(title.get(i).getChineseName()));
            temp.setCellStyle(headstyle);
            titleName[i] = title.get(i).getName();
        }
        int row = 1;
        for (Object entity : list) {
            HSSFRow temp = sheet.createRow(row);
            Field[] fields = c.getDeclaredFields();
            int cellIndex = 0;
            for (Field field : fields) {
                HSSFCell temp1 = temp.createCell(cellIndex);
                field.setAccessible(true);
                String name = field.getName();
                int index = equert(titleName, name);
                if (index == -1) continue;
                String value = field.get(entity) != null ? field.get(entity).toString() : "";
                ExcelLabel label = title.get(index);
                switch (label.getNumberType()) {
                    case NUMBER:
                        Double aDouble = Double.valueOf(value);
                        if (aDouble == 0) {
                            temp1.setCellStyle(headstyle2);
                            temp1.setCellValue(0);
                        } else {
                            temp1.setCellStyle(headstyle1);
                            temp1.setCellValue(aDouble);
                        }
                        break;
                    case STRING:
                        temp1.setCellValue(value);
                        break;
                    case BOOLEAN:
                        break;
                    case DATETIME:
                        break;
                    case FORMATDATETIME:
                        break;
                    case FORMATNUMBER:
                        break;
                }
                cellIndex++;
            }
            row++;
        }
        return workbook;
    }

    public WritableWorkbook createExcel(List<ExcelLabel> title, OutputStream out, List list, Class c, String sheetName, int verticalFreeze, int horizontalFreeze) throws Exception {
        String[] titleName = new String[title.size()];
        WritableWorkbook workbook = jxl.Workbook.createWorkbook(out);
        WritableSheet sheet = workbook.createSheet(sheetName, 0);

        WritableFont font = new WritableFont(WritableFont.TAHOMA, 9, WritableFont.BOLD);// 定义字体
        font.setColour(Colour.BLACK);// 字体颜色
        WritableCellFormat wc = new WritableCellFormat(font);
        wc.setAlignment(Alignment.CENTRE); // 设置对齐方式
        wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框线
        wc.setBackground(jxl.format.Colour.GREY_25_PERCENT); // 设置单元格的背景颜色
        //设置第一行标题的值
        sheet.setRowView(0, 280); // 设置第一行的行高
        sheet.getSettings().setVerticalFreeze(verticalFreeze);
        sheet.getSettings().setHorizontalFreeze(horizontalFreeze);
        int i = 0;
        for (ExcelLabel label : title) {
            titleName[i] = label.getName();
            int a = (int) (label.getChineseName().getBytes().length * 2);
            sheet.setColumnView(i, a);//设置列宽
            sheet.addCell(new Label(i, 0, label.getChineseName(), wc));//设置值
            i++;
        }

        int row = 1;
        for (Object entity : list) {
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String name = field.getName();
                int index = equert(titleName, name);
                if (index == -1) continue;
                String value = field.get(entity) != null ? field.get(entity).toString() : "";
                ExcelLabel label = title.get(index);
                switch (label.getNumberType()) {
                    case NUMBER:
                        double d = 0;
                        if (StringTools.isNotempty(value)) {
                            d = Double.valueOf(value);
                        }
                        Number labelN = new Number(index, row, d);
                        sheet.addCell(labelN);
                        break;
                    case STRING:
                        sheet.addCell(new Label(index, row, value));
                        break;
                    case BOOLEAN:
                        boolean flag = false;
                        if (StringTools.isNotempty(value)) {
                            flag = java.lang.Boolean.parseBoolean(value);
                        }
                        Boolean labelB = new Boolean(index, row, flag);
                        sheet.addCell(labelB);
                        break;
                    case DATETIME:
                        if (StringTools.isNotempty(value)) {
                            Date date = DateTools.strToDate(value);
                            DateTime labelDT = new DateTime(index, row, date);
                            sheet.addCell(labelDT);
                        } else {
                            sheet.addCell(new Label(index, row, value));
                        }
                        break;
                    case FORMATDATETIME:
                        if (StringTools.isNotempty(value)) {
                            Date fat_date = DateTools.strToDate(value);
                            DateFormat df = new DateFormat(label.getFormat());
                            try {
                                WritableCellFormat wcfDF = new WritableCellFormat(df);
                                DateTime labelDTF = new DateTime(index, row, fat_date, wcfDF);
                                sheet.addCell(labelDTF);
                            } catch (Exception e) {
                                sheet.addCell(new Label(index, row, ""));
                            }
                        } else {
                            sheet.addCell(new Label(index, row, value));
                        }
                        break;
                    case FORMATNUMBER:
                        double fat_d = 0;
                        if (StringTools.isNotempty(value)) {
                            fat_d = Double.valueOf(value);
                        }
                        NumberFormat nf = new NumberFormat(label.getFormat());
                        WritableCellFormat wcfN = new WritableCellFormat(nf);
                        Number labelNF = new Number(index, row, fat_d, wcfN);
                        sheet.addCell(labelNF);
                        break;
                }
            }
            row++;
        }

        return workbook;
    }

    /**
     * 读取下标值
     *
     * @param titleName
     * @param name
     * @return
     */
    private int equert(String[] titleName, String name) {
        int i = 0;
        boolean flag = false;
        for (; i < titleName.length; i++) {
            if (titleName[i].trim().equals(name.trim())) {
                flag = true;
                break;
            }
        }
        if (!flag) i = -1;
        return i;
    }


    public static String[][] getExcel(String fileName, Workbook sheet, int ignoreRows, int sheetNum) throws IOException {
        String name = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        if (name.equalsIgnoreCase("xls")) {
            return getData(fileName, sheet, ignoreRows, sheetNum);
        } else if (name.equalsIgnoreCase("xlsx")) {
            return getDataNew(fileName, sheet, ignoreRows, sheetNum);
        }
        return null;
    }

    public static Workbook getSheetNumber(String fileName) throws IOException, InvalidFormatException {
        String name = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        InputStream in = new FileInputStream(fileName);
        Workbook wb = null;
        if (name.equalsIgnoreCase("xls")) {
            wb = new HSSFWorkbook(in);
        } else if (name.equalsIgnoreCase("xlsx")) {
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    /**
     * 读取excel
     *
     * @param fileName
     * @param ignoreRows
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String[][] getData(String fileName, Workbook wb, int ignoreRows, int sheetNum) throws IOException {
        List<String[]> result = new ArrayList<String[]>();
        int rowSize = 0;
        if (wb == null) {
            File file = new File(fileName);
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            // 打开HSSFWorkbook
            wb = new HSSFWorkbook(in);
        }
        HSSFCell cell = null;
        HSSFSheet st = (HSSFSheet) wb.getSheetAt(sheetNum);
        // 第一行为标题，不取
        for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
            HSSFRow row = st.getRow(rowIndex);
            if (row == null) {
                continue;
            }
            int tempRowSize = row.getLastCellNum() + 1;
            if (tempRowSize > rowSize) {
                rowSize = tempRowSize;
            }
            for (int i = 0; i <= row.getLastCellNum(); i++) {
                if (st.isColumnHidden(i)) {
                    rowSize--;
                }
            }
            String[] values = new String[rowSize];
            Arrays.fill(values, "");
            boolean hasValue = false;
            int hideNum = 0;
            for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                String value = "";
                if (st.isColumnHidden(columnIndex)) {
                    hideNum++;
                    continue;
                }
                cell = row.getCell(columnIndex);
                if (cell != null) {
                    // 注意：一定要设成这个，否则可能会出现乱码
                    switch (cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_STRING:
                            value = cell.getStringCellValue();
                            break;
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                Date date = cell.getDateCellValue();
                                if (date != null) {
                                    value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                } else {
                                    value = "";
                                }
                            } else {
                                value = new DecimalFormat("#.00").format(cell.getNumericCellValue());
                            }
                            break;
//                            case HSSFCell.CELL_TYPE_FORMULA:
//                                // 导入时如果为公式生成的数据则无值
//                                if (!cell.getStringCellValue().equals("")) {
//                                    value = cell.getStringCellValue();
//                                } else {
//                                    value = cell.getNumericCellValue() + "";
//                                }
//                                break;
                        case HSSFCell.CELL_TYPE_BLANK:
                            value = "";
                            break;
                        case HSSFCell.CELL_TYPE_ERROR:
                            value = "";
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            value = (cell.getBooleanCellValue() == true ? "Y" : "N");
                            break;
                        default:
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            value = cell.getStringCellValue();
                    }
                }
                if (columnIndex == 0 && value.trim().equals("")) {
                    continue;
                }
                values[columnIndex - hideNum] = rightTrim(value);
                hasValue = true;
            }
            if (hasValue) {
                result.add(values);
            }
        }
        String[][] returnArray = new String[result.size()][rowSize];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = (String[]) result.get(i);
        }
        return returnArray;
    }

    public static String[][] getDataNew(String strPath, Workbook wb, int ignoreRows, int sheetNum) throws IOException {
        int rowSize = 0;
        List<String[]> result = new ArrayList<String[]>();
        if (wb == null) {
            // 构造 XSSFWorkbook 对象，strPath 传入文件路径
            InputStream in = new FileInputStream(strPath);
            wb = new XSSFWorkbook(in);
            // 读取第一章表格内容
        }
        XSSFSheet sheet = (XSSFSheet) wb.getSheetAt(sheetNum);
        XSSFCell cell = null;


        for (int rowIndex = ignoreRows; rowIndex <= sheet.getPhysicalNumberOfRows(); rowIndex++) {
            XSSFRow row = sheet.getRow(rowIndex);
            if (row == null) {
                continue;
            }
            int tempRowSize = row.getLastCellNum() + 1;
            if (tempRowSize > rowSize) {
                rowSize = tempRowSize;
            }
            for (int i = 0; i <= row.getLastCellNum(); i++) {
                if (sheet.isColumnHidden(i)) {
                    rowSize--;
                }
            }
            String[] values = new String[rowSize];
            Arrays.fill(values, "");
            boolean hasValue = false;
            int hideNum = 0;
            for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                String value = "";
                if (sheet.isColumnHidden(columnIndex)) {
                    hideNum++;
                    continue;
                }
                cell = row.getCell(columnIndex);
                if (cell != null) {
                    // 注意：一定要设成这个，否则可能会出现乱码
                    switch (cell.getCellType()) {
                        case XSSFCell.CELL_TYPE_STRING:
                            value = cell.getStringCellValue();
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC:
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                Date date = cell.getDateCellValue();
                                if (date != null) {
                                    value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                } else {
                                    value = "";
                                }
                            } else {
                                value = new DecimalFormat("#.00").format(cell.getNumericCellValue());
                            }
                            break;
//                        case XSSFCell.CELL_TYPE_FORMULA:
//                            // 导入时如果为公式生成的数据则无值
//                            if (!cell.getStringCellValue().equals("")) {
//                                value = cell.getStringCellValue();
//                            } else {
//                                value = cell.getNumericCellValue() + "";
//                            }
//                            break;
                        case XSSFCell.CELL_TYPE_BLANK:
                            break;
                        case XSSFCell.CELL_TYPE_ERROR:
                            value = "";
                            break;
                        case XSSFCell.CELL_TYPE_BOOLEAN:
                            value = (cell.getBooleanCellValue() == true ? "Y" : "N");
                            break;
                        default:
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            value = cell.getStringCellValue();
                    }
                }
                if (columnIndex == 0 && value.trim().equals("")) {
                    continue;
                }
                values[columnIndex - hideNum] = rightTrim(value);
                hasValue = true;
            }
            if (hasValue) {
                result.add(values);
            }
        }
        String[][] returnArray = new String[result.size()][rowSize];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = (String[]) result.get(i);
        }
        return returnArray;
    }


    /**
     * 去掉字符串右边的空格
     *
     * @param str 要处理的字符串
     * @return 处理后的字符串
     */

    public static String rightTrim(String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        for (int i = length - 1; i >= 0; i--) {
            if (str.charAt(i) != 0x20) {
                break;
            }
            length--;
        }
        return str.substring(0, length);
    }


    public static void main(String[] args) throws IOException {
        String[][] a = ExcelUtils.getExcel("D:\\74.xls", null, 0, 0);
        for (int i = 0; i < a.length; i++) {
            //判断城市包含其中城市筛选
            if (a[i][2].contains("上海") || a[i][2].contains("北京") || a[i][2].contains("深圳") || a[i][2].contains("广州")) {
                System.out.println(a[i][0] + "\t" + a[i][1]);
            }
        }
    }

}
