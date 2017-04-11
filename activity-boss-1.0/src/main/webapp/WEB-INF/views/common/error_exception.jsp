<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统异常</title>
  <link rel="stylesheet" type="text/css" href="${resourcePath!"https://resource.yeepay.com/yeepay-resources"}/boss/css/global.css" />
  <link rel="stylesheet" type="text/css" href="${resourcePath!"https://resource.yeepay.com/yeepay-resources"}/boss/css/layout.css" />
  <link rel="stylesheet" type="text/css" href="${resourcePath!"https://resource.yeepay.com/yeepay-resources"}/boss/css/master.css" />
</head>

<body>
  <div class="Container">
    <!------------ 错误页面 ------------>
    <div class="weberror">
      <div class="web_tit">
        <div class="web_l"></div>
        <h2>温馨提示</h2>
        <div class="web_r"></div>
      </div>
      <div class="web_con">
        <div class="web_left"><img src="${resourcePath!"https://resource.yeepay.com/yeepay-resources"}/boss/images/u129_original.png" width="96" height="96" /></div>
        <div class="web_right">
          <h3>系统运行异常,请与管理员联系。</h3>
          <p class="kongbai"></p>
          <p>您现在可以返回：<span><a href="javascript:history.go(-1);">上一页</a></span></p>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
