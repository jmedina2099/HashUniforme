/**
 * 
 */
package org.hashuniforme.hash.tabla;

import java.util.Arrays;
import java.util.Hashtable;

import org.hashuniforme.hash.funciones.FuncionHash;

/**
 * @author "Jorge Medina"
 *
 */
public class TablaHash {
	
	
	private static boolean SAVE_OBJECT_IN_TABLE = false; // Generally true, false for saving memory.
	private static final boolean DEBUG_COLISSIONS = false;

	private FuncionHash funcionHash;
	
	private Hashtable<Long,Object>[] tablaHash;
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
			this.tablaHash[i] = new Hashtable<Long,Object>();
			this.sizesColisiones[i] = 0;
			this.sizesCasillas[i] = 0;
		}
	}
	
	public boolean add( Object o) {
		String objeto = o.toString();
		long hashOld = this.funcionHash.getHash(objeto);
		long hash = hashOld;
		if( hash < 0 ) {
			hash = Long.MAX_VALUE + hash;
		}

		int hashMod = (int)(hash % capacity); // Index of the array for insertion.
		
		//System.out.println( "HASH="+hashOld );
		
		if( hashMod < tablaHash.length ) {
			Object obj;
			if( (obj=this.tablaHash[hashMod].get(hashOld))!=null ) { // Collision, dude!.
				if( !new String(objeto.getBytes()).equals(obj.toString()) ) {				
					this.sizesColisiones[hashMod]++;
					if( DEBUG_COLISSIONS ) {
						System.out.println( "ColisionTHIS-HASH= **START**\n\n"+ hashOld +"\n\n **END** OBJECT=\n\n"+Arrays.toString(objeto.getBytes())+"\n\n"+new String(objeto.getBytes()) );
						System.out.println( "ColisionWITH/THIS-HASH= **START**\n\n"+ hashOld +"\n\n **END** OBJECT=\n\n"+Arrays.toString(obj.toString().getBytes())+"\n\n"+obj.toString() );
						return false;
					}
				}
			} else {
				if( SAVE_OBJECT_IN_TABLE ) {
					this.tablaHash[hashMod].put( hashOld, objeto );
				} else {
					this.tablaHash[hashMod].put( hashOld, hashOld );
				}
			}
			this.sizesCasillas[hashMod]++;			
		} else {
			System.out.println( "indice hash, fuera de rango!" );
		}
		
		return true;
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
