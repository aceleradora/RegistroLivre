package br.com.aceleradora.registrolivre.util.comparator;

import java.util.Comparator;

import br.com.aceleradora.registrolivre.model.Empresa;

public class RecenteComparator implements Comparator<Empresa> {
	@Override
	public int compare(Empresa empresa1, Empresa empresa2){
		return empresa1.getDataRegistro().compareTo(empresa2.getDataRegistro());
	}
}

