public class HashTrie extends ListTrie {
	HashNode[] table;

	public HashTrie(int size) {
		super();
		table = new HashNode[size];
	}

	public class HashNode {
		char c;
		Node parent;
		Node child;
		HashNode next;

		HashNode(Node p, char c, Node q) {
			parent = p;
			this.c = c;
			child = q;
		}
		@Override 
		public String toString(){
			StringBuilder sb = new StringBuilder();
			
			
			sb.append("par:" + parent.label.toString() + " c:"+c +" child " +child.label.toString());
			
			
			
			return sb.toString();
		}

	}

	public int hash(Node parent, char c) {
		return (Math.abs(parent.hashCode() + (int) c) % table.length);
	}

	public void hashInsert(Node p, char c,Node q) {
		int i = hash(p, c);
		HashNode x = table[i];
		HashNode y = new HashNode(p, c, q);
		y.next = x;
		table[i] = y;
	}

	// like heapify for a Node
	public void hashify(Node n) {
		if (n.parent == n && n != null) {
			hashInsert(n, n.label.charAt(0), n);
			for (Node child : n.children) {
				hashify(child);
			}
		} else{
			hashInsert(n.parent, n.label.charAt(0),n);
			for (Node child : n.children) {
				hashify(child);
			}
		}

	}
	public boolean search(Node n,String s) {
		if(n.parent == n){
			Node child = findChild(n,s.charAt(0));
			search(child,s);
		}else{
			int diff = diffIndex(n.label,s);
			Node child = findChild(n,s.charAt(diff));
			search(child,s.substring(diff));

		}
			
		
		
		
		return false;
	}

	public Node findChild(Node parent, char c) {
		int i = hash(parent, c);
		HashNode p = table[i];
		Node r = null;
		while (p != null) {
			if (p.parent == parent && p.c == c) {
				r = p.child;
			}
			p = p.next;
		}
		return r;
	}

	public static void main(String[] args) {

		HashTrie ht = new HashTrie(20);
		ht.insertString("cats");
		ht.insertString("cap");
		ht.insertString("crabs");
		ht.insertString("poop");
		ht.insertString("kelseysmells");
		ht.insertString("likepoop");
		ht.insertString("pot");
		ht.insertString("alexisthepoop");
		ht.hashify(ht.root);
		System.out.println("");
		ht.search(ht.root,"cap");

	}

}