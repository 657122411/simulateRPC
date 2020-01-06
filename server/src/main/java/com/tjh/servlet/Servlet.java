package com.tjh.servlet;

import com.alibaba.fastjson.JSON;
import com.sun.deploy.net.HttpRequest;
import com.tjh.utils.AnnoUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


@WebServlet("/path")
public class Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, List<Map<String, List<String>>>> map = null;
        try {
            map = AnnoUtil.parseAnno("com.tjh.dao");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        PrintWriter printWriter = resp.getWriter();
        printWriter.println(JSON.toJSON(map));
        printWriter.flush();
        printWriter.close();
    }
}
