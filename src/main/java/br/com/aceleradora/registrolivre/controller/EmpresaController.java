package br.com.aceleradora.registrolivre.controller;

import java.util.List;

import br.com.aceleradora.registrolivre.dao.EmpresaDAO;
import br.com.aceleradora.registrolivre.model.Empresa;
import br.com.aceleradora.registrolivre.model.Validador;
import br.com.aceleradora.registrolivre.util.Arquivo;
import br.com.aceleradora.registrolivre.util.ClienteCloudinary;
import br.com.aceleradora.registrolivre.util.DataOrdenadaTransformer;
import br.com.aceleradora.registrolivre.util.EnderecoTransformer;
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

	public void listagem(List<Empresa> listaDeEmpresas) {
		if (listaDeEmpresas.size() == 0) {
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
							.transform(new DataOrdenadaTransformer("dataOrdenada", "dd/MM/yyyy", "yyyyMMdd"), "dataEmissaoDocumento")
							.transform(new EnderecoTransformer(), "endereco")
							.serialize(listaDeEmpresas));
		}
	}

	@Get("/listagem")
	public void listarTodos() {
		List<Empresa> listaDeTodasEmpresas = daoEmpresa.getTodas();
		result.forwardTo(this).listagem(listaDeTodasEmpresas);
	}

	@Get("/busca")
	public void busca(String busca) {
		if (busca != null) {
			List<Empresa> listaDeResultadosDeEmpresas = daoEmpresa
					.pesquisa(busca);
			result.forwardTo(this).listagem(listaDeResultadosDeEmpresas);
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
		salvar(empresa, arquivo, false);
	}

	@Post("/empresa/cadastrar/{empresa.id}")
	public void atualizar(final Empresa empresa, final UploadedFile arquivo) {
		salvar(empresa, arquivo, true);		
	}

	private void salvar(final Empresa empresa, final UploadedFile arquivo,
			final boolean alteracao) {
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
				if ((alteracao && arquivo != null)
						|| (!alteracao && arquivo == null)) {
					that(Validador.verificaExtensaoArquivo(arquivo), "arquivo",
							"extensao.invalida");
				}
			}
		});

		if (alteracao) {
			validator.onErrorRedirectTo(this).cadastro(empresa);
		} else {
			validator.onErrorRedirectTo(this).cadastro();
		}

		empresa.retiraPontosTracosBarrasCnpjECpf();

		if (arquivo != null) {

			Arquivo arquivoParaUpload = new Arquivo(arquivo.getFile(), empresa);

			ClienteCloudinary clienteCloudinary = new ClienteCloudinary(
					arquivoParaUpload);

			if (alteracao) {
				if (clienteCloudinary.atualiza(empresa.getUrl())) {
					empresa.setUrl(clienteCloudinary.getArquivo()
							.getUrlArquivo());
				} else {
					result.include("erro",
							"Erro ao atualizar arquivo, por favor tente novamente!");
					result.redirectTo(this).cadastro();
				}
			} else {
				if (clienteCloudinary.upload()) {
					empresa.setUrl(clienteCloudinary.getArquivo()
							.getUrlArquivo());
				} else {
					result.include("erro",
							"Erro ao salvar arquivo, por favor tente novamente!");
					result.redirectTo(this).cadastro();
				}
			}
		}

		daoEmpresa.salva(empresa);
		String mensagem = (alteracao) ? "Atualização realizada com sucesso!"
				: "Cadastro realizado com sucesso!";		

		result.include("mensagem", mensagem);
		result.redirectTo(this).visualizacao(empresa);
	}
}