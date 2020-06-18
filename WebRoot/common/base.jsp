<%@ page language="java" import="java.util.*" errorPage="/error.jsp" pageEncoding="utf-8"%>
<%@ page import="com.gxuwz.medical.domain.role.*" %>
<%@ page import="com.gxuwz.medical.dao.*" %>
<%@ page import="com.gxuwz.medical.domain.vo.*" %>
<%@ page import="com.gxuwz.medical.config.*" %>
<%@ page import="com.gxuwz.medical.domain.menu.*" %>
<%@ page import="com.gxuwz.medical.domain.institution.*" %>
<%@ page import="com.gxuwz.medical.domain.family.*" %>
<%@ page import="com.gxuwz.medical.domain.peasant.*" %>
<%@ page import="com.gxuwz.medical.domain.chronicdis.*" %>
<%@ page import="com.gxuwz.medical.domain.card.*" %>
<%@ page import="com.gxuwz.medical.domain.medical.*" %>
<%@ page import="com.gxuwz.medical.domain.user.*" %>
<%@ page import="com.gxuwz.medical.domain.area.*" %>
<%@ page import="com.gxuwz.medical.domain.pay.*" %>
<%@ page import="com.gxuwz.medical.domain.policy.*" %>
<%@ page import="com.gxuwz.medical.domain.apply.*" %>
<%@ page import="com.gxuwz.medical.exception.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
