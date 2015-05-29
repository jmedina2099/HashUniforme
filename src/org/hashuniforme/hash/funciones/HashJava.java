/**
 * 
 */
package org.hashuniforme.hash.funciones;

/**
 * @author "Jorge Medina"
 *
 */
public class HashJava implements FuncionHash {

	/**
	 * 
	 */
	public HashJava() {
	}

	/* (non-Javadoc)
	 * @see org.hashuniforme.hash.funciones.FuncionHash#getHash(java.lang.String)
	 */
	@Override
	public int getHash(String o) {
		int hash = o.hashCode();
		if( hash < 0 ) {
			hash = Integer.MAX_VALUE + hash;
		}
		
		return hash;
	}

}
