/**
 * 
 */
package org.hashuniforme.main;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

import org.hashuniforme.hash.funciones.FuncionHash;
import org.hashuniforme.hash.funciones.HashIterativeBoolean;
import org.hashuniforme.hash.funciones.HashJava;
import org.hashuniforme.hash.tabla.TablaHash;

/**
 * @author "Jorge Medina"
 *
 */
public final class Main {

	/**
	 * For testing with a file dictionary of words.
	 * There are 2996 words repeated!!!
	 * @param args	
	 */
	public static void main2(String[] args) {

		long timeIni = System.currentTimeMillis();
		System.out.println( "START " );

		int total = 0;
		
		int capacityOfTable = 1000; //31991; //100003; //1009;
		FuncionHash funcionHash = new HashIterativeBoolean(); //
		TablaHash tablaHash = new TablaHash( funcionHash, capacityOfTable );
		
		//System.out.println( "tensada".hashCode() == "friabili".hashCode() );
		
		InputStream istream = Main.class.getResourceAsStream( "/esp.txt" ); //( "/ES.txt" );  
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

		//System.out.println( tablaHash.toSizes() );
		
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
		
		int capacityOfTable = 1000;//31991;//1009; // Size of the Hashtable.

		//FuncionHash funcionHash = new HashJava();
		//FuncionHash funcionHash = new HashSumaChars();
		
		FuncionHash funcionHash = new HashIterativeBoolean();
		TablaHash tablaHash = new TablaHash( funcionHash, capacityOfTable );
		
		/*
		byte[] bitesA =  getRandomBitesFromFile( "/colisiones-java-1.txt", 8000 );
		byte[] bitesB =  getRandomBitesFromFile( "/colisiones-java-2.txt", 8000 );
		tablaHash.add( new String( bitesA) );
		tablaHash.add( new String( bitesB) );
		*/
		
		byte[] bites = getRandomBites( 1000 ); //getRandomBitesFromFile( "/bitesRandom3.txt",8000 ); //  
		System.out.println( "STARTING..." );
		System.out.println( "<"+Arrays.toString(bites)+">-["+bites.length+"]" );

		byte biteAnt;
		int total = 0;
		for( int index=0; index<bites.length; index++ ) {
			//System.out.println( "BITES="+ Integer.toBinaryString( bites[index] & 0xFF ) );			
			for( int offset=0; offset<8; offset++ ) {
				biteAnt = bites[index];
				
				bites[index] ^= (1 << offset); // Toogle bit.
				//System.out.println( Integer.toBinaryString( bites[index] & 0xFF ) );

				if( !tablaHash.add( new String( bites) ) ) { // Add next string, 2 bits different to all entries.
					System.out.println( "Ups / "+index+"-"+offset );
				}
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
	
	public static byte[] getWhiteBites( int size ) {
		return new byte[size];
	}

	public static byte[] getRandomBitesFromFile( String filename, int sizeBites ) {
		
		byte[] bites = new byte[sizeBites];
		
		URL url = Main.class.getResource( filename );
		
		InputStream istream = Main.class.getResourceAsStream( filename );
		BufferedInputStream bis = new BufferedInputStream(istream);
		BufferedReader reader = new BufferedReader(new InputStreamReader(bis) );
		
		String word = null;
		int count = 0;
		try {
			word = reader.readLine();
			StringTokenizer st = new StringTokenizer(word, ",");
			while( st.hasMoreElements() ) {
				bites[count++] = (byte)Integer.parseInt( st.nextToken().trim() );
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(reader!=null) try { reader.close(); } catch (IOException e) {}
			if(bis!=null) try { bis.close(); } catch (IOException e) {}
			if(istream!=null) try{ istream.close(); } catch (IOException e) {}
		}

		return bites;
	}

}

// End of Main.java