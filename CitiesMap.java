//NAME: Netanel Amiel. ID: 303136972.
import java.util.Vector;


public class CitiesMap {

	private Graph<City> map; //create map as City (inside Graph Class).

	public CitiesMap() //all the methods inside this class define the method from graph class into 'map'.
	{
		map = new Graph<City>(); //Initialize map and City (inside Graph Class).
	}
	public boolean addCity(City c) 
	{
		return map.addVertex(c);
	}
	public boolean addWay(City c1, City c2)
	{
		return map.addEdge(c1,c2);
	}
	public Vector<City> getCities()
	{
		return map.getVertices();
	}
	public Vector<City> getWays(City c)
	{
		return map.getEdges(c);
	}
	public Vector<City> findPath(City c1, City c2)
	{
		return map.bfs(c1,c2);
	}
	
}
