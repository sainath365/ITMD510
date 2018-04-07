import java.util.ArrayList;

public class test{
	   
   public static void main(String[] args) {
      
	   int grades[] = { 60, 70, 75, 100, 61, 83, 90, 89, 72, 91};
	   int counter[] = new int[grades.length + 1];
	   for (int i = 0; i < grades.length; ++i) { // loop over entire array
		   if (grades[i] == 100) // account for the odd ball {
			   counter[10]++;
		 else
		  System.out.println(counter[(grades[i] % 100) / 10]++);
		}
	   String[] ranges = {"00-09: ", "10-19: ", "20-29: ", "30-39: ", "40-49: ",
			   "50-59: ", "60-69: ", "70-79: ", "80-89: ", "90-99: ", "100: " };
		// show the bar chart per the range above
	   for(int i =0; i<=10; i++) {
		
		if (counter[i]!=0) {
			System.out.print(ranges[i]);
			int num = counter[i];
			while (num !=0) {
				System.out.print("*");
				num--;
			}
			System.out.print("\n");
		}else {
			System.out.println(ranges[i]);
		}
		    
		   
	   }
	   /*
	   System.out.print("00-09: ");
	   
	   
	   System.out.println("10-19: "+ counter[1]);
	   System.out.println("20-29: "+ counter[2]);
	   System.out.println("30-39: "+ counter[3]);
	   System.out.println("40-49: "+ counter[4]);
	   System.out.println("50-59: "+ counter[5]);
	   System.out.println("60-69: "+ counter[6]);
	   System.out.println("70-79: "+ counter[7]);
	   System.out.println("80-89: "+ counter[8]);
	   System.out.println("90-99: "+ counter[9]);
	   System.out.println("100: "+ counter[10]);
*/
	   
    }
}



