import java.util.Random;
import java.util.Scanner;

//ONEMAX fitness calculate
public class Question3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner cin = new Scanner(System.in);
		String a = cin.next();
		ONEMAX(a);
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
		System.out.print(sum);
		return sum;
	} 

}
