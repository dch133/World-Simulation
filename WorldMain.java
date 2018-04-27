
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class WorldMain
{


    // constructor & main method
    public static void main(String[] args)
    {
        try
        {
            World.getInstance().buildWorld();
            while (true)
            {
                for (int i = 0; i < 100; i++)
                {
                    TimeUnit.MILLISECONDS.sleep(400);
                    World.getInstance().display();
                    World.step();
                }
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, "Would you like to run simulation again?",
                        "Restart Simulation", dialogButton);
                if (dialogResult == 0)
                {
                }
                else System.exit(0);
            }
        } catch (Exception e) {e.printStackTrace();}
    }
}

