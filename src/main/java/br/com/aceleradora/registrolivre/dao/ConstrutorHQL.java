package br.com.aceleradora.registrolivre.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

public class ConstrutorHQL {
	
	public static final String SELECT 		= "SELECT ";
	public static final String FROM 		= " FROM ";
	public static final String AS 			= " AS ";
	public static final String WHERE 		= " WHERE ";
	public static final String LIKE 		= " LIKE ";
	public static final String AND 			= " AND ";
	public static final String OR 			= " OR ";
	public static final String ORDER_BY		= " ORDER BY ";
	public static final String ASC			= " ASC ";
	public static final String DESC			= " DESC ";
	public static final String JOIN 		= " JOIN ";
	public static final String LEFT_JOIN 	= " LEFT JOIN ";
	public static final String RIGHT_JOIN 	= " RIGHT JOIN ";
	public static final String INNER_JOIN 	= " INNER JOIN ";	
	public static final String EQUALS 		= " = ";
	public static final String DISTINCT     = "DISTINCT ";
	public static final String ANY 			= "%";
	
	
	private StringBuilder sintaxe;
	private Map<String, Object> parametros;
	
	public ConstrutorHQL(String hql){
		sintaxe = new StringBuilder(hql);
	}
	
	public ConstrutorHQL(){
		sintaxe = new StringBuilder();
	}
	
	public Map<String, Object> getParametros(){
		return parametros;
	}
	
	public void select(String parametro){
		sintaxe.append(SELECT + parametro);
	}
	
	public void from(String parametro){
		sintaxe.append(FROM + parametro);
	}
	
	public void as(String parametro){
		sintaxe.append(AS + parametro);
	}
	
	public void where(){
		sintaxe.append(WHERE);
	}
	
	public void like(String parametro, String valor){
		sintaxe.append(parametro + LIKE + valor);
	}
	
	public void orderBy(String parametro, String ordem){
		sintaxe.append(ORDER_BY + parametro + ordem);
	}
	
	public void or(){
		sintaxe.append(OR);
	}
	
	public void and(){
		sintaxe.append(AND);
	}
	
	public void leftJoin(String parametro){
		sintaxe.append(LEFT_JOIN + parametro);
	}
	
	public void rightJoin(String parametro){
		sintaxe.append(RIGHT_JOIN + parametro);
	}
	
	public void innerJoin(String parametro){
		sintaxe.append(INNER_JOIN + parametro);
	}
	
	public void join(String parametro){
		sintaxe.append(JOIN + parametro);
	}
	
	public void equals(String primeiroValor, String segundoValor){
		sintaxe.append(primeiroValor + EQUALS + segundoValor);
	}
	
	public String lower(String parametro){
		return "lower("+parametro+")";
	}
	
	public String unaccent(String parametro){
		return "unaccent("+parametro+")";
	}
	
	public String circundarCom(String coringa, String valor){
		return coringa + valor + coringa;
	}
	
	public Query construir(Session sessao){
		Query queryHql = sessao.createQuery(sintaxe.toString());		
		
		if(parametros != null){
			queryHql = setarParametros(queryHql);
		}
		
		return queryHql;
	}
	
	private Query setarParametros(Query queryHql){		
		
		Iterator iteradorParametros = parametros.entrySet().iterator();
		while(iteradorParametros.hasNext()){
			Map.Entry valores = (Map.Entry) iteradorParametros.next();
			System.out.println(valores.getKey().toString() + " = " + valores.getValue());
			queryHql.setParameter(valores.getKey().toString(), valores.getValue());			
			iteradorParametros.remove();
		}
		
		return queryHql;
	}
	
	public void adicionarParametro(String nome, Object valor){
		if(parametros == null){
			parametros = new HashMap<String, Object>();
		}
		
		parametros.put(nome, valor);		
	}	
	
	@Override
	public String toString(){
		return sintaxe.toString();
	}
}