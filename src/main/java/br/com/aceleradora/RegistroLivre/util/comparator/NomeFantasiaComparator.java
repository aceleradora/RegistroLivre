package br.com.aceleradora.RegistroLivre.util.comparator;

import java.util.Comparator;

import br.com.aceleradora.RegistroLivre.model.Empresa;

public class NomeFantasiaComparator implements Comparator<Empresa> {
	@Override
	public int compare(Empresa empresa1, Empresa empresa2){
		return empresa1.getNomeFantasia().compareToIgnoreCase(empresa2.getNomeFantasia());
	}
}

