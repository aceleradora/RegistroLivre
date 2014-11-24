package br.com.aceleradora.RegistroLivre.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;

@Resource
public class HomeController {
	@Get("/")
	public void home() {
	}
}