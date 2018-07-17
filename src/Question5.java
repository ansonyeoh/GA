import java.util.Random;
import java.util.Scanner;

public class Question5 {
   
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		double chi = cin.nextDouble();
		int n = cin.nextInt();
		int lambda = cin.nextInt();
		int k = cin.nextInt();
		int repetitions = cin.nextInt();
		
		String[] individual = new String[lambda];
	
		String xbest = null;
		float fbest = 0;
		
		double rate = chi/n;
		//Repeat
		for(int c = 0; c < repetitions; c++) {
			int t = 0;
			individual = initialize(n, lambda);	
			//Generation
			while (ONEMAX(getfittestindividual(individual)) < n) {
				t++;
				individual = evolve(individual, lambda, k, repetitions, rate);
			}
			xbest = getfittestindividual(individual);
			fbest = ONEMAX(xbest);
			//System.out.println(t);
			System.out.println(n + " " + chi + " " + lambda + " " + k + " " + t + " " + fbest + " " + xbest);
		}
	}
	
	public static String[] initialize(int n, int lambda) {
		String[] population = new String[lambda];
		int[] chrome = new int[n];
		
		for(int i=0; i < lambda; i++) {
			for(int r = 0; r < n; r++) {
				Random rn = new Random();
				int c = rn.nextInt(2);
				if(c == 1) {
					chrome[r] = 0;
				}else {
					chrome[r] = 1;
				}
			}
			
			StringBuffer sb = new StringBuffer();
			for(int t = 0; t < n ;t++) {
				sb.append(chrome[t]);
			}
			String individual = sb.toString();
			//System.out.println(individual);
			population[i] = individual;
		}
		
		return population;
	}

	public static String mutation(String bits_x, double chi) {
		int n = bits_x.length();
		int[] genes = new int[n];
		String output = "";
		String input = bits_x.toString();
		
		for(int i = 0; i< n; i++) {
			Character ch = input.charAt(i);
			genes[i] = Integer.parseInt(ch.toString());
		}
		
		for(int i = 0; i<n; i++) {
			if(Math.random()<= chi) {
				
				if(genes[i] == 0) {
					genes[i] = 1;
				}else {
					genes[i] = 0;
				}
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
			}else {
				genesz[i] = genesx[i];
			}
		}
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < n ;i++) {
			sb.append(genesz[i]);
		}
		output = sb.toString();
		//System.out.println(output);
	
		return output;
	}

	public static String tournamentSelect(String[] population, int k) {
		Random rn = new Random();
		String output = "";
		int[] num = new int[k];
		int n = population.length;
		
	    for (int i = 0; i < k; i++) {
	    	int c = rn.nextInt(n);
	      	num[i] = c;
	       	if(ONEMAX(population[c])>ONEMAX(population[num[0]])) {
	       		num[0] = c;
        	}
        }
        output = population[num[0]];
	    //System.out.println(population[num[0]]);
		
		return output;
	}
	
	public static int ONEMAX(String bits_x) {
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
	
	public static String getfittestindividual(String[] population) {
		String xbest = "";
		int b = 0;
		for(int i=0; i<population.length;i++) {
			if(ONEMAX(population[i])> ONEMAX(population[b])) {
				b = i;
				xbest = population[i];
			}
		}
		return xbest;
	}
	

	public static String[] evolve(String[] generation, int lambda, int k, int repetitions, double chi) {
			String x, y, mutation_x, mutation_y;
			String[] gbest = new String[lambda];
			
			//One generation
			for(int i= 0; i < lambda; i++) {
				//Select
				x = tournamentSelect(generation, k);
				y = tournamentSelect(generation, k);
			
				//Mutation
				mutation_x = mutation(x, chi);
				mutation_y = mutation(y, chi);
			
				//Crossover
				gbest[i] = crossover(mutation_x, mutation_y);	
			}	
		return gbest;
	}

}
