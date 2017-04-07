<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ page contentType="text/html;charset=UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../common.jsp"/>
<title>MSBlog</title>
<style>
.popover {
	position: absolute;
	top: 0;
	left: 0;
	z-index: 1060;
	display: none;
	max-width: 276px;
	padding: 1px;
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
	font-style: normal;
	font-weight: 400;
	letter-spacing: normal;
	line-break: auto;
	line-height: 1.42857143;
	text-align: start;
	text-decoration: none;
	text-shadow: none;
	text-transform: none;
	white-space: normal;
	word-break: normal;
	word-spacing: normal;
	word-wrap: normal;
	font-size: 14px;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid #ccc;
	border: 1px solid rgba(0, 0, 0, .2);
	border-radius: 6px;
	-webkit-box-shadow: 0 5px 10px rgba(0, 0, 0, .2);
	box-shadow: 0 5px 10px rgba(0, 0, 0, .2)
}

.popover.top {
	margin-top: -10px
}

.popover.right {
	margin-left: 10px
}

.popover.bottom {
	margin-top: 10px
}

.popover.left {
	margin-left: -10px
}

.popover-title {
	margin: 0;
	padding: 8px 14px;
	font-size: 14px;
	background-color: #f7f7f7;
	border-bottom: 1px solid #ebebeb;
	border-radius: 5px 5px 0 0
}

.popover-content {
	padding: 9px 14px
}

.popover>.arrow, .popover>.arrow:after {
	position: absolute;
	display: block;
	width: 0;
	height: 0;
	border-color: transparent;
	border-style: solid
}

.popover>.arrow {
	border-width: 11px
}

.popover>.arrow:after {
	border-width: 10px;
	content: ""
}

.popover.top>.arrow {
	left: 50%;
	margin-left: -11px;
	border-bottom-width: 0;
	border-top-color: #999;
	border-top-color: rgba(0, 0, 0, .25);
	bottom: -11px
}

.popover.top>.arrow:after {
	content: " ";
	bottom: 1px;
	margin-left: -10px;
	border-bottom-width: 0;
	border-top-color: #fff
}

.popover.right>.arrow {
	top: 50%;
	left: -11px;
	margin-top: -11px;
	border-left-width: 0;
	border-right-color: #999;
	border-right-color: rgba(0, 0, 0, .25)
}

.popover.right>.arrow:after {
	content: " ";
	left: 1px;
	bottom: -10px;
	border-left-width: 0;
	border-right-color: #fff
}

.popover.bottom>.arrow {
	left: 50%;
	margin-left: -11px;
	border-top-width: 0;
	border-bottom-color: #999;
	border-bottom-color: rgba(0, 0, 0, .25);
	top: -11px
}

.popover.bottom>.arrow:after {
	content: " ";
	top: 1px;
	margin-left: -10px;
	border-top-width: 0;
	border-bottom-color: #fff
}

.popover.left>.arrow {
	top: 50%;
	right: -11px;
	margin-top: -11px;
	border-right-width: 0;
	border-left-color: #999;
	border-left-color: rgba(0, 0, 0, .25)
}

.popover.left>.arrow:after {
	content: " ";
	right: 1px;
	border-right-width: 0;
	border-left-color: #fff;
	bottom: -10px
}

.ladda-button {
	position: relative
}

.ladda-button .ladda-spinner {
	position: absolute;
	z-index: 2;
	display: inline-block;
	width: 32px;
	height: 32px;
	top: 50%;
	margin-top: 0;
	opacity: 0;
	pointer-events: none
}

.ladda-button .ladda-label {
	position: relative;
	z-index: 3
}

.ladda-button .ladda-progress {
	position: absolute;
	width: 0;
	height: 100%;
	left: 0;
	top: 0;
	background: rgba(0, 0, 0, .2);
	visibility: hidden;
	opacity: 0;
	transition: .1s linear all !important
}

.ladda-button[data-loading] .ladda-progress {
	opacity: 1;
	visibility: visible
}

.ladda-button, .ladda-button .ladda-spinner, .ladda-button .ladda-label
	{
	transition: .3s cubic-bezier(.175, .885, .32, 1.275) all !important
}

