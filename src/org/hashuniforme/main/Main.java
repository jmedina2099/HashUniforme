/**
 * 
 */
package org.hashuniforme.main;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

import org.hashuniforme.hash.funciones.FuncionHash;
import org.hashuniforme.hash.funciones.HashIterativeBoolean;
import org.hashuniforme.hash.tabla.TablaHash;

/**
 * @author "Jorge Medina"
 *
 */
public final class Main {

	/**
	 * For testing with a file dictionary of words.
	 * @param args
	 */
	public static void mainTestDictionary(String[] args) {

		long timeIni = System.currentTimeMillis();
		System.out.println( "START " );

		int total = 0;
		
		int prime = 1009;
		FuncionHash funcionHash = new HashIterativeBoolean();
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
		
		int prime = 1009; // Size of the Hashtable.
		

		//FuncionHash funcionHash = new HashJava( prime );
		//FuncionHash funcionHash = new HashSumaChars( prime );
		
		FuncionHash funcionHash = new HashIterativeBoolean();
		TablaHash tablaHash = new TablaHash( funcionHash, prime );
		
		byte[] bites = getRandomBites( 2000 );
		System.out.println( "STARTING..." );
		System.out.println( "<"+Arrays.toString(bites)+">-["+bites.length+"]" );
		
		byte biteAnt;
		int total = 0;
		for( int index=0; index<bites.length; index++ ) {
			//System.out.println( "BITES="+Integer.toBinaryString(bites[index]) );			
			for( int offset=0; offset<8; offset++ ) {
				biteAnt = bites[index];
				
				bites[index] ^= (1 << offset); // Toogle bit.
				//System.out.println( Integer.toBinaryString(bites[index]) );

				tablaHash.add( new String( bites) ); // Add next string, 2 bits different to all entries.
				bites[index] = biteAnt;
				total++;
			}
		}
			
		System.out.println( tablaHash.toSizes() );
		System.out.println( "COLISIONES="+tablaHash.getColisiones() );
		System.out.println( "TOTAL="+total );

		long timeNow = System.currentTimeMillis() - timeIni;
		System.out.println( "TIME = "+(timeNow/(1000.0*60.0))+" mins, "+(timeNow/(1000.0))+" secs." );
	}
	
	/**
	 * Get an array of random bytes of longitud <size>.
	 * @param size
	 * @return
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

// End of Main.java