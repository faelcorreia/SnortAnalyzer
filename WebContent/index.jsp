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

<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="flot/excanvas.min.js"></script><![endif]-->
<script language="javascript" type="text/javascript"
	src="flot/jquery.js"></script>
<script language="javascript" type="text/javascript"
	src="scripts/graficos.js"></script>
<script language="javascript" type="text/javascript"
	src="flot/jquery.flot.js"></script>
<script language="javascript" type="text/javascript"
	src="flot/jquery.flot.pie.js"></script>

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

</head>
<body>
	<div id="estrutura">
		<div id="cabecalho">
			<a href="index.jsp?tipo=1"><img src="imagens/logo.png" /></a>
		</div>
		<div id="menu">
			<%@ include file="menu.html"%>
		</div>
		<div id="corpo">
			<div id="dados"></div>

			<div id="interactive"></div>
			<script>
				var data = [];
			<%try {
				int id = Integer.parseInt(request.getParameter("tipo"));
				out.print(Controladora.retornaGrafico(id));
			} catch (Exception e) {
				out.print(Controladora.retornaGrafico(1));
			}%>
				$.plot($("#interactive"), data, {
					series : {
						pie : {
							show : true
						}

					},
					grid : {
						hoverable : true,
						clickable : true
					},

				});
				$("#interactive").bind("plothover", pieHover);
				$("#interactive").bind("plotclick", pieClick);
			</script>


		</div>
		<div id="rodape"></div>
	</div>
</body>
</html>