package org.hashuniforme.hash.funciones;

import gnu.crypto.hash.BaseHash;
import gnu.crypto.hash.IMessageDigest;
import gnu.crypto.hash.MD5;
import gnu.crypto.util.Util;

/**
 * <p>The iterative algorithm takes as input a message of arbitrary
 * length and produces as output a 160-bit "fingerprint" or "message digest" of
 * the input.</p>
 *
 * <p>References:</p>
 *
 * <ol>
 *    <li><a href="http://132.248.9.195/ptd2009/abril/0642039/Index.html">Funciones Hash Criptográficas</a>.<br>
 *    Jorge Alberto Medina Rosas.</li>
 * </ol>
 *
 * @author Jorge Alberto Medina Rosas
 * @version $Revision: 1.7.2 $
 */
public class IterativeBoolean extends BaseHash {

   // Constants and variables
   // -------------------------------------------------------------------------

   /** 160-bit output length. */
   private static final int DIGEST_LENGTH = 20;
	
   /** The iterative algorithm operates on 640-bit blocks, or 80 bytes. */
   private static final int BLOCK_SIZE = 80; // inner block size in bytes

   private static final String DIGEST0 = "80E7C74500D72AF7000109ED00FEEC2A004DA32E";

   /** caches the result of the correctness test, once executed. */
   private static Boolean valid;

   /** 160-bit interim result. */
   private int h0, h1, h2, h3, h4;

   private static final String ITER_HASH = "iter";

   private static final boolean DEBUG_PARTIAL_HASH = false;
   
   // Constructor(s)
   // -------------------------------------------------------------------------

   /** Trivial 0-arguments constructor. */
   public IterativeBoolean() {
      super(ITER_HASH, DIGEST_LENGTH, BLOCK_SIZE);
   }

   /**
    * <p>Private constructor for cloning purposes.</p>
    *
    * @param md the instance to clone.
    */
   private IterativeBoolean(IterativeBoolean md) {
      this();

      this.h0 = md.h0;
      this.h1 = md.h1;
      this.h2 = md.h2;
      this.h3 = md.h3;
      this.h4 = md.h4;
      this.count = md.count;
      this.buffer = (byte[]) md.buffer.clone();
   }

   // Class methods
   // -------------------------------------------------------------------------

   // Instance methods
   // -------------------------------------------------------------------------

   // java.lang.Cloneable interface implementation ----------------------------

   public Object clone() {
      return new IterativeBoolean(this);
   }

