package com.boot.test.utils.excel;

/**
 * Created with IntelliJ IDEA.
 * User: tao
 * Date: 13-9-9
 * Time: 下午1:53
 * To change this template use File | Settings | File Templates.
 */
public class ExcelLabel {

    private String name;            //字段名，英文或者拼音
    private String chineseName;    //字段名，中文
    private String format;          //格式
    private ExcelValueType numberType; //值类别

    public ExcelLabel(String name, String chineseName, String format, ExcelValueType numberType) {
        this.name = name;
        this.chineseName = chineseName;
        this.format = format;
        this.numberType = numberType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public ExcelValueType getNumberType() {
        return numberType;
    }

    public void setNumberType(ExcelValueType numberType) {
        this.numberType = numberType;
    }
}
