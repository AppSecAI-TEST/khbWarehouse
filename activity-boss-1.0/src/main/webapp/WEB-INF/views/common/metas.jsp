<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url var="resourceUrl" value="/static"/>
<spring:url var="ctxPath" value="/"/>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<script type="text/javascript">
  var GV = {
    ctxPath: '${ctxPath}',
    imgPath: '${resourceUrl}/images',
    jsPath: '${resourceUrl}/js'
  };
</script>
