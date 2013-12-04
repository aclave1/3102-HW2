package graph;

public class Node {

	Node next;
	NodeList leader;
	int vertexId;
	int rank;

	public Node(int v) {
		vertexId = v;
	}

	public Node() {
	}
}
