package ksl.academic.structure;

import java.util.Objects;

public final class Vertex implements Comparable<Vertex> {

	private String name;
	private int weight;

	public Vertex(String name) {
		this(name, Integer.MAX_VALUE);
	}
	
	public Vertex(String name, int weight) {
		this.name = name;
		this.weight = weight;
	}
	
	public boolean equals(Object o) {
		if (o == this) return true;
		
		if (!(o instanceof Vertex)) return false;
		
		Vertex v = (Vertex) o;
		return (Objects.equals(name, v.name));
	}
	
	public int hashCode() {
		return Objects.hash(name);
	}

	public String toString() {
		return name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(Vertex v) {
		if (v == null) return -1;
		return weight - v.weight;
	}
}
