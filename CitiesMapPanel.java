//NAME: Netanel Amiel. ID: 303136972.
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;


import javax.swing.Box.Filler;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class CitiesMapPanel extends JPanel
{
	private CitiesMap cm; 
	private JButton cmdAddWay,cmdFindPath,cmdClearMap,cmdClearPath; //buttons names.
	private JLabel lblFrom,lblTo; // label to the panel.
	private JTextField txtFrom,txtTo; //texts fields.
	
	private JPanel southPanel; // to avoid unnecessary clicks on the southern side of the panel (defines Cities)- we will use this p. method to cancel this ability.
	private int panelHeight; //helps us to get the height that we need to cancel from below.
	private boolean drawLine; //draw line method- helps us later to know when to draw the line between the cities.
	private String str;
	
	//private vectors.
	private Vector<City> citiesMap;
	private Vector<City> suchCity;

	public CitiesMapPanel()
	{
		suchCity = null;
		this.drawLine = false;
		this.cm = new CitiesMap();
		this.panelHeight = panelHeight;

		cmdAddWay = new JButton("Add Way");
		cmdFindPath = new JButton("Find Path");
		cmdClearMap = new JButton("Clear Map");
		cmdClearPath = new JButton("Clear Path");
		lblFrom = new JLabel("From: ");
		lblTo = new JLabel("To: ");
		txtFrom = new JTextField("");
		txtTo = new JTextField("");

		setLayout(new BorderLayout());
		this.setBackground(Color.WHITE); //default background is WHITE.


		southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(2,4,2,2)); //'cubes' that limits our Panel from below. (see 'South Panel' method above).

		//buttons on the lower southpanel (on JPanel).
		southPanel.add(lblFrom);
		southPanel.add(txtFrom);
		southPanel.add(lblTo);
		southPanel.add(txtTo);
		southPanel.add(cmdAddWay);
		southPanel.add(cmdFindPath);
		southPanel.add(cmdClearMap);
		southPanel.add(cmdClearPath);

		//sets the south panel on the lower side of JPanel.
		add(southPanel, BorderLayout.SOUTH);

		MouseListener mouseListener = new MouseListener();
		addMouseListener(mouseListener);

		ButtonListener buttonListener = new ButtonListener(); //define the buttons by the listeners.
		cmdAddWay.addActionListener(buttonListener);
		cmdFindPath.addActionListener(buttonListener);
		cmdClearMap.addActionListener(buttonListener);
		cmdClearPath.addActionListener(buttonListener);

	}
	protected void paintComponent(Graphics g) //'pen' draw method.
	{
		int index = 0; 
		super.paintComponent(g);
		panelHeight = this.getHeight()-southPanel.getHeight();

		
		Vector<City> cities = cm.getCities();
		if(cities != null)
		{
			for(City c : cities) //every city gets tiny oval above is with its name in the right side.
			{
				g.fillOval(c.getCenterX(),c.getCenterY(), 3, 3);
				g.drawString(c.getName(), c.getCenterX()+4, c.getCenterY()+4);
				if(drawLine)//keep lines between cities when necessary.
				{
					Vector<City> city = cm.getWays(c);
					if (city != null)
					{
						for (City v : city) 
						{	
							g.setColor(Color.BLACK);
							g.drawLine(v.getCenterX(), v.getCenterY(), c.getCenterX(), c.getCenterY());	
						}
					}
				}
			}
		}
		if (suchCity != null) //get edge- if there is suchCity, its Activated.
		{
			while (index < (suchCity.size()-1))
			{
				City c1 = suchCity.get(index);
				City c2 = suchCity.get(index+1);
				g.setColor(Color.RED);
				g.drawLine(c1.getCenterX(), c1.getCenterY(), c2.getCenterX(), c2.getCenterY());
				index++;
			}
		}
		else
			index=0;
	}
	private class MouseListener extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e)
		{
			super.mouseClicked(e);
			if(e.getY() > panelHeight-3)
				return;

			if(e.getButton() == MouseEvent.BUTTON1); //when the left button on the mouse clicked- you can add city by name and anchor point to the map.
			{
				str = JOptionPane.showInputDialog(null, "Please Enter A City Name: ");
				if(str == null || str.equalsIgnoreCase(""))
					return;
				City c = new City(str, e.getX(), e.getY());

				if(!cm.addCity(c))
				{
					JOptionPane.showMessageDialog(null, "This City Is Already Exists");
				}
				repaint();
			}
		}
	}
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == cmdAddWay) //add way (black line between chosen cities as shown the Paintcomponent met.).
			{
				String from = txtFrom.getText();
				String to = txtTo.getText();

				City cityFrom = null;
				City cityTo = null;

				Vector<City> temp = cm.getCities();

				for(City v : temp)
				{
					if(from.equals(v.getName()))
						cityFrom = v;
					else if(to.equals(v.getName()))
						cityTo = v;
				}
				if(cityFrom != null && cityTo != null)
				{
					cm.addWay(cityFrom, cityTo);
					drawLine = true;
					repaint();
				}
				else if(cityFrom == null && cityTo != null)
					JOptionPane.showMessageDialog(null, "Please Check Your Source");
				else if(cityFrom != null && cityTo == null)
					JOptionPane.showMessageDialog(null, "Please Check Your Target");
			}

			else if (e.getSource() == cmdFindPath) //finding the path between connected cities and mark it with RED line on the Black.
			{
				String txtC1 = txtFrom.getText();
				String txtC2 = txtTo.getText();
				if (txtC1.equalsIgnoreCase("") || txtC2.equalsIgnoreCase(""))
					JOptionPane.showMessageDialog(null, "Error: Please Fill Both Fields!");
				else
				{
					City cityFrom = null;
					City cityTo = null;
					Vector<City> cities = cm.getCities();
					for (City c : cities) 
					{
						if (c.getName().equals(txtC1))
							cityFrom = c;
						else if (c.getName().equals(txtC2))
							cityTo = c;
					}
					if (cityFrom == null && cityTo == null)
						JOptionPane.showMessageDialog(null, "Both cities does not exist! :(");
					else if(cityFrom == null && cityTo != null)
						JOptionPane.showMessageDialog(null, "Please Check Your Source");
					else if(cityFrom != null && cityTo == null)
						JOptionPane.showMessageDialog(null, "Please Check Your Target");
					else
					{
						suchCity = cm.findPath(cityFrom, cityTo);
						if (suchCity == null)
							JOptionPane.showMessageDialog(null,"There is no such way :( ");
					}
					repaint();
				}
			}
			else if (e.getSource() == cmdClearMap) //delets all the Cities and Lines on the map.
			{
				cm = new CitiesMap();
				suchCity = null;
				txtFrom.setText("");
				txtTo.setText("");
				repaint();
			}
			else if (e.getSource() == cmdClearPath) //clears the red line and the Text fields.
			{
				suchCity = null;
				txtFrom.setText("");
				txtTo.setText("");
				repaint();
			}
		}
	}
}
