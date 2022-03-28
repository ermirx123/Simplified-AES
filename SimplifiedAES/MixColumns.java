public class MixColumns {

   public static void main(String[] args) {
        
   
       
       //    String[][] mat1 = {{"F","F"},{"F","F"}},mat2 = {{"F","F"},{"F","F"}};                                   
       //    String[][] mat3 = {{"A","2"},{"0","E"}},mat4 = {{"5","1"},{"8","0"}}; 
       
      String[][] mat1 = {{"1","1"},{"1","2"}};
      String[][] mat3 = {{"B","7"},{"2","E"}};
            
      printMatrix(multiply(mat1,mat3));
      //   System.out.println("\n");
        // printMatrix(multiply(mat3,mat4));
          
            
   }
	   
   public static String[][] multiply(String mat1[][],String mat2[][]) { 
           
      String res[][] = new String[2][2];
           
      int i, j, k;
           
      for (i = 0; i < 2; i++) { 
           
         for (j = 0; j < 2; j++) { 
               
            res[i][j] = ""; 
                  
            for (k = 0; k < 2; k++)  {
                   
               String re = multiply(mat1[i][k],mat2[k][j]);
               res[i][j] = add(res[i][j],re); 
            } 
         }
      } 
           
      return res;
   } 

	   
   public static String add(String hex1,String hex2) {
      
      int dec1 = 0,dec2 = 0;
         
      if(hex1.length() != 0) {
         
         dec1 = Integer.parseInt(hex1,16);
      }    
         
      if(hex2.length() != 0) {
             
         dec2 = Integer.parseInt(hex2,16);
      }
         
      String bin1 = Integer.toBinaryString(dec1),bin2 = Integer.toBinaryString(dec2);
         
      String binResult = twoBinaryXor(bin1,bin2);            
      int binResultDec = Integer.parseInt(binResult,2);
      String hexResultDec = Integer.toHexString(binResultDec);
         
      return hexResultDec.toUpperCase();
   }      
	   
	   
   public static String twoBinaryXor(String bin1,String bin2) {
        
      String binary1 = appendZeros(bin1);
      String binary2 = appendZeros(bin2);      
      
      boolean[] b1 = binaryToBoolean(binary1),b2 = binaryToBoolean(binary2);
      boolean[] r = new boolean[b1.length];
         
      for(int i = 0; i < b1.length; i++) {
            
         r[i] = b1[i]^b2[i];
      }
            
      String rBinary = booleanToBinary(r);
             
      return rBinary;
   }
	   
   public static String appendZeros(String binary) {
      
      if(binary.length() < 4) {
          			 
         if(binary.length() == 3) {
            			
            binary = "0" + binary;
            			 
         } else if(binary.length() == 2) {
            		 
            binary = "00" + binary;
            	 
         } else if(binary.length() == 1) {
            		 
            binary = "000" + binary;
               
         } else {
            
            binary = "0000" + binary;
            
         }
                
      }
         
      return binary;      
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
	   
	   
	   
   public static void printMatrix(String[][] matrix) {
      
      for(int i = 0; i < matrix.length; i++) {
         
         for(int j = 0; j < matrix[0].length; j++) {
            
            System.out.print(matrix[i][j] + " ");  
         }
         
         System.out.println();
      }     
   }
	   
   public static String multiply(String hex1, String hex2) {
      
      String[] varg = {"1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
         
      int row = 0,col = 0;
         
      for(int i = 0; i < varg.length; i++) {
          
         if(varg[i] == hex1) {
            
            row = i;
         }  
         if(varg[i] == hex2) {
            
            col = i;
         }
      }
         
      if(hex1.equals("0") || hex2.equals("0") ) {
         
         return "0";
      }
      
      String[][] multiTable = 
            {
            {"1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"},
            {"2","4","6","8","A","C","E","3","1","7","5","B","9","F","D"},
            {"3","6","5","C","F","A","9","B","8","D","E","7","4","1","2"},
            {"4","8","C","3","7","B","F","6","2","E","A","5","1","D","9"},
            {"5","A","F","7","2","D","8","E","B","4","1","9","C","3","6"},
            {"6","C","A","B","D","7","1","5","3","9","F","E","8","2","4"},
            {"7","E","9","F","8","1","6","D","A","3","4","2","5","C","B"},
            {"8","3","B","6","E","5","D","C","4","F","7","A","2","9","1"},
            {"9","1","8","2","B","3","A","4","D","5","C","6","F","7","E"},
            {"A","7","D","E","4","9","3","F","5","8","2","1","B","6","C"},
            {"B","5","E","A","1","F","4","7","C","2","9","D","6","8","3"},
            {"C","B","7","5","9","E","2","A","6","1","D","F","3","4","8"},
            {"D","9","4","1","C","8","5","2","F","B","6","3","E","A","7"},
            {"E","F","1","D","3","2","C","9","7","6","8","4","A","B","5"},
            {"F","D","2","9","6","4","B","1","E","C","3","8","7","5","A"}
            };   
            
      return multiTable[row][col];
   }
	   
   public static void printAddTable() {
      
      String[] varg = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
         
      for(int i = 0; i < varg.length; i++) {
            
         for(int j = 0; j < varg.length; j++) {
               
            System.out.print(add(varg[i],varg[j]) + " ");
         }
         System.out.println();
      }
      
   }
	   
   public static void printMultiplyTable() {
      
      String[] varg = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
         
      for(int i = 1; i < varg.length; i++) {
            
         for(int j = 1; j < varg.length; j++) {
               
            System.out.print(multiply(varg[i],varg[j]) + " ");
         }
         System.out.println();
      }
      
   } 


}