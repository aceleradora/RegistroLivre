package br.com.aceleradora.registrolivre.controller;

import java.util.Calendar;
import java.util.List;

import br.com.aceleradora.registrolivre.dao.EmpresaDAO;
import br.com.aceleradora.registrolivre.model.Empresa;
import br.com.aceleradora.registrolivre.model.Validador;
import br.com.aceleradora.registrolivre.util.Arquivo;
import br.com.aceleradora.registrolivre.util.CalendarTransformer;
import br.com.aceleradora.registrolivre.util.ClienteCloudinary;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.validator.Validations;
import flexjson.JSONSerializer;

@Resource
public class EmpresaController {
	private EmpresaDAO daoEmpresa;
	private Result result;
	private Validator validator;

	public EmpresaController(EmpresaDAO dao, Result result, Validator validator) {
		this.daoEmpresa = dao;
		this.result = result;
		this.validator = validator;
	}

	@Get("/cadastro")
	public void cadastro() {
	}

	@Get("/atualizar/{empresa.id}")
	public Empresa cadastro(Empresa empresa) {
		result.include("editar", true);
		return daoEmpresa.getById(empresa.getId());
	}

	public void listagem() {
	}

	@Get("/listagem")
	public void listarTodos() {
		List<Empresa> listaDeResultadosDeEmpresas = daoEmpresa.getTodas();

	@Get("/busca")
	public void busca(String busca) {
		if (busca == null) {
			result.redirectTo(HomeController.class).home();
		}

		busca = busca.replaceAll("[/.-]", "");

		List<Empresa> listaDeResultadosDeEmpresas = daoEmpresa.pesquisa(busca);

		if (listaDeResultadosDeEmpresas.size() == 0) {
			result.include("listaDeResultadosDeEmpresasVazia", true);
			result.redirectTo(HomeController.class).home();
		} else {
			result.include(
					"resultadoBusca",
					new JSONSerializer()
							.include("id")
							.include("nomeFantasia")
							.include("endereco.logradouro")
							.include("dataEmissaoDocumento")
							.exclude("*")
							.transform(new CalendarTransformer("dd/MM/yyyy"),
									Calendar.class)
							.serialize(listaDeResultadosDeEmpresas));

			result.redirectTo(this).listagem();
		}
	}

	@Get("/busca")
	public void busca(String busca) {
		if (busca != null) {
			busca = busca.replaceAll("[./-]", "");

			List<Empresa> listaDeResultadosDeEmpresas = daoEmpresa
					.pesquisa(busca);

			if (listaDeResultadosDeEmpresas.size() == 0) {
				result.include("listaDeResultadosDeEmpresasVazia", true);
				result.redirectTo(HomeController.class).home();
			} else {
				result.include(
						"resultadoBusca",
						new JSONSerializer()
								.include("id")
								.include("nomeFantasia")
								.include("endereco.logradouro")
								.include("dataEmissaoDocumento")
								.exclude("*")
								.transform(
										new CalendarTransformer("dd/MM/yyyy"),
										Calendar.class)
								.serialize(listaDeResultadosDeEmpresas));

				result.redirectTo(this).listagem();
			}
		} else {
			result.include("buscaVazia", true);
			result.redirectTo(HomeController.class).home();
		}
	}

	@Get("/visualizacao/{empresa.id}")
	public Empresa visualizacao(Empresa empresa) {
		return daoEmpresa.getById(empresa.getId());
	}

	@Post("/empresa/cadastrar/")
	public void cadastrar(final Empresa empresa, final UploadedFile arquivo) {
		validator.checking(new Validations() {
			{
				that(Validador.verificaCnpj(empresa.getCnpj()), "empresa.cnpj",
						"cnpj.invalido");
				that(Validador.verificaNumeroEndereco(empresa),
						"empresa.endereco.numero", "numero.invalido");
				that(Validador.verificaNomeFantasia(empresa.getNomeFantasia()),
						"empresa.nomeFantasia", "nomeFantasia.obrigatorio");
				that(Validador.verificaCpfListaSocio(empresa.getSocios()),
						"empresa.socios", "cpf.invalido");
				that(Validador.verificaExtensaoArquivo(arquivo), "arquivo",
						"extensao.invalida");
			}
		});
		validator.onErrorRedirectTo(this).cadastro();

		empresa.retiraPontosTracosBarrasCnpjECpf();

		Arquivo arquivoParaUpload = new Arquivo(arquivo.getFile(), empresa);

		ClienteCloudinary clienteCloudinary = new ClienteCloudinary(
				arquivoParaUpload);

		if (clienteCloudinary.upload()) {
			empresa.setUrl(clienteCloudinary.getArquivo().getUrlArquivo());
			daoEmpresa.adiciona(empresa);
			result.include("mensagem", "Cadastro realizado com sucesso!");
			result.redirectTo(this).visualizacao(empresa);
		} else {
			result.include("erro",
					"Erro ao cadastrar, por favor tente novamente!");
			result.redirectTo(this).cadastro();
		}

	}

	@Post("/empresa/cadastrar/{empresa.id}")
	public void atualizar(final Empresa empresa, final UploadedFile arquivo) {
		validator.checking(new Validations() {
			{
				that(Validador.verificaCnpj(empresa.getCnpj()), "empresa.cnpj",
						"cnpj.invalido");
				that(Validador.verificaNumeroEndereco(empresa),
						"empresa.endereco.numero", "numero.invalido");
				that(Validador.verificaNomeFantasia(empresa.getNomeFantasia()),
						"empresa.nomeFantasia", "nomeFantasia.obrigatorio");
				that(Validador.verificaCpfListaSocio(empresa.getSocios()),
						"empresa.socios", "cpf.invalido");

				if (arquivo != null) {
					that(Validador.verificaExtensaoArquivo(arquivo), "arquivo",
							"extensao.invalida");
				}
			}
		});
		validator.onErrorRedirectTo(this).cadastro(empresa);

		if (arquivo != null) {

			Arquivo arquivoParaUpload = new Arquivo(arquivo.getFile(), empresa);

			ClienteCloudinary clienteCloudinary = new ClienteCloudinary(
					arquivoParaUpload);

			if (clienteCloudinary.atualiza(empresa.getUrl())) {
				empresa.setUrl(clienteCloudinary.getArquivo().getUrlArquivo());
			} else {
				result.include("erro",
						"Erro ao atualizar, por favor tente novamente!");
				result.redirectTo(this).cadastro(empresa);
			}

		}

		daoEmpresa.atualiza(empresa);
		result.include("mensagem", "Atualização realizada com sucesso!");
		result.redirectTo(this).visualizacao(empresa);
	}

}
