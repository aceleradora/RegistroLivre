//package br.com.aceleradora.RegistroLivre.test;
//
//import static org.junit.Assert.*;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import br.com.aceleradora.RegistroLivre.dao.EmpresaDAO;
//import br.com.aceleradora.RegistroLivre.model.Empresa;
//
//public class EmpresaDAOTest {
//	private EmpresaDAO empresaDao;
//	
//	@Before
//	public void setUp(){
//		empresaDao = new EmpresaDAO();
//	}
//	
//	@Test
//	public void retornaEmpresaQuandoForBuscada(){
//		Empresa empresaTest = new Empresa();
//		empresaTest.setCnpj("123");
//		
//		empresaDao.getTodas(empresaTest);
//		
//		assertEquals(empresaTest);
//	}
//}
