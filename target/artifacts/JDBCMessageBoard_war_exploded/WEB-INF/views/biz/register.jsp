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
    <title>ע�����</title>
</head>

<body bgcolor="#ddd" style="font-family:Microsoft YaHei" >
<div style="text-align:center;margin-top:120px">
    <h1 >��ע��</h1>
    <form action="addUser.do" method="post">
        <table style="margin-left:40%">
            <caption>�û�ע��</caption>
            <tr>
                <td>��¼����</td>
                <td><input name="username" type="text" size="20"></td>
            </tr>
            <tr>
                <td>����:</td>
                <td><input name="password" type="password" size="21"></td>
            </tr>
            <tr>
                <td>��ʵ����:</td>
                <td><input name="real_name" type="text" size="21"></td>
            </tr>
            <tr>
                <td>����:</td>
                <td><input name="birthday" type="text" size="21"></td>
            </tr>
            <tr>
                <td>�绰:</td>
                <td><input name="phone" type="text" size="21"></td>
            </tr>
            <tr>
                <td>��ַ:</td>
                <td><input name="address" type="text" size="21"></td>
            </tr>
        </table>
        <input type="submit" value="ע��">
        <input type="reset" value="����">
    </form>
    <br>
    <a href="/login.do">��¼</a>
</div>
</body>
</html>

