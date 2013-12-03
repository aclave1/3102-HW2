package graph;

public class Edge {

	Node t;
	Node f;
	int w;
	public Edge(Node to,Node from,int weight){
		t=to;
		f=from;
		w=weight;
	}
	public Edge(){
	}
	@Override
	public String toString(){
		return "{"+t+", "+f+"} "+w;
	}
}
