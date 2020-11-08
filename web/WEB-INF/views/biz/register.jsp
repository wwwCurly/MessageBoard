<%--
  Created by IntelliJ IDEA.
  User: 86150
  Date: 2020/11/4
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>注册界面</title>
</head>

<body bgcolor="#ddd" style="font-family:Microsoft YaHei" >
<div style="text-align:center;margin-top:120px">
    <h1 >请注册</h1>
    <form action="addUser.do" method="post">
        <table style="margin-left:40%">
            <caption>用户注册</caption>
            <tr>
                <td>登录名：</td>
                <td><input name="username" type="text" size="20"></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input name="password" type="password" size="21"></td>
            </tr>
            <tr>
                <td>真实姓名:</td>
                <td><input name="real_name" type="text" size="21"></td>
            </tr>
            <tr>
                <td>生日:</td>
                <td><input name="birthday" type="text" size="21"></td>
            </tr>
            <tr>
                <td>电话:</td>
                <td><input name="phone" type="text" size="21"></td>
            </tr>
            <tr>
                <td>地址:</td>
                <td><input name="address" type="text" size="21"></td>
            </tr>
        </table>
        <input type="submit" value="注册">
        <input type="reset" value="重置">
    </form>
    <br>
    <a href="/login.do">登录</a>
</div>
</body>
</html>

