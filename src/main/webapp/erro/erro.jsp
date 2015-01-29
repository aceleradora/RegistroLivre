<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt-br">
<head>
	<title>Error 404</title>
	<jsp:include page="/WEB-INF/jsp/includes/assets.jsp" />	
</head>
<body>


					
					
		<video autoplay loop poster="../assets/img/ci.jpg" class="bg_video">
			<source src="../assets/video/Aceleradora.webm" type="video/webm">
			<source src="../assets/video/Aceleradora.mp4" type="video/mp4">
		</video>	
		
		<div class="erro">
			<h1 class="centralize erro-titulo erro-texto">404</h1>
			<span class="erro-texto">Não se preocupe já estamos trabalhando para corrigir este erro!!!</span>

			<div>
				<a href="/" class="btn btn-lg btn-default erro-botao" role="button">Voltar para o início</a>
			</div>		
		</div>

	
</body>
</html>