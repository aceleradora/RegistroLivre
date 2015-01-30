package br.com.aceleradora.registrolivre.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang.StringEscapeUtils;

import br.com.aceleradora.registrolivre.validador.annotations.CNPJValido;
import br.com.aceleradora.registrolivre.validador.annotations.NomeFantasiaValido;

@Entity
public class Empresa {
	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "geradorId", sequenceName = "empresa_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "geradorId")
	private long id;
	@Column(nullable = false)
	@CNPJValido
	private String cnpj;
	private String razaoSocial;
	@Column(nullable = false)
	@NomeFantasiaValido
	private String nomeFantasia;
	private Calendar dataCriacao;
	private Calendar dataEmissaoDocumento;
	private Calendar dataRegistro;
	@Pattern(regexp = ".+\\.pdf", message = "Somente arquivos com a extensão .pdf podem ser cadastrados.")
	private String url;
	@Embedded
	private Endereco endereco;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "empresa_socios", joinColumns = { @JoinColumn(name = "empresa_id") }, inverseJoinColumns = { @JoinColumn(name = "socio_id") })
	private List<Socio> socios;

	public Empresa() {
		socios = new ArrayList<Socio>();
		setDataRegistro(Calendar.getInstance());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCnpj() {
		return StringEscapeUtils.escapeHtml(this.cnpj);
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaoSocial() {
		return StringEscapeUtils.escapeHtml(this.razaoSocial);
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return StringEscapeUtils.escapeHtml(this.nomeFantasia);
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Calendar getDataEmissaoDocumento() {
		return dataEmissaoDocumento;
	}

	public void setDataEmissaoDocumento(Calendar dataEmissaoDocumento) {
		this.dataEmissaoDocumento = dataEmissaoDocumento;
	}

	public Calendar getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Calendar dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Socio> getSocios() {
		return socios;
	}

	public void setSocios(List<Socio> socios) {
		this.socios = socios;
	}

	public void retiraPontosTracosBarrasCnpjECpf() {
		if (this.cnpj != null) {
			String cnpjSemPontoTraco = retiraPontosTracos(this.cnpj);
			this.cnpj = cnpjSemPontoTraco;
		}

		String cpfSemPontoTraco = "";

		if (socios != null) {
			for (Socio socio : socios) {
				if (socio.getCpf() != null) {
					cpfSemPontoTraco = retiraPontosTracos(socio.getCpf());
					socio.setCpf(cpfSemPontoTraco);
				}
			}
		}
	}

	public String retiraPontosTracos(String stringComPontoTraco) {
		String stringSemPontoTraco = stringComPontoTraco
				.replaceAll("[/.-]", "");

		return stringSemPontoTraco;
	}

	public boolean contemDados() {
		boolean temSocios = false;

		if (socios.size() > 0) {
			for (Socio socio : socios) {
				if (socio.getCpf() != null || socio.getNome() != null) {
					temSocios = true;
				}
			}
		}

		if (temSocios 
			|| cnpj != null 
			|| nomeFantasia != null
			|| razaoSocial != null 
			|| (endereco != null 
				&& (endereco.getUf() != null 
				 || endereco.getCidade() != null 
				 || endereco.getLogradouro() != null)
				)
			) {
				return true;
		}else{
			return false;
		}
	}
	
	public boolean cnpjJaExistente(List<String> listaDeCnpj){
		return listaDeCnpj.contains(getCnpj());
	}

	public String trazDadosDaEmpresa(boolean antigo) {
		String dados = "";
		
		String data = formataData(this.dataRegistro);
		
		if(antigo){
			dados += "<b>Data do Registro Antigo:</b> " + data + "<br/>";
		} else {
			dados += "<br/><b>Data do Registro Novo:</b> " + data + "<br/>";
		}
		
		dados += "<b>CNPJ:</b> " + this.cnpj + "<br/>";
		dados += "<b>Nome Fantasia:</b> " + this.nomeFantasia + "<br/>";
		
		if(this.razaoSocial != null){
			dados += "<b>Razão Social:</b> " + this.razaoSocial + "<br/>"; 
		}
		if(this.dataCriacao != null){
			dados += "<b>Data Criação:</b> " + formataData(this.dataCriacao) + "<br/>";
		}
		if(this.dataEmissaoDocumento != null){
			dados += "<b>Data Emissão do Documento:</b> " + formataData(this.dataEmissaoDocumento) + "<br/>";
		}
		if(this.url != null){
			dados += "<b>Nome do Documento:</b> " + this.url.substring(61) + "<br/>";
		}
		if(this.endereco != null){
			if(this.endereco.getLogradouro() != null){
				dados += "<b>Logradouro:</b> " + this.endereco.getLogradouro() + "<br/>";
			}
			if(this.endereco.getComplemento() != null){
				dados += "<b>Complemento:</b> " + this.endereco.getComplemento() + "<br/>";
			}
			if(this.endereco.getNumero() != null){
				dados += "<b>Numero:</b> " + this.endereco.getNumero() + "<br/>";
			}
			if(this.endereco.getCep() != null){
				dados += "<b>CEP:</b> " + this.endereco.getCep() + "<br/>";
			}
			if(this.endereco.getUf() != null){
				dados += "<b>Estado:</b> " + this.endereco.getUf() + "<br/>";
			}
			if(this.endereco.getCidade() != null){
				dados += "<b>Cidade:</b> " + this.endereco.getCidade() + "<br/>";
			}
		}
		if (socios.size() > 0) {
			for (Socio socio : socios) {
				if (socio.getCpf() != null || socio.getNome() != null) {
					dados += "<b>Nome do Sócio:</b> " + socio.getNome() + "<br/>" + 
								"<b>CPF:</b> " + socio.getCpf() + "<br/>";				
					}
			}
		}
		return dados;
	}

	public String formataData(Calendar data) {
		SimpleDateFormat formatacaoDataETempo = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String dataFormatada = formatacaoDataETempo.format(data.getTime());
		return dataFormatada;
	}
}
