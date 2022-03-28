public class SimplifiedAes2 {
	
   public static void main(String[] args) {
      
   //   String[][] Pt = {{"A","2"},{"E","4"}}; 
   //   String[][] Ke = {{"8","F"},{"A","1"}};
   
      String[][] Pt = {{"A","2"},{"0","4"}}; 
      String[][] Ke = {{"8","F"},{"A","1"}};
      
      String[][] Pt2 = {{"A","2"},{"E","4"}}; 
      String[][] Ke2 = {{"C","F"},{"A","1"}};
      
        
      String[][] res = EnryptAes(Pt2,Ke2);
      
      printMatrix(res);
      
   }
	   
   public static String[][] EnryptAes(String[][] P,String[][] K) {
     
      KeyGeneration2 keyGen = new KeyGeneration2();
      
      String[][] mat = {{"1","1"},{"1","2"}};
   
      String[][] addXorMatrix = addXorMatrix(P,K);   	
   
      
      // Round 1     
      String[][] sBoxOperations = sBoxOperations(addXorMatrix);
      rotateRowKtime(sBoxOperations,1,1);
   
      String[][] afterMixedCol = MixColumns.multiply(mat, sBoxOperations);		     
      String[][] binaryAfterMixedCol = hexToBinaryMatrix(afterMixedCol);
      
      String[][] K_binary = hexToBinaryMatrix(K);
      String[] K1_start = twoToOneDimArray(K_binary);
      
      String[] keyGen1 = keyGen.round2(K1_start,1);
      String[][] keyGen1Mat = oneToTwoDimArray(keyGen1);
      String[][] keyGen1MathHex = binaryToHex(keyGen1Mat);  
                
      String[][] addRoundKey = addXorMatrix(afterMixedCol,keyGen1MathHex);
   	// End of Round 1
      
      // Round 2     
      String[][] sBoxOperations2 = sBoxOperations(addRoundKey);     
      rotateRowKtime(sBoxOperations2,1,1);
      
      String[][] afterMixedCol2 = MixColumns.multiply(mat, sBoxOperations2);	     
      String[][] binaryAfterMixedCol2 = hexToBinaryMatrix(afterMixedCol2);
   	
      String[] keyGen2 = keyGen.round2(keyGen1,2);     
      String[][] keyGen2Mat = oneToTwoDimArray(keyGen2); 
      String[][] keyGen2MathHex = binaryToHex(keyGen2Mat); 
      
      String[][] addRoundKey2 = addXorMatrix(afterMixedCol2,keyGen2MathHex);
      // End of Round 2
      
      // Round 3
      String[][] sBoxOperations3 = sBoxOperations(addRoundKey2);
      rotateRowKtime(sBoxOperations3,1,1);
   	
      String[] keyGen3 = keyGen.round2(keyGen2,3);     
      String[][] keyGen3Mat = oneToTwoDimArray(keyGen3); 
      String[][] keyGen3MathHex = binaryToHex(keyGen3Mat); 
       
      
      String[][] addRoundKey3 = addXorMatrix(sBoxOperations3,keyGen3MathHex);
      // End of Round 3
      
      String[][] cipherText = binaryToHex(addRoundKey3);
           
   	     
      return cipherText;   	  
   }
	   
   public static String[][] sBoxOperations(String[][] mat) {
   	   
      String[][] sBox = sBox();
      
      for(int i = 0; i < mat.length; i++) {
         
         for(int j = 0; j < mat[0].length; j++) {
            
            String firstDigits = mat[i][j].substring(0,2);
            int firstDec = Integer.parseInt(firstDigits,2);
                        
            String lastDigits = mat[i][j].substring(2,4);       
            int lastDec = Integer.parseInt(lastDigits,2);
               
            mat[i][j] = sBox[firstDec][lastDec]; 
         }
            
      }
         
      return mat;
   }
	   
   public static String[][] sBox() {
      
      return new String[][] {   {"6","B","0","4"},
                                {"7","E","2","F"},
                                {"9","8","A","C"},
                                {"3","1","5","D"}  };
   }
	   
   public static void printMatrix(String[][] matrix) {
      
      for(int i = 0; i < matrix.length; i++) {
         
         for(int j = 0; j < matrix[0].length; j++) {
            
            System.out.print(matrix[i][j] + " ");  
         }
         
         System.out.println();
      }     
   }
	   	   
   public static boolean[] binaryToBoolean(String binary) {
      
      boolean[] bol = new boolean[binary.length()];
         
      for(int i = 0; i < binary.length(); i ++) {
           
         if(binary.charAt(i) == '1') {
            
            bol[i] = true;
             
         } else {
             
            bol[i] = false;
         } 
         
      }
      
      return bol;
   }
	   
   public static String booleanToBinary(boolean[] bol) {
      
      String binary = "";
       
      for(int i = 0; i < bol.length; i++) { 
         
         if(bol[i]) {
            
            binary = binary + "1";   
         } else {
            
            binary = binary + "0";
         }
         
      }
        
      return binary;
   }
	   
   public static String[][] hexToBinaryMatrix(String[][] matHex) {
      
      String[][] matBinary = new String[matHex.length][matHex[0].length];
         
      for(int i = 0; i < matHex.length; i++) {
            
         for(int j = 0; j < matHex[0].length; j++) {
              
            String hexadecimal = matHex[i][j];    
            int decimal = Integer.parseInt(hexadecimal,16); 
            String binary = Integer.toBinaryString(decimal);                       
            matBinary[i][j] = binary; 
         }
         
      }
         
      for(int i = 0; i < matBinary.length; i++) {
       	   
         for(int j = 0; j < matBinary[0].length; j++) {
         	 
            if(matBinary[i][j].length() < 4) {
            	 
               if(matBinary[i][j].length() == 1) {
               	
                  matBinary[i][j] = "000" + matBinary[i][j];
               	 
               } else if(matBinary[i][j].length() == 2) {
                
                  matBinary[i][j] = "00" + matBinary[i][j];
               
               }  else if(matBinary[i][j].length() == 3) {
                
                  matBinary[i][j] = "0" + matBinary[i][j];
               } else {
                
                  matBinary[i][j] = "0000" + matBinary[i][j];
               }
            	 
            	 
            }
         	 
         }
       	  
      }
         
      return matBinary;
   }
   
   public static String[][] binaryToHex(String[][] binaryMat) {
   
      String[][] hexMat = new String[binaryMat.length][binaryMat[0].length];
      
      for(int i = 0; i < binaryMat.length; i++) {
         
         for(int j = 0; j < binaryMat[0].length; j++) {
         
            int decimal = Integer.parseInt(binaryMat[i][j],2);
            String hexStr = Integer.toString(decimal,16);
            hexStr = hexStr.toUpperCase();
            hexMat[i][j] = hexStr;
         }
      
      }
      
      
      return hexMat;
   }

	   
   public static String[][] addXorMatrix(String[][] P, String[][] K) {
      
      String[][] Pbinary = hexToBinaryMatrix(P),Kbinary = hexToBinaryMatrix(K);
      String[][] PxORk = new String[2][2];
         
      for(int i = 0; i < PxORk.length; i++) {
       	  
         for(int j = 0; j < PxORk[0].length; j++) {
         	  
            PxORk[i][j] = twoBinaryXor(Pbinary[i][j],Kbinary[i][j]);
         }    	  
       	  
      }
      
      return PxORk;
   }
	   
   public static String twoBinaryXor(String bin1,String bin2) {
      
      boolean[] b1 = binaryToBoolean(bin1),b2 = binaryToBoolean(bin2);
      boolean[] r = new boolean[b1.length];
      
      for(int i = 0; i < b1.length; i++) {
         
         r[i] = b1[i] ^ b2[i];
      }
         
      String rBinary = booleanToBinary(r);
          
      return rBinary;
   }
	   
   public static void rotateRowKtime(String[][] mat,int row,int k) {
      
      String[] rotateArray =  leftRotate(mat[row],k);
      mat[row] = rotateArray;
   		   	     	   
   }
	   
   public static String[] leftRotate(String[] varg, int nr) {
   	   
      for(int i = 0; i < nr; i++) {
            
         rotateByOne(varg);
            
      }
   	   
      return varg;
   }
		   
   public static void rotateByOne(String[] varg) {
   	   
      int i;
      String temp;
   	   
      temp = varg[0];   
   	   
      for(i = 0; i < varg.length-1; i++) {
            
         varg[i] = varg[i+1];      
      }
   	      
      varg[i] = temp;      
   }
         
         
   public static String[][] oneToTwoDimArray(String[] array) {
   
      String[][] mat = new String[array.length/2][array.length/2];
   
      int t = 0;
   
      for(int i = 0; i < mat.length; i++) {
      
         for(int j = 0; j < mat[0].length; j++) {
         
            mat[i][j] = array[t++];  
         }
      
      }
     
      return mat;
   }
   
   public static String[] twoToOneDimArray(String[][] matrix) {
      
      String[] array = new String[matrix.length*matrix[0].length];
         
      int t = 0;
         
      for(int i = 0; i < matrix.length; i++) {
            
         for(int j = 0; j < matrix[0].length; j++) {
            
            array[t++] = matrix[i][j];
         } 
            
      }
        
      return array;
   }

}