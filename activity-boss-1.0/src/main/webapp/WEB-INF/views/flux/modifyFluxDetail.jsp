<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>修改流量信息</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<script type="text/javascript">
$(function () {
$("#updateFluxForm").submit(function(){
  return $(this).validateSubmit({
    cmccFlowId: {
          req: true,
          label: "移动流量包产品编号"
      },
      cuccFlowId: {
        req: true,
        label: "联通流量包产品编号"
    },
    ctccFlowId: {
      req: true,
      label: "电信流量包产品编号"
  }
  });
  });
});
</script>
</head>
<body>
	<div class="Container">
		<div class="Content fontText">
			<div class="information">
			    <div class="clear"></div>
			    <div class="search_tit">
					<h2 class="fw fleft f14">修改流量信息</h2>
				</div>
			    <form name="updateFluxForm" id="updateFluxForm" action="modifyFlux" method="post" >
			      <div class="input_cont">
			        <ul>
                       <li>
                  <label class="text_tit">序号：</label>
                  <input class="input_text" type="text" readonly="readonly" name="id" id="id" value="${fluxPlatDetailDto.id  }">
                </li>
			          <li>
			            <label class="text_tit">批次任务号：</label>
			            <input class="input_text" type="text" readonly="readonly" name="batchTaskId" id="batchTaskId" value="${fluxPlatDetailDto.batchTaskId  }">
			          </li>
			          <li>
                  <label class="text_tit">会员编号：</label>
                  <input class="input_text" type="text" readonly="readonly" name="memberNo" id="memberNo" value="${fluxPlatDetailDto.memberNo  }">
                </li>
                <li>
                  <label class="text_tit">手机号：</label>
                  <input class="input_text" type="text" readonly="readonly" name="mobileNo" id="mobileNo" value="${fluxPlatDetailDto.mobileNo  }">
                </li>
                <li>
                  <label class="text_tit">移动流量包产品编号：</label>
                  <input class="input_text" type="text"  name="cmccFlowId" id="cmccFlowId" value="${fluxPlatDetailDto.cmccFlowId  }">&nbsp;<font color="red">*</font>
                </li>
                 <li>
                  <label class="text_tit">联通流量包产品编号：</label>
                  <input class="input_text" type="text"  name="cuccFlowId" id="cuccFlowId" value="${fluxPlatDetailDto.cuccFlowId  }">&nbsp;<font color="red">*</font>
                </li>
                 <li>
                  <label class="text_tit">电信流量包产品编号：</label>
                  <input class="input_text" type="text"  name="ctccFlowId" id="ctccFlowId" value="${fluxPlatDetailDto.ctccFlowId  }">&nbsp;<font color="red">*</font>
                </li>
                <li>
                <label class="text_tit">流量生效类型：</label>
                 <select name="effectType" id="effectType" >
                       <option value='currentmonth' ${fluxPlatDetailDto.effectType.equals('currentmonth')?'selected':''}>当月生效</option>
                       <option value='nextmonth' ${fluxPlatDetailDto.effectType.equals('nextmonth')?'selected':''}>次月生效</option>
                       </select>&nbsp;<font color="red">*</font>
                       </li>
			        </ul>
			      </div>
			      <div class="btn">
					<input type="submit" class="btn_sure fw" value="保存" />
					<input type="button"onclick="window.history.go(-1)" class="btn_cancel fw" value="取消" />
				  </div>
				  <div class="clearer"></div>
			    </form>
			  </div>
			<br />
		</div>
	</div>
</body>
</html>
