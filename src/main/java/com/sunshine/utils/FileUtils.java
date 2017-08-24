package com.sunshine.utils;

import com.sunshine.utils.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileUtils {

    private static Logger logger = Logger.getLogger(FileUtils.class);

    private static int deleteFileNumber = 0; // 文件删除的数量，包含目录文件

    /**
     * <p>获取当前路径下的所有文件，不包含文件夹（目录）；2017年3月19日 下午5:34:30</p>
     * @author qiushengming
     * @return 文件队列
     */
    public static List<File> getFile(String path) {
        File file = new File(path);
        return getFile(file);
    }

    public static List<File> getFile(File file){
        List<File> enableFile = new ArrayList<>();
        /* 判断文件是否存在 */
        if (file.exists()) {
            /* 判断是否是文件 */
            if (file.isFile()) {
                enableFile.add(file);
            }else if (file.isDirectory()) {
                File[] listFile = file.listFiles();
                if(listFile != null){
                    /* 否则如果它是一个目录*/
                    for(File f : listFile){
                        enableFile.addAll(getFile(f));
                    }
                    logger.info(file.getName() + ">>" + listFile.length);
                }
            }
        }
        return enableFile;
    }

    /**
     * 删除文件的对外接口；删除当前路劲下的所有文件，包含文件夹及其目录；
     * 2017年6月14日
     * qiushengming
     * @param currentPath 删除文件的路径
     * @return 无
     */
    public static void deleteFile(String currentPath){
        File file = new File(currentPath);
        deleteFile(file);
    }

    /**
     * 递归删除文件
     * 2017年6月14日
     * qiushengming
     * @param file 要删除的文件对象
     * @return 无
     */
    private static void deleteFile(File file) {
        /* 判断文件是否存在 */
        if (file.exists()) {
            /* 判断是否是文件 */
            if (file.isFile()) {
                /* 删除文件 */
                file.delete();
                deleteFileNumber++;
            }else if (file.isDirectory()) { // 否则如果它是一个目录
                /* 声明目录下所有的文件 files[] */
                File[] files = file.listFiles();
                if(files != null){
                    /* 遍历目录下所有的文件 */
                    for (File file1 : files) {
                    /* 把每个文件用这个方法进行迭代 */
                        deleteFile(file1);
                    }
                }
                /* 删除文件夹 */
                file.delete();
                deleteFileNumber++;
            }
        }else {
            logger.info(file.getName() + "文件夹删除完毕！！");
        }
    }

    /**
     * 创建目录</br>
     * 2017年6月15日
     * @author qiushengming
     * @param path 路径
     * @return Boolean true重新创建，false已存在
     */
    public static boolean createDirectory(String path){
        File file =new File(path);
        boolean mkdir = false;
        /*如果文件夹不存在则创建*/
        if(!file.exists() && !file.isDirectory()){
            mkdir = file.mkdir();
            logger.info(path + "创建成功！！！");
        }else{
            logger.info(path + "已存在！！！");
        }
        return mkdir;
    }

    public static void createFile(String path) throws IOException {
        File file =new File(path);
        if (!file.exists()) {
            boolean b = file.createNewFile();
            if (b) {
                logger.info(path + "创建成功！！！");
            }
        }
    }

    /**
     * 判断path是否存在
     * 2017年6月20日</br>
     * @author qiushengming
     * @param filePath 文件路径
     * @return Boolean 文件存在ture，不存在false
     */
    public static Boolean isEnable(String filePath){
        File f = new File(filePath);

        return !(!f.exists() && !f.isDirectory());
    }

    /**
     * copy文件</br>
     * 2017年7月4日</br>
     * @author qiushengming
     * @param oldPath copy的源文件
     * @param newPath 目标路劲
     * @return 无
     */
    public static void copy(String oldPath, String newPath){
        File oldfile = new File(oldPath);
        copy(oldfile, newPath);
    }

    /**
     * copy文件</br>
     * 2017年7月4日，拷贝临床路径特殊化需求，需要拿上一级目录的年份</br>
     * @author qiushengming
     * @param oldfile copy的源文件
     * @param newPath 目标路径
     * @reutnr 无
     */
    public static void copy(File oldfile, String newPath) {
        try {
            int byteread;
            String fileName = oldfile.getName();
            /*删除文件名称开头非中文字符*/
            fileName = fileName.substring(StringUtils.findHanZiFirstIndexOf(fileName));

            /*特殊化拼接拷贝路径+名称*/
            newPath += fileName.replace(".do", getSpecial(oldfile));

            if (oldfile.exists()) {
                InputStream in = new FileInputStream(oldfile);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = in.read(buffer)) != -1) {
                    fs.write(buffer, 0, byteread);
                }
                fs.close();
                in.close();
            }
        }
        catch (Exception e) {
            logger.error(e);
        }
    }

    private static String getSpecial(File f) {
        File parentFile = f.getParentFile();

        if(parentFile == null){
            logger.warn("未找到文件名称包含#的文件！！！");
            return "#";
        }

        if(!parentFile.getName().contains("#")){
            return getSpecial(parentFile);
        }else{
            return "_" + parentFile.getName().substring(1,5) + "_"
                    + System.currentTimeMillis() + ".do";
        }
    }

    public static int getDeleteFileNumber() {
        return deleteFileNumber;
    }
}
