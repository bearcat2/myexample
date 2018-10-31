package com.headerits.util.excel;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.io.Serializable;

/**
 * <p>Description: 封装excel相应数据的实体 </p>
 * <p>Title: ExcelBean </p>
 * <p>Create Time: 2018/7/20 16:12 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
public class ExcelBean implements Serializable {
    private static final long serialVersionUID = 6215485079047405791L;
    /**
     * 列头（标题）名
     */
    private String headTextName;
    /**
     * 对应字段名
     */
    private String propertyName;
    /**
     * 合并单元格数
     */
    private Integer cols;
    /**
     * 单元格样式对象
     */
    private XSSFCellStyle cellStyle;

    public ExcelBean() {
    }

    public ExcelBean(String headTextName, String propertyName) {
        this.headTextName = headTextName;
        this.propertyName = propertyName;
        this.cols = 0;
    }

    public ExcelBean(String headTextName, String propertyName, Integer cols) {
        this.headTextName = headTextName;
        this.propertyName = propertyName;
        this.cols = cols;
    }

    public String getHeadTextName() {
        return headTextName;
    }

    public void setHeadTextName(String headTextName) {
        this.headTextName = headTextName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }

    public XSSFCellStyle getCellStyle() {
        return cellStyle;
    }

    public void setCellStyle(XSSFCellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }

    @Override
    public String toString() {
        return "ExcelBean{" +
                "headTextName='" + headTextName + '\'' +
                ", propertyName='" + propertyName + '\'' +
                ", cols=" + cols +
                ", cellStyle=" + cellStyle +
                '}';
    }
}
