<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>天气情况</title>
</head>
<body>
	<table border="2" width="600">
		<tr>
			<th align="left"><s:property value="county.getName()" /></th>
			<th align="left">天气</th>
			<th align="left">温度</th>
			<!-- <th align="left">${requestScope.county.name}</th> -->
		</tr>
		<s:iterator value="dayWeathers" status="st">
			<tr>
				<td><s:property value="date" /></td>
				<td><s:property value="weatherType.name" /></td>
				<td><s:property value="temp" /></td>
				<!--<td>${date}</td>  -->
			</tr>
		</s:iterator>
	</table>
</body>
</html>