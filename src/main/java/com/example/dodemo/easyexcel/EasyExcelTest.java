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
 * @author jiangdongzhao
 * @version V1.0.0
 * @date 2019/10/17
 */
public class EasyExcelTest {

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        //文件夹
        String dir="superior";
        String dir2="C:\\Users\\user\\Desktop\\";

        List<Path> fileNames = Files.list(Paths.get(dir2 + dir)).collect(toList());
        String xlsxName = Paths.get(dir2 + dir + ".xlsx").toString();


        List<List<IndexData>> allData = new ArrayList<>();
        //读取文件夹内文件
        for (Path x : fileNames) {
            List<IndexData> data = new ArrayList<>();
            properties.clear();
            try {
                FileInputStream input = new FileInputStream(new File(x.toString()));
                properties.load(new InputStreamReader(input, StandardCharsets.UTF_8));
                for (String key : properties.stringPropertyNames()) {
                    IndexData indexData = new IndexData();
                    indexData.setKey(key);
                    indexData.setValue(properties.getProperty(key));
                    indexData.setSheet(x.getFileName().toString().split(".properties")[0]);
                    data.add(indexData);
                }
                allData.add(data);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        ExcelWriter excelWriter;
        // 这里 指定文件
        excelWriter = EasyExcel.write(xlsxName, IndexData.class).build();
        for (int i = 0; i < allData.size(); i++) {
            // 这里注意 如果同一个sheet只要创建一次
            WriteSheet writeSheet;
            writeSheet = EasyExcel.writerSheet(i, allData.get(i).get(0).getSheet()).build();
            excelWriter.write(allData.get(i), writeSheet);
        }
        excelWriter.finish();
    }


}