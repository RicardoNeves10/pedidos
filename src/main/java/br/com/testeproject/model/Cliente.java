package br.com.testeproject.model;

import java.io.Serializable;
import java.text.Collator;
import java.util.Date;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.testeproject.abstracts.AbstractJSF;

@Entity
@Table(name="clientes")
public class Cliente extends AbstractJSF implements Serializable, Comparable<Cliente>{

	private static final long serialVersionUID = 3012597861711578216L;

	@Column(name="nome", length = 80)
	private String nome;
	
	@Column(name = "sexo", length = 10, nullable = false)
	private String sexo;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dataNascimento", length = 10, nullable = false)
	private Date dataNascimento;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "idEndereco", nullable = false ,foreignKey = @ForeignKey(name = "fk_cliente_endereco"))
	private Endereco endereco;
	
	public Cliente() {
		endereco = new Endereco();
	}

	public Cliente(String nome, String sexo, Date dataNascimento, Endereco endereco) {
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.endereco = endereco;
	}

	public final String getNome() {
		return nome;
	}

	public final void setNome(String nome) {
		this.nome = nome;
	}

	public final Date getDataNascimento() {
		return dataNascimento;
	}

	public final void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public final Endereco getEndereco() {
		return endereco;
	}

	public final void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public final String getSexo() {
		return sexo;
	}

	public final void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sexo == null) {
			if (other.sexo != null)
				return false;
		} else if (!sexo.equals(other.sexo))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cliente [nome=");
		builder.append(nome);
		builder.append(", sexo=");
		builder.append(sexo);
		builder.append(", dataNascimento=");
		builder.append(dataNascimento);
		builder.append(", endereco=");
		builder.append(endereco);
		builder.append(", toString()=");
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(Cliente clienteComparar) {
		final Collator colator = Collator.getInstance(new Locale("pt", "BR"));

		return colator.compare(this.getNome(), clienteComparar.getNome());
	}
	
}
