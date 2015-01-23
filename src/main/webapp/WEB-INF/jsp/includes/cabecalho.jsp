<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav btn-borders-navbar">
				<li><a href="/"><span class="glyphicon glyphicon-search"></span> Busca</a></li>
				<li><a href="/cadastro"><span class="glyphicon glyphicon-cloud-upload"></span> Cadastrar Empresa</a></li>				
				<li><a href="/listagem"><span class="glyphicon glyphicon-th-list"></span> Listar Empresas</a></li>				
				<li><a href="/sobre"><span class="glyphicon glyphicon-user"></span> Quem somos</span></a>
				<li><a href="/contato"><span class="glyphicon glyphicon-envelope"></span> Contato</a></li>
			</ul>			
			<form id="inputaBuscaNavbar" class="form navbar-form navbar-right " role="search" name="pesquisa" action="/busca" method="GET">
				<div class="form-group">
					<input class="form-control" type="text" name="busca" id="campoPesquisadoNavbar" placeholder="Buscar empresa"/>
				</div>
				<div class="form-group">
					<input type="submit" id="btn-submit-cabecalho" class="btn btn-sm btn-primary pull-right margin-0-6" value="Buscar"/>
				</div>
			</form>			
		</div>
	</div>
</nav>