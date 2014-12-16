<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/">Home</a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav btn-borders-navbar">
				<li><a href="/cadastro">Cadastrar Empresa</a></li>				
				<li><a href="/listagem">Listar Empresas</a></li>
			</ul>
				<form class="form navbar-form navbar-left " role="search" name="pesquisa" action="/busca" method="GET">
					<div class="form-group">
						<input class="form-control" type="text" name="busca" id="campoPesquisado" />
					</div>
					<div class="form-group">
						<input type="submit" id="btn-submit-cabecalho" class="btn btn-sm btn-primary pull-right margin-0-6" value="Buscar"/>
					</div>
				</form>
		
		</div>
	</div>
</nav>