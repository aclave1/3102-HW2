import java.util.LinkedList;
import java.util.List;

public class ListTrie {
	Node root;

	public class Node {
		char lead;
		String label;
		List<Node> children;
		boolean isWord;

		public Node() {
			label = "";
			children = new LinkedList<Node>();
		}

		public Node(char l) {
			lead = l;
			label = "";
			children = new LinkedList<Node>();
		}

		public Node(char c, String s) {
			lead = c;
			label = s;
			children = new LinkedList<Node>();
		}

		@Override
		public String toString() {

			return new String("" + lead + " label:" + label);

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
	public Node lSearch(char key, Node node) {
		for (Node child : node.children) {
			if (child.lead == key) {
				return child;
			}
		}
		return null;
	}

	// search the trie for a string
	public boolean tSearch(String s) {
		char[] chars = s.toCharArray();
		Node n = root;
		for (int i = 0; i < chars.length; i++) {
			if (this.lSearch(chars[i], n) == null) {
				System.out.printf("\"%s\" is not in the trie\n", s);
				return false;
			} else {
				if (i != chars.length) {
					n = this.lSearch(chars[i], n);
				}
			}
		}
		if (n.isWord == true) {
			System.out.printf("%s is in the trie\n", s);
			return true;
		} else
			System.out.printf("%s is a prefix\n", s);
		return false;
	}

	// noncompact
	// public void insertString(String s) {
	// char[] chars = s.toCharArray();
	// Node n = root;
	// for (int i = 0; i < chars.length; i++) {
	// if (this.lSearch(chars[i], n) == null) {
	// this.insertChar(chars[i], n);
	// n = this.lSearch(chars[i], n);
	// } else {
	// n = this.lSearch(chars[i], n);
	// }
	// }
	// n.isWord = true;
	// }

	// call this to insert onto the trie
	public void insertString(String s) {
		insertString(s, root);
	}

	// call this to insert a string onto a node
	// the algorithm is upside down.
	public void insertString(String s, Node n) {
		int diff = diffIndex(n.label, s);
		
		// either it was called on the root, or their first letters are
		// different.
		if (diff == 0) {
			Node candidate = lSearch(s.charAt(0), n);
			if (candidate != null) {
				insertString(s, candidate);
			} else {
				Node child = new Node(s.charAt(0), s);
				n.children.add(child);
			}
		}

		// the label is a prefix to the inserted string, and can be happily
		// inserted!
		else if (diff == n.label.length()) {
			// do stuff
	
			n.children.add(new Node(s.charAt(0),s));
			
		}
		// they share a prefix but they differ before the end of the label.
		else if (diff > 1 && diff < n.label.length()) {
			// do stuff
			String temp = n.label;
			n.label = n.label.substring(0,diff);//this needs to be backwards, we're turning the label into a subtstring when we need a prefix ending at diff
			insertString(temp.substring(diff),n);
			insertString(s.substring(diff),n);
			
		}

		else if (diff == 1) {
			for (Node child : n.children) {
				if (diffIndex(child.label, s.substring(diff)) != 0) {
					insertString(s.substring(diff), child);
				}
			}
			Node child = new Node(s.charAt(diff),s.substring(diff));
			n.children.add(child);
		}

		else
			System.out.println("Something bad happened :(");
	}

	public void insertChar(char c, Node n) {
		for (Node child : n.children) {
			if (child.lead == c) {
				return;
			}
		}
		n.children.add(new Node(c));

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

		for (;i<l.length-1; i++) {
			if (l[i] != in[i]) {
				return i;
			}
		}
		return l.length ;

	}

	public void preorder(Node e, String pref) {
		if (e == null) {
			return;
		} else
			pref = pref + e.label;

		if (e.isWord) {
			System.out.println(pref);
		}

		for (Node child : e.children) {
			preorder(child, pref);
		}
	}

}
