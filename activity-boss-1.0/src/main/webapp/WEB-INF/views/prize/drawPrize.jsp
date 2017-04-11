<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<html>
<head>
<title>抽奖</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<%-- <script src="${resourceUrl}/js/product.js"></script> --%>
</head>
<body>
  <div class="Container">
    <div class="Content fontText">
      <!--search star-->
      <div class="information">
        <form id="queryPrizeListForm" method="get"
          action="${ctxPath}prize/drawPrize">
          <div class="search">
            <div class="search_tit">
              <h2 class="fw fleft f14">抽奖</h2>
            </div>
            <div class="search_con">
              <ul class="fix">
                <li><label class="text_tit">会员编号：</label> <input
                  type="text" name="memberNo" class="input_text" /></li>
                <li><label class="text_tit">活动编码：</label> <input
                  type="text" name="activityCode" class="input_text" /></li>
                <li><label class="text_tit">事件编码：</label> <input
                  type="text" name="actionCode" class="input_text" /></li>
                <li><label class="text_tit">奖品名称：</label> <select
                    id="prizeId" name="prizeId"
                    class="input_text">
                      <c:forEach var="item"
                        items="${activityPrizeDto}">
                        <option
                          value="${item.id}">${item.prizeName}</option>
                      </c:forEach>
                </select></li>
                <div class="btn">
                  <input type="submit" class="btn_sure fw" value="抽奖" />
                <div class="clearer"></div>
            </div>
          </div>
        </form>
      </div>
      <div class="clearer"></div>
      <br />
    </div>
  </div>
</body>
</html>
