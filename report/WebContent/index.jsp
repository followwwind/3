<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>login</title>
    <script src="js/jquery.min.js"></script>
    <style>
        * {
            padding: 0px;
            margin: 0px;
            box-sizing: border-box;
        }
        html, body{
           width: 100%;
           height: 100%;
        }
        .app {
          padding: 3px;
          height: 100%;
          overflow: hidden;
        }
        .content {
          padding-top: 3px;
          height: 100%;
          display: flex;
        }
        .header {
          border: black solid 1px;
          height: 30px;
          line-height: 30px;
        }
        .header p {
          text-align: center;
        }
        .aside {
          width: 200px;
          min-width: 200px;
          border: black solid 1px;
          display: flex;
          flex-direction: column;
        }
        section {
          flex: 1;
          border: black solid 1px;
          display: flex;
          flex-direction: column;
        }
    </style>
</head>
<body>
    <div class="app">
      <div class="header">
          <p>xx报告管理系统</p>
      </div>
      <div class="content">
          <div class="aside"> </div>
          <section>
              <iframe src="list.jsp" frameborder="0" width="100%" height="100%"><iframe>
          </section>
      <div>
    </div>
</body>
<script>
    var login = function(){
        $.ajax({
            type:"post",
            url:"login.do",
            data: {"username": $('#username').val(), "password": $('#password').val()},
            dataType: "json",
            //contentType: "application/json; charset=utf-8",
            success:function(data) {
                if(data.code === 1){
                    alert('登录成功');
                    location.href="index.jsp";
                }else{
                    alert("账号或密码错误");
                }
            },
            error: function(){
                alert('error');
            }
        });
    };
</script>
</html>
