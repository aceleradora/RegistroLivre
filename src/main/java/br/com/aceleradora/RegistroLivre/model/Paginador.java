package br.com.aceleradora.RegistroLivre.model;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@SessionScoped
@Component
public class Paginador {
	private final int ELEMETOS_POR_PAGINA = 20;
	private List<Empresa> listaEmpresas;

	public Paginador() {
	}

	public void setListaEmpresas(List<Empresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}
	
	public int getListaSize(){
		return listaEmpresas.size();
	}

	public List<Empresa> getPagina(int pagina) {
		if (listaEmpresas == null) {
			return new ArrayList<Empresa>();
		}

		int indexPrimeiroElemento = (pagina - 1) * ELEMETOS_POR_PAGINA;
		int indexUltimoElemento;

		if (pagina * ELEMETOS_POR_PAGINA > listaEmpresas.size()) {
			indexUltimoElemento = listaEmpresas.size();
		} else {
			indexUltimoElemento = pagina * ELEMETOS_POR_PAGINA;
		}
		if (indexPrimeiroElemento < indexUltimoElemento) {
			return listaEmpresas.subList(indexPrimeiroElemento,
					indexUltimoElemento);
		} else {
			return new ArrayList<Empresa>();
		}
	}
}
