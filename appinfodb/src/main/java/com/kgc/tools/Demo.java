package com.kgc.tools;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:liao
 * @Description:TODO
 * @date:2020/8/26
 */
public class Demo {
    public static void main(String[] args) {
        String str1 = "B03SG70Si52SL00Nn01Eo63Sp20Er41WS40Ns62N";
        List<String> stringList = getStrList(str1,4);
        String[] strArray =  stringList.toArray(new String[0]);
        List<String> strings = new ArrayList<>();
        for (int i=0;i<strArray.length;i++){
            strings.add(strArray[i].substring(0,1));
        }
        String[] strArray1 =  strings.toArray(new String[0]);
        boolean flag = strEquals(strArray1);
        System.out.println(flag);
    }

    //判断是否为指定顺序
    public static boolean strEquals(String[] str){
        if (str[0].equalsIgnoreCase("b")&&
                str[1].equalsIgnoreCase("g")&&
                str[2].equalsIgnoreCase("i")&&
                str[3].equalsIgnoreCase("l")&&
                str[4].equalsIgnoreCase("n")&&
                str[5].equalsIgnoreCase("o")&&
                str[6].equalsIgnoreCase("p")&&
                str[7].equalsIgnoreCase("r")&&
                str[8].equalsIgnoreCase("s")&&
                str[9].equalsIgnoreCase("y")
        ){
            return true;
        }
        return false;
    }



    public static List<String> getStrList(String inputString, int length) {
        int size = inputString.length() / length;
        if (inputString.length() % length != 0) {
            size += 1;
        }
        return getStrList(inputString, length, size);
    }


    /**
     * 把原始字符串分割成指定长度的字符串列表
     *
     * @param inputString
     *            原始字符串
     * @param length
     *            指定长度
     * @param size
     *            指定列表大小
     * @return
     */
    public static List<String> getStrList(String inputString, int length,
                                          int size) {
        List<String> list = new ArrayList<String>();
        for (int index = 0; index < size; index++) {
            String childStr = substring(inputString, index * length,
                    (index + 1) * length);
            list.add(childStr);
        }
        return list;
    }


    /**
     * 分割字符串，如果开始位置大于字符串长度，返回空
     *
     * @param str
     *            原始字符串
     * @param f
     *            开始位置
     * @param t
     *            结束位置
     * @return
     */
    public static String substring(String str, int f, int t) {
        if (f > str.length()){
            return null;
        }
        if (t > str.length()) {
            return str.substring(f, str.length());
        } else {
            return str.substring(f, t);
        }
    }
}
