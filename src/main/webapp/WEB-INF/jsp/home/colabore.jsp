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
				<div class="col-lg-8 col-md-8 col-sm-10 col-xs-12 col-lg-offset-2 col-md-offset-2 col-sm-offset-1">
					
					<h1 class="centralize title">Colabore</h1>
					

					<p class="text">O <b>Registro Livre</b> é um projeto sem fins lucrativos, defensor da liberdade de informação 
					e voltado a oferecer subsídios e especial para jornalistas produzirem reportagens investigativas.</br> 
					Aceitamos e incentivamos a contribuição de cada qual dentro de suas possibilidades.</p>
					
					<p class="text">Se você tem contratos sociais de empresas brasileiras para compartilhar conosco, faça o upload 
					em nosso banco de dados, ou então os envie para o email contato@registrolivre.inf.br.</br>
					
					<h2 class="centralize subtitle"> Jornalistas</h2>
					
					<p class="text">Os repórteres são o público-alvo principal do Registro Livre, porque no desempenho de suas 
					funções eles muitas vezes necessitam e adquirem os contratos sociais de empresas brasileiras nas Juntas Comerciais 
					de cada Estado. Nossa iniciativa pretende diminuir os custos de reportagem ao oferecer estes documentos públicos de forma livre e 
					gratuita. Por outro lado, contamos com a contribuição de jornalistas experientes, que já tenham um arquivo de contratos sociais 
					levantados ao longo de sua carreira. Se você é um repórter investigativo experiente, faça o upload de seus arquivos, ou os envie 
					para o email contato@registrolivre.inf.br. Se não tiver arquivos, fale sobre o projeto com seus colegas e os incentive a contribuir.
					</p>
					
					<h2 class="centralize subtitle"> Advogados, contadores, administradores e outros profissionais da área comercial. </h1>
					<p class="text">Assim como os jornalistas, advogados, contadores, administradores e outros profissionais da área 
					comercial lidam cotidianamente com contratos sociais. Estes grupos poderão se beneficiar do Registro Livre e, mais importante, 
					podem se tornar os principais colaboradores do nosso projeto. Se você trabalha num estabelecimento como estes, solicite autorização 
					e nos envie contratos sociais.</p>
					
					<h1 class="centralize subtitle">Cientistas da informação, engenheiros de computação, hackers e programadores em geral</h2>
					<p class="text">O Registro Livre é um projeto de software livre. Portanto, você pode obter nosso código-fonte no 
					<a href="https://github.com/aceleradora6-tw/RegistroLivre.git" target="_blank">Github</a> e aplicar
					 em seus próprios projetos. Melhor ainda, você pode nos ajudar a aprimorá-lo! Também adoraríamos ver os dados disponíveis 
					aqui servindo como base para análises e estudos, ou sugestões de novas funcionalidades.</p>
					
					<h2 class="centralize subtitle"> Todos os cidadãos</h2>
					<p class="text">Os contratos sociais são documentos públicos. Obter informações sobre a finalidade e estrutura 
					societária de empresas nacionais é essencial para exercer a cidadania -- por exemplo, para fiscalizar a relação entre contribuições de 
					campanha e desempenho de políticos. Esperamos que o Registro Livre seja muito útil para você e o convidamos a contribuir para aumentar 
					sua utilidade, nos enviando documentos. Algumas sugestões:</p>
					
					<ul>
						<li>Se você tem tempo livre, crie um cadastro na Junta Comercial de São Paulo, que oferece algumas informações gratuitamente, e 
						ajude a povoar nosso banco de dados com esses documentos.</li>
						<li>Se você puder oferecer algumas dezenas de reais, procure a Junta Comercial de seu Estado e adquira para nós os contratos sociais 
						de empresas que você julgue importantes (concessionárias de serviços públicos, por exemplo).</li>
						<li>Se você conhece pessoas que detêm arquivos de contratos sociais, fale com elas e as incentive a contribuir com o Registro Livre.</li>
						<li>Divulgue o Registro Livre em redes sociais como
						<a href="https://twitter.com/home?status=http://registro-livre-aceleradora.herokuapp.com/" onclick="window.open(this.href, 'mywin',
							'left=20,top=20,width=500,height=500,toolbar=1,resizable=0'); return false;" >
							Twitter
						</a>  e 
 					<a href="https://www.facebook.com/sharer/sharer.php?u=http://registro-livre-aceleradora.herokuapp.com/" onclick="window.open(this.href, 'mywin',
						'left=20,top=20,width=500,height=500,toolbar=1,resizable=0'); return false;">
						Facebook.
					</a>										
					</li>
					</ul>	
				</div>
				
				</div>
			</div>
			
		<jsp:include page="/WEB-INF/jsp/includes/rodape.jsp" />		
</body>
</html>