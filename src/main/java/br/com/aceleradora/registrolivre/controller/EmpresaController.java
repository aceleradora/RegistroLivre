package br.com.aceleradora.registrolivre.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.aceleradora.registrolivre.dao.IEmpresaDAO;
import br.com.aceleradora.registrolivre.model.Email;
import br.com.aceleradora.registrolivre.model.Empresa;
import br.com.aceleradora.registrolivre.util.Arquivo;
import br.com.aceleradora.registrolivre.util.CalendarTransformador;
import br.com.aceleradora.registrolivre.util.ClienteCloudinary;
import br.com.aceleradora.registrolivre.util.DataOrdenadaTransformador;
import br.com.aceleradora.registrolivre.util.EmissorDeEmail;
import br.com.aceleradora.registrolivre.util.EnderecoTransformador;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.view.Results;
import flexjson.JSONSerializer;

@Resource
public class EmpresaController {
	private IEmpresaDAO daoEmpresa;
	private Result result;
	private Validator validator;

	public EmpresaController(IEmpresaDAO dao, Result result, Validator validator) {
		this.daoEmpresa = dao;
		this.result = result;
		this.validator = validator;
	}

	@Get("/cadastro")
	public void cadastro() {
	}

	@Get("/atualizar/{empresa.id}")
	public Empresa cadastro(Empresa empresa) {
		empresa = daoEmpresa.getById(empresa.getId());
		result.include("editar", true);
		result.include("nomeArquivoAntigo", empresa.getUrl().substring(61));
		return empresa;
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
							.include("dataRegistro")
							.include("nomeFantasia")
							.include("endereco.logradouro")
							.include("dataEmissaoDocumento")
							.exclude("*")
							.transform(new CalendarTransformador("yyyyMMdd"),
									"dataRegistro")
							.transform(
									new DataOrdenadaTransformador(
											"dataEmissaoOrdenada",
											"dd/MM/yyyy", "yyyyMMdd"),
									"dataEmissaoDocumento")
							.transform(new EnderecoTransformador(), "endereco")
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
			List<Empresa> listaDeResultadosDeEmpresas = daoEmpresa.pesquisa(busca);
			result.forwardTo(this).listagem(listaDeResultadosDeEmpresas);
		} else {
			result.include("buscaVazia", true);
			result.redirectTo(HomeController.class).home();
		}
	}

	@Post("/buscaAvancada")
	public void buscaAvancada(Empresa empresa) {
		if (!empresa.contemDados()) {
			result.include("buscaVazia", true);
			result.redirectTo(HomeController.class).home();
		} else {
			List<Empresa> listaDeResultadosDeEmpresas = daoEmpresa.pesquisaAvancadaEspecifica(empresa);

			if (listaDeResultadosDeEmpresas.size() == 0) {
				result.include("buscaAproximada", true);
				listaDeResultadosDeEmpresas = daoEmpresa.pesquisaAvancadaAproximada(empresa);
			}

			result.redirectTo(this).listagem(listaDeResultadosDeEmpresas);
		}
	}

	@Get("/visualizacao/{empresa.id}")
	public Empresa visualizacao(Empresa empresa) {
		return daoEmpresa.getById(empresa.getId());
	}

	@Post("/empresa/cadastrar/")
	public void cadastrar(Empresa empresa, UploadedFile arquivo) {
		validar(empresa);
		validator.onErrorRedirectTo(this).cadastro();

		empresa.setUrl(arquivo.getFileName());

		salvar(empresa, arquivo, false);
	}

	@Post("/empresa/cadastrar/{empresa.id}")
	public void atualizar(Empresa empresa, UploadedFile arquivo) {
		if (arquivo != null) {
			empresa.setUrl(arquivo.getFileName());
		}

		validar(empresa);
		validator.onErrorRedirectTo(this).cadastro(empresa);

		salvar(empresa, arquivo, true);
	}

	private void validar(Empresa empresa) {
		validator.validate(empresa);
		validator.validate(empresa.getEndereco());
	}

	private void salvar(Empresa empresa, UploadedFile arquivo, boolean alteracao) {
		empresa.retiraPontosTracosBarrasCnpjECpf();
		if (empresa.cnpjJaExistente(daoEmpresa.getTodosCNPJ()) && !alteracao) {
			result.include("erro",
					"CNPJ Já Existente!");
			result.include(empresa);
			result.redirectTo(this).cadastro();
		} else {
			if(alteracao){
				enviaMensagemDeAlteracao(empresa);
			}
			if (arquivo != null) {

				ClienteCloudinary clienteCloudinary = criaSessaoCloudnary(
						empresa, arquivo);

				if (alteracao) {
					atualizaArquivo(empresa, clienteCloudinary);
				} else {
					uploadArquivo(empresa, clienteCloudinary);
				}
			}

			daoEmpresa.salva(empresa);
			String mensagem = (alteracao) ? "Atualização realizada com sucesso!"
					: "Cadastro realizado com sucesso!";

			result.include("mensagem", mensagem);
			result.redirectTo(this).visualizacao(empresa);
		}
	}

	private void enviaMensagemDeAlteracao(Empresa empresa) {
		Empresa antigaEmpresa = daoEmpresa.getById(empresa.getId());
		Empresa novaEmpresa = empresa;
		String mensagemAlterecaoDeEmpresa = "";
		mensagemAlterecaoDeEmpresa += antigaEmpresa.trazDadosDaEmpresa(true) +
									  "\n\n\n" + novaEmpresa.trazDadosDaEmpresa(false);
		
		daoEmpresa.limpaSessao();
		
		Email email = new Email("Registro Livre", 
								"registrolivreaceleradora@gmail.com", 
								"Alteração da Empresa " + empresa.getId(), 
								mensagemAlterecaoDeEmpresa);

		EmissorDeEmail emissor = new EmissorDeEmail(); 
		emissor.enviar(email, "Alteração");
	}

	private ClienteCloudinary criaSessaoCloudnary(final Empresa empresa,
			final UploadedFile arquivo) {
		Arquivo arquivoParaUpload = new Arquivo(arquivo.getFile(), empresa);

		ClienteCloudinary clienteCloudinary = new ClienteCloudinary(
				arquivoParaUpload);
		return clienteCloudinary;
	}

	private void uploadArquivo(final Empresa empresa,
			ClienteCloudinary clienteCloudinary) {
		if (clienteCloudinary.upload()) {
			empresa.setUrl(clienteCloudinary.getArquivo().getUrlArquivo());
		} else {
			result.include("erro",
					"Erro ao salvar arquivo, por favor tente novamente!");
			result.include(empresa);
			result.redirectTo(this).cadastro();
		}
	}

	private void atualizaArquivo(final Empresa empresa,
			ClienteCloudinary clienteCloudinary) {
		if (clienteCloudinary.atualiza(empresa.getUrl())) {
			empresa.setUrl(clienteCloudinary.getArquivo().getUrlArquivo());
		} else {
			result.include("erro",
					"Erro ao atualizar arquivo, por favor tente novamente!");
			result.redirectTo(this).cadastro(empresa);
		}
	}

	@Get
	public void autoCompletar(String textoDigitado) {
		List<String> empresas = daoEmpresa.getParaAutoCompletar(textoDigitado);

		result.use(Results.json()).from(empresas).serialize();
	}

	@Get
	public void autoCompletarSocio(String textoDigitado) {
		List<String> socios = daoEmpresa
				.getParaAutoCompletarSocio(textoDigitado);

		result.use(Results.json()).from(socios).serialize();
	}
	
	@Get
	public void download(long... ids){
		
		Collection<Long> idDaUrl = new ArrayList<Long>();
		
//		for (Long id : ids) {
//			teste.add(id);
//			System.out.println(id);
//		}
		List<String> urlDocumentos = daoEmpresa.getLinksDocumentos(idDaUrl);
		
		
	}

	@Get
	public void cnpjUnico(String cnpjDigitado) {
		result.use(Results.json()).from(!daoEmpresa.getTodosCNPJ().contains(cnpjDigitado)).serialize();
	}
}