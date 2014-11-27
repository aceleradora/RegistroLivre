package br.com.aceleradora.RegistroLivre.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.aceleradora.RegistroLivre.dao.EmpresaDAO;
import br.com.aceleradora.RegistroLivre.model.Empresa;
import br.com.aceleradora.RegistroLivre.model.Validador;
import br.com.aceleradora.RegistroLivre.util.Arquivo;
import br.com.aceleradora.RegistroLivre.util.ClienteCloudinary;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.validator.Validations;

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

	@Get("/listagem")
	public List<Empresa> listagem() {
		result.include("totalDeRegistros",
				daoEmpresa.contaQuantidadeDeRegistros());
		return daoEmpresa.getTodas();
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
		validator.onErrorUsePageOf(this).cadastro();

		Arquivo arquivoParaUpload = new Arquivo(arquivo.getFile(),
				empresa.getCnpj());
		ClienteCloudinary clienteCloudinary = new ClienteCloudinary(
				arquivoParaUpload);

		if (clienteCloudinary.upload()) {
			empresa.setUrl(clienteCloudinary.getArquivo().getUrlArquivo());
			daoEmpresa.adiciona(empresa);
			result.include("mensagem", "Cadastro realizado com sucesso!");
			result.redirectTo(this).visualizacao(empresa);
		} else {

			ArrayList<String> listaErros = new ArrayList<String>();
			listaErros.add("Erro ao cadastrar, por favor tente novamente!");

			result.include(listaErros);
			result.redirectTo(this).cadastro();
		}

	}

	public void atualizar(final Empresa empresa) {
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

			}
		});
		validator.onErrorUsePageOf(this).cadastro();

		daoEmpresa.atualiza(empresa);
		result.include("mensagem", "Atualização realizada com sucesso!");
		result.redirectTo(this).visualizacao(empresa);
	}

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
				that(Validador.verificaExtensaoArquivo(arquivo), "arquivo",
						"extensao.invalida");
			}
		});
		validator.onErrorUsePageOf(this).cadastro();

		Arquivo arquivoParaUpload = new Arquivo(arquivo.getFile(),
				empresa.getCnpj());

		ClienteCloudinary clienteCloudinary = new ClienteCloudinary(
				arquivoParaUpload);

		if (clienteCloudinary.atualiza(empresa.getUrl())) {
			empresa.setUrl(clienteCloudinary.getArquivo().getUrlArquivo());
			daoEmpresa.atualiza(empresa);
			result.include("mensagem", "Atualização realizada com sucesso!");
			result.redirectTo(this).visualizacao(empresa);
		} else {
			ArrayList<String> listaErros = new ArrayList<String>();
			listaErros.add("Erro ao atualizar, por favor tente novamente!");

			result.include(listaErros);
			result.redirectTo(this).cadastro();
		}
	}

	@Post("/empresa/cadastrar/{empresa.id}")
	public void atualizaTeste(Empresa empresa, UploadedFile arquivo) {
		if (arquivo != null) {
			atualizar(empresa, arquivo);
		} else {
			atualizar(empresa);
		}
	}
}