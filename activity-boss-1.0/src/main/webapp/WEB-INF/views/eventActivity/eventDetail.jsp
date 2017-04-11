<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>事件详情</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
</head>
<body>
  <div class="Container">
    <div class="Content fontText">
      <div class="information">
          <div class="clear"></div>
          <div class="search_tit">
          <h2 class="fw fleft f14">事件详情</h2>
        </div>
            <div class="input_cont">
              <ul>
                <li>
                  <label class="text_tit">事件编码：</label>
                  <c:out value="${activityActionDtO.actionCode }"></c:out>
                </li>
                <li><label class="text_tit">事件名称：</label>
              <c:out value="${activityActionDtO.actionName }"></c:out>
            </li>
                <li>
                  <label class="text_tit">奖品列表：</label>
                  <table>
                    <c:forEach items="${activityPrizeDtoList}" var="prize" varStatus="num">
                      <tr>
                      <td>
                        <span>奖品序号：</span>
                      <c:out value="${prize.id }" />;
                        </td>
                        <td>
                        <span>奖品名称：</span>
                      <c:out value="${prize.prizeName }" />;
                        </td>
                         <td>
                        <span>奖品概率：</span>
                      <c:out value="${prize.prizeOdds }" />%
                        </td>
                         <td>
                        <span>奖品总数：</span>
                      <c:out value="${prize.prizeTotalCount }" />;
                        </td>
                         <td>
                        <span>已中奖数：</span>
                      <c:out value="${prize.prizeGrantCount }" />;
                        </td>
                          <td>
                        <span>奖品等级：</span>
                        <c:forEach var="item"
                      items="${_textResource.getTextMap('activity_prize_level') }">
                       <c:if test="${item.key==prize.prizeLevel}">
                      <c:out value="${item.value }" />;
                      </c:if>
                    </c:forEach>
                        </td>
                </tr>
                    </c:forEach>
                  </table>
                </li>
              </ul>
            </div>
            <div class="btn">
          <input type="button" onclick="window.history.go(-1)" class="btn_cancel fw" value="取消" />
          </div>
          <div class="clearer"></div>
          </form>
        </div>
      <br />
    </div>
  </div>
</body>
</html>
