
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Tsp extends JPanel 
{

    private static final long serialVersionUID = 1L;
    private int labelPadding = 10;//12
    private Color lineColor = new Color(255,255,254);//changes colours on line , white 
    private Color pointColor = new Color(16,16,16 );//colours on points , black
    private Color gridColor = new Color(16, 16, 16, 16);//colour on grid , black
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private static int pointWidth = 10;
    private int numberYDivisions = 6;//creates fields
    private List<Double> address;
    private int padding = 25;//20
    
    private int count = 0;
    private JLabel label3;

    String info;
    static String result;
    

    private ArrayList<House> initialOrders = new ArrayList<House>(Arrays.asList(//all the addresses as an arrayList
        
        new House("Apache", 53.38197, -6.59274),//apache
        new House("1", 53.37521, -6.6103),
        new House("2", 53.37626, -6.59308),
        new House("3", 53.37077, -6.48279),//coordinates entered manually
        new House("4", 53.3473, -6.55057),
        new House("5", 53.31159, -6.60538),
        new House("6", 53.36115, -6.48907),
        new House("7", 53.37402, -6.49363),
        new House("8", 53.33886, -6.55468),
        new House("9", 53.2908, -6.67746),
        
        new House("10", 53.39459, -6.66995),
        new House("11", 53.33239, -6.55163),
        new House("12", 53.39847, -6.66787),
        new House("13", 53.29128, -6.67836),
        new House("14", 53.37416, -6.49731),
        new House("15", 53.35298, -6.54921),
        new House("16", 53.39839, -6.66767),
        new House("17", 53.36141, -6.51834),
        new House("18", 53.36287, -6.52468),
        new House("19", 53.33835, -6.53984),
        
        new House("20", 53.39351, -6.5542),
        new House("21", 53.36883, -6.51468),
        new House("22", 53.36833, -6.50589),
        new House("23", 53.37043, -6.48193),
        new House("24", 53.36115, -6.48907),
        new House("25", 53.38895, -6.60579),
        new House("26", 53.34678, -6.53415),
        new House("27", 53.36518, -6.48913),
        new House("28", 53.33751, -6.53173),
        new House("29", 53.37954, -6.58793),
        
        new House("30", 53.34133, -6.51856),
        new House("31", 53.36976, -6.59828),
        new House("32", 53.33591, -6.53566),
        new House("33", 53.38579, -6.58673),
        new House("34", 53.37414, -6.60028),
        new House("35", 53.34514, -6.53615),
        new House("36", 53.29206, -6.67685),
        new House("37", 53.35401, -6.54603),
        new House("38", 53.38122, -6.59226),
        new House("39", 53.36869, -6.48314),
        new House("40", 53.37247, -6.60044)
        
   
    ));

    
    /*
     * tsp is a constructor method
     * @returns List address;
    */
    public Tsp(List<Double> address) 
    {
        this.address = address;
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (3 * padding) - labelPadding) / (address.size() - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxAddress() - getMinAddress());

        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < address.size(); i++) 
        {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxAddress() - address.get(i)) * yScale + padding);
            graphPoints.add(new Point(x1, y1));//adds point to graph points
        }

        g2.setColor(Color.GREEN);//sets background to green
        //fill the rectangle
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - 
                labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.GREEN);

   
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() -
                padding, getHeight() - padding - labelPadding);

        Stroke oldStroke = g2.getStroke();
        g2.setColor(lineColor);//sets line colour
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) 
        {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;//runs through all the graph points
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);//draws line between points
        }
 
        g2.setStroke(oldStroke);
        g2.setColor(pointColor);//sets point colour
        for (int i = 0; i < graphPoints.size(); i++) 
        {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = graphPoints.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }

    /*
          getting the min address using Math();
          getMinAddress is an accessor method
          @Return the minAddress
    */


    private double getMinAddress() 
    {
        double minAddress = Double.MAX_VALUE;
        for (Double address : address) 
        {
            minAddress = Math.min(minAddress, address);
        }
        return minAddress;
    }
    /*
       *  getting the max address using Math();
       * getMaxAddress is an accessor method 
       * @Return the maxAddress;
           */
    
    private double getMaxAddress() 
    {
        double maxAddress = Double.MIN_VALUE;
        for (Double address : address) 
        {
            maxAddress = Math.max(maxAddress, address);
        }
        return maxAddress;
    }
    /* setting address */
    public void setAddress(List<Double> address) 
    {
        this.address = address;
        invalidate();
        this.repaint();
    }

    public List<Double> getAddress() 
    {
        return address;
    }
    /* creating the method createAndShowGui in the main method, where I create the frame too and put it in the panel*/
    private static void createAndShowGui() 
    {
        List<Double> address = new ArrayList<>();//list of random doubles
        Random random = new Random();//random generator
        int maxDataPoints = 40;//max points on the graph
        int maxAddress = 8 ;//8
        for (int i = 0; i < maxDataPoints; i++) //loops from 0 to maxDatePoints
        {
            address.add((double) random.nextDouble() * maxAddress);//creates random addresses
       
        }
        
        
        
        Tsp mainPanel = new Tsp(address);//main panel for graph

        mainPanel.setPreferredSize(new Dimension(700, 600));
        // creating the frame 
        JFrame frame = new JFrame("TakeAway Orders");
        
        JButton button = new JButton("Submit");

        JLabel label1 = new JLabel("Enter Addresses");
        JLabel label2 = new JLabel("Order of Addresses");
        JLabel label3 = new JLabel("Output");
        
        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 125);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        JPanel sidePanel = new JPanel();
        sidePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        sidePanel.setBackground(Color.YELLOW);
        sidePanel.setBounds(20, 20, 20, 20);
        sidePanel.setLayout(new GridLayout(0, 1));
        sidePanel.add(label2);
        sidePanel.add(label3);//label3 is the result
        

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        bottomPanel.setBackground(Color.RED);
        bottomPanel.setLayout(new GridLayout(0, 1));
        bottomPanel.add(label1);
        bottomPanel.add(userText);
        bottomPanel.add(button);
        
        button.addActionListener(new ActionListener() 
        
        {
           public void actionPerformed(ActionEvent e) 
           {
               String info = userText.getText(); //get the text from the input
               
               //String arr [][] = new String [41][4];
                
               label3.setText(result);

           }
        }); 
        
        frame.add(sidePanel, BorderLayout.EAST);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        
        
    }

      /*the main method runs createAndShowGui*/
    public static void main(String[] args) 
    {
      /*Tsp addresses = new Tsp();//calls addresses
      
      ArrayList<House> orders = new ArrayList<House>();
      orders.addAll(addresses.initialOrders);//adds intialOrders to orders
      addresses.printShortestRoute(new NearestNeighbor().findShortestRoute(orders));//calls the printRoute methed
        */                                                                           //adding the fondShortestRoute from Route
      SwingUtilities.invokeLater(new Runnable() //runs the createAndShowGui method
      {
         public void run() 
         {
            createAndShowGui();
         }
      });
    }

   private void printShortestRoute(Route shortestRoute)
   {
        
        String result = shortestRoute.toString(); //turns shortest route to a string 
        result = result.replaceAll(" ",""); //gets rid of spaces
        //System.out.println(result);//prints string of orders
   }
}  


