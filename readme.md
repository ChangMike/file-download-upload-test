#### 文件下载
1. 文件只要不是放在WEB-INF目录下，浏览器输入路径可以正确下载
2. 如果需要权限控制，文件放在WEB-INF目录下
- 指定响应内容类型
- 使用Response.getOutputStream获取ServletOutputStream把文件响应给浏览器 
#### 测试
- 启动项目，访问 127.0.0.1/download?password=123456