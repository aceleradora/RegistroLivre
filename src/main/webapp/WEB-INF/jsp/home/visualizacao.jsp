<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<link href="assets/css/bootstrap.css" rel="stylesheet">
</head>
	<body>
		<h2>Registro Livre</h2>
		
		<h2>Visualização de Empresa</h2>
		
		<label>CNPJ: </label> 						<label>${empresa.cnpj}</label>
		<br />
		<label>Nome Fantasia: </label> 				<label>${empresa.nomeFantasia}</label>
		<br />
		<label>Razão Social: </label> 				<label>${empresa.razaoSocial}</label>
		<br />
		<label>Endereço: </label> 					<label>${empresa.endereco}</label>
		<br />
		<label>Número: </label> 					<label>${empresa.numero}</label>
		<label>Compl.: </label> 					<label>${empresa.complemento}</label>
		<br />
		<label>Cidade: </label> 					<label>${empresa.cidade}</label>
		<label>Estado: </label> 					<label>${empresa.estado}</label>
		<br />
		<label>CEP: </label> 						<label>${empresa.cep}</label>
		<br />
		<label>Data de abertura: </label> 			<label>${empresa.dataAbertura}</label>
		<a href="Download" ><img src="pdfImage.jpg"></a>
		<br />
		<label>Emissão do documento: </label> 		<label>${empresa.emissaoDocumento}</label>
		<br />
		<br />
		
		<h2>Estrutura Societária</h2>
		
		<c:forEach items="${empresa.socioList}" var="socio">					
			<label>Sócio ${socio.id + 1}: </label> 			<label>${socio.nome}</label>						
			<label>- CPF: </label> 							<label>${socio.cpf}</label>	
			<c:if test="${socio.situacaoDoSocio == true}">
				<span class="label label-success">Ativo</span>
			</c:if>		
			<c:if test="${socio.situacaoDoSocio == false}">
				<span class="label label-important">Inativo</span>
			</c:if>		
		</c:forEach>
	</body>
</html>