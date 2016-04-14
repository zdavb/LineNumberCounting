package com.dacas.main;

import java.io.*;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dave on 2016/4/14.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("欢迎来到DCS文本文件统计工具");
        System.out.println("本程序将从rule.txt文件中读取规则");

        try {
            String rootPath = URLDecoder.decode(new Main().getClass().getResource("/").getPath(), "utf-8");//以utf8格式读取，否则可能乱码
            Rule rule = ReadRule.parseFile(rootPath + "\\rule.txt");
            List<File> files = rule.getIncludeFiles();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(rule.getOutputFile()),"utf-8"));//同理
            for(int i = 0;i<files.size();i++){
                File file = files.get(i);
                String path = file.getAbsolutePath();
                if(rule.getExcludeFiles().contains(path)){//exclude file
                    continue;
                }
                if(file.isDirectory()){//将子文件都存入files
                    File[] sonFiles = file.listFiles();
                    files.addAll(Arrays.asList(sonFiles));
                    continue;
                }

                String name = file.getName();
                String suffix = name.substring(name.indexOf('.'));
                if(rule.getSuffixes().contains(suffix)){
                    LineNumberReader reader = new LineNumberReader(new FileReader(file));
                    int count = 0;
                    while(reader.readLine()!=null)
                        count++;
                    writer.write(format(path,count));
                    writer.newLine();
                    writer.write(anotherLine());
                    writer.newLine();
                    reader.close();
                }
            }
            writer.flush();
            writer.close();
            System.out.println("统计完成");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    private static String format(String first,Object second){
        int count = 100;//字符串长度
        int firstLen = first.length();
        count = Math.max(firstLen,count);

        StringBuilder builder = new StringBuilder(first);
        for(int i = firstLen;i<count;i++){
            builder.append(' ');
        }
        builder.append('|');
        builder.append(second);

        return builder.toString();
    }
    private static String anotherLine(){
        StringBuilder builder = new StringBuilder();
        for(int i = 0;i<100;i++)
            builder.append('-');
        return builder.toString();
    }
}
