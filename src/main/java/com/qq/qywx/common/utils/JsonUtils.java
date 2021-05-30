package com.qq.qywx.common.utils;

import java.io.*;

/**
 * Created by lynnking.
 */

public class JsonUtils {

    public static String readJsonFile(String fileName) {
        String jsonStr;
        try {
            // 根据文件名创建文件对象
            File jsonFile = new File(fileName);
            // 将文件关联到文件读取流
            FileReader fileReader = new FileReader(jsonFile);
            Reader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(jsonFile), "utf-8"));
            // 读操作
            int ch;
            StringBuilder sb = new StringBuilder();
            while ((ch=bufferedReader.read()) != -1){
                sb.append((char) ch);
            }
            fileReader.close();
            bufferedReader.close();
            jsonStr = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonStr;
    }

}
