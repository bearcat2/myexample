package com.headerits.java.demo;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.read.context.AnalysisContext;
import com.alibaba.excel.read.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: alibaba easyexcel 测试 </p>
 * <p>Title: EasyExcelTest </p>
 * <p>Create Time: 2018/10/10 10:44 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
public class EasyExcelTest {

    private InputStream inputStream;

    private OutputStream outputStream;
    private int column = 1;


    @Before
    public void before() throws Exception {
        String inFilePath = "F:/泉州3.xlsx";
        inputStream = new FileInputStream(inFilePath);
        String outFilePath = "F:/demo.xlsx";
        outputStream = new FileOutputStream(outFilePath);
    }

    @Test
    public void testRead() throws Exception {
        ExcelReader excelReader = new ExcelReader(inputStream
                , ExcelTypeEnum.XLSX, null, new AnalysisEventListener<ExcelReadModel>() {
            @Override
            public void invoke(ExcelReadModel excelReadModel, AnalysisContext context) {
                System.out.println(excelReadModel);
                //System.out.println(context.getCurrentRowNum());
                System.out.println(context.getCurrentRowAnalysisResult());
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                System.out.println(context + "完成...");
            }
        });
        excelReader.read(new Sheet(2, 1, ExcelReadModel.class));
        excelReader.finish();
    }

    @Test
    public void testWrite() throws Exception {
        ExcelWriter excelWriter = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX);
        List<ExcelWriteModel> excelWriteModels = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            excelWriteModels.add(new ExcelWriteModel("张三", "123456"));
            excelWriteModels.add(new ExcelWriteModel("李四", "234567"));
            excelWriteModels.add(new ExcelWriteModel("王五", "345678"));
        }
        Sheet sheet = new Sheet(1, 0, ExcelWriteModel.class);
        sheet.setSheetName("用户信息");
        //TableStyle tableStyle = new TableStyle();
        //tableStyle.setTableHeadBackGroundColor(IndexedColors.RED);
        //tableStyle.setTableContentBackGroundColor(IndexedColors.WHITE);
        //sheet.setTableStyle(tableStyle);
        long startTime = System.currentTimeMillis();
        System.out.println("开始,当前时间" + startTime + "数据大小" + excelWriteModels.size());
        excelWriter.write(excelWriteModels, sheet);
        excelWriter.finish();
        System.out.println("导出成功...");
        long endTime = System.currentTimeMillis();
        System.out.println("结束,当前时间" + endTime + "耗时" + (endTime - startTime));
    }
}
