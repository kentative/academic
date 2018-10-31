package ksl.academic.structure.graph;

import java.util.Objects;

/**
 * Data structure representing a graph vertex
 */
public final class Vertex implements Comparable<Vertex> {

	public String name;
	public int weight;

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

	@Override
	public int compareTo(Vertex v) {
		if (v == null) return -1;
		return weight - v.weight;
	}
}
