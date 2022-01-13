package com.xsjt.core.distributed.enums;

public enum DataSourceEnum {

    DB1("one"),DB2("two");

    private String value;

    DataSourceEnum(String value){this.value=value;}

    public String getValue() {
        return value;
    }
}