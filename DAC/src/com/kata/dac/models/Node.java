package com.kata.dac.models;

import java.util.HashSet;
import java.util.Set;

public class Node {
	private String name;
	
	private Set<Node> children;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Node> getChildren() {
		return children;
	}

	public void setChildren(Set<Node> children) {
		this.children = children;
	}
	
	public void addChild(Node child) {
		this.children.add(child);
	}
	
	public Node(String name, Node...children ) {
		this.name = name;
		this.children = new HashSet<>();
		int length = children.length;
		for(int i = 0;i < length ; i++) {
			this.addChild(children[i]);
		}
	}
	
	public boolean hasChildren() {
		if(this.getChildren().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean equals(Node node) {
		return this.name.equalsIgnoreCase(node.getName());
	}
	
	public boolean hasChild(Node node) {
		return this.children.contains(node);
	}
}
