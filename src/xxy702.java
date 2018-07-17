import java.util.Random;
import java.util.Scanner;

public class xxy702 {
   
	public static void main(String[] args) {
		double chi=0;
		int n=0;
		int lambda=0;
		int k=0;
		int repetitions=0;
		String bits_x ="";
		String bits_y ="";
		String population ="";
		
		if(Integer.parseInt(args[1])==1) {
			for(int i=0;i<args.length;i++){
				if(args[i].equals("-chi")){
					chi = Double.parseDouble(args[i+1]);
				}else if(args[i].equals("-bits_x")){
					bits_x = args[i+1];
				}else if(args[i].equals("-repetitions")){
					repetitions = Integer.parseInt(args[i+1]);
				} 
			}
			int w = bits_x.length();
			chi = chi/w;
			for(int j=0; j<repetitions;j++) {
				System.out.println(question1(bits_x, chi));
			}
			//System.out.print(chi + " "+bits_x +" "+ repetitions+" "+w);
		}else if(Integer.parseInt(args[1])==2) {
			for(int i=0;i<args.length;i++){
				if(args[i].equals("-bits_y")){
					bits_y = args[i+1];
				}else if(args[i].equals("-bits_x")){
					bits_x = args[i+1];
				}else if(args[i].equals("-repetitions")){
					repetitions = Integer.parseInt(args[i+1]);
				} 
			}
			for(int j=0; j<repetitions;j++) {
				System.out.println(question2(bits_x, bits_y));
			}
		}else if(Integer.parseInt(args[1])==3) {
			bits_x = args[3];
			System.out.println(question3(bits_x));
		}else if(Integer.parseInt(args[1])==4) {

			for(int i=0;i<args.length;i++){
				if(args[i].equals("-population")){
					population = args[i+1];
				}else if(args[i].equals("-k")){
					k = Integer.parseInt(args[i+1]);
				}else if(args[i].equals("-repetitions")){
					repetitions = Integer.parseInt(args[i+1]);
				}
			}
			String[] generation = population.split(" ");
			for (int i = 0; i<repetitions; i++) {
				System.out.println(question4(generation,k));
			}
		}else if(Integer.parseInt(args[1])==5) {
			for(int i=0;i<args.length;i++){
				if(args[i].equals("-chi")){
					chi = Double.parseDouble(args[i+1]);
				}else if(args[i].equals("-n")){
					n = Integer.parseInt(args[i+1]);
				}else if(args[i].equals("-lambda")){
					lambda = Integer.parseInt(args[i+1]);
				}else if(args[i].equals("-k")){
					k = Integer.parseInt(args[i+1]);
				}else if(args[i].equals("-repetitions")){
					repetitions = Integer.parseInt(args[i+1]);
				} 
			
			}
			for(int j=0; j<repetitions;j++) {
				question5(chi, n, lambda, k, repetitions);
			}
		}
	
	}
	
	public static void question5(double chi, int n, int lambda, int k, int repetitions) {
		
		String[] individual = new String[lambda];
		int runtime= 0;
		String xbest = null;
		float fbest = 0;
		
		double rate = chi/n;	
		int t = 0;
		individual = initialize(n, lambda);	
		//Generation
				
		while (question3(getfittestindividual(individual)) < n) {
			if(t<5000) {
				t++;
				individual = evolve(individual, lambda, k, repetitions, rate);
			}else break;
		}		
				
		xbest = getfittestindividual(individual);
		fbest = question3(xbest);
		System.out.println(String.valueOf(n) + "	" + String.valueOf(chi) + "	" + String.valueOf(lambda) + "	" + String.valueOf(k) + "	" + String.valueOf(t) + "	" + String.valueOf(fbest) + "	" + String.valueOf(xbest));	
	}
	
	public static String[] initialize(int n, int lambda) {
		String[] population = new String[lambda];
		int[] chrome = new int[n];
		String individual;
		
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
			individual = sb.toString();
			//System.out.println(individual);
			population[i] = individual;
		}
		
		return population;
	}

	public static String question1(String bits_x, double chi) {
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

	public static String question2(String bits_x, String bits_y) {
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

	public static String question4(String[] population, int k) {
		Random rn = new Random();
		String output = "";
		int[] num = new int[k];
		int n = population.length;
		
	    for (int i = 0; i < k; i++) {
	    	int c = rn.nextInt(n);
	      	num[i] = c;
	       	if(question3(population[c])>question3(population[num[0]])) {
	       		num[0] = c;
        	}
        }
        output = population[num[0]];
	       //System.out.println(population[num[0]]);
		
		return output;
	}
	
	public static int question3(String bits_x) {
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
			if(question3(population[i])> question3(population[b])) {
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
				x = question4(generation, k);
				y = question4(generation, k);
			
				//Mutation
				mutation_x = question1(x, chi);
				mutation_y = question1(y, chi);
			
				//Crossover
				gbest[i] = question2(mutation_x, mutation_y);	
			}	
		return gbest;
	}

}
