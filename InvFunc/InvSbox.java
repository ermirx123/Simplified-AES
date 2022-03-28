public class InvSbox  {

   public static void main(String[] args) {
     
  
      String[][] Pt = {{"A","8"},{"C","3"}};  
      String[][] Pt2 = {{"A","B"},{"C","D"}};  
      
      String[][] encryptIt = sBoxOperations(Pt);      
      printMatrix(encryptIt);      
      System.out.println();      
      String[][] decryptIt = sBoxOperationsInverse(encryptIt);       
      printMatrix(decryptIt); 
     
   }
   
   
      
   public static String[][] sBoxOperationsInverse(String[][] encryptedMat) {
   
      String[][] sBox = sBox();
      
      String[] encryptedArray = twoToOneDimArray(encryptedMat);
      
      String[] decrpytedArray = new String[4];
      int t = 0;
      
      for(int k = 0; k < 4; k++) {
      
         String val = encryptedArray[k];  
      
         for(int i = 0; i < sBox.length; i++) {
         
            for(int j = 0; j < sBox[0].length; j++) {
            
               if(sBox[i][j] == val) { 
               
                  decrpytedArray[t++] = convertIt(i,j); 
               } 
               
            }
         
         }
      
      }      
      
      String[][] decyptedMatrix = oneToTwoDimArray(decrpytedArray);
     
      return decyptedMatrix;
   }
   public static String convertIt(int dec1,int dec2) {
   
      String bin1 = appendZeros2(Integer.toBinaryString(dec1));
      String bin2 = appendZeros2(Integer.toBinaryString(dec2));
   
      String bin = bin1+bin2;
   
      int dec = Integer.parseInt(bin,2);  
   
      String hexStr = Integer.toString(dec,16);
      hexStr = hexStr.toUpperCase();
     
      return hexStr;
   }
   
   public static String[][] sBoxOperations(String[][] mat) {
      
      mat = hexToBinaryMatrix(mat);
   	   
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
      
      return new String[][] {    {"6","B","0","4"},
                                 {"7","E","2","F"},
                                 {"9","8","A","C"},
                                 {"3","1","5","D"}  };
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

	   
   public static void printMatrix(String[][] matrix) {
      
      for(int i = 0; i < matrix.length; i++) {
         
         for(int j = 0; j < matrix[0].length; j++) {
            
            System.out.print(matrix[i][j] + " ");  
         }
         
         System.out.println();
      }     
   }
   
  
   
   
   public static String appendZeros2(String binary) {
      
      if(binary.length() < 2) {
          			 
         if(binary.length() == 1) {
            		 
            binary = "0" + binary;
               
         } else {
            
            binary = "00" + binary;
            
         }
                
      }
         
      return binary;      
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