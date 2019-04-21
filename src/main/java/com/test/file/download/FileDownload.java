package com.test.file.download;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * 测试地址: 127.0.0.1/download?password=123456
 */

@WebServlet("/download")
public class FileDownload extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        if ("123456".equals(password)) {
            // 使用 UTF8 编码文件名
            String filename = java.net.URLEncoder.encode("Head First Servlets and JSP.pdf","UTF-8");
            // 设置响应内容类型及编码方式
            response.setContentType("application/pdf; charset=UTF-8");
            response.setHeader("Cache-Control", "max-age=0");
            // 输出到客户端的文件名要使用setHeader来设置
            response.setHeader("Content-disposition", "attachment; filename=" + filename);
            // 事先在/WEB-INF文件夹下放入一个pdf文件
            InputStream inputStream = getServletContext().getResourceAsStream("/WEB-INF/Head First Servlets and JSP.pdf");
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            inputStream.close();
            outputStream.close();
        }
    }
}
