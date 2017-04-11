<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>批量导入兑换码列表</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<%-- <script src="${resourceUrl}/js/product.js"></script> --%>

<script type="text/javascript">
  $(function () {
    DatePickerExt.time("createTimeStart");
    DatePickerExt.time("createTimeEnd");
  });
  function toRedeemCodeImport(){
    location.href = '${ctxPath}goodsDetail/toAddRedeemCodeList';
  }
</script>
</head>
<body>
	<div class="Container">
		<div class="Content fontText">
			<!--search star-->
			<div class="information">
				<form id="redeemCodeListForm" method="get"
					action="${ctxPath}goodsDetail/queryRedeemCodeList">
					<div class="search">
						<div class="search_tit">
							<h2 class="fw fleft f14">查询批量导入兑换码列表</h2>
						</div>
						<div class="search_con">
							<ul class="fix">
								<li><label class="text_tit">商品ID：</label> <input
									type="text" id="goodId" name="goodId" class="input_text"
									value="${goodId}" /></li>
								<li><label class="text_tit">兑换码：</label> <input
									type="text" id="redeemCode" name="redeemCode" class="input_text"
									value="${redeemCode}" /></li>
                                <li><label class="text_tit">创建起始时间：</label>
                                    <input type="text" name="createTimeStart" class="input_text" id="createTimeStart" value="${createTimeStart}" readonly="true" />
                                </li>
                                <li><label class="text_tit">创建截止时间：</label>
                                    <input type="text" name="createTimeEnd" class="input_text" value="${createTimeEnd}" id="createTimeEnd" readonly="true" />
                                </li>
							</ul>
							<div class="btn">
								<input type="submit" class="btn_sure fw" value="查询" /> 
                                <input type="button" onclick="clearAll();" class="btn_cancel fw" value="清空" />
                                <input type="button" class="btn_sure fw" onclick="toRedeemCodeImport();" value="批量导入" />
							</div>
							<div class="clearer"></div>
						</div>
					</div>
				</form>
			</div>
			<div class="clearer"></div>
			<div class="result">
				<h2 class="fw">查询结果</h2>
				<q:table queryService="activityQueryService" queryKey="redeemCodeList"
					template="querytableForUnion" formId="redeemCodeListForm"
					class="table_list" pageSize="100">
					<q:nodata>无符合条件的记录</q:nodata>
					<q:column title="序号" value="${id }" width="40px" />
					<q:column title="兑换码" value="${redeem_code }" />
                    <q:column title="使用状态" value="${used_status }" />
					<q:column title="创建时间" value="${_messageFormater.formatDate(create_time) }" />
					<q:column title="使用时间" value="${_messageFormater.formatDate(used_time) }" />
                    <q:column title="商品ID" value="${good_id }" />
					<q:column title="商品名称" value="${goods_name }" />
					<q:column title="商品编码" value="${goods_code }" />
                    
				</q:table>
			</div>
			<div class="clearer"></div>
			<br />
		</div>
	</div>
</body>
</html>
