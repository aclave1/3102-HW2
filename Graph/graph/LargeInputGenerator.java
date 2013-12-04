package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 *
 * @author Alex Clavelle
 * Solely to create a large data set to test with.
 *
 */
public class LargeInputGenerator {

	public LargeInputGenerator() {
	}

	/**
	 *
	 * @param v the number of verts in the input list
	 * @param e the number of edges in the input list
	 * @param w the maximum weight of any edge.
	 * @return
	 */
	public static List<String> opGen(int v,int e,int w){
		List<String> ops = new ArrayList<String>();
		Random rand = new Random();
		String attr = v + " "+ e;
		ops.add(attr);
		for(int i = 0;i< e;i++){
			int from = rand.nextInt(v);
			int to = rand.nextInt(v);
			int weight = rand.nextInt(w);
			String s = from + " "+ to+ " " + weight;
			ops.add(s);
		}
		return ops;
	}



}
