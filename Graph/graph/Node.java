package graph;
public class Node {
	int vertexId;
	Node parent;
	int rank;
	public Node(int v){
		vertexId = v;
	}
	public Node(){
	}
	public Node(Node p){
		parent = p;
	}
	@Override
	public String toString(){
		return ""+vertexId;
	}
}
