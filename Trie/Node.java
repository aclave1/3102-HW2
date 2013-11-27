import java.util.LinkedList;
import java.util.List;
public class Node {
		String label;
		List<Node> children;
		Node parent;

		public Node() {
			label = "$";
			children = new LinkedList<Node>();
		}

		public Node(String s) {
			label = s;
			children = new LinkedList<Node>();
		}
		public Node(Node p, String s){
			label = s;
			children = new LinkedList<Node>();
			parent = p;
		}
		
		@Override
		public String toString() {
			return label;
		}
	}
