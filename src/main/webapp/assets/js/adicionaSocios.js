var cont = 1;

function adicionaSocio(){
	var htmlSocio = '<li class="list-group-item" id="socio' + cont + '">' +
	'<div class="list-group-item-heading centralize">' +
	'<h4>Dados dos sócios</h4>' +
	'</div>' +
	'<div class="form-group">' +
	'<label>Nome do sócio</label>' +
	'<input class="form-control" name="empresa.socios[' + cont + '].nome" value="">' +
	'</div>' +
	'<div class="form-group">' +
	'<label>CPF</label>' +
	'<input class="form-control" name="empresa.socios[' + cont + '].cpf" value="">' +
	'</div>' +
	'<div class="form-group">' +
	'<label>' +
	'<input type="checkbox" name="empresa.socios[' + cont + '].inativo" > Inativo'
	'</label>' + 
	'</div>' +
	'</li>';
	var divSocios = document.getElementById("divSocios");
	divSocios.innerHTML += htmlSocio;
	cont++;
	//alert(htmlSocio);
} 