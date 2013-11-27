import java.util.LinkedList;
import java.util.List;

public class ListTrie {
	Node root;

	public class Node {
		String label;
		List<Node> children;

		public Node() {
			label = null;
			children = new LinkedList<Node>();
		}

		public Node(String s) {
			label = s;
			children = new LinkedList<Node>();
		}

		@Override
		public String toString() {
			return label;
		}
	}

	public ListTrie() {
		root = new Node();
		root.children = new LinkedList<Node>();
	}

	/**
	 * 
	 * @param key
	 *            : The character we're looking for
	 * @param node
	 *            the node whose children we're searching
	 * @return null if the node does not have the child, a reference to the
	 *         child if it does.
	 */
	public Node lSearch(Node node, String key) {
		for (Node child : node.children) {
			int diff = diffIndex(child.label, key);
			if (diff != 0) {
				return child;
			}
		}
		return null;
	}

	public boolean searchTrie(Node n, String s) {
		boolean retval = false;
		if(n.label == null){
			Node candidate = lSearch(n, s);
			retval = searchTrie(candidate,s);
		}
		else{
			int diff = diffIndex(n.label, s);
			if(diff > n.label.length() || diff > s.length()){
				retval = false;
			}
			else if(diff == n.label.length() && diff == s.length()){
				retval =  true;
			}
			else if(diff == n.label.length() && diff < s.length()){
				Node candidate = lSearch(n, s.substring(diff));
				retval = searchTrie(candidate,s.substring(diff));
			}
			
		}
		return retval;
	}

	// call this to insert onto the trie
	public void insertString(String s) {
		insertString(s, root);
	}

	// call this to insert a string onto a node
	// the algorithm is upside down.
	public void insertString(String s, Node n) {
		int diff;
		if (n.label == null) {
			diff = 0;

		} else {
			diff = diffIndex(n.label, s);
		}
		// either it was called on the root, or their first letters are
		// different.
		if (diff == 0) {
			Node candidate = lSearch(n, s.substring(0, 1));
			if (candidate != null) {
				insertString(s, candidate);
			} else {
				Node child = new Node(s);
				n.children.add(child);
				child.children.add(new Node("$"));
			}
		}
		// the label is a prefix to the inserted string
		else if (diff == n.label.length()) {
			for (Node child : n.children) {
				int childdiff = diffIndex(child.label, s.substring(diff));
				if (childdiff > 0) {
					insertString(s.substring(diff), child);
					// child.children.add(new Node("$"));
					return;
				}
			}
			Node child = new Node(s.substring(diff));
			n.children.add(child);
		}
		// they share a prefix but they differ before the end of the label.
		else if (diff > 0 && diff < n.label.length()) {
			String temp = n.label;
			n.label = n.label.substring(0, diff);
			Node child = new Node(temp.substring(diff));
			child.children = n.children;
			n.children = new LinkedList<Node>();
			n.children.add(child);
			insertString(s.substring(diff - 1), n);
		}
		else
			System.out.println("Something bad happened :(");
	}

	/**
	 * finds the point in the input string where the label differs
	 * 
	 * @param label
	 *            the label we're matching against within the trie
	 * @param instring
	 *            the string or substring we are inserting
	 * @return the point in the string at which the two strings differ
	 */
	public static int diffIndex(String label, String instring) {
		char[] l = label.toCharArray();
		char[] in = instring.toCharArray();
		int i = 0;
		// System.out.printf("l=%s in=%s \n",label,instring);
		for (; i < l.length && i < in.length; i++) {
			if (i == l.length || i == in.length) {
				return i;
			}
			if (l[i] != in[i]) {
				return i;
			}
		}
		return i;

	}

	public void preorder(Node p, String pref) {

	}

}
