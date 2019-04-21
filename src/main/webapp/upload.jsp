<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>文件上传</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
    <form action="upload" method="post" enctype="multipart/form-data">
        <span>Select File :</span>
        <input type="file" name="filename" value="" /><br>
        <input type="submit" value="Upload" name="upload"/>
    </form>
</body>
</html>
