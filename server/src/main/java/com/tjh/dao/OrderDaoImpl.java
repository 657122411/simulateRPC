package com.tjh.dao;

import com.tjh.anno.RpcClazz;
import com.tjh.anno.RpcMethod;

/**
 * 模拟服务端
 */
@RpcClazz
public class OrderDaoImpl {

    @RpcMethod
    public void queryOrder(String name) {
        System.out.println("query success: name = " + name);
    }

}
