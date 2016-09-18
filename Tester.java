import java.util.Vector;

import javax.swing.JFrame;


public class Tester {


	public static void main(String[] args)
	{

		JFrame frame = new JFrame(" Ex5 ");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,600);

		CitiesMapPanel p = new CitiesMapPanel();
		frame.add(p);

		frame.setVisible(true);
		
//			Graph<Integer> g = new Graph<>();
//	g.addVertex(3);
//	g.addVertex(4);
//	g.addVertex(5);
//	g.addVertex(6);
//	g.addEdge(3, 4);
//	g.addEdge(4 ,3);
//	g.addEdge(4, 6);
//	String a="a";
//	String b="b";
//	String c="c";
//	String d="d";
//	String e="e";
//	String f="f";
//	g.addVertex(a);
//	g.addVertex(b);
//	g.addVertex(c);
//	g.addVertex(d);
//	g.addVertex(e);
//	g.addVertex(f);
//
//	g.addEdge(a, b);
//	g.addEdge(a, d);
//	g.addEdge(b, c);
//	g.addEdge(b, e);
//	g.addEdge(d, c);
//	
////	
////	
//	System.out.println(g.getEdges(3));
//	System.out.println(g.getEdges(4));
//	System.out.println(g.bfs(a, e));
////	System.out.println(g.getVerteices());
	}
}
