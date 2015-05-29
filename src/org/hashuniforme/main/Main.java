/**
 * 
 */
package org.hashuniforme.main;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Arrays;
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

	
	public static void main22(String[] args) {

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

		System.out.println( "COLISIONES="+tablaHash.getColisiones() );
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
		
		int prime = 1009; //1000003;//31991;//1000003;//1009;
		int iteraciones = 10;
		
		//FuncionHash funcionHash = new HashIteradaRandomBits( prime );

		FuncionHash funcionHash = new HashJava( prime );
		//FuncionHash funcionHash = new HashPrimo( prime );
		
		TablaHash tablaHash = new TablaHash( funcionHash, prime );
		
		//String prepend = "AAAAAAAAAAAAAAAAAAAA";
		//String prepend = "ZZZZZZZZZZZZZZZZZZZZ";
		//String prepend = "MMMMMMMMMMMMMMMMMMMM";
		//String prepend = getRandomBits(20);
		byte[] bites = getRandomBites( 4000 );
		//String prepend = getRandomBits( 62500 );
		System.out.println( "STARTING..." );
		System.out.println( "<"+Arrays.toString(bites)+">-["+bites.length+"]" );
		
		byte biteAnt;
		int total = 0;
		for( int index=0; index<bites.length; index++ ) {
			//System.out.println( "BITES="+Integer.toBinaryString(bites[index]) );			
			for( int offset=0; offset<8; offset++ ) {
				biteAnt = bites[index];
				
				bites[index] ^= (1 << offset);
				//System.out.println( Integer.toBinaryString(bites[index]) );

				tablaHash.add( new String( bites) );
				bites[index] = biteAnt;
				total++;
			}
		}
			
		/*
		String word = null;
		int index = 0;
		for( int i=0; i<prepend.length()*32;i++ ) {
			word = nextWord(prepend,i);
			tablaHash.add( word );
			index++;
		}
		*/

		System.out.println( tablaHash.toSizes() );
		System.out.println( "COLISIONES="+tablaHash.getColisiones() );
		System.out.println( "TOTAL="+total );
		//System.out.println( tablaHash.toSizes() );		

		long timeNow = System.currentTimeMillis() - timeIni;
		System.out.println( "TIME = "+(timeNow/(1000.0*60.0))+" mins, "+(timeNow/(1000.0))+" secs." );
	}
	
	public static String getWordOfBits( int bits ) {
		StringBuffer sb = new StringBuffer();
		StringBuffer concat = new StringBuffer("HOLAMUNDO-");
		
		int size = 0;
		while( (size=sb.length()*16) < bits ) {
			sb.append( concat );
		}
		
		return sb.toString();
	}
	
	/*
	public static void mainX(String[] args) {

		String word = "HOLAMUNDO-HOLAMUNDO-";

		int tope = word.length() * 32;
		for( int i=0; i<33; i++ ) {
			System.out.println( nextWord(word,i) );
		}
	}
	*/
	
	/*
	private static String nextWord(String word, int index) {
		
		int character = (int)(index/32d);
		
		String prep = "";
		String postp = "";
		if( character > 0 ) {
			prep = word.substring(0,character);
		}
		if( character+1 < word.length() ) {
			postp = word.substring(character+1,word.length());
		}
		
		int bite32 = word.charAt( character );
		String word32 = complete32bitsWord( bite32 );
		
		// Busco el bit en cuestion y lo incremento modulo 2.
		int offset = index % 32;
		char bit = word32.charAt(offset);
		int bitInt = bit=='0'? 1: 0;
		
		String prepend32 = "";
		String postpend32 = "";
		
		if( offset > 0 ) {			
			prepend32 = word32.substring(0,offset);
		}
		if( offset+1 < word32.length() ) {
			postpend32 = word32.substring(offset+1,word32.length());
		}
		
		String nextWord32 = prepend32 + bitInt + postpend32;
		//System.out.println( nextWord32 + "-" + nextWord32.length()  );
		
		long newChar = Long.parseLong(nextWord32, 2);
		//System.out.println( "newChar="+newChar );
		//System.out.println( "newCharInt="+(int)newChar );

		char newChar1 = (char)(newChar >> 16 ); // los bits de arriba.
		char newChar2 = (char)newChar;
		
		//System.out.println( nextWord32 + "-" + nextWord32.length()  );

		return new StringBuffer()
			.append( prep  )
			.append( newChar1 )
			.append( newChar2 )
			.append( postp )
			.toString();
	}
*/

	public static byte[] getRandomBites( int size ) {
		byte[] bites = new byte[size];
		
		Random random = new Random();
		for( int i=0; i<size; i++ ) {
			bites[i] = (byte)random.nextInt( Byte.MAX_VALUE );
		}
		
		return bites;
	}
	
}
