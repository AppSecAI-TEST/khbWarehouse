<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!------------------绑卡失败-弹出层------------------>
<!-- <div id="mask" style="display: none"></div> -->
<div id="failMask" class="alertLayer regMask pr" style="display: none; width:100%; top: 12%; left: 0px;">
    <div class="tipsBox radius4">
        <a class="btnClosed font-white pa" href="javascript:void(0)"><i class="icon icon-error2"></i></a>
        <div id="btnLayer" class="radius4 pr failTips pr">
            <img src="static/images/FailMask.png" class="repeatImg" alt=""/>
            <div class="failCon tc pa" id="layerBody">
                <p id="failTitle" class="failTitle">绑卡失败</p>
                <p id="errorMsgMask" class="orange">银行卡和预留手机号不符</p>
            </div>
            <p class="pro pa">如有问题，请拨打客服热线 <i class="orange">4001-500-882</i></p>
        </div>
    </div>
</div>
