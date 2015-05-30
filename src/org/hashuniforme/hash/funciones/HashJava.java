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
	public long getHash(String o) {
		return o.hashCode();
	}

}
