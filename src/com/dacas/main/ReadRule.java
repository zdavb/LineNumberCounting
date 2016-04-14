package com.dacas.main;

import java.io.*;

/**
 * Created by dave on 2016/4/14.
 */
public class ReadRule {
    public static Rule parseFile(String file) throws IOException{
        Rule rule = new Rule();//统计规则

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        int state = 0;
        while ((line=reader.readLine())!=null){
            line = line.trim();

            if(line.length() == 0 || line.charAt(0) == '#')
                continue;

            switch (line){
                case "INCLUDE":
                    state = 0;
                    continue;
                case "EXCLUDE":
                    state = 1;
                    continue;
                case "SUFFIX":
                    state = 2;
                    continue;
                case "OUTPUT":
                    state = 3;
                    continue;
                default:
                    break;
            }
           switch (state){
               case 0:
                   File file0 = new File(line);
                   rule.getIncludeFiles().add(file0);
                   break;
               case 1:
                   rule.getExcludeFiles().add(line);
                   break;
               case 2:
                   rule.getSuffixes().add(line);
                   break;
               case 3:
                   File outFile = new File(line);
                   if(!outFile.exists()){
                       System.out.println("输出文件不存在，创建完毕");
                       outFile.createNewFile();
                   }

                   rule.setOutputFile(outFile);
           }
        }
        reader.close();
        return rule;
    }
}
