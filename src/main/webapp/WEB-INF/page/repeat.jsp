<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>测试</title>
    <%@include file="/WEB-INF/common/common.jsp" %>

</head>
<body>
<div class="layui-container" style="margin-top: 50px;">
    <form class="layui-form" action="${basePath}/demo2" method="post">

        <input type="hidden" name="random" value="${randomxx}">

        <div class="layui-form-item">
            <label class="layui-form-label">输入框</label>
            <div class="layui-input-inline">
                <input type="text" name="username" placeholder="请输入标题" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">密码框</label>
            <div class="layui-input-inline">
                <input type="password" name="password" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">生日</label>
            <div class="layui-input-inline">
                <input type="text" name="birthday" id="birthday" placeholder="请选择生日" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

<script>
    layui.use(['form', 'laydate'], function () {
        var form = layui.form,
            laydate = layui.laydate;

        laydate.render({
            elem: "#birthday",
            type: "datetime",
            theme: "molv"
        });
    });
</script>
</body>
</html>
