package br.com.aceleradora.registrolivre.model;

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

import br.com.aceleradora.registrolivre.controller.HomeController;
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
				if (socio.getCpf() == null || socio.getNome() == null) {
					temSocios = true;
				}
			}
		}

		if (!temSocios
			&& cnpj == null
			&& nomeFantasia == null
			&& razaoSocial == null
			&& endereco.getUf() == null
			&& endereco.getCidade() == null
			&& endereco.getLogradouro() == null) {
				return false;
		}
		
		return true;
	}
}
