<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>reg</title>
    <script src="js/jquery.min.js"></script>
    <style>
        th, td {
            border: black solid 1px;
        }
    </style>
</head>
<body>
    <fieldset style="margin: 10% auto;text-align: center;width: 30%; height: 50%;">
        <legend style="text-align: center;">注册</legend>
        <table style="border: black solid 1px;margin: 10% auto;">
            <tbody>
                <tr>
                    <th>
                        账号
                    </th>
                    <td>
                        <input type="text" id="username">
                    </td>
                </tr>
                <tr>
                    <th>
                        密码
                    </th>
                    <td>
                        <input type="password" id="password">
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button id="reg" onclick="reg()">注册</button>
                        <a href="login.jsp"><button id="login">返回</button></a>
                    </td>
                </tr>
            </tbody>
        </table>
    </fieldset>
</body>
<script>
    var reg = function(){
        $.ajax({
            type:"post",
            url:"reg.do",
            data: {"username": $('#username').val(), "password": $('#password').val()},
            dataType: "json",
            //contentType: "application/json; charset=utf-8",
            success:function(data) {
                if(data.code === 1){
                    alert('注册成功');
                }else{
                    alert(data.msg);
                }
            },
            error: function(){
                alert('error');
            }
        });
    };
</script>
</html>
