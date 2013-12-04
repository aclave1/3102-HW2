package graph;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Graph_Util {

	List<String> input;

	public static void main(String[] args) {
		// SUPPLY A FILEPATH TO THE INPUT ON THE COMMAND LINE!!!!!
		/**
		 * input files should be formatted like this:
		 *
		 * First Line is <num-vertexes,num-edges>
		 * Subsequent lines are edges: <from-vert,to-vert,edge weight>
		 * 6 3
		 * 0 1 3
		 * 1 3 4
		 */
		Graph_Util util = new Graph_Util();
		util.loadfile(args[0]);
		List<String[]> ops = util.makeOps(util.input);

		String[] attr = ops.get(0);
		Graph g = new Graph(Integer.parseInt(attr[0]),
				Integer.parseInt(attr[1]));
		for (int i = 1; i < ops.size(); i++) {
			String[] op = ops.get(i);
			g.connect(Integer.parseInt(op[0]), Integer.parseInt(op[1]),
					Integer.parseInt(op[2]));
		}
		long starttime = System.nanoTime();
		EdgeList e = g.kruskal_mst(g.etable, g.verts, g.edges);
		long endtime = System.nanoTime();
		double elapsed =  (endtime - starttime)/1000000.0;
		System.out.println("Set: \n" + g.etable + " weight: " + g.etable.weight);
		System.out.println("Minimum spanning tree of set:\n"+e + " weight: " + e.weight);
		System.out.printf("Kruskal's algorithm took %.6f milliseconds for the Linked List with leader-pointer at every node\n\n",elapsed );


		RootedGraph rg = new RootedGraph(Integer.parseInt(attr[0]),Integer.parseInt(attr[1]));
		for (int i = 1; i < ops.size(); i++) {
			String[] op = ops.get(i);
			rg.connect(Integer.parseInt(op[0]), Integer.parseInt(op[1]),
					Integer.parseInt(op[2]));
		}
		starttime = System.nanoTime();
		EdgeList e2 = rg.kruskal_mst(rg.etable, rg.verts, rg.edges);
		endtime = System.nanoTime();
		elapsed = (endtime - starttime)/1000000.0;

		System.out.println("Set: \n" + rg.etable + " weight: " + rg.etable.weight);
		System.out.println("Minimum spanning tree of set:\n"+e2 + " weight: " + e2.weight);
		System.out.printf("Kruskal's algorithm took %.6f milliseconds for rooted trees with path compression.\n\n",elapsed );

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
			System.out.printf("File successfully loaded: %d lines\n\n", count);
			return true;
		} else
			System.out.println("File load unsuccessfull. ");
		return false;
	}

	public List<String[]> makeOps(List<String> s) {
		List<String[]> ops = new ArrayList<String[]>();
		for (int i = 0; i < s.size(); i++) {
			String[] op = s.get(i).split(" ");
			ops.add(op);
		}
		return ops;
	}
}
