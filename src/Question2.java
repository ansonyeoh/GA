import java.util.Random;
import java.util.Scanner;

//Crossover
public class Question2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner cin = new Scanner(System.in);
		String a = cin.next();
		String b = cin.next();
		int c = cin.nextInt();

		for(int r = 0; r<c; r++) {
			String output = crossover(a, b);
			System.out.println(output);
		}
	}
	public static String crossover(String bits_x, String bits_y) {
		Random rn = new Random();
		int n = bits_x.length();
		int[] genesx = new int[n];
		int[] genesy = new int[n];
		int[] genesz = new int[n];
		String output = "";
		String inputx = bits_x.toString();
		String inputy = bits_y.toString();
		
		for(int i = 0; i< n; i++) {
			Character x = inputx.charAt(i);
			Character y = inputy.charAt(i);
			genesx[i] = Integer.parseInt(x.toString());
			genesy[i] = Integer.parseInt(y.toString());
		}
		
		for(int i = 0; i <n; i++) {
			if(genesx[i]!=genesy[i]) {
				int c = rn.nextInt(2);
				if (c == 1) {
					genesz[i] = 1;
				}else {
					genesz[i] = 0;
				}
				System.out.println(c);
			}else {
				genesz[i] = genesx[i];
			}
		}
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < n ;i++) {
			sb.append(genesz[i]);
		}
		output = sb.toString();	
		
		return output;
	}
}
