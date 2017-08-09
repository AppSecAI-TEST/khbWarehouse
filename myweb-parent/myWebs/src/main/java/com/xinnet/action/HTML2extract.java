/*package com.xinnet.action;

import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.InputStreamReader;  
import java.io.PrintWriter;  
import java.net.URI;  
import java.net.URL;  
import java.nio.channels.FileChannel;  
import java.util.ArrayList;  
import java.util.List;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
  
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;  
import org.jsoup.select.Elements;  
  
public class HTML2extract {  
    private static String   oldDir      = "E:/Metronic v4.1.0/";  
    private static String   newDir      = "D:/MyMetronic v4x2/";  
    private static String   _temp       = "http://";  
    private static String   subdir      = "v4.1.0/theme/templates/admin4/";  
    private static URI      base        = null;  
    private static String   htmlpage    = oldDir + subdir + "ui_tiles.html";  
  
    public static void main(String[] args) throws Exception {  
        base = new URI( _temp + subdir );  
  
        File input = new File( htmlpage );  
  
        String htmpage_dir = htmlpage.substring( 0, htmlpage.lastIndexOf( "/" ) );  
        // 新目录  
        mkDir( htmpage_dir.replace( oldDir, newDir ) );  
        // 复制当前html  
        fCopy( htmlpage, htmlpage.replace( oldDir, newDir ) );  
  
        Document doc = Jsoup.parse( input, "UTF-8" );  
        List<String> list = new ArrayList<String>();  
        // css  
        getUrl( list, doc, "link", "href" );  
        // js  
        getUrl( list, doc, "script", "src" );  
        // img  
        getUrl( list, doc, "img", "src" );  
  
        //  
        for (String url : list) {  
            if ((url == null) || (url.trim().length() == 0)) {  
                continue;  
            }  
  
            xf( url );  
  
            // css文件  
            if (url.toLowerCase().trim().endsWith( ".css" )) {  
                List<String> l2 = new ArrayList<String>();  
                cssParser( l2, url.replace( _temp, oldDir ) );  
                for (String c : l2) {  
                    xf( getRealUrl( url.substring( 0, url.lastIndexOf( "/" ) ), c ) );  
                }  
            }  
        }  
    }  
  
    private static String getRealUrl(String d1, String d2) {  
        // System.out.println(d1+d2);  
        if (d2.startsWith( "../" )) {  
            d1 = d1.substring( 0, d1.lastIndexOf( "/" ) );  
            d2 = d2.substring( 3, d2.length() );  
  
            return getRealUrl( d1, d2 );  
        } else {  
            return d1 + "/" + d2;  
        }  
    }  
  
    private static void xf(String url) {  
        if(url.indexOf( "?" )!=-1){  
            url=url.substring( 0,url.indexOf( "?" ) );  
        }  
        // 原地址  
        String s = url.replace( _temp, oldDir );  
        // System.out.println(s);  
  
        // 新增地址  
        String t = url.replace( _temp, newDir );  
        String n_dir = t.substring( 0, t.lastIndexOf( "/" ) );  
        mkDir( n_dir );  
        // System.out.println( n_dir );  
        fCopy( s, t );  
    }  
  
    *//** 
     * 获取地址 
     * 
     * @param list 
     * @param tag 
     * @param attr 
     *//*  
    private static void getUrl(List<String> list, Document doc, String tag, String attr) {  
        Elements imgs = doc.getElementsByTag( tag );  
  
        for (Element el : imgs) {  
            try {  
                String url = el.attr( attr );  
                URI abs = base.resolve( url );  
                URL absURL = abs.toURL(); // 转成URL  
                list.add( absURL.toString() );  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    private static void cssParser(List<String> l, String cssf) throws Exception {  
        String text = readFile( cssf );  
        Pattern pat = Pattern.compile( "(?is)url\\((.*?)\\)" );  
        Matcher mat = pat.matcher( text );  
        while (mat.find()) {  
            String url = (mat.group( 1 ));  
            if(url!=null && url.trim().length()>0){  
                l.add( url.replaceAll( "'", "" ).replaceAll( "\"", "" ) );  
            }  
        }  
    }  
  
    *//** 
     * 生成文件夹 
     * 
     * @param fileurl 
     *//*  
    public static void mkDir(String fileurl) {  
        // 创建文件夹  
        File saveDirFile = new File( fileurl );  
  
        if (!saveDirFile.exists() && !saveDirFile.isDirectory()) {  
            saveDirFile.mkdirs();  
        }  
    }  
  
    *//** 
     * 复制文件 
     * 
     * @param oldFile 
     * @param newFile 
     *//*  
    public static void fCopy(String oldFilePath, String newFilePath) {  
        FileInputStream fi = null;  
        FileOutputStream fo = null;  
        FileChannel in = null;  
        FileChannel out = null;  
  
        try {  
            //  
            File oldFile = new File( oldFilePath );  
  
            if (!oldFile.exists() || oldFile.isDirectory()) {  
                 System.err.println("不存在==" + oldFilePath);  
  
                // 文件不存在或者是文件夹  
                return;  
            }  
  
            File newFile = new File( newFilePath );  
  
            if (newFile.exists()) {  
                 System.err.println("已存在==" + newFilePath);  
  
                // 文件已存在  
                return;  
            }  
            System.out.println( newFilePath );  
            fi = new FileInputStream( oldFile );  
  
            if (fi != null) {  
                fo = new FileOutputStream( newFile );  
                in = fi.getChannel(); // 得到对应的文件通道  
                out = fo.getChannel(); // 得到对应的文件通道  
                in.transferTo( 0, in.size(), out ); // 连接两个通道，并且从in通道读取，然后写入out通道  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (fi != null) {  
                    fi.close();  
                }  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
  
            try {  
                if (in != null) {  
                    in.close();  
                }  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
  
            try {  
                if (fo != null) {  
                    fo.close();  
                }  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
  
            try {  
                if (out != null) {  
                    out.close();  
                }  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    private static String readFile(String fileName) throws Exception {  
        BufferedReader reader = new BufferedReader( new InputStreamReader( (new FileInputStream( fileName )), "UTF-8" ) );  
        try {  
            StringBuilder data = new StringBuilder();  
            String line = null;  
            while ((line = reader.readLine()) != null) {  
                data.append( line );  
            }  
            reader.close();  
            return data.toString();  
        } catch (Exception e) {  
            if (reader != null) {  
                reader.close();  
            }  
            return null;  
        }  
    }  
}*/