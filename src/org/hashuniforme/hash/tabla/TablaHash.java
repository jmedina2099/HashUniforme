/**
 * 
 */
package org.hashuniforme.hash.tabla;

import java.util.ArrayList;
import java.util.Hashtable;

import org.hashuniforme.hash.funciones.FuncionHash;

/**
 * @author "Jorge Medina"
 *
 */
public class TablaHash {
	
	private FuncionHash funcionHash;
	
	private Hashtable<Integer,Object>[] tablaHash;
	private Integer[] sizesColisiones;
	private Integer[] sizesCasillas;

	private int capacity = 0;
	
	public TablaHash( FuncionHash funcionHash,
					  int capacity ) {
		this.capacity = capacity;
		this.funcionHash = funcionHash;
		this.sizesColisiones = new Integer[capacity];
		this.sizesCasillas = new Integer[capacity];
		this.tablaHash = new Hashtable[capacity];
		for( int i=0; i<capacity; i++ ) {
			this.tablaHash[i] = new Hashtable<Integer,Object>();
			this.sizesColisiones[i] = 0;
			this.sizesCasillas[i] = 0;
		}
	}
	
	public void add( Object o) {
		String objeto = o.toString();
		int hash = this.funcionHash.getHash(objeto);
		int hashMod = hash % capacity;
		
		//System.out.println( "HASH="+hash );
		
		Object obj;
		if( hashMod < tablaHash.length ) {
			if( (obj=this.tablaHash[hashMod].get(hash))!=null ) {
				this.sizesColisiones[hashMod]++;
				//System.out.println( "HASH="+ hash +"/ OBJECT="+objeto+" / OBJ="+obj );
			} else {
				this.tablaHash[hashMod].put( hash, objeto );
			}
			this.sizesCasillas[hashMod]++;			
		} else {
			System.out.println( "indice hash, fuera de rango!" );
		}
	}

	public int getColisiones() {
		int colisiones = 0;
		for( int i=0; i<this.sizesColisiones.length; i++ ) {
			if( this.sizesColisiones[i] != 0 ) {
				colisiones += this.sizesColisiones[i];
			}
		}
		
		return colisiones;
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
