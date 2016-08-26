<%--
  Created by IntelliJ IDEA.
  User: HAWI
  Date: 25.08.2016
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Oving 2</title>
    <script>
        function onHover(imgElement) {
            imgElement.src = '/images/harald.png';
        }
        function offHover(imgElement) {
            imgElement.src = '/images/harald2.png';
        }
    </script>
</head>
<body>
<img id="haraldImg" src="/images/harald2.png" alt="image" onmouseover="onHover(this);"
     onmouseout="offHover(this);"/>
</body>
</html>
