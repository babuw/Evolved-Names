/*
 * <pre> 
 * Class: <b>Genome</b> 
 * File: Genome.java 
 * Course: TCSS 342 – Winter 2016
 * Assignment 2 – Evolved Names
 * Copyright 2016 Benjamin Abdipour
 * </pre>
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Genome {
	private LinkedList<String> myGenome;
	private double myMutationRate;
	private final String[] myCharList = {"A", "B", "C", "D", "E", "F", "G", "H", "I"
			, "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W"
			, "X", "Y", "Z", " ", "-", "’"};
	private final String myDefaultValue = "A";
	private final String target = new Population(0, 0.0).target;
	private final String[] arrTarget = arrayMaker(target);
	
	public String[] arrayMaker(String input) {
		String[] arrTargetLocal = new String[input.length()];
		for(int i = 0; i < input.length(); i++) {
			arrTargetLocal[i] = String.valueOf(input.charAt(i));
		}
		
		return arrTargetLocal;
	}

	Genome(double mutationRate) {
		myGenome = new LinkedList<String>();
		myGenome.add(myDefaultValue);
		myMutationRate = mutationRate;
	}

	Genome(Genome gene) {
		myMutationRate = 0.05;
		myGenome = new LinkedList<String>();
		for(int i = 0; i < gene.toString().length(); i++) {
			String genomeChar = String.valueOf(gene.toString().charAt(i));
			myGenome.add(genomeChar);
		}
	}

	public void mutate() {
		Random rnd = new Random();
		if(rnd.nextDouble() < myMutationRate) {
			myGenome.add(rnd(0, myGenome.size()), myCharList[rnd(0, myCharList.length - 1)]);
		}

		if(rnd.nextDouble() < myMutationRate && myGenome.size() > 1) {
			myGenome.remove(rnd(0, myGenome.size() - 1));
		}

		for(int i = 0; i < myGenome.size(); i++) {
			if(rnd.nextDouble() < myMutationRate) {
				myGenome.set(i, myCharList[rnd(0, myCharList.length - 1)]);
			}
		}
	}

	public void crossover(Genome other) {
		ArrayList<String> bothGenomes = new ArrayList<String>();
		bothGenomes.add(myToString(myGenome));
		bothGenomes.add(other.toString());

		int i = 0;
		boolean done = false;
		LinkedList<String> myGenomeTemp = new LinkedList<String>();

		while(!done) {
			int rndSelection = rnd(0, 1);
			try{
				if(String.valueOf(bothGenomes.get(rndSelection).charAt(i)) != null) {
					myGenomeTemp.add(String.valueOf(bothGenomes.get(rndSelection).charAt(i)));
					i++;
				}
			} catch (IndexOutOfBoundsException ex) {
				done = true;
			}
		}

		for(int j = 0; j < myGenome.size(); j++) {
			myGenome.remove(j);
		}
		myGenome = myGenomeTemp;
	}

	public String myToString(LinkedList<String> theList) {
		String output = "";
		for(int i = 0; i < theList.size(); i++) {
			output += theList.get(i);
		}
		return output;
	}

	public Integer fitness() {

		//instruction method
		int n = myGenome.size();
		int m = arrTarget.length;
		int l = Math.max(n, m);
		int f = Math.abs(m - n);
		f += l;

		for(int i = 0; i < Math.min(n, m); i++) {
			if(myGenome.get(i).equals(arrTarget[i])) {
				f--;
			}
		}

		return f;

		////////////////////////////////////////////////////////////////////////////////////////////////
		//////////extra credit method, Wagner-Fischer algorithm//////////

//				int n = myGenome.size();
//				int m = arrTarget.length;
//				int[][] D = new int[n + 1][m + 1];
//				for(int i = 0; i < m + 1; i++) {
//					D[0][i] = i;
//				}
//		
//				for(int i = 0; i < n + 1; i++) {
//					D[i][0] = i;
//				}
//		
//				for(int i = 1; i <= n; i++) {
//					for(int j = 1; j <= m; j++) {
//						if(myGenome.get(i - 1).equals(String.valueOf(arrTarget[j - 1]))) {
//							D [i][j]= D [i - 1][j - 1];
//						} else {
//							D [i][j] = Math.min(Math.min(D [i - 1][j] + 1 , D[i][j - 1 ] + 1 ) , D[i - 1][j - 1 ]+ 1);
//						}
//					}
//				}
//		
//				return D[n][m] + (int)(Math.abs(n - m) + 1) / 2;

		////////////////////////////////////////////////////////////////////////////////////////////////
	}

	public String toString() {
		String output = "";
		for(Object strObject : myGenome) {
			output += String.valueOf(strObject);
		}

		return output;
	}

	public int rnd(int theMin, int theMax) {
		Random rnd = new Random();
		return rnd.nextInt(theMax - theMin + 1) + theMin;
	}
}
