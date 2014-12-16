package br.com.aceleradora.registrolivre.dao;

import br.com.aceleradora.registrolivre.model.Empresa;

public interface IEmpresaDAO {
	public Empresa getById(long id);	
	public void adiciona(Empresa empresa);
	public void atualiza(Empresa empresa);
}