.ladda-button[data-style=zoom-in], .ladda-button[data-style=zoom-in] .ladda-spinner,
	.ladda-button[data-style=zoom-in] .ladda-label {
	transition: .3s ease all !important
}

.ladda-button[data-style=zoom-in] {
	overflow: hidden
}

.ladda-button[data-style=zoom-in] .ladda-spinner {
	left: 50%;
	margin-left: -16px;
	transform: scale(.2)
}

.ladda-button[data-style=zoom-in] .ladda-label {
	position: relative;
	display: block
}

.ladda-button[data-style=zoom-in][data-loading] .ladda-label {
	opacity: 0;
	transform: scale(2.2)
}

.ladda-button[data-style=zoom-in][data-loading] .ladda-spinner {
	opacity: 1;
	margin-left: 0;
	transform: none
}

a[title=站长统计] {
	display: none
}

::-moz-focus-inner {
	border: 0
}

* {
	font-family: "Microsoft YaHei"
}

h2, body, input, button {
	margin: 0
}

h2, body, input, button, section {
	padding: 0;
	font-weight: 400
}

a {
	text-decoration: underline
}

html {
	font-size: 10px
}

body {
	position: relative;
	background-color: #3686be;
	min-height: 100%;
	text-align: center
}

header {
	padding: 7% 0 20px
}

header img {
	width: 200px
}

header a, section {
	position: relative;
	z-index: 1
}

section {
	background-color: #fff;
	border-radius: 2px;
	max-width: 400px;
	margin: 0 auto;
	padding-top: 20px;
	padding-bottom: 20px
}

section h2 {
	font-size: 32px;
	color: #565656
}

section>div {
	padding: 10px 0
}

section input {
	background-color: #fcfcfc;
	border-radius: 2px;
	font-size: 18px;
	padding: 14px;
	width: 72%;
	border: 1px solid #dddcdc
}

section input:focus {
	border-color: #3686be
}

section button {
	background-color: #6ec78b;
	border-radius: 2px;
	width: 79%;
	border: none;
	padding: 12px 15px;
	cursor: pointer;
	color: #fff;
	font-size: 1.7rem
}

section button span {
	color: inherit
}

section button[disabled] {
	background-color: #ccc;
	border-color: #ccc;
	cursor: not-allowed
}

section>div, section a, section span {
	color: #8d928e;
	font-size: 1.4rem
}

section .ladda-button span {
	font-size: 1.8rem
}

footer {
	position: relative;
	z-index: 1;
	color: #fff;
	padding-top: 20px;
	font-size: 1.3rem
}

footer a {
	color: inherit;
	font-size: 1.6rem
}

#particles-js {
	position: absolute;
	width: 100%;
	top: 0;
	background-position: 50% 50%;
	z-index: 0
}

button::-moz-focus-inner {
	border: 0
}

button:hover {
	background-color: #5cb479
}
</style>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="utf-8">
<link rel="shortcut icon" type="image/ico" href="/img/favicon.png">
<%-- <script src="${ctx}/static/js/jia.js" defer=""></script> --%>
<meta name="viewport" content="width=device-width,initial-scale=1">
</head>
<body>
	<header>
		<a href="/"><img src="${ctx }/static/img/avatar.jpg"></a>
	</header>
	<form id="submit">
		<section>
			<h2 class="title-js">登录</h2>
			<div class="email-js">
				<input name="userName" type="text" placeholder="用户名">
			</div>
			<div class="password-js">
				<input name="passWord" type="password" placeholder="密码">
			</div>
			<div>
				<button autocomplete="off" class="submit-js">登录</button>
			</div>
			<div class="afterSubmit-js"></div>
		</section>
	</form>
	<footer class="footer-js">您还没有注册账户？<a href="${ctx}/account/toRegister?returnUrl=${returnUrl}">请注册</a></footer>
	<div id="particles-js"></div>
	
</body>
<script type="text/javascript">
$(".submit-js").click(function(){
  $.ajax({
    url:"${ctx}/account/login",
    dataType:'json',
    data: $("#submit").serialize(),
    type:'post',
    success:function(data){
      if(data.code =="success") {
        location.href="${returnUrl}";
      } else {
        alert(data.message);
      }
    },
  }); 
  return false;
})
</script>
</html>
