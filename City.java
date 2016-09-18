//NAME: Netanel Amiel. ID: 303136972.
public class City { // each City have name and coordinates for X and Y on the Panel (Map).

	private String name;
	private int centerX;
	private int centerY;

	public City(String name, int x, int y) 
	{
		this.name = name;
		this.centerX = x;
		this.centerY = y;
	}

	public String getName() //returns the name.
	{
		return name;
	}
	public void setName(String str) //sets the name.
	{
		this.name = str;
	}
	public int getCenterX() //returns X coordinate.
	{
		return this.centerX;
	}
	public void setCenterX(int x) //sets X coordinate.
	{
		this.centerX = x;
	}
	public int getCenterY() //gets Y coordinate.
	{
		return this.centerY;
	}
	public void setCenterY(int y) //returns Y coordinate.
	{
		this.centerY = y;
	}
	public boolean equals(Object other) //checks if there's already name of city thats equals to the 'other' city name. 
	{
		if(this.getName().equals(((City) other).getName()))
			return true;
		return false;
	}
}

