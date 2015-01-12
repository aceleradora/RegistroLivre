package br.com.aceleradora.registrolivre.controller;

import java.util.List;

import br.com.aceleradora.registrolivre.dao.EmpresaDAO;
import br.com.aceleradora.registrolivre.model.Empresa;
import br.com.aceleradora.registrolivre.util.Arquivo;
import br.com.aceleradora.registrolivre.util.CalendarTransformer;
import br.com.aceleradora.registrolivre.util.ClienteCloudinary;
import br.com.aceleradora.registrolivre.util.DataOrdenadaTransformer;
import br.com.aceleradora.registrolivre.util.EnderecoTransformer;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.view.Results;
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
							.transform(new CalendarTransformer("yyyyMMdd"), "dataRegistro")
							.transform(new DataOrdenadaTransformer("dataEmissaoOrdenada", "dd/MM/yyyy", "yyyyMMdd"), "dataEmissaoDocumento")
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
	
	@Post("/buscaAvancada")
	public void buscaAvancada(Empresa empresa){
		List<Empresa> listaDeResultadosDeEmpresas = daoEmpresa.pesquisaAvancadaEspecifica(empresa);
		
		if (listaDeResultadosDeEmpresas.size() == 0){
			listaDeResultadosDeEmpresas = daoEmpresa.pesquisaAvancadaAproximada(empresa);
		}
		
		if (listaDeResultadosDeEmpresas.size() == 0){			
			result.redirectTo(HomeController.class).home();
		}
		
		result.forwardTo(this).listagem(listaDeResultadosDeEmpresas); 		
	}

	@Get("/visualizacao/{empresa.id}")
	public Empresa visualizacao(Empresa empresa) {
		return daoEmpresa.getById(empresa.getId());
	}

	@Post("/empresa/cadastrar/")
	public void cadastrar(Empresa empresa, UploadedFile arquivo) {
		empresa.setUrl(arquivo.getFileName());
		
		validar(empresa);
		validator.onErrorRedirectTo(this).cadastro();
		
		salvar(empresa, arquivo, false);
	}

	@Post("/empresa/cadastrar/{empresa.id}")
	public void atualizar(Empresa empresa, UploadedFile arquivo) {		
		if(arquivo !=null){
			empresa.setUrl(arquivo.getFileName());
		}
		
		
		validar(empresa);
		validator.onErrorRedirectTo(this).cadastro(empresa);
		
		salvar(empresa, arquivo, true);		
	}
	
	private void validar(Empresa empresa){
		validator.validate(empresa);
		validator.validate(empresa.getEndereco());
	}

	private void salvar(Empresa empresa, UploadedFile arquivo,	boolean alteracao) {
		
		empresa.retiraPontosTracosBarrasCnpjECpf();

		if (arquivo != null) {

			ClienteCloudinary clienteCloudinary = criaSessaoCloudnary(empresa, arquivo);

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
			empresa.setUrl(clienteCloudinary.getArquivo()
					.getUrlArquivo());
		} else {
			result.include("erro",
					"Erro ao salvar arquivo, por favor tente novamente!");
			result.redirectTo(this).cadastro();
		}
	}

	private void atualizaArquivo(final Empresa empresa,
			ClienteCloudinary clienteCloudinary) {
		if (clienteCloudinary.atualiza(empresa.getUrl())) {
			empresa.setUrl(clienteCloudinary.getArquivo()
					.getUrlArquivo());
		} else {
			result.include("erro",
					"Erro ao atualizar arquivo, por favor tente novamente!");
			result.redirectTo(this).cadastro();
		}
	}
	
	@Get()
	public void autoCompletar(String textoDigitado){
		List<String> empresas = daoEmpresa.getParaAutoCompletar(textoDigitado);
		
		result.use(Results.json()).from(empresas).serialize();
	}
	
}