import java.util.LinkedList;
import java.util.List;

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
		int hashval;

		HashNode(Node p, char c, Node q) {
			parent = p;
			this.c = c;
			child = q;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("par:" + parent.label.toString() + " c:" + c + " child "
					+ child.label.toString());
			return sb.toString();
		}
	}

	public int hash(Node parent, char c) {
		return (Math.abs(parent.hashCode() * (int) parent.label.charAt(0)* (int) c) % table.length);
	}

	public void hashInsert(Node p, char c, Node q) {
		int i = hash(p, c);
		HashNode x = table[i];
		HashNode y = new HashNode(p, c, q);
		y.next = x;
		table[i] = y;
	}

	// like heapify for a Node
	public void hashify(Node n) {
		if(n==null){return;}
		if (n.parent == n && n != null) {
			hashInsert(n, n.label.charAt(0), n);
			for (Node child : n.children) {
				hashify(child);
			}
		} else if (n.parent != n && n != null) {
			if (n.label != null) {
				hashInsert(n.parent, n.label.charAt(0), n);
				for (Node child : n.children) {
					hashInsert(n, child.label.charAt(0), child);
					hashify(child);
				}
			}
		}
	}

	public Node findChild(Node parent, char c) {
		int i = hash(parent, c);
		HashNode p = table[i];
		Node r = null;
		while (p != null) {
			if (p.parent == parent && p.c == c) {
				return p.child;
			}
			p = p.next;
		}

		return r;
	}

	public boolean search(Node n, String s) {
		boolean retval = false;
		if (n.parent == n) {
			Node child = findChild(n, s.charAt(0));
			if (child != null) {
				retval = search(child, s);
			} else
				retval = false;
		} else {
			int diff = diffIndex(n.label, s);
			if (diff > n.label.length() 
					|| diff > s.length()
					||diff < n.label.length()) {
				retval = false;
			} else if (diff == n.label.length() && diff == s.length()) {
				retval = true;
			} else if (diff == n.label.length() && diff < s.length()) {
				Node child = findChild(n, s.charAt(diff));
				if (child != null) {
					retval = search(child, s.substring(diff));
				} else {
					retval = false;
				}
			}

		}
		return retval;
	}
}