package graph;

import java.util.ArrayList;
import java.util.List;

public class EdgeList {
	List<Edge> edges;
	public  EdgeList() {
		edges = new ArrayList<Edge>();
	}
	public Edge get(int i){
		return edges.get(i);
	}
	public void add(Edge e){
		edges.add(e);
	}

}