class NearestNeighbor
{
    public Route findShortestRoute(ArrayList<House> orders)
    {
        ArrayList<House> shortestRouteOrders = new ArrayList<House>(orders.size());
        
        House house = orders.get(0);//starts at first coordinates aka Apache
        updateRoutes(shortestRouteOrders, orders, house);
        while(orders.size() >= 1)
        {
            house = getNextHouse(orders, house);
            updateRoutes(shortestRouteOrders, orders, house);
        }
        return new Route(shortestRouteOrders);
    }
    private void updateRoutes(ArrayList<House> shortRouteOrders, ArrayList<House> orders, House house)//changes route
    {
        shortRouteOrders.add(house);//adds house to route 
        orders.remove(house);//removes house from order list
        
    }
    private House getNextHouse(ArrayList<House> orders, House house)
    {
        return orders.stream().min((house1, house2) -> {
            int flag = 0;
            if(house1.measureDistance(house) < house2.measureDistance(house)) flag = -1;
            else if (house1.measureDistance(house) > house2.measureDistance(house)) flag = 1;
            return flag;
        }).get();
    }
    
}



class House
{
    private static final double EARTH_EQUATORIAL_RADIUS = 6378.1370D;//as static these are constant used variables
    private static final double CONVERT_DEGREES_TO_RADIANS = Math.PI/180D;//
    private static final double CONVERT_KM_TO_MILES = 0.621371;//
    private double longitude;//makes them only accessible within declared class
    private double latitude;
    private String name;
    public House(String name, double latitude, double longitude)
    {
        this.name = name; 
        this.longitude = longitude * CONVERT_DEGREES_TO_RADIANS;//converts
        this.latitude = latitude * CONVERT_DEGREES_TO_RADIANS;
    }
    public String getName() 
    { 
        return name; 
    }
    public double getLatitude() 
    { 
        return this.latitude; 
    }
    public double getLongitude() 
    { 
        return this.longitude; 
    }
    public double measureDistance(House house) //measure distance between latitudes and longitudes
    {
        double deltaLongitude = (house.getLongitude() - this.getLongitude());
        double deltaLatitude = (house.getLatitude() - this.getLatitude());
        double a = Math.pow(Math.sin(deltaLatitude / 2D), 2D) +
                    Math.cos(this.getLatitude()) * Math.cos(house.getLatitude()) * Math.pow(Math.sin(deltaLongitude / 2D), 2D);
        return CONVERT_KM_TO_MILES * CONVERT_DEGREES_TO_RADIANS * 2D * Math.atan2(Math.sqrt(a), Math.sqrt(1D - a));
    }
    public String toString()
    { 
        return this.name;
    }
}


class Route
{
  private ArrayList<House> orders = new ArrayList<House>();
  public Route(ArrayList<House> orders) 
  { 
      this.orders.addAll(orders); 
  }
  public ArrayList<House> getOrders() 
  { 
      return orders; 
  }
  public String toString() 
  { 
      return Arrays.toString(orders.toArray()); 
  }
}
