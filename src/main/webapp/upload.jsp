<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>servlet3.0文件上传</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
<form action="upload" method="post" enctype="multipart/form-data">
    <span>选择文件：</span>
    <input type="file" name="file"/><br>
    <input type="submit" value="Upload" name="upload" />
</form>
</body>
</html>