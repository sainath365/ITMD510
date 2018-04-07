import java.util.ArrayList;

public class X
{
   public static void main(String[]args)throws Exception 
   {
	   int grades[] = { 60, 70, 75, 100, 61, 83, 90, 89, 72, 91 };
		int counter[] = new int[grades.length + 1];
		for (int i = 0; i < grades.length; ++i) { // loop over entire array
	    // inspect each element and increment the index
		 // if the grade is in a certain range (ex. 0-9, 10-19…)
		 if (grades[i] == 100) // account for the odd ball {
		  counter[10]++;
		 else
		  counter[(grades[i] % 100) / 10]++;
		}
		// show the bar chart per the range above
	 
	   String[] ranges = {"00-09: ", "10-19: ", "20-29: ", "30-39: ", "40-49: ",
				   "50-59: ", "60-69: ", "70-79: ", "80-89: ", "90-99: ", "100: " };
			// show the bar chart per the range above
		   for(int i =0; i<=grades.length; i++) {
			
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
   }
   
   public static void popList(ArrayList<Number> list) {
   	 
	for(int i=1; i<=5; i++) {
   		list.add(i);
   		
   	}

}
}

