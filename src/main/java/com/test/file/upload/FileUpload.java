package com.test.file.upload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 测试地址: http://127.0.0.1/upload.jsp
 * 会上传文件到桌面
 */
@WebServlet("/upload")
public class FileUpload extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 使用ServletInputStream读取文件内容
        byte[] content = bodyContent(request);
        // 把读取到的文件内容转换为字符串，以方便截取文件名和文件内容
        String contentAsTxt = new String(content, "ISO-8859-1");
        // 截取文件名
        String filename = filename(contentAsTxt);
        filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");
        // 截取文件内容
        Range fileRange = fileRange(contentAsTxt, request.getContentType());
        // 写入服务器磁盘
        write(
                content,
                contentAsTxt.substring(0, fileRange.start)
                        .getBytes("ISO-8859-1")
                        .length,
                contentAsTxt.substring(0, fileRange.end)
                        .getBytes("ISO-8859-1")
                        .length,
                "C:\\Users\\Administrator\\Desktop\\" + filename
        );
    }

    private byte[] bodyContent(HttpServletRequest request) throws IOException {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            InputStream in = request.getInputStream();
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
            return out.toByteArray();
        }
    }

    private String filename(String contentTxt) throws UnsupportedEncodingException {
        Pattern pattern = Pattern.compile("filename=\"(.*)\"");
        Matcher matcher = pattern.matcher(contentTxt);
        matcher.find();
        return matcher.group(1);
    }

    private static class Range {
        final int start;
        final int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private Range fileRange(String content, String contentType) {
        Pattern pattern = Pattern.compile("filename=\".*\"\\r\\n.*\\r\\n\\r\\n(.*+)");
        Matcher matcher = pattern.matcher(content);
        matcher.find();
        int start = matcher.start(1);

        String boundary = contentType.substring(
                contentType.lastIndexOf("=") + 1, contentType.length());
        int end = content.indexOf(boundary, start) - 4;

        return new Range(start, end);
    }

    private void write(byte[] content, int start, int end, String file) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(content, start, (end - start));
        }
    }
}
