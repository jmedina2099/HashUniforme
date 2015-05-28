/**
 * 
 */
package org.hashuniforme.hash.funciones;

/**
 * @author "Jorge Medina"
 *
 */
public class HashPrimo implements FuncionHash {

	private static int PRIME = 101;

	/**
	 * 
	 */
	public HashPrimo() {
	}
	
	/**
	 * 
	 */
	public HashPrimo( int prime ) {
		PRIME = prime;
	}

	/* (non-Javadoc)
	 * @see org.hashuniforme.hash.funciones.FuncionHash#getHash(java.lang.String)
	 */
	@Override
	public int getHash(String o) {
		
		int hash = 0;
		for( int i=0; i<o.length(); i++ ) {
			hash += (int)o.charAt( i );
		}
		
		hash %= PRIME;
	
		return hash;
	}
}
