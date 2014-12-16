package br.com.aceleradora.registrolivre.controller;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.aceleradora.registrolivre.dao.EmpresaDAO;
import br.com.aceleradora.registrolivre.model.Empresa;
import br.com.aceleradora.registrolivre.model.Validador;
import br.com.aceleradora.registrolivre.util.Arquivo;
import br.com.aceleradora.registrolivre.util.CalendarTransformer;
import br.com.aceleradora.registrolivre.util.ClienteCloudinary;
import br.com.aceleradora.registrolivre.util.Paginador;
import br.com.aceleradora.registrolivre.util.comparator.CnpjComparator;
import br.com.aceleradora.registrolivre.util.comparator.NomeFantasiaComparator;
import br.com.aceleradora.registrolivre.util.comparator.RecenteComparator;
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
	private Paginador paginador;

	public EmpresaController(EmpresaDAO dao, Result result,
			Validator validator, Paginador paginador) {
		this.daoEmpresa = dao;
		this.result = result;
		this.validator = validator;
		this.paginador = paginador;
	}

	@Get("/cadastro")
	public void cadastro() {
	}

	@Get("/atualizar/{empresa.id}")
	public Empresa cadastro(Empresa empresa) {
		result.include("editar", true);
		return daoEmpresa.getById(empresa.getId());
	}

	@Get("/listagem")
	public void listagem() {
	}

	@Get("/ordenacao/{tipo}/{ordem}")
	public void listaOrdenada(String tipo, String ordem) {
		Comparator<Empresa> comparador = null;
		if (tipo.equals("nomeFantasia")) {
			comparador = new NomeFantasiaComparator();
		} else if (tipo.equals("cnpj")) {
			comparador = new CnpjComparator();
		} else if (tipo.equals("recentes")) {
			comparador = new RecenteComparator();
		}
		if (ordem.equals("decrescente")) {
			Collections.sort(paginador.getListaEmpresas(),
					Collections.reverseOrder(comparador));
		} else {
			Collections.sort(paginador.getListaEmpresas(), comparador);
		}
		result.redirectTo(this).listagem();
	}

	@Get("/busca")
	public void busca(String busca) {
		if (busca == null) {
			result.redirectTo(HomeController.class).home();
		}
		
		busca = busca.replaceAll("[./-]", "");

		List<Empresa> listaDeResultadosDeEmpresas = daoEmpresa.pesquisa(busca);

		if (listaDeResultadosDeEmpresas.size() == 0) {
			result.include("listaDeResultadosDeEmpresasVazia", true);
			result.redirectTo(HomeController.class).home();
		} else {
			result.include(
					"resultadoBusca",
					new JSONSerializer().include("nomeFantasia")
							.include("endereco.logradouro")
							.include("dataEmissaoDocumento").exclude("*")
							.transform(new CalendarTransformer("dd/MM/yyyy"), Calendar.class)
							.serialize(listaDeResultadosDeEmpresas));

			result.redirectTo(this).listagem();
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
