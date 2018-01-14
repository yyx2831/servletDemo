<%--
  Created by IntelliJ IDEA.
  User: yyx
  Date: 2017/12/26
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/transfer" method="post">
        转出账户:<input type="text" name="out"><br>
        转入账户:<input type="text" name="in"><br>
        转账金额:<input type="text" name="money"><br>
        <input type="submit" value="确认转账">
    </form>
</body>
</html>
