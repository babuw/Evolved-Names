/*
 * <pre> 
 * Class: <b>Population</b> 
 * File: Population.java 
 * Course: TCSS 342 – Winter 2016
 * Assignment 2 – Evolved Names
 * Copyright 2016 Benjamin Abdipour
 * </pre>
 */

import java.util.LinkedList;

public class Population {

	public final String target = "CHRISTOPHER PAUL MARRIOTT";
//	public final String target = "Benjamin ABDIPOUR";
	public Genome mostFit;
	private double myMutationRate;
	private int myNumGenomes;
	private LinkedList<Genome> genomePopulation;

	public Population(Integer numGenomes , Double mutationRate) {
		mostFit = null;
		myNumGenomes = numGenomes;
		myMutationRate = mutationRate;

		genomePopulation = new LinkedList<Genome>();
		for(int i = 0; i < myNumGenomes; i++) {
			genomePopulation.add(new Genome(mutationRate));
		}
	}

	public void day() {
		sort();
		mostFit = genomePopulation.get(0);
		for(int i = (int) (myNumGenomes / 2); i < myNumGenomes; i++) {
			Genome myGenome = new Genome(myMutationRate);
			int actionType = myGenome.rnd(1, 2);
			switch(actionType) {
			case 1:
				Genome tempGenome1 = new Genome(genomePopulation.get(myGenome.rnd(0,
						(int) (myNumGenomes / 2) - 1)));
				tempGenome1.mutate();
				genomePopulation.set(i, tempGenome1);
				break;
			case 2:
				Genome tempGenome2 = new Genome(genomePopulation.get(myGenome.rnd(0,
						(int) (myNumGenomes / 2) - 1)));
				tempGenome2.crossover(genomePopulation.get(myGenome.rnd(0,
						(int) (myNumGenomes / 2) - 1)));
				tempGenome2.mutate();
				genomePopulation.set(i, tempGenome2);
				break;
			}
		}
	}

	public void sort() {
		boolean sorted = false;
		while(!sorted) {
			sorted = true;
			for(int i = 0; i < myNumGenomes - 1; i++) {
				if(genomePopulation.get(i).fitness() > genomePopulation.get(i + 1).fitness()) {
					Genome tempGenome = genomePopulation.get(i);
					genomePopulation.set(i, genomePopulation.get(i + 1));
					genomePopulation.set(i + 1, tempGenome);
					sorted = false;
				}
			}
		}
	}
}