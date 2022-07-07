package imageio;

import javax.swing.*;
import java.awt.*;

/**
 * @Description:
 * @author: 任传君
 * @date: 2022.05.10
 */
public class MyJComponent extends JComponent {


    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(Color.BLUE);
        g2d.drawRect(10, 10, 50 , 50);
        g2d.dispose();
    }


}
