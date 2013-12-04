package graph;

public class Edge implements Comparable<Edge> {
	Node to;
	Node from;
	int weight;

	public Edge(Node t, Node f, int w) {
		to = t;
		from = f;
		weight = w;
	}

	@Override
	public int compareTo(Edge e) {
		if (weight > e.weight) {
			return 1;
		} else if (weight < e.weight) {
			return -1;
		} else
			return 0;
	}

}
