package com.example.dodemo.easyexcel;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;


/**
 * 基础数据类
 *
 * @author Jiaju Zhuang
 **/
public class IndexData {
    @ExcelProperty(value = "key", index = 0)
    private String key;
    @ExcelProperty(value = "value", index = 1)
    private String value;

    @ExcelProperty(value = "desc", index = 2)
    private String desc;

    @ExcelIgnore
    private String sheet;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSheet() {
        return sheet;
    }

    public void setSheet(String sheet) {
        this.sheet = sheet;
    }
}