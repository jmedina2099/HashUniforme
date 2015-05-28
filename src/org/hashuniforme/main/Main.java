/**
 * 
 */
package org.hashuniforme.main;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import org.hashuniforme.hash.funciones.FuncionHash;
import org.hashuniforme.hash.funciones.HashIteradaRandomBits;
import org.hashuniforme.hash.funciones.HashJava;
import org.hashuniforme.hash.tabla.TablaHash;

/**
 * @author "Jorge Medina"
 *
 */
public final class Main {

	
	public static void main2(String[] args) {

		long timeIni = System.currentTimeMillis();
		System.out.println( "START " );

		int total = 0;
		
		int prime = 1009;
		FuncionHash funcionHash = new HashIteradaRandomBits( prime );
		TablaHash tablaHash = new TablaHash( funcionHash, prime );

		InputStream istream = Main.class.getResourceAsStream( "/esp.txt" );
		BufferedInputStream bis = new BufferedInputStream(istream);
		BufferedReader reader = new BufferedReader(new InputStreamReader(bis) );
		
		String word = null;
		try {
			while( (word=reader.readLine())!=null ) {
				if( word.length() > 4 ) {
					tablaHash.add( word );
					total++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println( tablaHash.toSizes() );
		System.out.println( "TOTAL="+total );

		long timeNow = System.currentTimeMillis() - timeIni;
		System.out.println( "TIME = "+(timeNow/(1000.0*60.0))+" mins, "+(timeNow/(1000.0))+" secs." );
		
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		long timeIni = System.currentTimeMillis();
		System.out.println( "START " );
		
		int prime = 1009;
		int iteraciones = 100;
		
		//FuncionHash funcionHash = new HashIteradaRandomBits( prime );

		FuncionHash funcionHash = new HashJava( prime );
		//FuncionHash funcionHash = new HashPrimo( prime );
		
		TablaHash tablaHash = new TablaHash( funcionHash, prime );
		
		//String prepend = "AAAAAAAAAAAAAAAAAAAA";
		//String prepend = "ZZZZZZZZZZZZZZZZZZZZ";
		//String prepend = "MMMMMMMMMMMMMMMMMMMM";
		String prepend = getRandomBits(20);
		//System.out.println( "STARTING..." );
		//System.out.println( "<"+prepend+">-["+prepend.length()+"]" );
		
		int total = 0;
		
		for( int charAt3 = 0; charAt3<iteraciones; charAt3++ ) {
			for( int charAt2 = 0; charAt2<iteraciones; charAt2++ ) {
				for( int charAt = 0; charAt<iteraciones; charAt++ ) {
					total++;
					tablaHash.add( prepend+""+charAt+""+charAt2+""+charAt3 );
				}
			}
		}

		System.out.println( tablaHash.toSizes() );		

		long timeNow = System.currentTimeMillis() - timeIni;
		System.out.println( "TIME = "+(timeNow/(1000.0*60.0))+" mins, "+(timeNow/(1000.0))+" secs." );
	}
	
	public static String getRandomWord( int size ) {
		StringBuilder sb = new StringBuilder(); 
		
		Random random = new Random();
		int char1;
		
		for( int i=0; i<size; i++ ) {
			char1 = random.nextInt( (int)'Z'-(int)'A'+1 ) + (int)'A';
			sb.append( (char)char1 );
		}
		
		return sb.toString();
	}

	public static String getRandomBits( int size ) {
		StringBuilder sb = new StringBuilder(); 
		
		Random random = new Random();
		int char1;
		
		for( int i=0; i<size; i++ ) {
			char1 = random.nextInt( Integer.MAX_VALUE );
			sb.append( (char)char1 );
		}
		
		return sb.toString();
	}
	
}
