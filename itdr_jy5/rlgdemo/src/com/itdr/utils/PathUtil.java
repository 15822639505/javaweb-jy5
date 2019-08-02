package com.itdr.utils;

public class PathUtil {

public static String getPath(String path){
    String s1=path.replace(".","/");
    String[] strings=s1.split("/");
    return strings[1];
}


}
