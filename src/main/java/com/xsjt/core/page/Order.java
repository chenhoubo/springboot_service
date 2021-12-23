package com.xsjt.core.page;

/**
 *
 * <p>
 *     数据库排序
 * </p>
 *
 * @author: Harriss
 * date: 2021年10月18日 上午9:51:57
 */
public enum Order {

    ASC("asc"), DESC("desc");

    private String des;

    Order(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
