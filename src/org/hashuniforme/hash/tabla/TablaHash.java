/**
 * 
 */
package org.hashuniforme.hash.tabla;

import java.util.Hashtable;

import org.hashuniforme.hash.funciones.FuncionHash;

/**
 * @author "Jorge Medina"
 *
 */
public class TablaHash {
	
	private static final boolean SAVE_OBJECT_IN_TABLE = false; // Generally true, false for saving memory.
	private static final boolean DEBUG_COLISSIONS = false;

	private FuncionHash funcionHash;
	
	private Hashtable<Integer,Object>[] tablaHash;
	private Integer[] sizesColisiones;
	private Integer[] sizesCasillas;

	private int capacity = 0;
	
	@SuppressWarnings("unchecked")
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
		int hashMod = hash % capacity; // Index of the array for insertion.
		
		//System.out.println( "HASH="+hash );
		
		Object obj;
		if( hashMod < tablaHash.length ) {
			if( (obj=this.tablaHash[hashMod].get(hash))!=null ) { // Collision, dude!.
				this.sizesColisiones[hashMod]++;
				if( DEBUG_COLISSIONS ) {
					System.out.println( "Colision-HASH="+ hash +"/ OBJECT="+objeto+" / OBJ="+obj );
				}
			} else {
				if( SAVE_OBJECT_IN_TABLE ) {
					this.tablaHash[hashMod].put( hash, objeto );
				} else {
					this.tablaHash[hashMod].put( hash, hash );
				}
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
	
}
