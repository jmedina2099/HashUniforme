/**
 * 
 */
package org.hashuniforme.testing.main;

import java.util.Comparator;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import org.hashuniforme.testing.hash.funciones.FuncionHashTesting;
import org.hashuniforme.testing.hash.funciones.HashIteradaTesting;
import org.hashuniforme.testing.hash.tabla.TablaHashTesting;

/**
 * @author "Jorge Medina"
 *
 */
public final class MainTesting {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		long timeIni = System.currentTimeMillis();
		System.out.println( "START "+(int)'A'+" / "+(int)'Z' );
		
		int vacias;
		
		Comparator<Object[]> comparator = new Comparator<Object[]>() {

			@Override
			public int compare(Object[] o1, Object[] o2) {
				int compare = Integer.compare( (Integer)o1[0], (Integer)o2[0] );
				if( compare != 0 )
					return compare;
				
				return ((String)o1[1]).compareTo((String)o2[1]);
			}

		};
		TreeMap<Object[],String> funciones = new TreeMap<Object[],String>(comparator ) {
			@Override
			public String toString() {
				StringBuilder sb = new StringBuilder(); 
				
				Set<Object[]> keys = this.keySet();
				for( Object[] key : keys ) {
					sb.append( key[0] )
					.append( '-' )
					.append( key[1] )
					.append( ',' );
				}
				return sb.toString();
			}
		};

		int oper1=0,oper2=0,oper3=0,oper4=0;
		
		int total = 0;
		String cadena;
		try {
			
			for( oper1=0; oper1<4; oper1++) {
				for( oper2=0; oper2<4; oper2++) {
					for( oper3=0; oper3<4; oper3++) {
						for( oper4=0; oper4<4; oper4++) {
							vacias = executeTest( oper1,oper2,oper3,oper4 );
							cadena = "("+oper1+","+oper2+","+oper3+","+oper4+")";
							funciones.put( new Object[]{vacias,cadena},cadena );
							System.out.println( "VACIAS="+vacias+"-"+cadena );
							total++;
							
							//if(total == 3)
								//throw new Exception();
						}
					}
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println( "TOTAL="+total );
		System.out.println( funciones );
		
		long timeNow = System.currentTimeMillis() - timeIni;
		System.out.println( "TIME = "+(timeNow/(1000.0*60.0))+" mins, "+(timeNow/(1000.0))+" secs." );
	}

	public static int executeTest(int oper1, int oper2, int oper3, int oper4) {
		int prime = 1009;
		int iteraciones = 100;
		
		FuncionHashTesting funcionHash = new HashIteradaTesting( prime );

		//HashJava funcionHash = new HashJava( prime );
		//FuncionHash funcionHash = new HashPrimo( prime );
		
		TablaHashTesting tablaHash = new TablaHashTesting( funcionHash, prime );		

		//String prepend = "AAAAAAAAAAAAAAAAAAAA";
		String prepend = getRandomBits(20);
		System.out.println( "STARTIING..." );
		System.out.println( "<"+prepend+">-["+prepend.length()+"]" );
		
		int total = 0;
		
		for( int charAt3 = 0; charAt3<iteraciones; charAt3++ ) {
			for( int charAt2 = 0; charAt2<iteraciones; charAt2++ ) {
				for( int charAt = 0; charAt<iteraciones; charAt++ ) {
					total++;
					tablaHash.add( prepend+""+charAt+""+charAt2+""+charAt3
							,oper1,oper2,oper3,oper4 );
				}
			}
		}

		return tablaHash.getVacias();		
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
