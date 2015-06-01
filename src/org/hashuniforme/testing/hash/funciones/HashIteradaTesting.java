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
	public long getHash(String o, int oper1, int oper2, int oper3, int oper4) {
		return getHashEval( o,oper1,oper2,oper3,oper4 );
	}
	

	public long getHashEval( String o,int oper1, int oper2, int oper3, int oper4 ) {

		long hash = 0;
		long char1=0,char2=0,char3=0,char4=0,char5=0;

		long sumaAnt1 = (long)o.charAt( o.length()-2 );
		long sumaAnt2 = (long)o.charAt( 2 );
		long sumaAnt3 = 0;
		long sumaAnt4 = 0;
		long sumaAnt5 = 0;

		char1 += sumaAnt1;
		char2 += (long)o.charAt( o.length()-1 );
		char3 += (long)o.charAt( 0 );
		char4 += (long)o.charAt( 1 );
		char5 += sumaAnt2;
		sumaAnt5 += sumaAnt4;
		sumaAnt4 += sumaAnt3;
		sumaAnt3 += sumaAnt2;
		sumaAnt2 += sumaAnt1;
		sumaAnt1 += evaluaFuncBool1( char1,char2,char3,char4,char5,oper1,oper2,oper3,oper4 );

		char1 += sumaAnt1;
		char2 += char3;
		char3 += char4;
		char4 += (long)o.charAt( 2 );
		char5 += sumaAnt2;
		sumaAnt5 += sumaAnt4;
		sumaAnt4 += sumaAnt3;
		sumaAnt3 += sumaAnt2;
		sumaAnt2 += sumaAnt1;
		sumaAnt1 += evaluaFuncBool1( char1,char2,char3,char4,char5,oper1,oper2,oper3,oper4 );

		// Main Loop.
		for( int i=2; i<o.length()-2; i++ ) {
			char1 += sumaAnt1;
			char2 += char3;
			char3 += char4;
			char4 += (long)o.charAt( i+1 );
			char5 += sumaAnt2;
			sumaAnt5 += sumaAnt4;
			sumaAnt4 += sumaAnt3;
			sumaAnt3 += sumaAnt2;
			sumaAnt2 += sumaAnt1;
			sumaAnt1 += evaluaFuncBool1( char1,char2,char3,char4,char5,oper1,oper2,oper3,oper4 );
		}

		char1 += sumaAnt1;
		char2 += char3;
		char3 += char4;
		char4 += (long)o.charAt( o.length()-1 );
		char5 += sumaAnt2;
		sumaAnt5 += sumaAnt4;
		sumaAnt4 += sumaAnt3;
		sumaAnt3 += sumaAnt2;
		sumaAnt2 += sumaAnt1;
		sumaAnt1 += evaluaFuncBool1( char1,char2,char3,char4,char5,oper1,oper2,oper3,oper4 );

		char1 += sumaAnt1;
		char2 += char3;
		char3 += char4;
		char4 += (long)o.charAt( 0 );
		char5 += sumaAnt2;
		sumaAnt5 += sumaAnt4;
		sumaAnt4 += sumaAnt3;
		sumaAnt3 += sumaAnt2;
		sumaAnt2 += sumaAnt1;
		sumaAnt1 += evaluaFuncBool1( char1,char2,char3,char4,char5,oper1,oper2,oper3,oper4 );

		hash = (sumaAnt1 << 48) |
	               ((sumaAnt1+sumaAnt2 << 32) & 0xffffffffL) |
	               ((sumaAnt1+sumaAnt2+sumaAnt3 << 16) & 0xffffffffL) |
	               ((sumaAnt1+sumaAnt4+sumaAnt5) & 0xffffffffL);
					

		return hash;	
	}

	public long evaluaFuncBool1(Long char1, Long char2, Long char3, Long char4, Long char5,
			int oper1, int oper2, int oper3, int oper4) {
		
		long sumParcial = 0;
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
