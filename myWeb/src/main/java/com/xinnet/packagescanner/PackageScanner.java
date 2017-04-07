package com.xinnet.packagescanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 扫描包的工具类
 * @author hongbin.kang
 * @date 2017年4月7日下午6:54:30
 */
public class PackageScanner {
    private Logger logger = LoggerFactory.getLogger(PackageScanner.class);
 
    private String basePackage;
    private ClassLoader cl;

    public PackageScanner(String basePackage) {
        this.basePackage = basePackage;
        this.cl = getClass().getClassLoader();

    }
     
    public PackageScanner(String basePackage, ClassLoader cl) {
        this.basePackage = basePackage;
        this.cl = cl;
    }

   /**
    * 扫描包下所有的类
    * @author hongbin.kang
    * @date 2017年3月29日 上午10:06:25
    * @return
    * @throws IOException
    */
    public List<String> getFullyQualifiedClassNameList() throws IOException {
        logger.info("开始扫描包{}下的所有类", basePackage);

        return doScan(basePackage, new ArrayList<String>());
    }

    /**
     * 所有被扫描的类的列表
     * @author hongbin.kang
     * @date 2017年3月29日 上午10:06:48
     * @param basePackage
     * @param nameList
     * @return
     * @throws IOException
     */
    private List<String> doScan(String basePackage, List<String> nameList) throws IOException {
        //用/来代替.
        String splashPath = StringUtil.dotToSplash(basePackage);

        //获取类路径
        URL url = cl.getResource(splashPath);
        String filePath = StringUtil.getRootPath(url);

        //扫描jar或者类目录
        List<String> names = null; // contains the name of the class file. e.g., Apple.class will be stored as "Apple"
        if (isJarFile(filePath)) {
            //JAR包
            if (logger.isDebugEnabled()) {
                logger.debug("{} 是一个JAR包", filePath);
            }

            names = readFromJarFile(filePath, splashPath);
        } else {
            // 目录
            if (logger.isDebugEnabled()) {
                logger.debug("{} 是一个目录", filePath);
            }
            names = readFromDirectory(filePath);
        }

        for (String name : names) {
            if (isClassFile(name)) {
                //nameList.add(basePackage + "." + StringUtil.trimExtension(name));
            	if(isJarFile(filePath)) {
            		nameList.add(StringUtil.splashToDot(name).replace(".class", ""));
            	} else {
            		nameList.add(toFullyQualifiedName(name, basePackage));
            	}
            } else {
                
                // 递归扫描
                doScan(basePackage + "." + name, nameList);
            }
        }

        if (logger.isDebugEnabled()) {
            for (String n : nameList) {
                logger.debug("找到{}", n);
            }
        }

        return nameList;
    }

    /**
     * 获取类名称的全名
     * @author hongbin.kang
     * @date 2017年3月29日 上午10:12:06
     * @param shortName
     * @param basePackage
     * @return
     */
    private String toFullyQualifiedName(String shortName, String basePackage) {
        StringBuilder sb = new StringBuilder(basePackage);
        sb.append('.');
        sb.append(StringUtil.trimExtension(shortName));

        return sb.toString();
    }

    /**
     * 获取类名称的全名
     * @author hongbin.kang
     * @date 2017年3月29日 上午10:12:42
     * @param jarPath
     * @param splashedPackageName
     * @return
     * @throws IOException
     */
    private List<String> readFromJarFile(String jarPath, String splashedPackageName) throws IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("从JAR包中读取类: {}", jarPath);
        }

        JarInputStream jarIn = new JarInputStream(new FileInputStream(jarPath));
        JarEntry entry = jarIn.getNextJarEntry();

        List<String> nameList = new ArrayList<>();
        while (null != entry) {
            String name = entry.getName();
            if (name.startsWith(splashedPackageName) && isClassFile(name)) {
                nameList.add(name);
            }
            entry = jarIn.getNextJarEntry();
        }
        return nameList;
    }

    /**
     * 读取目录下的class文件名
     * @author hongbin.kang
     * @date 2017年3月29日 上午10:38:31
     * @param path
     * @return
     */
    private List<String> readFromDirectory(String path) {
        File file = new File(path);
        String[] names = file.list();

        if (null == names) {
            return null;
        }

        return Arrays.asList(names);
    }

    private boolean isClassFile(String name) {
        return name.endsWith(".class");
    }

    private boolean isJarFile(String name) {
        return name.endsWith(".jar");
    }

    /**
     * For test purpose.
     */
    public static void main(String[] args) throws Exception {
    	PackageScanner scan = new PackageScanner("com.xinnet.facade.facade");
        List<String> list = scan.getFullyQualifiedClassNameList();
        System.out.println(list);
        List<Class<?>> classList = new ArrayList<Class<?>>();
        for(String str : list) {
        	//是接口放到类里面
        	if(Class.forName(str).isInterface()) {
        		classList.add(Class.forName(str));
        	}
        }
    }

}
