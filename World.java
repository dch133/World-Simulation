import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class World
{
    private static WorldObject[][] array;
    JFrame mainFrame = new JFrame("World Simulation"); // A window for GUI

    //Populate the world with objects
    public void buildWorld()
    {
        //handcode the objects into the world: 5 immovable, 3 moveable and 2 autonomous objects as a test
        WorldObject i1 = new Immovable("A",0,0 );
        WorldObject i2 = new Immovable("B",1,1 );
        WorldObject i3 = new Immovable("C",18,2 );
        WorldObject i4 = new Immovable("D",3,9 );
        WorldObject i5 = new Immovable("E",4,7 );

        WorldObject m1 = new Movable("F",1,3 );
        WorldObject m2 = new Movable("G",2,10 );
        WorldObject m3 = new Movable("H",10,2 );

        WorldObject a1 = new Autonomous("I",8,9 );
        WorldObject a2 = new Autonomous("J",3,0 );

        instance.add(i1,0,0);
        instance.add(i2,1,1);
        instance.add(i3,18,2);
        instance.add(i4,3,9);
        instance.add(i5,4,7);

        instance.add(m1,1,3);
        instance.add(m2,2,10);
        instance.add(m3,10,2);

        instance.add(a1,8,9);
        instance.add(a2,3,0);

    }


    //create an object of SingleObject
    private static World instance = new World(new WorldObject[20][20]); //set size of array

    //make the constructor private so that this class cannot be
    //instantiated
    private World(WorldObject[][] array)
    {
        this.array = array;
    }

    //Get the only object available
    public static World getInstance(){
        return instance;
    }

    public static void step()
    {
        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[0].length; j++)
            {
                if (array[i][j] == null) continue; //Skip empty spots in array
                if (array[i][j].getToken() == 'A')
                {
                    if (array[i][j].checkHasMoved())
                    {
                        array[i][j].setHasMoved(false); //reset the check for next iteration
                        continue;
                    }
                    else
                    {
                        array[i][j].setHasMoved(true); //make sure the same object doesn't move twice in the same iteration
                        array[i][j].step(); //make all autonomous obj move one at a time
                    }
                }
            }
        }
    }

    public void display()
    {
        WorldObject[][] ground = this.getArray();
        JPanel[][] panelArray = new JPanel[ground.length][ground[0].length];

        mainFrame.setSize(400, 400);
        JPanel panel = new JPanel(new GridLayout(20,20)); //for a 20 X 20 world
        panel.setSize(400, 400);
        mainFrame.add(panel);
        JLabel label;

        for (int i = 0; i < ground.length; i++)
        {
            for (int j = 0; j < ground[0].length; j++)
            {
                if (ground[i][j] != null) label = new JLabel(""+ground[i][j].getToken(), JLabel.CENTER);

                else label = new JLabel("");

                label.setSize(20,20 );
                panelArray[i][j] = new JPanel();
                panelArray[i][j].add(label);
                panelArray[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                panel.add(panelArray[i][j]);
            }
        }
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    public WorldObject[][] getArray()
    {
        return this.array;
    }


    public void add(WorldObject item,int x,int y)
    {
        try
        {
            //add to array
            instance.getArray()[x][y] = item;

        }
        catch (Exception e)
        {
            System.out.println("Object cannot be added to an invalid position: "+x+","+y);
        }








}












}
