<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>servlet3.0多文件上传</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
<form action="upload" method="post" enctype="multipart/form-data">
    File 1  :<input type="file" name="file1"/><br>
    File 2  :<input type="file" name="file2"/><br>
    File 3  :<input type="file" name="file3"/><br>
    <!-- html5一个按钮多文件上传，只要添加multiple属性就可以，后台代码不用改 -->
    File 4  :<input type="file" name="file4" multiple="multiple"/><br>
    <input type="submit" value="Upload" name="upload" />
</form>
</body>
</html>