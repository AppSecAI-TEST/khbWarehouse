<%
	String location = "popularize/toPopularize";
	String source = request.getParameter("s");
	if(source != null) {
	    location = request.getContextPath() + "/activity/promo?source=" + source;
	}
    response.resetBuffer();
    response.setStatus(HttpServletResponse.SC_FOUND);
    response.setHeader("Location", location);
%>