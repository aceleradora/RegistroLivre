package br.com.aceleradora.RegistroLivre.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import br.com.aceleradora.RegistroLivre.dao.Entidade;

@Entity
@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "geradorId", sequenceName = "empresa_sequence")
public class Empresa extends Entidade {
	@Column(nullable = false)
	private String cnpj;
	private String razaoSocial;
	@Column(nullable = false)
	private String nomeFantasia;
	private Date dataCriacaoEmpresa;
	private Date dataEmissaoDocumento;
	private Date dataRegistro;
	private String url;
	@Embedded
	private Endereco endereco;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "empresa_socios", joinColumns = { @JoinColumn(name = "empresa_id") }, inverseJoinColumns = { @JoinColumn(name = "socio_id") })
	private List<Socio> socios;
	@Transient
	private SimpleDateFormat formatadorDeData;
	
	public Empresa() {
		socios = new ArrayList<Socio>();
		formatadorDeData = new SimpleDateFormat("dd/MM/yyyy");
		formatadorDeData.setLenient(false);
		
		setDataRegistro(new Date());
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getDataCriacao() {
		try {
			return formatadorDeData.format(dataCriacaoEmpresa);
		} catch (Exception e) {
			return "";
		}
	}

	public void setDataCriacao(String dataCriacao){
		try {
			this.dataCriacaoEmpresa = formatadorDeData.parse(dataCriacao);
		} catch (Exception e) {
			this.dataCriacaoEmpresa = null;
		}
	}

	public String getDataEmissaoDocumento() {
		try {
			return formatadorDeData.format(dataEmissaoDocumento);
		} catch (Exception e) {
			return "";
		}
	}

	public void setDataEmissaoDocumento(String dataEmissaoDocumento){
		try {						
			this.dataEmissaoDocumento = formatadorDeData.parse(dataEmissaoDocumento);			
		} catch (Exception e) {			
			this.dataEmissaoDocumento = null;
		}
	}

	public List<Socio> getSocios() {
		return socios;
	}

	public void setSocios(List<Socio> socios) {
		this.socios = socios;
	}

	public String getDataRegistro() {
		try {
			return formatadorDeData.format(dataRegistro);
		} catch (Exception e) {
			return "";
		}
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}