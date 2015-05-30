package org.hashuniforme.hash.funciones;


public class HashIterativeMult implements FuncionHash {

	public HashIterativeMult() {
	}

	@Override
	public long getHash(String o) {
		return getHashEval( o); // Return hash <int> without modulus size of table.
	}
	

	public long getHashEval( String o) {

		long hash = 0;
		long char1,char2,char3,char4,char5;
		long sumaAnt1 = (long)o.charAt( o.length()-2 );
		long sumaAnt2 = (long)o.charAt( 2 );

		char1 = sumaAnt1;
		char2 = (long)o.charAt( o.length()-1 );
		char3 = (long)o.charAt( 0 );
		char4 = (long)o.charAt( 1 );
		char5 = sumaAnt2;
		sumaAnt2 = sumaAnt1;
		sumaAnt1 = evaluaFuncBool( char1,char2,char3,char4,char5);
		hash += sumaAnt1;

		char1 = sumaAnt1;
		char2 = (long)o.charAt( 0 );
		char3 = (long)o.charAt( 1 );
		char4 = (long)o.charAt( 2 );
		char5 = sumaAnt2;
		sumaAnt2 = sumaAnt1;
		sumaAnt1 = evaluaFuncBool( char1,char2,char3,char4,char5);
		hash += sumaAnt1;

		// Main Loop.
		for( int i=2; i<o.length()-2; i++ ) {
			char1 = sumaAnt1;
			char2 = (long)o.charAt( i-1 );
			char3 = (long)o.charAt( i );
			char4 = (long)o.charAt( i+1 );
			char5 = sumaAnt2;
			sumaAnt2 = sumaAnt1;			
			sumaAnt1 = evaluaFuncBool( char1,char2,char3,char4,char5);
			hash += sumaAnt1;
		}

		char1 = sumaAnt1;
		char2 = (long)o.charAt( o.length()-3 );
		char3 = (long)o.charAt( o.length()-2 );
		char4 = (long)o.charAt( o.length()-1 );
		char5 = sumaAnt2;
		sumaAnt2 = sumaAnt1;
		sumaAnt1 = evaluaFuncBool( char1,char2,char3,char4,char5);
		hash += sumaAnt1;

		char1 = sumaAnt1;
		char2 = (long)o.charAt( o.length()-2 );
		char3 = (long)o.charAt( o.length()-1 );
		char4 = (long)o.charAt( 0 );
		char5 = sumaAnt2;
		sumaAnt2 = sumaAnt1;
		sumaAnt1 = evaluaFuncBool( char1,char2,char3,char4,char5);
		hash += sumaAnt1;

		return hash;	
	}
	
	public long evaluaFuncBool(Long char1, Long char2, Long char3, Long char4, Long char5) {
		long hash = evaluaFuncBool1( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool2( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool3( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool4( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool5( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool6( char1,char2,char3,char4,char5 );
				   /*+
				   evaluaFuncBool7( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool8( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool9( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool10( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool11( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool12( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool13( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool14( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool15( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool16( char1,char2,char3,char4,char5 );*/
		
		return hash;	
	}
	
	//0-(0,0,0,0),0-(0,0,4,0),0-(0,0,4,1),0-(0,1,4,0),0-(0,1,4,1),0-(0,3,4,0),
	
	// (0,0,0,0)=(+,+,+,+)
	public long evaluaFuncBool1(Long char1, Long char2, Long char3, Long char4, Long char5) {
		return ( char1 + char2 ) + ( char3 + char4 ) + char5;
	}
	
	// (0,0,4,0)=(+,+,*,+)
	public long evaluaFuncBool2(Long char1, Long char2, Long char3, Long char4, Long char5) {
		return ( char1 + char2 ) + ( char3 * char4 ) + char5;
	}	
	
	// (0,0,4,1)=(+,+,*,^)
	public long evaluaFuncBool3(Long char1, Long char2, Long char3, Long char4, Long char5) {
		return ( char1 + char2 ) + ( char3 * char4 ) ^ char5;
	}	
	
	// (0,1,4,0)=(+,^,*,+)
	public long evaluaFuncBool4(Long char1, Long char2, Long char3, Long char4, Long char5) {
		return ( char1 + char2 ) ^ ( char3 * char4 ) + char5;
	}

	// (0,1,4,1)=(+,^,*,^)
	public long evaluaFuncBool5(Long char1, Long char2, Long char3, Long char4, Long char5) {
		return ( char1 + char2 ) ^ ( char3 * char4 ) ^ char5;
	}

	// (0,3,4,0)=(+,|,*,+)
	public long evaluaFuncBool6(Long char1, Long char2, Long char3, Long char4, Long char5) {
		return ( char1 + char2 ) | ( char3 * char4 ) + char5;
	}
	
	//0-(0,3,4,1),0-(0,4,0,0),0-(0,4,0,1),0-(0,4,1,0),0-(0,4,1,1),0-(0,4,3,0),

	// (0,3,4,1)=(+,|,*,^)
	public long evaluaFuncBool7(Long char1, Long char2, Long char3, Long char4, Long char5) {
		return ( char1 + char2 ) | ( char3 * char4 ) ^ char5;
	}

	// (0,4,0,0)=(+,*,+,+)
	public long evaluaFuncBool8(Long char1, Long char2, Long char3, Long char4, Long char5) {
		return ( char1 + char2 ) * ( char3 + char4 ) + char5;
	}

	// (0,4,0,1)=(+,*,+,^)
	public long evaluaFuncBool9(Long char1, Long char2, Long char3, Long char4, Long char5) {
		return ( char1 + char2 ) * ( char3 + char4 ) ^ char5;
	}

	// (0,4,1,0)=(+,*,^,+)
	public long evaluaFuncBool10(Long char1, Long char2, Long char3, Long char4, Long char5) {
		return ( char1 + char2 ) * ( char3 ^ char4 ) + char5;
	}

	// (0,4,1,1)=(+,*,^,^)
	public long evaluaFuncBool11(Long char1, Long char2, Long char3, Long char4, Long char5) {
		return ( char1 + char2 ) * ( char3 ^ char4 ) ^ char5;
	}
	
	// (0,4,3,0)=(+,*,|,+)
	public long evaluaFuncBool12(Long char1, Long char2, Long char3, Long char4, Long char5) {
		return ( char1 + char2 ) * ( char3 | char4 ) + char5;
	}
	
	//0-(0,4,3,1),0-(0,4,4,0),0-(0,4,4,1),0-(1,0,4,0),0-(1,0,4,1),0-(1,1,4,0),

	// (0,4,3,1)=(+,*,|,^)
	public long evaluaFuncBool13(Long char1, Long char2, Long char3, Long char4, Long char5) {
		return ( char1 + char2 ) * ( char3 | char4 ) ^ char5;
	}

	// (0,4,4,0)=(+,*,*,+)
	public long evaluaFuncBool14(Long char1, Long char2, Long char3, Long char4, Long char5) {
		return ( char1 + char2 ) * ( char3 * char4 ) + char5;
	}

	// (0,4,4,1)=(+,*,*,^)
	public long evaluaFuncBool15(Long char1, Long char2, Long char3, Long char4, Long char5) {
		return ( char1 + char2 ) * ( char3 * char4 ) ^ char5;
	}	
	
	// (1,0,4,0)=(^,+,*,+)
	public long evaluaFuncBool16(Long char1, Long char2, Long char3, Long char4, Long char5) {
		return ( char1 ^ char2 ) + ( char3 * char4 ) + char5;
	}

	//,533-(4,3,1,0),614-(3,0,4,1),635-(4,3,0,1),644-(1,3,0,0),
}