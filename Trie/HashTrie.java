public class HashTrie {
	
	public class Node{
		char lead;
		String label;
		boolean isWord;
		Node firstChild;
		Node rightSibling;
	}
	
	public class HashNode{
		char c;
		Node parent;
		Node child;
		HashNode next;
		
		HashNode(Node p, char c, Node q){
			parent = p;
			c = this.c;
			child = q;
		}
	}
	
	public HashNode[] table;
	
	Node root;
	
	public HashTrie(){
			table = new HashNode[173529];
	}	
	
	public int hash(Node parent, char c){
		return (Math.abs(parent.hashCode()+(int)c) % 173529);
	}

	public void insert(Node p, char c, Node q){
		int i = hash(p,c);
		HashNode x = table[i];
		HashNode y = new HashNode(p, c, q);
		y.next = x;
		table[i] = y;  
	}
	
	public Node findChild(Node parent, char c){
		int i = hash(parent, c);
		HashNode p = table[i];
		Node r = null;
		while(p != null){
			if(p.parent == parent && p.c == c){
				r = p.child;
			}
			p = p.next;
		}
		return r;
	}
	
	public void preorder(Node p) {
		if (p == null) {
			return;
		}
		Node q=p.firstChild;
		while(q != null){
			insert(p, q.lead, q);
			preorder(q);
			q=q.rightSibling;
		}
	}
	
	public static void main(String[] args){
		HashTrie table = new HashTrie();
		
	}
	
}
