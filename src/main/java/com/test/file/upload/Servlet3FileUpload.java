package com.test.file.upload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * 测试地址: http://127.0.0.1/upload.jsp
 * 会上传文件到桌面
 */
@MultipartConfig
@WebServlet("/upload")
public class Servlet3FileUpload extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part file = request.getPart("file");
        String filename = file.getSubmittedFileName();

        try(InputStream in = file.getInputStream();
            OutputStream out = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\" + filename)) {
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
        }
    }
}
