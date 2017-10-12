<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>servlet async page</title>
</head>
<body>
    <script src="assets/js/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script type="text/javascript">

        deferred();

        function deferred() {
            $.get('defer',function (data) {
                console.log(data);
                deferred();
            });
        }
    </script>
</body>
</html>