<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="q" uri="/ajaxquery-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url var="resourceUrl" value="/static"/>
<spring:url var="ctxPath" value="/"/>
<html>
<head>
  <title>首页</title>
  <style>
    .btn {
      display: inline-block;
      padding: 6px 12px;
      margin-bottom: 0;
      font-size: 14px;
      font-weight: normal;
      line-height: 1.428571429;
      text-align: center;
      white-space: nowrap;
      vertical-align: middle;
      cursor: pointer;
      background-image: none;
      border: 1px solid transparent;
      border-radius: 4px;
      -webkit-user-select: none;
      -moz-user-select: none;
      -ms-user-select: none;
      -o-user-select: none;
      user-select: none;
      border-color: #adadad;
    }
  </style>
</head>
<body>
<div class="container">
  <a class="btn" href="${ctxPath}snapshot/query">快照查询</a>
  <a class="btn" href="${ctxPath}snapshot/stat">快照统计</a>
  <a class="btn" href="${ctxPath}snapshot/generate/full">全量快照</a>
  <a class="btn" href="${ctxPath}snapshot/generate/increment">增量快照</a>

  </div>
</body>
</html>
