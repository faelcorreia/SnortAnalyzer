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
</head>

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
<body>
	<div id="estrutura">
		<div id="cabecalho">
			<a href="index.jsp?tipo=1"><img src="imagens/logo.png" /></a>
		</div>
		<div id="menu">
			<%@ include file="menu.html"%>
		</div>
		<div id="corpo">
			<div align="center">
				<a href="http://pucminas.br"> <img src="imagens/puc.jpg" /></a>
			</div>
			<div style="margin-left: 60%; text-align: right;">
				<b><i>O mantra de qualquer bom engenheiro de seguran�a �:
						"Seguran�a n�o � um produto, mas um processo." � mais que projetar
						uma criptografia forte em um sistema; � desenhar todo o sistema de
						tal forma que todas as medidas de seguran�a, incluindo
						criptografia, trabalhem juntas.<br /> <br />Bruce Schneier
				</i></b>
			</div>
			<br /> <br /> A evolu��o das redes de computadores � evidente nas
			�ltimas d�cadas passadas. O n�mero de protocolos desenvolvidos, as
			tecnologias destinadas para a resolu��o de novos problemas e a ampla
			discuss�o em volta da interliga��o de dispositivos demonstram o
			quanto o meio acad�mico e o mercado de TI se preocupam com isso. Hoje
			� quase imposs�vel que participantes de qualquer institui��o utilizem
			computadores sem que estes estejam conectados a algum lugar de alguma
			forma. Por�m, como � visto amplamente, seja na pr�pria internet ou
			atrav�s da m�dia, a rede tr�s diversos problemas relacionados a
			seguran�a da informa��o. � necess�rio que o administrador da rede se
			preocupe com isso e aplique m�todos de prote��o contra intrus�es ou
			amea�as capazes de fazer a pr�pria rede ficar indispon�vel. Hoje as
			ferramentas que auxiliam nessa tarefa s�o muitas vezes complexas e a
			an�lise dos resultados complicada. Esse trabalho visa facilitar a
			an�lise dos eventos de um dos IDS mais utilizados no mundo, o Snort,
			provendo uma vis�o de alto n�vel ao administrador atrav�s de
			gr�ficos.<br /> <br /> <b>Autor</b>: Rafael Silva Correia <br /> <b>Orientador</b>:
			Renato Moreira Hadad
		</div>
		<div id="rodape"></div>
	</div>
</body>
</html>