   // Implementation of concrete methods in BaseHash --------------------------

   
   /**
    * <p>Implementation of the iterative algorithm.</p>
    *
    * @param in 640-bit block.
    * @param i the offset index.
    */
   protected synchronized void transform(byte[] in, int i) {
      int[] X = new int[20];
      X[0] =  (in[i++] & 0xFF) | (in[i++] & 0xFF) << 8 | (in[i++] & 0xFF) << 16 | in[i++] << 24;
      X[1] =  (in[i++] & 0xFF) | (in[i++] & 0xFF) << 8 | (in[i++] & 0xFF) << 16 | in[i++] << 24;
      X[2] =  (in[i++] & 0xFF) | (in[i++] & 0xFF) << 8 | (in[i++] & 0xFF) << 16 | in[i++] << 24;
      X[3] =  (in[i++] & 0xFF) | (in[i++] & 0xFF) << 8 | (in[i++] & 0xFF) << 16 | in[i++] << 24;
      X[4] =  (in[i++] & 0xFF) | (in[i++] & 0xFF) << 8 | (in[i++] & 0xFF) << 16 | in[i++] << 24;
      X[5] =  (in[i++] & 0xFF) | (in[i++] & 0xFF) << 8 | (in[i++] & 0xFF) << 16 | in[i++] << 24;
      X[6] =  (in[i++] & 0xFF) | (in[i++] & 0xFF) << 8 | (in[i++] & 0xFF) << 16 | in[i++] << 24;
      X[7] =  (in[i++] & 0xFF) | (in[i++] & 0xFF) << 8 | (in[i++] & 0xFF) << 16 | in[i++] << 24;
      X[8] =  (in[i++] & 0xFF) | (in[i++] & 0xFF) << 8 | (in[i++] & 0xFF) << 16 | in[i++] << 24;
      X[9] =  (in[i++] & 0xFF) | (in[i++] & 0xFF) << 8 | (in[i++] & 0xFF) << 16 | in[i++] << 24;
      X[10] = (in[i++] & 0xFF) | (in[i++] & 0xFF) << 8 | (in[i++] & 0xFF) << 16 | in[i++] << 24;
      X[11] = (in[i++] & 0xFF) | (in[i++] & 0xFF) << 8 | (in[i++] & 0xFF) << 16 | in[i++] << 24;
      X[12] = (in[i++] & 0xFF) | (in[i++] & 0xFF) << 8 | (in[i++] & 0xFF) << 16 | in[i++] << 24;
      X[13] = (in[i++] & 0xFF) | (in[i++] & 0xFF) << 8 | (in[i++] & 0xFF) << 16 | in[i++] << 24;
      X[14] = (in[i++] & 0xFF) | (in[i++] & 0xFF) << 8 | (in[i++] & 0xFF) << 16 | in[i++] << 24;
      X[15] = (in[i++] & 0xFF) | (in[i++] & 0xFF) << 8 | (in[i++] & 0xFF) << 16 | in[i++] << 24;
      X[16] = (in[i++] & 0xFF) | (in[i++] & 0xFF) << 8 | (in[i++] & 0xFF) << 16 | in[i++] << 24;
      X[17] = (in[i++] & 0xFF) | (in[i++] & 0xFF) << 8 | (in[i++] & 0xFF) << 16 | in[i++] << 24;
      X[18] = (in[i++] & 0xFF) | (in[i++] & 0xFF) << 8 | (in[i++] & 0xFF) << 16 | in[i++] << 24;
      X[19] = (in[i++] & 0xFF) | (in[i++] & 0xFF) << 8 | (in[i++] & 0xFF) << 16 | in[i  ] << 24;

      int A = h0;
      int B = h1;
      int C = h2;
      int D = h3;
      int E = h4;
      
      int char1 = X[18];
      int char2 = X[19];
      int char3 = X[0];
      int char4 = X[1];
      int char5 = X[2];
      
      E += D;
      D += C;
      C += B;
      B += A;
      A += evaluaFuncBool(char1,char2,char3,char4,char5);
      
      char1 += A;
      char2 += char3;
      char3 += char4;
      char4 += X[2];
      char5 += B;      
      E += D;
      D += C;
      C += B;
      B += A;
      A += evaluaFuncBool(char1,char2,char3,char4,char5);

      for( int offset=2; offset<18; offset++ ) {
          char1 += A;
          char2 += char3;
          char3 += char4;
          char4 += X[offset+1];
          char5 += B;
          E += D;
          D += C;
          C += B;
          B += A;
          A += evaluaFuncBool(char1,char2,char3,char4,char5);
      }
      
      char1 += A;
      char2 += char3;
      char3 += char4;
      char4 += X[19];
      char5 += B;
      E += D;
      D += C;
      C += B;
      B += A;
      A += evaluaFuncBool(char1,char2,char3,char4,char5);

      char1 += A;
      char2 += char3;
      char3 += char4;
      char4 += X[0];
      char5 += B;
      E += D;
      D += C;
      C += B;
      B += A;
      A += evaluaFuncBool(char1,char2,char3,char4,char5);

      if( DEBUG_PARTIAL_HASH ) {
          System.out.println( "**** E = "+E );
          System.out.println( "**** D = "+D );
          System.out.println( "**** C = "+C );
          System.out.println( "**** B = "+B );
          System.out.println( "**** A = "+A );
      }

      h0 += A;
      h1 += B;
      h2 += C;
      h3 += D;
      h4 += E;
   }
   
   private int evaluaFuncBool(int char1, int char2, int char3, int char4, int char5) {
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
                 evaluaFuncBool12( char1,char2,char3,char4,char5 ) +
                 evaluaFuncBool13( char1,char2,char3,char4,char5 ) +
                 evaluaFuncBool14( char1,char2,char3,char4,char5 ) +
                 evaluaFuncBool15( char1,char2,char3,char4,char5 ) +
                 evaluaFuncBool16( char1,char2,char3,char4,char5 );

      if( DEBUG_PARTIAL_HASH ) {
          System.out.println( "**** hashParcial = "+hash );
      }

      return hash;	
   }

   // (0,1,1,1)=(+,^,^,^)
   private int evaluaFuncBool1(int char1, int char2, int char3, int char4, int char5) {
      return ( char1 + char2 ) ^ ( char3 ^ char4 ) ^ char5;
   }

   // (2,1,0,1)=(&,^,+,^)	
   private int evaluaFuncBool2(int char1, int char2, int char3, int char4, int char5) {
      return ( char1 & char2 ) ^ ( char3 + char4 ) ^ char5;
   }	
	
   // (1,0,0,1)=(^,+,+,^)
   private int evaluaFuncBool3(int char1, int char2, int char3, int char4, int char5) {
      return ( char1 ^ char2 ) + ( char3 + char4 ) ^ char5;
   }	
	
   // (1,1,0,1)=(^,^,+,^)
   private int evaluaFuncBool4(int char1, int char2, int char3, int char4, int char5) {
      return ( char1 ^ char2 ) ^ ( char3 + char4 ) ^ char5;
   }
	
   // (2,0,0,0)=(&,+,+,+)
   private int evaluaFuncBool5(int char1, int char2, int char3, int char4, int char5) {
      return ( char1 & char2 ) + ( char3 + char4 ) + char5;
   }

   // (2,0,0,1)=(&,+,+,^)
   private int evaluaFuncBool6(int char1, int char2, int char3, int char4, int char5) {
      return ( char1 & char2 ) + ( char3 + char4 ) ^ char5;
   }

   // (3,0,1,1)=(^,^,+,^)
   private int evaluaFuncBool7(int char1, int char2, int char3, int char4, int char5) {
      return ( char1 ^ char2 ) ^ ( char3 + char4 ) ^ char5;
   }

