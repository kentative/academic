package ksl.academic.structure;

public class Edge {

	private String id;
	private Vertex source;
	private Vertex target;
	private int weight;

	public Edge(Vertex source, Vertex target, int weight) {
		this.source = source;
		this.target = target;
		this.weight = weight;
		this.id = generateId(source, target);
	}
	
	public static String generateId(Vertex s, Vertex t) {
		return s.getName() + t.getName();
	}
	
	public String toString() {
		return "|"+id+"|="+weight;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Vertex getSource() {
		return source;
	}

	public void setSource(Vertex source) {
		this.source = source;
	}

	public Vertex getTarget() {
		return target;
	}

	public void setTarget(Vertex target) {
		this.target = target;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
}
