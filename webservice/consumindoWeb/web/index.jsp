<%@page import="util.PegaUrl"%>
<%
PegaUrl pu = new PegaUrl();

String a = pu.sendGet("https://viacep.com.br/ws/RS/Bag%C3%A9/Paulo%20tuyuty%20Camargo/json/", null);
out.print(a);
%>