package org.apache.demo.util;

public class CastUtil {

    public static String castString(Object obj){

        return CastUtil.castString(obj,"");
    }

    public static String castString(Object obj,String defaultValue){
        return obj != null?String.valueOf(obj):defaultValue;
    }

    /**
     *
     * 转为int型
     */
    public static int castInt(Object obj){
        return CastUtil.castInt(obj,0);
    }

    /**
     *
     *转为int型(提供默认值)
     *
     */
    public static int castInt(Object obj,int defaultValue){
        int intValue = defaultValue;
        if(obj != null){
            String strValue = castString(obj);
            if(StringUtil.isNotEmpty(strValue)){
                try {
                    intValue = Integer.parseInt(strValue);
                }catch (NumberFormatException e){
                    intValue = defaultValue;
                }
            }

        }
        return intValue;
    }


    /**
     *
     * 转为boolean型
     */
    public static boolean castBoolean(Object obj){
        return CastUtil.castBoolean(obj,false);
    }

    /**
     *
     *转为boolean型(提供默认值)
     *
     */
    public static boolean castBoolean(Object obj,boolean defaultValue){
        boolean booleanValue = defaultValue;
        if(obj != null){
            booleanValue = Boolean.parseBoolean(castString(obj));
        }
        return booleanValue;
    }



}
