package com.itdr.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class PoolUtil {

    public static ComboPooledDataSource co = new ComboPooledDataSource();

public static ComboPooledDataSource getCo(){
    return co;
}
}
