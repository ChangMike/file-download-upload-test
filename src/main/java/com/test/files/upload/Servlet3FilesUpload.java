package com.test.files.upload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * 测试地址: http://127.0.0.1/upload.jsp
 * 会上传文件到桌面
 */
// 默认会写出到/target/tmp路径下，下面指定到了桌面
@MultipartConfig(location = "C:\\Users\\Administrator\\Desktop")
@WebServlet("upload")
public class Servlet3FilesUpload extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // 為了處理中文檔名

        request.getParts()
                .stream()
                // submit按钮也是一个part.getName,排除submit按钮
                .filter(part -> (part.getName() != null && part.getName().startsWith("file")))
                .forEach(part -> {
                    try {
                        // 真正的文件名称
                        String filename = part.getSubmittedFileName();
                        // 如果文件名称是空字符串，会报错，因为指定的其实是一个文件夹，这里要指定一个文件
                        if (filename != null && !filename.equals("")) {
                            part.write(filename);
                        }
                    } catch(IOException ex) {
                        throw new UncheckedIOException(ex);
                    }
                });
    }
}
