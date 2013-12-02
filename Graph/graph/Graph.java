package graph;

public class Graph {
	Node[] vtable;
	Edge[] etable;
	int verts;
	int edges;
	
	public Graph(int v, int e){
		verts = v;
		edges = e;
		vtable = new Node[v];
		etable = new Edge[e];
	}
	public Graph(){
	}
	//connect two verts via an edge
	public void connect(int v,int u){
		Edge e = new Edge();
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
