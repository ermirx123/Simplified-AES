import java.util.*;

public class KeyGeneration {
   
   public static void main(String[] args) {
    
      String[] K0 = {"1011","0101","1101","1111"};
   
      String[] K1 = round(K0);   
      String[] K2 = round(K1);
      String[] K3 = round(K2);  
   
      System.out.println(Arrays.toString(K1));
      System.out.println(Arrays.toString(K2));
      System.out.println(Arrays.toString(K3));
      
   }
   
   public static String[] threeRounds(String[] key0) {
	   
	      String[] key1 = round(key0);
	      String[] key2 = round(key1);
	      String[] key3 = round(key2);
	      
	      return key3;
	   }
   
   public static String[] round(String[] keyI) {
   
   
      boolean[] k0N = binaryToBoolean(keyI[0]); 
      boolean[] k1N = binaryToBoolean(keyI[1]); 
      boolean[] k2N = binaryToBoolean(keyI[2]); 
      boolean[] k3N = binaryToBoolean(keyI[3]); 
      
      boolean[] gFk3N = binaryToBoolean(gFunction(booleanToBinary(k3N)));
      
      boolean[] k0Nx_or_gFk3N = new boolean[4];
      
      for(int i = 0; i < 4; i++) {
        
         k0Nx_or_gFk3N[i] = k0N[i] ^ gFk3N[i];
      }
      
      boolean[] k0Nx_or_gFk3N_xOr_k1N = new boolean[4];
      
      for(int i = 0; i < 4; i++) {
        
         k0Nx_or_gFk3N_xOr_k1N[i] = k0Nx_or_gFk3N[i] ^ k1N[i];
      }
      
      boolean[] k0Nx_or_gFk3N_xOr_k1N_xOr_k2N = new boolean[4];
      
      for(int i = 0; i < 4; i++) {
      
         k0Nx_or_gFk3N_xOr_k1N_xOr_k2N[i] = k0Nx_or_gFk3N_xOr_k1N[i] ^ k2N[i];
      }
      
      boolean[] k0Nx_or_gFk3N_xOr_k1N_xOr_k2N_xOr_k3n = new boolean[4];
      
      for(int i = 0; i < 4; i++) {
      
         k0Nx_or_gFk3N_xOr_k1N_xOr_k2N_xOr_k3n[i] = k0Nx_or_gFk3N_xOr_k1N_xOr_k2N[i] ^ k3N[i];
      }
   
      String k0Next = booleanToBinary(k0Nx_or_gFk3N); 
      String k1Next = booleanToBinary(k0Nx_or_gFk3N_xOr_k1N); 
      String k2Next = booleanToBinary(k0Nx_or_gFk3N_xOr_k1N_xOr_k2N); 
      String k3Next = booleanToBinary(k0Nx_or_gFk3N_xOr_k1N_xOr_k2N_xOr_k3n);
      
      String[] res = new String[4];
      
      res[0] = k0Next;
      res[1] = k1Next;
      res[2] = k2Next;
      res[3] = k3Next;
   
      return res;
   }
   
   public static String gFunction(String binary) {
   
      boolean[] varg = binaryToBoolean(binary);
      boolean[] rotatedVarg = rotateByOne(varg);
      String rotatedString = booleanToBinary(rotatedVarg);
      String sBoxOperation = sBoxOperation(rotatedString);
      
      boolean[] varg2 = binaryToBoolean(sBoxOperation);
      
      boolean[][] RC = {{false,false,false,true},
                        {false,false,true,false},
                        {false,true,false,false}};
                        
      boolean[] RC1 = RC[0];
      boolean[] RC2 = RC[1];
      boolean[] RC3 = RC[2];
      
      
      boolean[] R = new boolean[4];
      
      for(int i = 0; i < 4; i++) {
      
         R[i] = varg2[i] ^ RC1[i] ^ RC2[i] ^ RC3[i];
      }
      
      String strR = booleanToBinary(R);                   
   
      return strR;
   } 
   
   public static String sBoxOperation(String binary) {
   	   
      String[][] sBox = sBox();
      
      String first = binary.substring(0,2);
      int decFirst = Integer.parseInt(first,2);
      
      String last = binary.substring(2,4);
      int decLast = Integer.parseInt(last,2);
      
      String vl = sBox[decFirst][decLast];      
         
      return hexToBinary(vl);
   }
	   
   public static String[][] sBox() {
      
      return new String[][] { {"6","B","0","4"},
                              {"7","E","2","F"},
                              {"9","8","A","C"},
                              {"3","1","5","D"}  };
   }
   
   public static String hexToBinary(String hex) {
      
      String hexadecimal = hex;    
      int decimal = Integer.parseInt(hexadecimal,16); 
      String binary = appendZeros(Integer.toBinaryString(decimal));
      
      
      return binary;
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
   
   public static boolean[] rotateByOne(boolean[] varg) {
   	   
      int i;
      boolean temp;
   	   
      temp = varg[0];   
   	   
      for(i = 0; i < varg.length-1; i++) {
            
         varg[i] = varg[i+1];      
      }
   	      
      varg[i] = temp;
      
      return varg;      
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

   
   

}