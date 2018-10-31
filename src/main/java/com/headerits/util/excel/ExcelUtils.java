package com.headerits.util.excel;

import com.headerits.util.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: 对excel导入导出的工具类 </p>
 * <p>Title: ExcelUtils </p>
 * <p>Create Time: 2018/7/20 10:45 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
public class ExcelUtils {
    /**
     * 2003- 版本的excel
     */
    private final static String excel2003L = ".xls";
    /**
     * 2007+ 版本的excel
     */
    private final static String excel2007U = ".xlsx";

    /*************************************文件上传****************************/
    public static List<List<Object>> getBankListByExcel(InputStream in, String fileName) throws Exception {
        List<List<Object>> list = null;
        //创建Excel工作薄  
        Workbook work = getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        list = new ArrayList<List<Object>>();
        //遍历Excel中所有的sheet  
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            //遍历当前sheet中的所有行  
            for (int j = sheet.getFirstRowNum(); j < sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }
                //遍历所有的列
                List<Object> li = new ArrayList<Object>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    li.add(getCellValue(cell));
                }
                list.add(li);
            }
        }
        return list;
    }

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     *
     * @param inStr,fileName
     * @return
     * @throws Exception
     */
    public static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (excel2003L.equals(fileType)) {
            //2003-
            wb = new HSSFWorkbook(inStr);
        } else if (excel2007U.equals(fileType)) {
            //2007+
            wb = new XSSFWorkbook(inStr);
        } else {
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    /**
     * 描述：对表格中数值进行格式化
     *
     * @param cell
     * @return
     */
    public static Object getCellValue(Cell cell) {
        Object value = null;
        //格式化number String字符
        DecimalFormat df = new DecimalFormat("0");
        //日期格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        //格式化数字
        DecimalFormat df2 = new DecimalFormat("0.00");

        switch (cell.getCellTypeEnum()) {
            case STRING:
                value = cell.getRichStringCellValue().getString();
                break;

            case NUMERIC:
                if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    value = df.format(cell.getNumericCellValue());
                } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                    value = sdf.format(cell.getDateCellValue());
                } else {
                    value = df2.format(cell.getNumericCellValue());
                }
                break;

            case BOOLEAN:
                value = cell.getBooleanCellValue();
                break;

            case BLANK:
                value = "";
                break;

            default:
                break;
        }
        return value;
    }

    /****************************************上传结束***************************************/

    /**
     * 多列头创建EXCEL
     *
     * @param clazz     数据源model类型
     * @param objs      excel标题列以及对应model字段名
     * @param map       标题列行数以及cell字体样式
     * @param fileName  excel文件名称
     * @param sheetName 工作簿名称
     * @param response  响应对象
     * @return
     * @throws Exception
     */
    public static void exportExcelFile(Class clazz, List objs, Map<Integer, List<ExcelBean>> map, String fileName, String sheetName, HttpServletResponse response) throws Exception {
        XSSFWorkbook workbook = null;
        OutputStream outputStream = null;
        try {
            // 创建新的Excel 工作簿
            workbook = new XSSFWorkbook();
            // 在Excel工作簿中建一工作表，其名为缺省值, 也可以指定Sheet名称
            XSSFSheet sheet = workbook.createSheet(sheetName);
            // 以下为excel的字体样式以及excel的标题与内容的创建，下面会具体分析;
            createFont(workbook);//字体样式
            createTableHeader(sheet, map);//创建标题（头）
            createTableRows(sheet, map, objs, clazz);//创建内容

            response.reset();
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            outputStream = new BufferedOutputStream(response.getOutputStream());
            workbook.write(outputStream);
            outputStream.flush();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (workbook != null) {
                workbook.close();
            }
        }
    }

    private static XSSFCellStyle fontStyle;
    private static XSSFCellStyle fontStyle2;

    public static void createFont(XSSFWorkbook workbook) {
        // 表头
        fontStyle = workbook.createCellStyle();
        XSSFFont font1 = workbook.createFont();
        font1.setFontName("宋体");
        font1.setFontHeightInPoints((short) 14);
        fontStyle.setFont(font1);
        fontStyle.setBorderBottom(BorderStyle.THIN);
        fontStyle.setBorderLeft(BorderStyle.THIN);
        fontStyle.setBorderTop(BorderStyle.THIN);
        fontStyle.setBorderRight(BorderStyle.THIN);
        fontStyle.setAlignment(HorizontalAlignment.CENTER);
        fontStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // 内容
        fontStyle2 = workbook.createCellStyle();
        XSSFFont font2 = workbook.createFont();
        font2.setFontName("宋体");
        // 设置字体大小
        font2.setFontHeightInPoints((short) 10);
        fontStyle2.setFont(font2);

        // 设置自动换行
        fontStyle2.setWrapText(true);
        // 下边框
        fontStyle2.setBorderBottom(BorderStyle.THIN);
        // 左边框
        fontStyle2.setBorderLeft(BorderStyle.THIN);
        // 上边框
        fontStyle2.setBorderTop(BorderStyle.THIN);
        // 右边框
        fontStyle2.setBorderRight(BorderStyle.THIN);
        // 水平居中
        fontStyle2.setAlignment(HorizontalAlignment.CENTER);
        // 竖直居中
        fontStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    }

    /**
     * 根据ExcelMapping 生成列头（多行列头）
     *
     * @param sheet 工作簿
     * @param map   每行每个单元格对应的列头信息
     */
    public static final void createTableHeader(XSSFSheet sheet, Map<Integer, List<ExcelBean>> map) {
        //cell起始位置
        int startIndex = 0;
        //cell终止位置
        int endIndex = 0;
        for (Map.Entry<Integer, List<ExcelBean>> entry : map.entrySet()) {
            XSSFRow row = sheet.createRow(entry.getKey());
            List<ExcelBean> excels = entry.getValue();
            for (int x = 0; x < excels.size(); x++) {
                //合并单元格
                if (excels.get(x).getCols() > 1) {
                    if (x == 0) {
                        endIndex += excels.get(x).getCols() - 1;
                        CellRangeAddress range = new CellRangeAddress(0, 0, startIndex, endIndex);
                        sheet.addMergedRegion(range);
                        startIndex += excels.get(x).getCols();
                    } else {
                        endIndex += excels.get(x).getCols();
                        CellRangeAddress range = new CellRangeAddress(0, 0, startIndex, endIndex);
                        sheet.addMergedRegion(range);
                        startIndex += excels.get(x).getCols();
                    }
                    XSSFCell cell = row.createCell(startIndex - excels.get(x).getCols());
                    // 设置内容
                    cell.setCellValue(excels.get(x).getHeadTextName());
                    if (excels.get(x).getCellStyle() != null) {
                        // 设置格式
                        cell.setCellStyle(excels.get(x).getCellStyle());
                    }
                    cell.setCellStyle(fontStyle);
                } else {
                    XSSFCell cell = row.createCell(x);
                    // 设置内容
                    cell.setCellValue(excels.get(x).getHeadTextName());
                    if (excels.get(x).getCellStyle() != null) {
                        // 设置格式
                        cell.setCellStyle(excels.get(x).getCellStyle());
                    }
                    cell.setCellStyle(fontStyle);
                }
            }
        }
    }

    /**
     * @param sheet
     * @param map
     * @param objs
     * @param clazz
     */
    public static void createTableRows(XSSFSheet sheet, Map<Integer, List<ExcelBean>> map, List objs, Class clazz)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, IntrospectionException,
            ClassNotFoundException, ParseException {

        int rowindex = map.size();
        int maxKey = 0;
        List<ExcelBean> ems = new ArrayList<>();
        for (Map.Entry<Integer, List<ExcelBean>> entry : map.entrySet()) {
            if (entry.getKey() > maxKey) {
                maxKey = entry.getKey();
            }
        }
        ems = map.get(maxKey);
        List<Integer> widths = new ArrayList<Integer>(ems.size());
        for (Object obj : objs) {
            XSSFRow row = sheet.createRow(rowindex);
            for (int i = 0; i < ems.size(); i++) {
                ExcelBean em = (ExcelBean) ems.get(i);
                // 获得get方法
                PropertyDescriptor pd = new PropertyDescriptor(em.getPropertyName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object rtn = getMethod.invoke(obj);
                String value = "";
                // 如果是日期类型 进行 转换
                if (rtn != null) {
                    if (rtn instanceof Date) {
                        value = DateUtils.date2String((Date) rtn, "yyyy-MM-dd HH:mm:ss");
                    } else if (rtn instanceof BigDecimal) {
                        NumberFormat nf = new DecimalFormat("#,##0.00");
                        value = nf.format((BigDecimal) rtn).toString();
                    } else if ((rtn instanceof Integer) && (Integer.valueOf(rtn.toString()) < 0)) {
                        value = "--";
                    } else {
                        value = rtn.toString();
                    }
                }
                XSSFCell cell = row.createCell(i);
                cell.setCellValue(value);
                cell.setCellType(CellType.STRING);
                cell.setCellStyle(fontStyle2);
                // 获得最大列宽
                int width = value.getBytes().length * 300;
                // 还未设置，设置当前
                if (widths.size() <= i) {
                    widths.add(width);
                    continue;
                }
                // 比原来大，更新数据
                if (width > widths.get(i)) {
                    widths.set(i, width);
                }
            }
            rowindex++;
        }

        // 设置列宽
        for (int index = 0; index < widths.size(); index++) {
            Integer width = widths.get(index);
            width = width < 2500 ? 2500 : width + 300;
            width = width > 10000 ? 10000 + 300 : width + 300;
            sheet.setColumnWidth(index, width);
        }
    }
}
