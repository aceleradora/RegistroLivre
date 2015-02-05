<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target=".navbar-collapse" >
				<span class="sr-only">Toggle navigation</span> 
				<span class="icon-bar"></span> <span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			
		</div>
		<div class="collapse navbar-mobile">
			<ul class="nav navbar-nav btn-borders-navbar">
				<li><a href="/"><span class="glyphicon glyphicon-search"></span> Busca</a></li>
				<li><a href="/cadastro"><span class="glyphicon glyphicon-cloud-upload"></span> Cadastrar Empresa</a></li>				
				<li><a href="/listagem"><span class="glyphicon glyphicon-th-list"></span> Listar Empresas</a></li>				
			</ul>
			
			
			<form id="inputaBuscaNavbar" class="form navbar-form navbar-right " role="search" name="pesquisa" action="/busca" method="GET">
			
				<div class="input-group">
					<input type="text" class="form-control" name="busca" id="campo-pesquisado-navbar" placeholder="Buscar" />
					<span><button type="submit" class="btn btn-default botao-busca-navbar"><span class="glyphicon glyphicon-search"></span></button></span>
				</div>								
			</form>			
		</div>
	</div>
</nav>