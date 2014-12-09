package br.com.aceleradora.RegistroLivre.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.aceleradora.RegistroLivre.dao.EmpresaDAO;
import br.com.aceleradora.RegistroLivre.model.Empresa;
import br.com.aceleradora.RegistroLivre.model.Paginador;
import br.com.aceleradora.RegistroLivre.model.Validador;
import br.com.aceleradora.RegistroLivre.util.Arquivo;
import br.com.aceleradora.RegistroLivre.util.ClienteCloudinary;
import br.com.aceleradora.RegistroLivre.util.comparator.*;
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

	@Get("/listagem/{pagina}")
	public List<Empresa> listagem(int pagina) {
		result.include("totalDeRegistros", paginador.getListaSize());
		return paginador.getPagina(pagina);
	}

	@Get("/listagem")
	public void listagem() {
		paginador.setListaEmpresas(daoEmpresa.getTodas());
		result.redirectTo(this).listagem(1);
	}

	@Get("/ordenacao/{tipo}/{ordem}")
	public void listaOrdenada(String tipo, String ordem) {
		Comparator<Empresa> comparador = null;
		if(tipo.equals("nomeFantasia")){
			comparador = new NomeFantasiaComparator();
		}else if(tipo.equals("cnpj")){
			comparador = new CnpjComparator();
		}else if(tipo.equals("recentes")){
			comparador = new RecenteComparator();
		}
		Collections.sort(paginador.getListaEmpresas(), comparador);
		result.redirectTo(this).listagem(1);
	}

	@Get("/busca")
	public void busca(String q) {
		if(q == null){
			result.redirectTo(HomeController.class).home();
		}
		List<Empresa> listaDeResultadosDeEmpresas = daoEmpresa.pesquisa(q);
		if (listaDeResultadosDeEmpresas.size() == 0) {
			result.include("listaDeResultadosDeEmpresasVazia", true);
			result.redirectTo(HomeController.class).home();
		} else {
			paginador.setListaEmpresas(listaDeResultadosDeEmpresas);
			result.redirectTo(this).listagem(1);
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
		validator.onErrorUsePageOf(this).cadastro();

		String nomeArquivo = empresa.getNomeFantasia().replace(' ', '_') + "_"
				+ empresa.getCnpj();

		Arquivo arquivoParaUpload = new Arquivo(arquivo.getFile(), nomeArquivo);

		ClienteCloudinary clienteCloudinary = new ClienteCloudinary(
				arquivoParaUpload);

		if (clienteCloudinary.upload()) {
			empresa.setUrl(clienteCloudinary.getArquivo().getUrlArquivo());
			daoEmpresa.adiciona(empresa);
			result.include("mensagem", "Cadastro realizado com sucesso!");
			result.redirectTo(this).visualizacao(empresa);
		} else {
			result.include("erro",
					"Erro ao atualizar, por favor tente novamente!");
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
		validator.onErrorUsePageOf(this).cadastro();

		if (arquivo != null) {
			String nomeArquivo = empresa.getNomeFantasia().replace(' ', '_')
					+ "_" + empresa.getCnpj();

			Arquivo arquivoParaUpload = new Arquivo(arquivo.getFile(),
					nomeArquivo);

			ClienteCloudinary clienteCloudinary = new ClienteCloudinary(
					arquivoParaUpload);

			if (clienteCloudinary.atualiza(empresa.getUrl())) {
				empresa.setUrl(clienteCloudinary.getArquivo().getUrlArquivo());
			} else {
				result.include("erro",
						"Erro ao atualizar, por favor tente novamente!");
				result.redirectTo(this).cadastro();
			}
		
		}
		
		daoEmpresa.atualiza(empresa);
		result.include("mensagem", "Atualização realizada com sucesso!");
		result.redirectTo(this).visualizacao(empresa);
	}
}
