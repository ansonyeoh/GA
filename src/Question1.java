import java.util.Random;
import java.util.Scanner;

//Mutation
public class Question1 {
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner cin = new Scanner(System.in);
		String a = cin.next();
		double b = cin.nextDouble();
		int c = cin.nextInt();
		
		for(int i =0; i < c; i++) {
			String output = mutation(a, b);
			System.out.println(output);
		}
	}
	
	public static String mutation(String bits_x, double chi) {
		Random rn = new Random();
		int n = bits_x.length();
		int[] genes = new int[n];
		String output = "";
		String input = bits_x.toString();
		
		for(int i = 0; i< n; i++) {
			Character ch = input.charAt(i);
			genes[i] = Integer.parseInt(ch.toString());
		}
		
		chi = chi/n;
		
		for(int i = 0; i<n; i++) {
			if(Math.random()<= chi) {					
				if(genes[i] == 0) {
					genes[i] = 1;
				}else {
					genes[i] = 0;
				}
				//System.out.println(i);
			}
		}
		
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < n ;i++) {
			sb.append(genes[i]);
		}
		output = sb.toString();
		//System.out.println(output);
		
		return output;
	}
	
}
