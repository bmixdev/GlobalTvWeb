<%--
  Created by IntelliJ IDEA.
  User: Lyubomyr
  Date: 02.02.2016
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<label id="chaUrl" name="chnUrl" value="${chUrl}">${chUrl}</label>
<br>
<embed type="application/x-vlc-plugin"
       name="video2"
       width="864px"
       height="540px"
       autoplay="yes" loop="no" hidden="no"
       target="${chUrl}" />
</body>
</html>