   // (3,1,0,1)=(|,^,+,^)
   private int evaluaFuncBool8(int char1, int char2, int char3, int char4, int char5) {
      return ( char1 | char2 ) ^ ( char3 + char4 ) ^ char5;
   }

   // (3,3,0,1)=(|,|,+,^)
   private int evaluaFuncBool9(int char1, int char2, int char3, int char4, int char5) {
      return ( char1 | char2 ) | ( char3 + char4 ) ^ char5;
   }

   // (0,0,0,1)=(+,+,+,^)
   private int evaluaFuncBool10(int char1, int char2, int char3, int char4, int char5) {
      return ( char1 + char2 ) + ( char3 + char4 ) ^ char5;
   }
	
   // (0,2,0,1)=(+,&,+,^)
   private int evaluaFuncBool11(int char1, int char2, int char3, int char4, int char5) {
      return ( char1 + char2 ) & ( char3 + char4 ) ^ char5;
   }
	
   // (1,0,1,1)=(^,+,^,^)
   private int evaluaFuncBool12(int char1, int char2, int char3, int char4, int char5) {
      return ( char1 ^ char2 ) + ( char3 ^ char4 ) ^ char5;
   }
	
   // (3,1,1,1)=(|,^,^,^)
   private int evaluaFuncBool13(int char1, int char2, int char3, int char4, int char5) {
      return ( char1 | char2 ) ^ ( char3 ^ char4 ) ^ char5;
   }

   // (0,0,1,1)=(+,+,^,^)
   private int evaluaFuncBool14(int char1, int char2, int char3, int char4, int char5) {
      return ( char1 + char2 ) + ( char3 ^ char4 ) ^ char5;
   }

   // (0,1,0,1)=(+,^,+,^)
   private int evaluaFuncBool15(int char1, int char2, int char3, int char4, int char5) {
      return ( char1 + char2 ) ^ ( char3 + char4 ) ^ char5;
   }	
	
   // (0,2,0,0)=(+,&,+,+)
   private int evaluaFuncBool16(int char1, int char2, int char3, int char4, int char5) {
      return ( char1 + char2 ) & ( char3 + char4 ) + char5;
   }

   protected byte[] padBuffer() {
      int n = (int)(count % BLOCK_SIZE);
      int padding = (n < BLOCK_SIZE-8) ? (BLOCK_SIZE-8 - n) : (BLOCK_SIZE*2-8 - n);
      byte[] result = new byte[padding + 8];

      // padding is always binary 1 followed by binary 0s
      result[0] = (byte) 0x80;

      // save number of bits, casting the int to an array of 8 bytes
      long bits = count << 3;
      result[padding++] = (byte) bits;
      result[padding++] = (byte)(bits >>>  8);
      result[padding++] = (byte)(bits >>> 16);
      result[padding++] = (byte)(bits >>> 24);
      result[padding++] = (byte)(bits >>> 32);
      result[padding++] = (byte)(bits >>> 40);
      result[padding++] = (byte)(bits >>> 48);
      result[padding  ] = (byte)(bits >>> 56);

      return result;
   }

   /**
    * <p>The result of the previously hash computing.</p>
    *
    * @return the output of the completed hash operation. (160-bit)
    */
   protected byte[] getResult() {
      byte[] result = new byte[] {
         (byte) h0, (byte)(h0 >>> 8), (byte)(h0 >>> 16), (byte)(h0 >>> 24),
         (byte) h1, (byte)(h1 >>> 8), (byte)(h1 >>> 16), (byte)(h1 >>> 24),
         (byte) h2, (byte)(h2 >>> 8), (byte)(h2 >>> 16), (byte)(h2 >>> 24),
         (byte) h3, (byte)(h3 >>> 8), (byte)(h3 >>> 16), (byte)(h3 >>> 24),
         (byte) h4, (byte)(h4 >>> 8), (byte)(h4 >>> 16), (byte)(h4 >>> 24)
      };

      return result;
   }

   protected void resetContext() {
      h0 = 0;
      h1 = 0;
      h2 = 0;
      h3 = 0;
      h4 = 0;
   }

   public boolean selfTest() {
      if (valid == null) {
         valid = new Boolean(DIGEST0.equals(Util.toString(new IterativeBoolean().digest())));
      }
      return valid.booleanValue();
   }
   
   public static void main( String[] args ) {
      String in = "abc";
      byte[] bites = in.getBytes();

      IMessageDigest algorithm;
      try {
          algorithm = new IterativeBoolean();
          algorithm.update( bites, 0, in.length() );
          byte[] md = algorithm.digest();
          System.out.println( "hash("+in+")="+Util.toString(md) );
       } catch (Exception e) {
    	   e.printStackTrace();
       }
		
      in = "abcd";
      bites = in.getBytes();
      try {
    	   algorithm = new IterativeBoolean();
    	   algorithm.update( bites, 0, in.length() );
    	   byte[] md = algorithm.digest();
    	   System.out.println( "hash("+in+")="+Util.toString(md) );
      } catch (Exception e) {
    	   e.printStackTrace();
      }
   }
}
