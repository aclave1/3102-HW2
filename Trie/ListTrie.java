import java.util.LinkedList;
import java.util.List;

public class ListTrie {
	Node root;

	public ListTrie() {
		root = new Node();
		root.parent = root;
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
		int bestdiff = 0;
		Node bestchild = new Node();
		for (Node child : node.children) {
			int diff = diffIndex(child.label, key);
			if (diff > bestdiff) {
				bestdiff = diff;
				bestchild = child;
			}
		}
		if (diffIndex(bestchild.label, key) != 0) {
			return bestchild;
		} else
			return null;
	}

	public boolean search(Node n, String s) {
		boolean retval = false;
		if (n.label == "$") {
			Node candidate = lSearch(n, s);
			retval = search(candidate, s);
		} else {
			int diff = diffIndex(n.label, s);
			if (diff > n.label.length() || diff > s.length()
					|| diff < n.label.length()) {
				retval = false;
			} else if (diff == n.label.length() && diff == s.length()
					&& n.isWord == true) {
				retval = true;
			} else if (diff == n.label.length() && diff < s.length()) {
				Node candidate = lSearch(n, s.substring(diff));
				if (candidate != null) {
					retval = search(candidate, s.substring(diff));
				} else {
					return false;
				}
			}

		}
		return retval;
	}

	// call this to insert onto the trie
	public void insertString(String s) {
		insertString(s, root);
	}

	public void insertString(String s, Node n) {
		int diff;
		if (n.label == "$") {
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
				child.parent = n;
				n.children.add(child);
				child.isWord = true;
			}
		}
		// the label is a prefix to the inserted string
		else if (diff == n.label.length()) {
			Node candidate = lSearch(n, s.substring(diff));
			if (candidate != null) {
				insertString(s.substring(diff), candidate);
			} else {
				Node child = new Node(s.substring(diff)); // needs to be diff -1
															// sometimes
				child.parent = n;
				n.children.add(child);
				child.isWord = true;
			}
		}
		// they share a prefix but they differ before the end of the label.
		else if (diff > 0 && diff < n.label.length()) {
			String temp = n.label;
			n.label = n.label.substring(0, diff);
			Node child = new Node(temp.substring(diff));
			child.parent = n;
			child.children = n.children;
			n.children = new LinkedList<Node>();
			n.children.add(child);
			child.isWord = true;
			if (diff < s.length()) {
				Node word = new Node(s.substring(diff));
				word.isWord = true;
				n.children.add(word);
				word.parent = n;

			} else {
				Node word = new Node(s.substring(diff));
				word.isWord = true;
				n.children.add(word);
				word.parent = n;
			}
		} else
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

}
