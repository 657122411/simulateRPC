package com.tjh.utils;

import com.tjh.anno.RpcClazz;
import com.tjh.anno.RpcMethod;

import java.io.File;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 自定义注解的解析
 */
public class AnnoUtil {
    public static Map<String, List<Map<String, List<String>>>> parseAnno(String packageName) throws ClassNotFoundException {
        Map<String, List<Map<String, List<String>>>> map = new HashMap<>();
        //编译输出路径
        String basePath = AnnoUtil.class.getResource("/").getPath();
        String packagePath = packageName.replace(".", "/");
        //取类文件
        File file = new File(basePath + packagePath);
        String[] names = file.list();
        for (String name : names) {
            name = name.replaceAll(".class", "");
            Class clazz = Class.forName(packageName + "." + name);
            //判断有无注解
            if (clazz.isAnnotationPresent(RpcClazz.class)) {
                Method[] methods = clazz.getDeclaredMethods();
                List<Map<String, List<String>>> rpcMethods = new ArrayList<>();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(RpcMethod.class)) {
                        //解析参数
                        Map<String, List<String>> rpcMethod = new HashMap<>();
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        List<String> params = new ArrayList<>();
                        for (Class<?> parameterType : parameterTypes) {
                            params.add(parameterType.getSimpleName());
                        }
                        rpcMethod.put(method.getName(), params);
                        rpcMethods.add(rpcMethod);

                    }
                }
                map.put(packageName + "." + name, rpcMethods);

            }

        }
        return map;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(AnnoUtil.parseAnno("com.tjh.dao"));
    }
}
