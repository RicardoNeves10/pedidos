package br.com.testeproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.testeproject.abstracts.AbstractJSF;
import br.com.testeproject.utilities.Utilities;

@Entity
@Table( name = "enderecos", uniqueConstraints = @UniqueConstraint( columnNames = { "logradouro", "numero", "bairro", "cidade", "cep", "uf" } ) )
public class Endereco extends AbstractJSF {

	@Column( name = "logradouro", length = 60, nullable = false )
	private String logradouro;

	@Column( name = "numero", length = 4, nullable = false )
	private String numero;

	@Column( name = "complemento", length = 60, nullable = true )
	private String complemento;

	@Column( name = "bairro", length = 30, nullable = false )
	private String bairro;

	@Column( name = "cidade", length = 30, nullable = false )
	private String cidade;

	@Column( name = "cep", length = 11, nullable = false )
	private String cep;

	@Column( name = "uf", length = 2, nullable = false )
	private String uf;
	
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro( String logradouro ) {
		this.logradouro = Utilities.normalizeString( logradouro );
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero( String numero ) {
		this.numero = Utilities.normalizeString( numero );
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento( String complemento ) {
		this.complemento = Utilities.normalizeString( complemento );
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro( String bairro ) {
		this.bairro = Utilities.normalizeString( bairro );
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade( String cidade ) {
		this.cidade = Utilities.normalizeString( cidade );
	}

	public String getCep() {
		return cep;
	}

	public void setCep( String cep ) {
		this.cep = Utilities.normalizeString( cep );
	}

	public String getUf() {
		return uf;
	}

	public void setUf( String uf ) {
		this.uf = Utilities.normalizeString( uf );
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( bairro == null ) ? 0 : bairro.hashCode() );
		result = prime * result + ( ( cep == null ) ? 0 : cep.hashCode() );
		result = prime * result + ( ( cidade == null ) ? 0 : cidade.hashCode() );
		result = prime * result + ( ( complemento == null ) ? 0 : complemento.hashCode() );
		result = prime * result + ( ( logradouro == null ) ? 0 : logradouro.hashCode() );
		result = prime * result + ( ( numero == null ) ? 0 : numero.hashCode() );
		result = prime * result + ( ( uf == null ) ? 0 : uf.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( !( obj instanceof Endereco ) ) {
			return false;
		}
		Endereco other = (Endereco) obj;
		if ( bairro == null ) {
			if ( other.bairro != null ) {
				return false;
			}
		} else if ( !bairro.equals( other.bairro ) ) {
			return false;
		}
		if ( cep == null ) {
			if ( other.cep != null ) {
				return false;
			}
		} else if ( !cep.equals( other.cep ) ) {
			return false;
		}
		if ( cidade == null ) {
			if ( other.cidade != null ) {
				return false;
			}
		} else if ( !cidade.equals( other.cidade ) ) {
			return false;
		}
		if ( complemento == null ) {
			if ( other.complemento != null ) {
				return false;
			}
		} else if ( !complemento.equals( other.complemento ) ) {
			return false;
		}
		if ( logradouro == null ) {
			if ( other.logradouro != null ) {
				return false;
			}
		} else if ( !logradouro.equals( other.logradouro ) ) {
			return false;
		}
		if ( numero == null ) {
			if ( other.numero != null ) {
				return false;
			}
		} else if ( !numero.equals( other.numero ) ) {
			return false;
		}
		if ( uf == null ) {
			if ( other.uf != null ) {
				return false;
			}
		} else if ( !uf.equals( other.uf ) ) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( logradouro );
		builder.append( ", " );
		builder.append( numero );
		builder.append( " " );
		builder.append( complemento );
		builder.append( " " );
		builder.append( bairro );
		builder.append( " " );
		builder.append( cidade );
		builder.append( "/" );
		builder.append( uf );
		builder.append( " " );
		builder.append( cep );

		return builder.toString();
	}
	
}
