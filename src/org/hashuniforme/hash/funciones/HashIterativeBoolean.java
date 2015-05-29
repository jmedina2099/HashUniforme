package org.hashuniforme.hash.funciones;


public class HashIterativeBoolean implements FuncionHash {

	public HashIterativeBoolean() {
	}

	@Override
	public int getHash(String o) {
		int hash = 0;
		try {
			hash = getHashEval( o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if( hash < 0 ) {
			hash = Integer.MAX_VALUE + hash;
		}
		
		return hash; // Return hash <int> without modulus size of table.
	}
	

	public int getHashEval( String o) {

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
		sumaAnt1 = evaluaFuncBool( char1,char2,char3,char4,char5);
		hash += sumaAnt1;

		char1 = sumaAnt1;
		char2 = (int)o.charAt( 0 );
		char3 = (int)o.charAt( 1 );
		char4 = (int)o.charAt( 2 );
		char5 = sumaAnt2;
		sumaAnt2 = sumaAnt1;
		sumaAnt1 = evaluaFuncBool( char1,char2,char3,char4,char5);
		hash += sumaAnt1;

		// Main Loop.
		for( int i=2; i<o.length()-2; i++ ) {
			char1 = sumaAnt1;
			char2 = (int)o.charAt( i-1 );
			char3 = (int)o.charAt( i );
			char4 = (int)o.charAt( i+1 );
			char5 = sumaAnt2;
			sumaAnt2 = sumaAnt1;			
			sumaAnt1 = evaluaFuncBool( char1,char2,char3,char4,char5);
			hash += sumaAnt1;
		}

		char1 = sumaAnt1;
		char2 = (int)o.charAt( o.length()-3 );
		char3 = (int)o.charAt( o.length()-2 );
		char4 = (int)o.charAt( o.length()-1 );
		char5 = sumaAnt2;
		sumaAnt2 = sumaAnt1;
		sumaAnt1 = evaluaFuncBool( char1,char2,char3,char4,char5);
		hash += sumaAnt1;

		char1 = sumaAnt1;
		char2 = (int)o.charAt( o.length()-2 );
		char3 = (int)o.charAt( o.length()-1 );
		char4 = (int)o.charAt( 0 );
		char5 = sumaAnt2;
		sumaAnt2 = sumaAnt1;
		sumaAnt1 = evaluaFuncBool( char1,char2,char3,char4,char5);
		hash += sumaAnt1;

		return hash;	
	}
	
	public int evaluaFuncBool(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		int hash = evaluaFuncBool1( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool2( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool3( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool4( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool5( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool6( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool7( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool8( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool9( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool10( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool11( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool12( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool13( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool14( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool15( char1,char2,char3,char4,char5 ) +
				   evaluaFuncBool16( char1,char2,char3,char4,char5 );
		
		return hash;	
	}
	
	// (0,0,0,0)=(+,+,+,+)
	public int evaluaFuncBool1(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 + char2 ) + ( char3 + char4 ) + char5;
	}
	
	// (3,0,0,0)=(|,+,+,+)
	public int evaluaFuncBool2(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 | char2 ) + ( char3 + char4 ) + char5;
	}	
	
	// (3,3,0,0)=(|,|,+,+)
	public int evaluaFuncBool3(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 | char2 ) | ( char3 + char4 ) + char5;
	}	
	
	// (0,3,0,0)=(+,|,+,+)
	public int evaluaFuncBool4(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 + char2 ) | ( char3 + char4 ) + char5;
	}

	// (3,0,1,0)=(|,+,^,+)
	public int evaluaFuncBool5(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 | char2 ) + ( char3 ^ char4 ) + char5;
	}

	// (0,3,0,1)=(+,|,+,^)
	public int evaluaFuncBool6(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 + char2 ) | ( char3 + char4 ) ^ char5;
	}

	// (1,3,0,0)=(^,|,+,+)
	public int evaluaFuncBool7(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 ^ char2 ) | ( char3 + char4 ) + char5;
	}

	// (3,0,0,1)=(|,+,+,^)
	public int evaluaFuncBool8(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 | char2 ) + ( char3 + char4 ) ^ char5;
	}

	// (2,0,0,0)=(&,+,+,+)
	public int evaluaFuncBool9(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 & char2 ) + ( char3 + char4 ) + char5;
	}

	// (1,3,1,0)=(^,|,^,+)
	public int evaluaFuncBool10(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 ^ char2 ) | ( char3 ^ char4 ) + char5;
	}
	
	// (3,3,1,0)=(|,|,^,+)
	public int evaluaFuncBool11(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 | char2 ) | ( char3 ^ char4 ) + char5;
	}
	
	// (3,1,1,0)=(|,^,^,+)
	public int evaluaFuncBool12(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 | char2 ) ^ ( char3 ^ char4 ) + char5;
	}
	
	// (3,1,0,0)=(|,^,+,+)
	public int evaluaFuncBool13(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 | char2 ) ^ ( char3 + char4 ) + char5;
	}

	// (0,0,3,0)=(+,+,|,+)
	public int evaluaFuncBool14(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 + char2 ) + ( char3 | char4 ) + char5;
	}

	// (3,1,0,1)=(|,^,+,^)
	public int evaluaFuncBool15(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 | char2 ) ^ ( char3 + char4 ) ^ char5;
	}	
	
	// (0,1,0,1)=(+,^,+,^)
	public int evaluaFuncBool16(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 + char2 ) ^ ( char3 + char4 ) ^ char5;
	}

}