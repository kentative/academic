package ksl.academic.structure.graph;

/**
 * Data object representing an edge. 
 */
public class Edge {

	public String id;
	public Vertex source;
	public Vertex target;
	public int weight;
	
	/**
	 * Instantiates a new edge with a default weight of 1
	 *
	 * @param source the source
	 * @param target the target
	 */
	public Edge(Vertex source, Vertex target) {
		this(source, target, 1);
	}
	
	/**
	 * Instantiates a new edge.
	 *
	 * @param source the source
	 * @param target the target
	 * @param weight the weight
	 */
	public Edge(Vertex source, Vertex target, int weight) {
		this.source = source;
		this.target = target;
		this.weight = weight;
		this.id = generateId(source, target);
	}
	
	/**
	 * Generate an edge id based on the vertices name
	 *
	 * @param s the source vertex
	 * @param t the target vertex
	 * @return the string the edge id
	 */
	public static String generateId(Vertex s, Vertex t) {
		return s.name + t.name;
	}
	
	public String toString() {
		return "|"+id+"|="+weight;
	}
}
