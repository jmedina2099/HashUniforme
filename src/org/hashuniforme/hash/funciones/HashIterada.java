package org.hashuniforme.hash.funciones;


public class HashIterada implements FuncionHash {

	private static int PRIME = 101;

	public HashIterada() {
	}
	
	public HashIterada( int prime ) {
		PRIME = prime;
	}

	@Override
	public int getHash(String o) {
		int hash = 0;
		try {
			hash += getHashEval( o);
			//hash += o.hashCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if( hash < 0 ) {
			hash = Integer.MAX_VALUE + hash;
		}
		
		return hash % PRIME;
	}
	

	public int getHashEval( String o) {

		int hash = 0;
		int char1,char2,char3,char4,char5;

		char1 = (int)o.charAt( o.length()-2 );
		char2 = (int)o.charAt( o.length()-1 );
		char3 = (int)o.charAt( 0 );
		char4 = (int)o.charAt( 1 );
		char5 = (int)o.charAt( 2 );
		hash += evaluaFuncBool( char1,char2,char3,char4,char5);

		char1 = (int)o.charAt( o.length()-1 );
		char2 = (int)o.charAt( 0 );
		char3 = (int)o.charAt( 1 );
		char4 = (int)o.charAt( 2 );
		char5 = (int)o.charAt( 3 );
		hash += evaluaFuncBool( char1,char2,char3,char4,char5);

		// Main Loop.
		for( int i=2; i<o.length()-2; i++ ) {
			char1 = (int)o.charAt( i-2 );
			char2 = (int)o.charAt( i-1 );
			char3 = (int)o.charAt( i );
			char4 = (int)o.charAt( i+1 );
			char5 = (int)o.charAt( i+2 );
			hash += evaluaFuncBool( char1,char2,char3,char4,char5);
		}

		char1 = (int)o.charAt( o.length()-4 );
		char2 = (int)o.charAt( o.length()-3 );
		char3 = (int)o.charAt( o.length()-2 );
		char4 = (int)o.charAt( o.length()-1 );
		char5 = (int)o.charAt( 0 );
		hash += evaluaFuncBool( char1,char2,char3,char4,char5);

		char1 = (int)o.charAt( o.length()-3 );
		char2 = (int)o.charAt( o.length()-2 );
		char3 = (int)o.charAt( o.length()-1 );
		char4 = (int)o.charAt( 0 );
		char5 = (int)o.charAt( 1 );
		hash += evaluaFuncBool( char1,char2,char3,char4,char5);

		
		if( hash < 0 ) {
			hash = Integer.MAX_VALUE + hash;			
		}

		return hash %=	PRIME;	
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
				   evaluaFuncBool12( char1,char2,char3,char4,char5 );
		
		return hash;	
	}
	
	// (0,1,0,1)=(+,^,+,^)
	public int evaluaFuncBool1(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 + char2 ) ^ ( char3 + char4 ) ^ char5;
	}

	// (0,1,0,3)=(+,^,+,|)
	public int evaluaFuncBool2(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 + char2 ) ^ ( char3 + char4 ) | char5;
	}

	// (1,0,1,0)=(^,+,^,+)
	public int evaluaFuncBool3(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 ^ char2 ) + ( char3 ^ char4 ) + char5;
	}
	
	// (3,0,3,0)=(|,+,|,+)
	public int evaluaFuncBool4(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 | char2 ) + ( char3 | char4 ) + char5;
	}

	// (3,0,0,0)=(|,+,+,+)
	public int evaluaFuncBool5(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 | char2 ) + ( char3 + char4 ) + char5;
	}

	// (1,0,3,0)=(^,+|,+)
	public int evaluaFuncBool6(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 ^ char2 ) + ( char3 | char4 ) + char5;
	}

	// (3,0,0,1)=(|,+,+,^)
	public int evaluaFuncBool7(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 | char2 ) + ( char3 + char4 ) ^ char5;
	}
	
	// (3,1,0,1)=(|,^,+,^)
	public int evaluaFuncBool8(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 | char2 ) ^ ( char3 + char4 ) ^ char5;
	}
	
	// (1,0,0,0)=(^,+,+,+)
	public int evaluaFuncBool9(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 ^ char2 ) + ( char3 + char4 ) + char5;
	}

	// (0,3,0,0)=(+,|,+,+)
	public int evaluaFuncBool10(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 + char2 ) | ( char3 + char4 ) + char5;
	}

	// (1,0,0,1)=(^,+,+,^)
	public int evaluaFuncBool11(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 ^ char2 ) + ( char3 + char4 ) ^ char5;
	}

	// (3,0,1,0)=(|,+,^,+)
	public int evaluaFuncBool12(Integer char1, Integer char2, Integer char3, Integer char4, Integer char5) {
		return ( char1 | char2 ) + ( char3 ^ char4 ) + char5;
	}
}