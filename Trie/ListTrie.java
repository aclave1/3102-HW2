import java.util.LinkedList;
import java.util.List;

public class ListTrie {
	Node root;

	public class Node {
		char lead;
		String label;
		List<Node> children;

		public Node() {
			children = new LinkedList<Node>();
		}

		public Node(char l, String s) {
			lead = l;
			label = s;
			children = new LinkedList<Node>();
		}

		public Node(char l) {
			lead = l;
			children = new LinkedList<Node>();
		}

		@Override
		public String toString() {

			return new String("" + lead + ": " + label);

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

	public boolean tSearch(String s) {
		char[] chars = s.toCharArray();
		Node n = root;
		for (int i = 0; i < chars.length; i++) {
			if (this.lSearch(chars[i], n) == null) {
				return false;
			} else {
				if (i == chars.length) {
					return true;
				}
				n = this.lSearch(chars[i], n);
			}
		}
		return false;
	}

	public void insertString(String s) {
		char[] chars = s.toCharArray();
		Node n = root;
		for (int i = 0; i < chars.length; i++) {
			if (this.lSearch(chars[i], n) == null) {
				this.insertChar(chars[i], n);
				n = this.lSearch(chars[i++], n);
			} else {
				n = this.lSearch(chars[i++], n);
			}
		}
	}

	public void insertChar(char c, Node n) {

		for (Node child : n.children) {
			if (child.lead == c) {
				return;
			}
		}
		n.children.add(new Node(c));

	}

}
