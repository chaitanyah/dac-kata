package com.kata.dac.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.kata.dac.models.Node;

public class DACDiagram {
	
	private static final String RUM_COLA = "Rum & Cola";
	private static final String LAGER = "Lager";
	private static final String DRINKS = "Drinks";
	private static final String NON_ALCOHOLIC = "Non Alcoholic";
	private static final String COLA = "Cola";
	private static final String SPARKLING_WATER = "Sparkling Water";
	private static final String ALCOHOLIC = "Alcoholic";
	private static final String RUM = "Rum";
	private static final String BEER = "Beer";
	private static final String WINE = "Wine";
	private static final String WHITE_WINE = "White wine";
	private static final String SANGRIA = "Sangria";

	public static void main(String args[]) {
		
		/*1. Create the DAC */
		Node sangria = new Node(SANGRIA);
		Node whiteWine = new Node(WHITE_WINE,sangria);
		Node wine = new Node(WINE,whiteWine);
		Node beer = new Node(BEER);
		Node rum = new Node(RUM);
		Node alcoholic = new Node(ALCOHOLIC,wine, beer, rum);
		Node cola = new Node(COLA);
		Node sparklingWater = new Node(SPARKLING_WATER,sangria);
		Node nonAlcoholic = new Node(NON_ALCOHOLIC,cola, sparklingWater);
		Node drinks = new Node(DRINKS,nonAlcoholic, alcoholic);
		
		Node rootNode = drinks;
		
		/*2. Check if drink exists */
		boolean isDrinkAvailable = isDrinkAvailable(rootNode, SANGRIA);
		System.out.println("Sangria available: " + isDrinkAvailable);
		
		isDrinkAvailable = isDrinkAvailable(rootNode, "fgdsfgdsfg");
		System.out.println("fgdsfgdsfg available: " + isDrinkAvailable);
		
		/* 3. Add new node */
		Node lager = new Node(LAGER);
		beer.addChild(lager);
		System.out.println(beer.getChildren().size());
		
		/*4. Add rum & cola */
		Node rumAndCola = new Node(RUM_COLA);
		rum.addChild(rumAndCola);
		cola.addChild(rumAndCola);
		
		/*5. Find nodes */
		
		Set<Node> nodes = searchNode(sangria,rootNode,new HashSet<Node>());
		
		Iterator<Node> childIterator= nodes.iterator();
		while(childIterator.hasNext()) {
			System.out.println(childIterator.next().getName());
		}
	}

	private static boolean isDrinkAvailable(Node rootNode, String string) {
		boolean status = false;
		if(string.equalsIgnoreCase(rootNode.getName())) {
			return true;
		} else if(rootNode.hasChildren()) {
			Set<Node> children = rootNode.getChildren();
			Iterator<Node> childIterator= children.iterator();
			while(childIterator.hasNext() && !status) {
				status = isDrinkAvailable(childIterator.next(), string);
			}
		}
		return status;
	}
	
	private static Set<Node> searchNode(Node node, Node containerNode, Set<Node> paths) {
		if(containerNode.equals(node)) {
			paths.add(containerNode);
		} else if(containerNode.hasChildren()) {
			paths.add(containerNode);
			Set<Node> children = containerNode.getChildren();
			Iterator<Node> childIterator= children.iterator();
			while(childIterator.hasNext()) {
				paths = searchNode(node,childIterator.next(),paths);
			}
			
		}
		return paths;
	}
	
}
