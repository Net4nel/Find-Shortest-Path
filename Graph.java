//NAME: Netanel Amiel. ID: 303136972.
import java.util.Vector;


public class Graph<E>
{
	private Vector<Vector<E>> vertices; //Vertices is Vector Of Vectors.

	public Graph()
	{
		vertices = new Vector<Vector<E>>();
	}

	public boolean addVertex(E ver) //checks if the vector 'ver' is already exists, if not this method adds it to the vectices Vectors and returns it.
	{
		if(appearsIn(ver) == true)
			return false;
		else
		{
			Vector<E> temp = new Vector<E>();
			temp.add(ver);
			this.vertices.add(temp);
		}
		return true;
	}
	public boolean addEdge(E ver1, E ver2) //connect 2 edges (adds it to each Vector - each one to the other Vector).
	{
		if(!appearsIn(ver1) || !appearsIn(ver2))
			return false;
		else
		{
			for (Vector<E> v : this.vertices) 
			{
				if(v.get(0).equals(ver1))
					v.add(ver2);
				else if(v.get(0).equals(ver2))
					v.add(ver1);
			}
			return true;
		}
	}
	public Vector<E> getEdges(E ver) //gets Vector and returns new 'temp' Vector of all the edges that connected to it.
	{
		if(appearsIn(ver))
		{
			for(Vector<E> v : this.vertices)
			{
				if(v.get(0).equals(ver))
				{
					if(v.size()==1)
						return null;
					else
					{
						Vector<E> temp = new Vector<E>(v);
						temp.remove(0);
						return temp;
					}
				}
			}
		}
		return null;
	}

	public Vector<E> getVertices() //returns all the edges there is on Vertices Vector of Vectors.
	{
		if(this.vertices.size()==0)
			return null;
		Vector<E> temp = new Vector<E>();
		for(Vector<E> v : this.vertices)
			temp.add(v.get(0));
		return temp;
	}
	public Vector<E> bfs(E source, E target) //gets the way from one edge to another (find the way if there is such). this method uses Vector inside Vector of Vectors and with deletes each time it searching.
	{
		if(!appearsIn(source) || !appearsIn(target))
			return null;
		Vector<Vector<E>> generalVec = new Vector<Vector<E>>();
		Vector<E> innerVec = new Vector<E>();

		innerVec.add(source);
		generalVec.add(innerVec);

		do
		{
			E lastDes = innerVec.get(innerVec.size()-1);
			generalVec.remove(0);

			Vector<E> edgesVec = getEdges(lastDes);

			if(edgesVec != null)
			{
				for(E e : edgesVec)
				{
					if(e.equals(target))
					{
						innerVec.add(target);
						return innerVec;
					}
					else if(!appearsIn(e,innerVec))
					{
						Vector<E> temp = new Vector<E>(innerVec);
						temp.add(e);
						generalVec.add(temp);
					}
				}
			}
			if(!generalVec.isEmpty())
				innerVec = generalVec.get(0);
		}while(!generalVec.isEmpty());
		return null;
	}

	// Private Methods:
	private boolean appearsIn(E ver) // checks if 'ver' appears in Vertices.
	{
		for (Vector<E> v : this.vertices)
		{
			if(v.get(0).equals(ver))
				return true;
		}
		return false;
	}
	private boolean appearsIn(E ver, Vector<E> vec) // checks if 'ver' is appears on 'vec'.
	{
		for(E e : vec)
		{
			if(e.equals(ver))
				return true;
		}
		return false;
	}


}
