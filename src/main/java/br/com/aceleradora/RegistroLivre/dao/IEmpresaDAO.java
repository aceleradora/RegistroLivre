package br.com.aceleradora.RegistroLivre.dao;

import java.util.List;

import br.com.aceleradora.RegistroLivre.model.Empresa;

public interface IEmpresaDAO {
	
	public Empresa getById(long id);	
	public void adiciona(Empresa empresa);
	public void atualiza(Empresa empresa);
}
