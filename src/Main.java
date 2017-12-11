/*
 * <pre> 
 * Class: <b>Main</b> 
 * File: Main.java 
 * Course: TCSS 342 – Winter 2016
 * Assignment 2 – Evolved Names
 * Copyright 2016 Benjamin Abdipour
 * </pre>
 */

public class Main {

	public static void main(String[] args) {
		Population population = new Population(100, 0.05);
		int generationNumber = 0;

		long startTime = System.currentTimeMillis();
		while(population.mostFit == null || population.mostFit.fitness() != 0) {
			population.day();

			generationNumber++;
			System.out.println("(\"" + population.mostFit.toString() + "\", " +
					population.mostFit.fitness() + ")");
		}
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;

		System.out.println("Generations: " + generationNumber);
		System.out.println("Running Time: " + elapsedTime + " milliseconds");

		testPopulation();
		testGenome();
	}

	private static void testPopulation() {
		System.out.println("--------------------------");
		System.out.println("TESTING Population()...");
		Population population = new Population(10, 0.05);
		System.out.println("A population of 10 A's is created.");
		population.day();
		System.out.println("Fitness of \"A\" compare to \"" + population.target + "\" is "
				+ population.mostFit.fitness());
	}

	private static void testGenome() {
		Genome genome1 = new Genome(0.05);
		System.out.println("--------------------------");
		System.out.println("TESTING Genome()...");
		genome1.mutate();
		System.out.print("A new Genome will be mutated 10 times: ");
		for(int i = 0; i < 10; i++) {
			genome1.mutate();
		}
		System.out.println(genome1.toString());

		Genome genome2 = new Genome(0.05);
		System.out.print("A new Genome will be created, mutated 10 times, and then "
				+ "crossed over the old Genome: ");
		for(int i = 0; i < 10; i++) {
			genome2.mutate();
		}
		genome1.crossover(genome2);
		System.out.println(genome2.toString());
	}
}
