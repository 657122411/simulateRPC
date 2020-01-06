package com.tjh.request;

import com.tjh.info.RpcInfo;

import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class ServerHandler implements Runnable {

    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcInfo rpcInfo = (RpcInfo) objectInputStream.readObject();
            //反射执行
            Class clazz = Class.forName(rpcInfo.getPackageName() + "." + rpcInfo.getClassName());
            Class[] classes = new Class[rpcInfo.getParams().length];
            for (int i = 0; i < classes.length; i++) {
                classes[i] = rpcInfo.getParams()[i].getClass();
            }
            Method method = clazz.getMethod(rpcInfo.getMethodName(), classes);
            method.invoke(clazz.newInstance(), rpcInfo.getParams());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
