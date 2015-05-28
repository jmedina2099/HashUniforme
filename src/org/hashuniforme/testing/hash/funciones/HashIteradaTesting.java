/**
 * 
 */
package org.hashuniforme.testing.hash.funciones;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author "Jorge Medina"
 *
 */
public class HashIteradaTesting implements FuncionHashTesting {

	private static int PRIME = 101;

	/**
	 * 
	 */
	public HashIteradaTesting() {
	}
	
	/**
	 * 
	 * @param prime
	 */
	public HashIteradaTesting( int prime ) {
		PRIME = prime;
	}

	@Override
	public int getHash(String o, int oper1, int oper2, int oper3, int oper4) {
		int hash = 0;
		try {
			hash = getHashEval( o,oper1,oper2,oper3,oper4 );
			//hash += getHash( o, this.getClass().getMethod( "evaluaFuncBool2", Integer.class,Integer.class,Integer.class,Integer.class,Integer.class ) );
			//hash += getHash( o, this.getClass().getMethod( "evaluaFuncBool3", Integer.class,Integer.class,Integer.class,Integer.class,Integer.class ) );
			//hash += getHash( o, this.getClass().getMethod( "evaluaFuncBool4", Integer.class,Integer.class,Integer.class,Integer.class,Integer.class ) );
			//hash += o.hashCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if( hash < 0 ) {
			hash = ~hash;
		}
		
		return hash % PRIME;
	}
	

	public int getHashEval( String o,int oper1, int oper2, int oper3, int oper4 ) {

		int hash = 0;
		int char1,char2,char3,char4,char5;

		char1 = (int)o.charAt( o.length()-2 );
		char2 = (int)o.charAt( o.length()-1 );
		char3 = (int)o.charAt( 0 );
		char4 = (int)o.charAt( 1 );
		char5 = (int)o.charAt( 2 );
		hash += evaluaFuncBool1( char1,char2,char3,char4,char5,oper1,oper2,oper3,oper4 );
		//hash += evaluaFuncBool2( char1,char2,char3,char4,char5 );
		//hash += evaluaFuncBool3( char1,char2,char3,char4,char5 );
		//hash += evaluaFuncBool4( char1,char2,char3,char4,char5 );

		char1 = (int)o.charAt( o.length()-1 );
		char2 = (int)o.charAt( 0 );
		char3 = (int)o.charAt( 1 );
		char4 = (int)o.charAt( 2 );
		char5 = (int)o.charAt( 3 );
		hash += evaluaFuncBool1( char1,char2,char3,char4,char5,oper1,oper2,oper3,oper4 );
		//hash += evaluaFuncBool2( char1,char2,char3,char4,char5 );
		//hash += evaluaFuncBool3( char1,char2,char3,char4,char5 );
		//hash += evaluaFuncBool4( char1,char2,char3,char4,char5 );

		// Loop!
		for( int i=2; i<o.length()-2; i++ ) {
			char1 = (int)o.charAt( i-2 );
			char2 = (int)o.charAt( i-1 );
			char3 = (int)o.charAt( i );
			char4 = (int)o.charAt( i+1 );
			char5 = (int)o.charAt( i+2 );
			hash += evaluaFuncBool1( char1,char2,char3,char4,char5,oper1,oper2,oper3,oper4 );
			//hash += evaluaFuncBool2( char1,char2,char3,char4,char5 );
			//hash += evaluaFuncBool3( char1,char2,char3,char4,char5 );
			//hash += evaluaFuncBool4( char1,char2,char3,char4,char5 );
		}

		char1 = (int)o.charAt( o.length()-4 );
		char2 = (int)o.charAt( o.length()-3 );
		char3 = (int)o.charAt( o.length()-2 );
		char4 = (int)o.charAt( o.length()-1 );
		char5 = (int)o.charAt( 0 );
		hash += evaluaFuncBool1( char1,char2,char3,char4,char5,oper1,oper2,oper3,oper4 );
		//hash += evaluaFuncBool2( char1,char2,char3,char4,char5 );
		//hash += evaluaFuncBool3( char1,char2,char3,char4,char5 );
		//hash += evaluaFuncBool4( char1,char2,char3,char4,char5 );

		char1 = (int)o.charAt( o.length()-3 );
		char2 = (int)o.charAt( o.length()-2 );
		char3 = (int)o.charAt( o.length()-1 );
		char4 = (int)o.charAt( 0 );
		char5 = (int)o.charAt( 1 );
		hash += evaluaFuncBool1( char1,char2,char3,char4,char5,oper1,oper2,oper3,oper4);
		//hash += evaluaFuncBool2( char1,char2,char3,char4,char5 );
		//hash += evaluaFuncBool3( char1,char2,char3,char4,char5 );
		//hash += evaluaFuncBool4( char1,char2,char3,char4,char5 );
		
		if( hash < 0 ) {
			hash = ~hash;			
		}

		return hash %=	PRIME;	
	}

	public int evaluaFuncBool1(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5,
			int oper1, int oper2, int oper3, int oper4) {
		
		int sumParcial = 0;
		if( oper1== 0 ) {
			sumParcial = char1 + char2;
		} else if( oper1== 1 ) {
			sumParcial = char1 ^ char2;
		} else if( oper1== 2 ) {
			sumParcial = char1 & char2;
		} else if( oper1== 3 ) {
			sumParcial = char1 | char2;
		}
		
		if( oper2== 0 ) {
			sumParcial += char3;
		} else if( oper2== 1 ) {
			sumParcial ^= char3;
		} else if( oper2== 2 ) {
			sumParcial &= char3;
		} else if( oper2== 3 ) {
			sumParcial |= char3;
		}

		if( oper3== 0 ) {
			sumParcial += char4;
		} else if( oper3== 1 ) {
			sumParcial ^= char4;
		} else if( oper3== 2 ) {
			sumParcial &= char4;
		} else if( oper3== 3 ) {
			sumParcial |= char4;
		}

		if( oper4== 0 ) {
			sumParcial += char5;
		} else if( oper4== 1 ) {
			sumParcial ^= char5;
		} else if( oper4== 2 ) {
			sumParcial &= char5;
		} else if( oper4== 3 ) {
			sumParcial |= char5;
		}

		return sumParcial;
	}
	
}
