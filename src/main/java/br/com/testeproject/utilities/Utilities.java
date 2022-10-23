package br.com.testeproject.utilities;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {
	
	public static Date parseDate( String data ) throws ParseException {
		if ( data == null || !( data.contains( "/" ) ) ) {
			return null;
		}
	
		SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy", new Locale("pt", "BR") );
		sdf.setLenient( false );//Tolerante sim/true  | nao/false
		
		return sdf.parse( data );
	}
	
	public static String parseDate( Date data ) throws ParseException {
		if ( data == null ) {
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy", new Locale("pt", "BR") );
		sdf.setLenient( false );//Tolerante sim/true | nao/false
		
		return sdf.format( data );
	}
	
	public static final boolean isCpfValido( String CPF ) {
		if ( ( CPF == null ) || CPF.isEmpty() ) {
			return false;
		}
		CPF = CPF.replace( ".", "" ).replace( "-", "" ).trim();
	
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		if ( CPF.equals( "00000000000" ) || CPF.equals( "11111111111" ) || CPF.equals( "22222222222" ) || CPF.equals( "33333333333" ) || CPF.equals( "44444444444" ) || CPF.equals( "55555555555" ) || CPF.equals( "66666666666" ) || CPF.equals( "77777777777" ) || CPF.equals( "88888888888" ) || CPF.equals( "99999999999" ) || ( CPF.length() != 11 ) ) {
			return ( false );
		}
		
		char dig10, dig11;
		int sm, i, r, num, peso;
		// "try" - protege o codigo para eventuais erros de conversao de tipo
		// (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			
			for ( i = 0; i < 9; i++ ) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = CPF.charAt( i ) - 48;
				sm = sm + ( num * peso );
				peso = peso - 1;
			}
			
			r = 11 - ( sm % 11 );
			if ( ( r == 10 ) || ( r == 11 ) ) {
				dig10 = '0';
			} else {
				dig10 = (char) ( r + 48 );
			}
			// converte no respectivo caractere numerico
			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for ( i = 0; i < 10; i++ ) {
				num = CPF.charAt( i ) - 48;
				sm = sm + ( num * peso );
				peso = peso - 1;
			}
			r = 11 - ( sm % 11 );
			if ( ( r == 10 ) || ( r == 11 ) ) {
				dig11 = '0';
			} else {
				dig11 = (char) ( r + 48 );
			}
			// Verifica se os digitos calculados conferem com os digitos
			// informados.
			if ( ( dig10 == CPF.charAt( 9 ) ) && ( dig11 == CPF.charAt( 10 ) ) ) {
				return ( true );
			} else {
				return ( false );
			}
		} catch ( InputMismatchException erro ) {
			return ( false );
		}
	
	}
	
	public static final String normalizeString( String texto ) {
		if ( ( texto == null ) || texto.isEmpty() ) {
			return null;
		}
	
		texto = texto.replaceAll( "[^a-zA-Z0-9������������������������������� ][.,!?:...]", "" );
		String padrao = "\\s{2,}";// caso tenha 2 espa�os ou mais
		Pattern regPat = Pattern.compile( padrao );
		Matcher matcher = regPat.matcher( texto );
		texto = matcher.replaceAll( " " ).trim();
		
		return texto;
	}
	
	public static final BigDecimal parseBigDecimal( String valor ) {
		if ( valor == null || valor.isEmpty() ) {
			return null;
		}
	
		String valorFormatado = valor.replace( ".", "" ).replace( ",", "." ).trim();
	
		return new BigDecimal( valorFormatado );
	}
	
	public static final String parseBigDecimal( BigDecimal valor ) {
		if ( valor == null ) {
			return null;
		}
	
		return valor.toString();
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isNuloOuVazio( Object obj ) {
		if ( obj == null ) {
			return true;
		}
	
		if ( obj instanceof String ) {
			String valor = (String) obj;
			return valor.isEmpty();
		}
		
		if ( obj instanceof ArrayList ) {
			ArrayList lista = (ArrayList) obj;
			return lista.isEmpty();
		}
		
		if ( obj instanceof Set ) {
			Set lista = (Set) obj;
			return lista.isEmpty();
		}
	
		return obj.equals( "" );
	}

}
