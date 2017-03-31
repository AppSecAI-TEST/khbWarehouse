package com.xinnet.packagescanner;

import java.net.URL;

public class StringUtil {
    private StringUtil() {

    }

    /**
     * "file:/home/whf/cn/fh" -> "/home/whf/cn/fh"
     * "jar:file:/home/whf/foo.jar!cn/fh" -> "/home/whf/foo.jar"
     */
    public static String getRootPath(URL url) {
        String fileUrl = url.getFile();
        int pos = fileUrl.indexOf('!');

        if (-1 == pos) {
            return fileUrl;
        }

        return fileUrl.substring(5, pos);
    }

    /**
     * "cn.fh.lightning" -> "cn/fh/lightning"
     * @param name
     * @return
     */
    public static String dotToSplash(String name) {
        return name.replaceAll("\\.", "/");
    }
    
    /**
     * "cn/fh/lightning" -> "cn.fh.lightning"
     * @param name
     * @return
     */
    public static String splashToDot(String name) {
        return name.replaceAll("/", "\\.");
    }

    /**
     * "Apple.class" -> "Apple"
     */
    public static String trimExtension(String name) {
        int pos = name.indexOf('.');
        if (-1 != pos) {
            return name.substring(0, pos);
        }

        return name;
    }

    /**
     * /application/home -> /home
     * @param uri
     * @return
     */
    public static String trimURI(String uri) {
        String trimmed = uri.substring(1);
        int splashIndex = trimmed.indexOf('/');

        return trimmed.substring(splashIndex);
    }
    
  //首字母转小写
    public static String toLowerCaseFirstOne(String s){
      if(Character.isLowerCase(s.charAt(0)))
        return s;
      else
        return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    //首字母转大写
    public static String toUpperCaseFirstOne(String s){
      if(Character.isUpperCase(s.charAt(0)))
        return s;
      else
        return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
}
