package graph;

import java.util.Collections;


public class Graph {
	Node[] vlist;
	EdgeList etable;
	int verts;
	int edges;

	public Graph(int v, int e){
		verts = v;
		edges = e;
		vlist = new Node[v];
		for(int i=0;i<v;i++){
			vlist[i] = new Node(i);
		}
		etable = new EdgeList();
	}
	public Graph(){
	}
	//connect two verts via an edge
	public void connect(int v,int u,int w){
		Edge e = new Edge(vlist[v],vlist[u],w);
		etable.add(e);
	}

	public NodeList find(int u){
		Node p = vlist[u];
		return p.leader;
	}
	public NodeList find(Node u){
		Node p = vlist[u.vertexId];
		return p.leader;
	}

	public void Union(int u,int v){
		NodeList p = find(u);
		NodeList q = find(v);
		if(p.size < q.size){NodeList r=p; ;p=q; q=r;}
		p.size += q.size;
		p.tail.next = q.head;
		p.tail = q.tail;
		Node s = q.head;
		while(s != null){ s.leader = p; s=s.next;}
		q=null;
	}
	public void union(Node u,Node v){
		NodeList p = find(u);
		NodeList q = find(v);
		if(p.size < q.size){NodeList r=p; ;p=q; q=r;}
		p.size += q.size;
		p.tail.next = q.head;
		p.tail = q.tail;
		Node s = q.head;
		while(s != null){ s.leader = p; s=s.next;}
		q=null;
	}
	public void makeSet(int v){
		NodeList p = new NodeList();
		Node q = new Node();
		p.head = p.tail = q;
		p.size = 1;
		q.vertexId = v;
		q.next = null;
		q.leader = p;
		vlist[v] = q;
	}
	public EdgeList kruskal_mst(EdgeList e, int numverts, int numedges ){
		EdgeList t = new EdgeList();
		int mstWeight = 0;
		Collections.sort(e.edges);
		for(int i=0;i<numverts;i++){
			makeSet(i);
		}
		for(int i=0;i<numedges;i++){
			Node u = e.get(i).from;
			Node v = e.get(i).to;
			if(find(u) != find(v)){
				union(u,v);
				t.add(e.get(i));
				mstWeight = e.get(i).weight;
			}
		}
		return t;
	}


}
