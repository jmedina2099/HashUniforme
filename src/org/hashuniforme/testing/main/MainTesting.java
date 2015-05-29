/**
 * 
 */
package org.hashuniforme.testing.main;

import java.util.Comparator;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import org.hashuniforme.testing.hash.funciones.HashIteradaTesting;
import org.hashuniforme.testing.hash.funciones.HashIterativeBooleanTesting;
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
			private static final long serialVersionUID = -249167753892625557L;
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
		int colisiones;

		String cadena;
		try {
			
			for( oper1=0; oper1<4; oper1++) {
				for( oper2=0; oper2<4; oper2++) {
					for( oper3=0; oper3<4; oper3++) {
						for( oper4=0; oper4<4; oper4++) {
							colisiones = executeTest( oper1,oper2,oper3,oper4 );
							cadena = "("+oper1+","+oper2+","+oper3+","+oper4+")";
							funciones.put( new Object[]{colisiones,cadena},cadena );
							System.out.println( "COLISIONES="+colisiones+"-"+cadena );
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
		
		HashIterativeBooleanTesting funcionHash = new HashIteradaTesting();

		//HashJava funcionHash = new HashJava( prime );
		//FuncionHash funcionHash = new HashSumaChars( prime );
		
		TablaHashTesting tablaHash = new TablaHashTesting( funcionHash, prime );

		byte[] bites = getRandomBites( 1000 );
		System.out.println( "STARTIING..." );
		//System.out.println( "<"+Arrays.toString(bites)+">-["+bites.length+"]" );
		
		byte biteAnt;
		for( int index=0; index<bites.length; index++ ) {
			//System.out.println( "BITES="+Integer.toBinaryString(bites[index]) );			
			for( int offset=0; offset<8; offset++ ) {
				biteAnt = bites[index];
				
				bites[index] ^= (1 << offset);
				//System.out.println( Integer.toBinaryString(bites[index]) );

				tablaHash.add( new String( bites)
						,oper1,oper2,oper3,oper4 );
				bites[index] = biteAnt;
			}
		}
		
		//System.out.println( tablaHash.toSizes() ); 

		return tablaHash.getColisiones();
	}
	
	public static byte[] getRandomBites( int size ) {
		byte[] bites = new byte[size];
		
		Random random = new Random();
		for( int i=0; i<size; i++ ) {
			bites[i] = (byte)random.nextInt( Byte.MAX_VALUE );
		}
		
		return bites;
	}
	
}
