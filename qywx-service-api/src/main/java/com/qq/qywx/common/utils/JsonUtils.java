package com.qq.qywx.common.utils;

import org.testng.annotations.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by 72088532 on 2021/1/11.
 */

public class JsonUtils {

    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            // 根据文件名创建文件对象
            File jsonFile = new File(fileName);
            // 将文件关联到文件读取流
            FileReader fileReader = new FileReader(jsonFile);
            Reader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(jsonFile), "utf-8"));
            // 读操作
            int ch = 0;
            StringBuilder sb = new StringBuilder();
            while ((ch=bufferedReader.read()) != -1){
                sb.append((char) ch);
            }
            fileReader.close();
            bufferedReader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test
    public void testReadJsonFile() {
        String jsonStr = readJsonFile("src/test/resources/userLogin.json");
//        System.out.println(jsonStr);
        String splitJsonStr = jsonStr.replace("},", "}!_&");
//        System.out.println(splitJsonStr);
        String[] jsonStrArray = splitJsonStr.split("!_&");
        Map<String, Object> params = new HashMap<>();
        for (String s : jsonStrArray) {
//            System.out.println(s);
            String[] ss = s.split(" : ");
            params.put(ss[0], ss[1]);
        }
        Set<Map.Entry<String, Object>> entries = params.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
}
