package graph;
import java.util.List;
import java.util.LinkedList;

public class Graph {
	Node[] vtable;
	List<Edge> eList;
	int verts;
	int edges;
	
	public Graph(int v){
		verts = v;
		vtable = new Node[v];
		eList = new LinkedList<Edge>();
		for(int i=0;i<v;i++){
			vtable[i] = new Node(i);
		}
	}
	public Graph(){
	}
	//connect two verts via an edge
	public void addEdge(int v,int u,int w){
		Edge e = new Edge(vtable[v],vtable[u],w);
		eList.add(e);
		edges++;
	}
	
	public void makeSet(int v) {
		Node p = new Node(v);
		vtable[v] = p;
		p.rank = 0;
		p.parent = p;
		p.vertexId = v;
	}

	public void union(int u, int v) {
		Node p, q = new Node();
		p = find(u);
		q = find(v);
		if (q.rank > p.rank) {
			p.parent = q;
		} else if (q.rank < p.rank) {
			q.parent = p;
		} else {
			p.parent = q;
			q.rank++;
		}
	}

	public Node find(int u) {
		Node p = vtable[u];
		if (p == p.parent) {
			return p;
		}
		Node q = p;
		while (q.parent != q) {
			q = q.parent;
		}
		while (p.parent != p) {
			Node r = p.parent;
			p.parent = q;
			p = r;
		}
		return q;
	}

}
