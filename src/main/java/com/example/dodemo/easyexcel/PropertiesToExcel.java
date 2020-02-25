package com.example.dodemo.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.util.stream.Collectors.toList;

/**
 * properties 转换为excel
 * <p>
 * 若sheet名称超过31个字符，则只支持单个文件夹10个文件以内
 *
 * @author jiangdongzhao
 * @version V1.0.0
 * @date 2019/10/17
 */
public class PropertiesToExcel {

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        //文件夹
        String dir = "user";
        //路径
        String dir2 = "C:\\Users\\user\\Desktop\\";

        List<Path> fileNames = Files.list(Paths.get(dir2 + dir)).collect(toList());
        String xlsxName = Paths.get(dir2 + dir + ".xlsx").toString();


        List<List<IndexData>> allData = new ArrayList<>();
        //读取文件夹内文件
        for (int i = 0; i < fileNames.size(); i++) {
            Path x = fileNames.get(i);
            List<IndexData> data = new ArrayList<>();
            properties.clear();
            FileInputStream input = new FileInputStream(new File(x.toString()));
            properties.load(new InputStreamReader(input, StandardCharsets.UTF_8));
            for (String key : properties.stringPropertyNames()) {
                IndexData indexData = new IndexData();
                indexData.setKey(key);
                indexData.setValue(properties.getProperty(key));
                String sheetName = x.getFileName().toString().split(".properties")[0];
                //sheetName超过31附带序号，防止被截取后一致
                if (sheetName.length() >= 31) {
                    sheetName = sheetName.substring(0, 29) + "-" + i;
                }
                indexData.setSheet(sheetName);
                data.add(indexData);
            }
            allData.add(data);
        }

        ExcelWriter excelWriter;
        // excel + 多个sheet
        excelWriter = EasyExcel.write(xlsxName, IndexData.class).build();
        for (int i = 0; i < allData.size(); i++) {
            if (!allData.get(i).isEmpty()) {
                WriteSheet writeSheet;
                writeSheet = EasyExcel.writerSheet(i, allData.get(i).get(0).getSheet()).build();
                excelWriter.write(allData.get(i), writeSheet);
            }
        }
        excelWriter.finish();
    }


}