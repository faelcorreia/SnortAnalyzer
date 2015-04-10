<%@page import="controller.Controladora"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>SnortAnalyzer</title>

<link href="style/layout.css" rel="stylesheet" type="text/css" />
<link href="style/menu.css" rel="stylesheet" type="text/css" />
<link href="style/tabela.css" rel="stylesheet" type="text/css" />

<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="flot/excanvas.min.js"></script><![endif]-->
<script language="javascript" type="text/javascript"
	src="flot/jquery.js"></script>

<script>
	$(document).ready(function() {

		$("ul.subnav").parent().append("<span></span>"); //Only shows drop down trigger when js is enabled (Adds empty span tag after ul.subnav*)  

		$("ul.topnav li span").click(function() { //When trigger is clicked...  

			//Following events are applied to the subnav itself (moving subnav up and down)  
			$(this).parent().find("ul.subnav").slideDown('fast').show(); //Drop down the subnav on click  

			$(this).parent().hover(function() {
			}, function() {
				$(this).parent().find("ul.subnav").slideUp('slow'); //When the mouse hovers out of the subnav, move it back up  
			});

			//Following events are applied to the trigger (Hover events for the trigger)  
		}).hover(function() {
			$(this).addClass("subhover"); //On hover over, add class "subhover"  
		}, function() { //On Hover Out  
			$(this).removeClass("subhover"); //On hover out, remove class "subhover"  
		});

	});
</script>

<script type="text/javascript" language="javascript"
	src="scripts/jquery.dataTables.js"></script>

<script>
	$(document).ready(function() {
		$('#tabela').dataTable({
			"aaSorting" : [ [ 0, "asc" ] ]
		});
	});
</script>

</head>
<body>
	<div id="estrutura">
		<div id="cabecalho">
			<a href="index.jsp?tipo=1"><img src="imagens/logo.png" /></a>
		</div>
		<div id="menu">
			<%@ include file="menu.html"%>
		</div>
		<div id="corpo2">
			<%
				out.print(Controladora.retornaTabelaEvento());
			%>
		</div>
		<div id="rodape"></div>
	</div>
</body>
</html>