

public class InvShiftRows {

   public static void main(String[] args) {
   
      String[][] Pt = {{"A","8"},{"C","3"}};
      
      String[] row = Pt[1];  rotateByOneLeft(row); Pt[1] = row;
      printMatrix(Pt);
      
      System.out.println();
      
      row = rotateByOneRight(row); Pt[1] = row;
      printMatrix(Pt);
      

   }
   
   
   

   public static void rotateRowKtime(String[][] mat,int row,int k,boolean leftRight) {
   
      if(leftRight) {
      
         String[] rotateArray = leftRotate(mat[row],k);
         mat[row] = rotateArray;
      
      } else {
      
         String[] rotateArray = rightRotate(mat[row],k);
         mat[row] = rotateArray;
      
      }
    		   	     	   
   }
	   
   public static String[] leftRotate(String[] varg, int nr) {
      
      for(int i = 0; i < nr; i++) {
            
         rotateByOneLeft(varg);
            
      }
         
      return varg;  
   }
   
   public static String[] rightRotate(String[] varg, int nr) {
     
      String[] rVarg = new String[varg.length];
      
      for(int i = 0; i < nr; i++) {
            
         rVarg = rotateByOneRight(varg);
            
      }
         
      return rVarg;  
   }
   
		   
   public static void rotateByOneLeft(String[] varg) {
   	   
      int i;
      String temp;
   	   
      temp = varg[0];   
   	   
      for(i = 0; i < varg.length-1; i++) {
            
         varg[i] = varg[i+1];      
      }
   	      
      varg[i] = temp;      
   }
   
   public static String[] rotateByOneRight(String[] varg) {
   	
      String[] rVarg = new String[varg.length];   
      String temp;
   	   
      temp = varg[varg.length-1];   
   	   
      for(int i = 1; i < varg.length; i++) {
            
         rVarg[i] = varg[i-1];      
      }
   	      
      rVarg[0] = temp;
      
      return rVarg;      
   }
   
   public static void printMatrix(String[][] matrix) {
      
      for(int i = 0; i < matrix.length; i++) {
         
         for(int j = 0; j < matrix[0].length; j++) {
            
            System.out.print(matrix[i][j] + " ");  
         }
         
         System.out.println();
      }     
   }



}