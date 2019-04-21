#### Servlet3.0上传文件
Servlet技术出现不久时，文件上传仍然是比较有挑战性的。
1. 开发者通常会使用商业的文件上传组件。
2. 值得庆幸的是，Apache于2003年发布了开源Comments FileUpload。   
3. 几年之后，Servlet的设计者才意识到文件上传的重要性，最终，文件上传在Servlet3中成了一项内置的特性。
---
#### 实现步骤
1. Servlet添加@MultipartConfig注解
2. 使用Part获取文件及文件名   
Part是一个表单元素，submit按钮也是一个part
---
#### 测试
1. 启动项目，并访问 http://127.0.0.1/upload.jsp
2. 会上传文件到桌面