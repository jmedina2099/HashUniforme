/**
 * 
 */
package org.hashuniforme.testing.hash.tabla;

import java.util.ArrayList;

import org.hashuniforme.testing.hash.funciones.FuncionHashTesting;

/**
 * @author "Jorge Medina"
 *
 */
public class TablaHashTesting {
	
	private FuncionHashTesting funcionHash;
	
	private ArrayList<String>[] tablaHash;
	private Integer[] sizesCasillas;
	private int vacias;
	
	public TablaHashTesting( FuncionHashTesting funcionHash,
					  int capacity ) {
		this.funcionHash = funcionHash;
		this.tablaHash = new ArrayList[capacity];
		this.sizesCasillas = new Integer[capacity];
		for( int i=0; i<capacity; i++ ) {
			this.tablaHash[i] = new ArrayList<String>();
			this.sizesCasillas[i] = 0;
		}
	}
	
	public void add( Object o, int oper1, int oper2, int oper3, int oper4) {
		String objeto = o.toString();
		int hash = this.funcionHash.getHash(objeto,oper1,oper2,oper3,oper4);
		
		//System.out.println( "HASH="+hash );
		
		if( hash < tablaHash.length ) {
			this.tablaHash[hash].add( objeto );
			this.sizesCasillas[hash]++;
		} else {
			System.out.println( "indice hash, fuera de rango!" );
		}
	}

	public int getVacias() {
		int vacias = 0;
		for( int i=0; i<this.sizesCasillas.length; i++ ) {
			if( this.sizesCasillas[i] == 0 ) {
				vacias++;
			}
		}
		
		return vacias;
	}

	public String toSizes() {
		int vacias = 0;
		int ocupadas = 0;
		for( int i=0; i<this.sizesCasillas.length; i++ ) {
			if( this.sizesCasillas[i] == 0 ) {
				System.out.println( this.sizesCasillas[i] );
				vacias++;
				continue;
			}
			System.out.println( this.sizesCasillas[i] );
			ocupadas++;
		}
		
		return "\nOCUPADAS="+ocupadas+"\nVACIAS="+vacias;
	}
	
	@Override
	public String toString() {
		String toString = "";
		int size;
		int vacias = 0;
		int ocupadas = 0;
		for( int i=0; i<tablaHash.length; i++ ) {
			size = tablaHash[i].size();
			if( size == 0 ) {
				System.out.println( tablaHash[i].size() );
				vacias++;
				continue;
			}
			System.out.println( tablaHash[i].size() );
			toString += tablaHash[i].toString();
			ocupadas++;
		}
		
		return toString + "\nOCUPADAS="+ocupadas+"\nVACIAS="+vacias;
	}

}
