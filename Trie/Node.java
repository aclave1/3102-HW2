import java.util.LinkedList;
import java.util.List;
public class Node {
		String label;
		List<Node> children;
		Node parent;
		boolean isWord;

		public Node() {
			label = "$";
			children = new LinkedList<Node>();
		}

		public Node(String s) {
			label = s;
			children = new LinkedList<Node>();
			isWord = false;
		}
		public Node(Node p, String s){
			label = s;
			children = new LinkedList<Node>();
			parent = p;
			isWord = false;
		}
		
		@Override
		public String toString() {
			return label;
		}
	}
