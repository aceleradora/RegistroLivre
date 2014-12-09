package br.com.aceleradora.RegistroLivre.util.comparator;

import java.util.Comparator;

import br.com.aceleradora.RegistroLivre.model.Empresa;

public class CnpjComparator implements Comparator<Empresa> {
	@Override
	public int compare(Empresa empresa1, Empresa empresa2){
		return empresa1.getCnpj().compareToIgnoreCase(empresa2.getCnpj());
	}
}

