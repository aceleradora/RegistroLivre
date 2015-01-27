<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="iso-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt-br">
<head>
	<title>Colabore</title>
	<jsp:include page="/WEB-INF/jsp/includes/assets.jsp" />	
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/includes/cabecalho.jsp" />
		<div class="content">
			<div class="row">
				<div class="col-lg-8 col-md-4 col-lg-offset-2 col-md-offset-3">
					
					<h1 class="centralize font-size-40 title"> Colabore</h1>
					

					<p class="text-justify font-size-19">O <b>Registro Livre</b> é uma plataforma de dados abertos cujo objetivo principal é colocar à
					disposição do público informações sobre empresas, imóveis e outros tipos de bens e atividades
					sujeitos a registro público no Brasil. Embora os dados disponíveis em cartórios e juntas
					comerciais sejam públicos, eles raramente são publicados. O cidadão que deseja ter acesso a
					contratos sociais e informações sobre propriedade de imóveis, por exemplo, precisa pagar
					taxas e aguardar vários dias para receber cópias de documentos. O Registro Livre vai reunir
					estes dados numa plataforma aberta, para a qual todo cidadão poderá contribuir. Desta forma,
					os registros públicos serão acessíveis a todos e tornados públicos de fato.</p>
					
					<p class="text-justify font-size-19">A <b>Aceleradora Ágil</b> é um programa inovador com participantes de diversas insituições que tem por objetivo, 
					com auxílio de profissionais experientes, incentiva-los a desenvolver competências técnicas, comportamentais, 
					de negócios e de governança necessárias para atuar em equipes de alto desempenho de desenvolvimento de software. 
					A Aceleradora é uma parceria ThoughtWorks, PUCRS e Centro de Inovação Microsoft.</p>
					
					<p class="text-justify font-size-19">O <b>Registro Livre</b> esta disponivel no <a href="https://github.com/aceleradora6-tw/RegistroLivre.git"><img class="imagemGit" src="/assets/img/iconeGit.png">Github</a>, colabore você também.</p>
			
				</div>
			</div>
			
		</div>
		<div class="ajustaRodapeColabore">
		<jsp:include page="/WEB-INF/jsp/includes/rodape.jsp" />	
	</div>	
</body>
</html>