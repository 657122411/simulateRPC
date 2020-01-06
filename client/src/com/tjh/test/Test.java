package com.tjh.test;

import com.tjh.info.RpcInfo;
import com.tjh.dao.UserDaoImpl;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Test {

    public static void main(String[] args) throws IOException {
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.login("龙哥");

        RpcInfo rpcInfo = new RpcInfo();
        rpcInfo.setPackageName("com.tjh.dao");
        rpcInfo.setClassName("OrderDaoImpl");
        rpcInfo.setMethodName("queryOrder");
        Object[] params = new Object[]{"wanglong"};
        rpcInfo.setParams(params);

        Socket socket = new Socket("127.0.0.1", 6666);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(rpcInfo);
        objectOutputStream.flush();
        objectOutputStream.close();

    }
}
