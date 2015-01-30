package br.com.aceleradora.registrolivre.dao;

import java.util.List;

import br.com.aceleradora.registrolivre.model.Empresa;

public interface IEmpresaDAO {

	public List<Empresa> getTodas();

	public List<String> getTodosCNPJ();

	public Empresa getById(long id);

	public List<Empresa> pesquisa(String textoParaBusca);

	public Long contaQuantidadeDeRegistros();

	public void salva(Empresa empresa);

	public List<String> getParaAutoCompletar(String textoDigitado);
	
	public List<String> getParaAutoCompletarSocio(String textoDigitado);

	public List<Empresa> pesquisaAvancadaEspecifica(Empresa empresa);

	public List<Empresa> pesquisaAvancadaAproximada(Empresa empresa);
	
	public void limpaSessao();
}