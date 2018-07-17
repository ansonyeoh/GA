import java.util.Random;
import java.util.Scanner;

public class Question4 {

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		String a = cin.nextLine();
		int b = cin.nextInt();
		int c = cin.nextInt();
		for(int i = 0; i< c; i++) {
			String output = tournamentSelect(a,b);
			System.out.println(output);
		}		
	}
	
	public static String tournamentSelect(String population, int k) {
		Random rn = new Random();
		String output = "";
		int[] num = new int[k];
		String[] individual = population.split(" "); 
		int n = individual.length;
		
	    for (int i = 0; i < k; i++) {
	       	int c = rn.nextInt(n);
	       	num[i] = c;
	       	if(ONEMAX(individual[c])>ONEMAX(individual[num[0]])) {
	       		num[0] = c;
        	}
	    }
	    output = individual[num[0]];
        //System.out.println(individual[num[0]]);

		return output;
	}

	public static int ONEMAX(String bits_x) {
		Random rn = new Random();
		int n = bits_x.length();
		int[] genes = new int[n];
		String input = bits_x.toString();
		int sum = 0;
		
		for(int i = 0; i < n; i++) {
			Character ch = input.charAt(i);
			genes[i] = Integer.parseInt(ch.toString());
			sum = sum + genes[i];
		}
		//System.out.print(sum);
		return sum;
	} 
}
