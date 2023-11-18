<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String url = request.getRequestURL().toString();
	if (url.contains("localhost")) {
		response.sendRedirect("index.html");
	} else {
		String baseURL = url.substring(0, url.length() - request.getRequestURI().length())
				+ request.getContextPath() + "/";
		baseURL = baseURL.replace("http://", "https://");
		response.sendRedirect(baseURL + "index.html");
	}
%>