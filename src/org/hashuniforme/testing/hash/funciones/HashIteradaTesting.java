/**
 * 
 */
package org.hashuniforme.testing.hash.funciones;


/**
 * @author "Jorge Medina"
 *
 */
public class HashIteradaTesting implements HashIterativeBooleanTesting {

	/**
	 * 
	 */
	public HashIteradaTesting() {
	}
	
	@Override
	public int getHash(String o, int oper1, int oper2, int oper3, int oper4) {
		int hash = 0;
		try {
			hash = getHashEval( o,oper1,oper2,oper3,oper4 );
		} catch (Exception e) {
			e.printStackTrace();
		}
		if( hash < 0 ) {
			hash = Integer.MAX_VALUE + hash;
		}
		
		return hash;
	}
	

	public int getHashEval( String o,int oper1, int oper2, int oper3, int oper4 ) {

		int hash = 0;
		int char1,char2,char3,char4,char5;
		int sumaAnt1 = (int)o.charAt( o.length()-2 );
		int sumaAnt2 = (int)o.charAt( 2 );

		char1 = sumaAnt1;
		char2 = (int)o.charAt( o.length()-1 );
		char3 = (int)o.charAt( 0 );
		char4 = (int)o.charAt( 1 );
		char5 = sumaAnt2;
		sumaAnt2 = sumaAnt1;
		sumaAnt1 = evaluaFuncBool1( char1,char2,char3,char4,char5,oper1,oper2,oper3,oper4 );
		hash += sumaAnt1;

		char1 = sumaAnt1;
		char2 = (int)o.charAt( 0 );
		char3 = (int)o.charAt( 1 );
		char4 = (int)o.charAt( 2 );
		char5 = sumaAnt2;
		sumaAnt2 = sumaAnt1;
		sumaAnt1 = evaluaFuncBool1( char1,char2,char3,char4,char5,oper1,oper2,oper3,oper4 );
		hash += sumaAnt1;

		// Main Loop.
		for( int i=2; i<o.length()-2; i++ ) {
			char1 = sumaAnt1;
			char2 = (int)o.charAt( i-1 );
			char3 = (int)o.charAt( i );
			char4 = (int)o.charAt( i+1 );
			char5 = sumaAnt2;
			sumaAnt2 = sumaAnt1;
			sumaAnt1 = evaluaFuncBool1( char1,char2,char3,char4,char5,oper1,oper2,oper3,oper4 );
			hash += sumaAnt1;
		}

		char1 = sumaAnt1;
		char2 = (int)o.charAt( o.length()-3 );
		char3 = (int)o.charAt( o.length()-2 );
		char4 = (int)o.charAt( o.length()-1 );
		char5 = sumaAnt2;
		sumaAnt2 = sumaAnt1;
		sumaAnt1 = evaluaFuncBool1( char1,char2,char3,char4,char5,oper1,oper2,oper3,oper4 );
		hash += sumaAnt1;

		char1 = sumaAnt1;
		char2 = (int)o.charAt( o.length()-2 );
		char3 = (int)o.charAt( o.length()-1 );
		char4 = (int)o.charAt( 0 );
		char5 = sumaAnt2;
		sumaAnt2 = sumaAnt1;
		sumaAnt1 = evaluaFuncBool1( char1,char2,char3,char4,char5,oper1,oper2,oper3,oper4);
		hash += sumaAnt1;
		
		return hash;	
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
