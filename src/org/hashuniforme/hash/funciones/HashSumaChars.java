/**
 * 
 */
package org.hashuniforme.hash.funciones;

/**
 * @author "Jorge Medina"
 *
 */
public class HashSumaChars implements FuncionHash {

	/**
	 * 
	 */
	public HashSumaChars() {
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
		
		return hash;
	}
}
