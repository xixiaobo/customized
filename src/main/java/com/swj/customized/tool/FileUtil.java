package com.swj.customized.tool;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxb on 2018/11/26 16:25
 */
@Slf4j
public class FileUtil {


    /**
     * 获取路径内所有指定后缀的文件
     * @param dirPath
     * @param suffix
     * @return
     */
    public static ArrayList<File> getDirFiles(String dirPath,
                                              final String suffix) {
        File path = new File(dirPath);
        File[] fileArr = path.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                String lowerName = name.toLowerCase();
                String lowerSuffix = suffix.toLowerCase();
                if (lowerName.endsWith(lowerSuffix)) {
                    return true;
                }
                return false;
            }

        });
        ArrayList<File> files = new ArrayList<File>();

        for (File f : fileArr) {
            if (f.isFile()) {
                files.add(f);
            }
        }
        return files;
    }

    /**
     * 创建文件并检测生成相应父级目录
     * @param path
     * @return
     */
    public static File mkdirFiles(String path) {
        File file = new File(path);
        try {
            if (!file.getParentFile().exists() && !file.isDirectory()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } else {
                file.createNewFile();
            }
        } catch (IOException e) {
            log.error("文件生成有误：\n"+e);
        }
        return file;
    }

    /**
     * 删除文件夹以及其中所有文件
     * @param folderPath
     */
    public static void delFolder(String folderPath) {
        try {
            boolean i=delAllFile(folderPath); //删除完里面所有内容
            int tryCount = 0;
            while (!i && tryCount++ < 10) {
                System.gc();    //回收资源
                i=delAllFile(folderPath);
            }
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
            log.info("失败");
        }
    }

    /**
     * 删除路径下所有文件
     * @param path
     * @return
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }


    public static List<String> readFile(String path){
        List<String> result=new ArrayList<>();
        try {
            File file = new File(path);
            if(!file.exists()){
                return new ArrayList<>();
            }
            //读取文件(字符流)
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
            //读取数据
            //循环取出数据
            String str = null;
            while ((str = in.readLine()) != null) {
                result.add(str);
            }
            //关闭流
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            result=new ArrayList<>();
        }
        return result;
    }

    public static void writeFile(String filePath,List<String> lines,boolean iswriting){
        try {
            File file = new File(filePath);
            FileOutputStream fos = null;
            if(!file.exists()){
                file=FileUtil.mkdirFiles(filePath);
//                file.createNewFile();//如果文件不存在，就创建该文件
                fos = new FileOutputStream(file);//首次写入获取
            }else{
                //如果文件已存在，那么就在文件末尾追加写入
                fos = new FileOutputStream(file,iswriting);//这里构造方法多了一个参数true,表示在文件末尾追加写入
            }

            //写入相应的文件
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos,"UTF-8"));
            for (String line:lines) {
                out.write(line);
                out.newLine();
            }
            //清楚缓存
            out.flush();
            //关闭流
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(String filePath,String line,boolean iswriting){
        try {
            File file = new File(filePath);
            FileOutputStream fos = null;
            if(!file.exists()){
                file=FileUtil.mkdirFiles(filePath);
//                file.createNewFile();//如果文件不存在，就创建该文件
                fos = new FileOutputStream(file);//首次写入获取
            }else{
                //如果文件已存在，那么就在文件末尾追加写入
                fos = new FileOutputStream(file,iswriting);//这里构造方法多了一个参数true,表示在文件末尾追加写入
            }

            //写入相应的文件
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos,"UTF-8"));
            out.write(line);
            out.newLine();
            //清楚缓存
            out.flush();
            //关闭流
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
