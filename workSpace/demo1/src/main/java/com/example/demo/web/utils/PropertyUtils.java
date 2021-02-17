package com.example.demo.web.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.*;

@Slf4j
public class PropertyUtils {

    public static Map<String, String> getAll(String filePath) {
        Map<String, String> propertyMap = new HashMap<String, String>();
        File file = new File(filePath);
        if (!file.exists()) {
            log.error("配置文件不存在, 路径: " + filePath);
            return propertyMap;
        }

        Properties properties = new Properties();
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filePath), "UTF-8");
            properties.load(new BufferedReader(reader));
            Set<String> names = properties.stringPropertyNames();
            if (null == names || names.size() == 0)
                return propertyMap;

            for (String name : names) {
                propertyMap.put(name, properties.getProperty(name));
            }
            return propertyMap;
        } catch (Exception ex) {
            log.error("读取配置文件失败");
        }
        return propertyMap;
    }

    public static Map<String, String> getAll(InputStream inputStream) {
        Map<String, String> propertyMap = new HashMap<String, String>();
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            Set<String> names = properties.stringPropertyNames();
            if (null == names || names.size() == 0)
                return propertyMap;

            for (String name : names) {
                propertyMap.put(name, properties.getProperty(name));
            }
            return propertyMap;
        } catch (Exception ex) {
            log.error("读取配置文件失败");
        }
        return propertyMap;
    }

    public static void writeProperties(String filePath, Map<String, String> props) {
        Properties properties = new Properties();
        FileOutputStream output = null;
        File file = new File(filePath);
        if (!file.exists()) {
            log.error("配置文件不存在, 路径: " + filePath);
            return;
        }
        try {
            output = new FileOutputStream(filePath);
            for (Map.Entry<String, String> entry : props.entrySet()) {
                properties.setProperty(entry.getKey(), entry.getValue());
            }
            properties.store(output, new Date().toString());// 保存键值对到文件中
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static String get(String filePath, String key) {
        File file = new File(filePath);
        if (!file.exists()) {
            log.error("配置文件不存在, 路径: " + filePath);
            return null;
        }
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(filePath));
            Set<String> names = properties.stringPropertyNames();
            if (null == names || names.size() == 0)
                return null;

            for (String name : names) {
                if (name.trim().equals(key))
                    return properties.getProperty(name);
            }
            return null;
        } catch (Exception ex) {
            log.error("读取配置文件失败");
            return null;
        }
    }

}
