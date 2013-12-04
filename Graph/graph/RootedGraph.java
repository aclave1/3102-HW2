package graph;

public class RootedGraph extends Graph{

	RootedNode[] nlist;

	public RootedGraph(int v,int e){
		super(v,e);
	}
	public RootedGraph(){}

	public RootedNode find(RootedNode u){
		if(u.parent != u){
			u.parent = find(u.parent);
		}
		return u.parent;
	}
	public void union(RootedNode u, RootedNode v){
		RootedNode uRoot = find(u);
		RootedNode vRoot = find(v);
		if(uRoot == vRoot){
			return;
		}
		if(uRoot.rank < vRoot.rank){
			uRoot.parent = vRoot;
		}
		else if(uRoot.rank > vRoot.rank){
			vRoot.parent = uRoot;
		}
		else
			vRoot.parent = uRoot;
			vRoot.rank += 1;
	}
	public void makeSet(RootedNode u){
		u.parent = u;
		u.rank = 0;
	}

}
