package com.xsjt.core.config.datasource;

/**
 * @author Harriss
 * @Date 2021/11/30 10:29
 * @Desc 用于设置，获取，清空 当前线程内的数据源变量
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new InheritableThreadLocal<>();

    /**
     *  设置数据源
     * @param db
     */
    public static void setDataSource(String db){
        contextHolder.set(db);
    }

    /**
     * 取得当前数据源
     * @return
     */
    public static String getDataSource(){
        return contextHolder.get();
    }

    /**
     * 清除上下文数据
     */
    public static void clear(){
        contextHolder.remove();
    }
}
