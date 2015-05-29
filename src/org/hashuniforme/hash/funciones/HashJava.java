/**
 * 
 */
package org.hashuniforme.hash.funciones;

/**
 * @author "Jorge Medina"
 *
 */
public class HashJava implements FuncionHash {

	private static int PRIME = 101;

	/**
	 * 
	 */
	public HashJava() {
	}

	public HashJava( int prime ) {
		PRIME = prime;
	}
	
	/* (non-Javadoc)
	 * @see org.hashuniforme.hash.funciones.FuncionHash#getHash(java.lang.String)
	 */
	@Override
	public int getHash(String o) {
		int hash = o.hashCode();
		if( hash < 0 ) {
			hash = ~hash;
		}
		
		return hash;
	}

}
