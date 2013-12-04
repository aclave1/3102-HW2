package graph;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Graph_Util {

	List<String> input;

	public static void main(String[] args){
		// SUPPLY A FILEPATH TO THE INPUT ON THE COMMAND LINE!!!!!
		Graph_Util util = new Graph_Util();
		util.loadfile(args[0]);
		List<String[]> ops = util.makeOps(util.input);

		String[] attr = ops.get(0);
		Graph g = new Graph(Integer.parseInt(attr[0]),Integer.parseInt(attr[1]));
		for(int i=1;i<ops.size();i++){
			String[] op = ops.get(i);
			g.connect(Integer.parseInt(op[0]), Integer.parseInt(op[1]), Integer.parseInt(op[2]));
		}
		EdgeList e = g.kruskal_mst(g.etable, g.verts, g.edges);
		System.out.println("Minimum spanning tree of: \n"+g.etable );
		System.out.println(e);
	}



	public boolean loadfile(String path) {
		input = new ArrayList<String>();
		Scanner filescn;
		int count = 0;
		try {
			filescn = new Scanner(new File(path));
			while (filescn.hasNextLine()) {
				String line = filescn.nextLine();
				input.add(line);
				count++;
			}
		} catch (Exception e) {

			System.out.println(e);
		}
		if (input.size() == count) {
			System.out.printf("File successfully loaded: %d lines\n\n",count);
			return true;
		} else
			System.out.println("File load unsuccessfull. ");
			return false;
	}

	public List<String[]> makeOps(List<String> s){
		List<String[]> ops = new ArrayList<String[]>();
		for(int i = 0;i<s.size();i++){
			String[] op = s.get(i).split(" ");
			ops.add(op);
		}
		return ops;
	}
}
