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
			label = " ";
			children = new LinkedList<Node>();
		}

		public Node(char c, String s) {
			lead = c;
			label = s;
		}

		@Override
		public String toString() {

			return new String("" + lead);

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
	public void insertString(String s, Node n) {
		
		int diff = diffIndex(n.label, s);
		//either it was called on the root, or their first letters are different. 
		if (diff == 0) 
		{
			Node child = new Node(s.charAt(0), s);
			n.children.add(child);
		} 
		
		//their first letters are the same
		else if (diff == 1) {
			for (Node child : n.children) 
			{
				if (diff != 0) 
				{
					insertString(s.substring(diff), child);
				}
			}
			n.children.add(new Node(s.charAt(0), s));
		} 
		//they share a prefix but they differ before the end of the label.  
		else if (diff > 1) 
		{
			//do stuff
			insertString(s.substring(diff));
		} 
		//the label is a prefix to the inserted string, and can be happily inserted!
		else if(diff == n.label.toCharArray().length){
			//do stuff
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

		for (; i < l.length - 1; i++) {
			if (l[i] != in[i]) {
				return i;
			}
		}
		return l.length - 1;

	}

}
