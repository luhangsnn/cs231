
/*
Luhang Sun
CS 231 Project 2
Control.java
*/
import javax.swing.JOptionPane;
import java.awt.event.MouseListener;

public class Control extends LifeSimulation {
    public Control(int a, int b, double d) {
        super(a, b, d);
    }

    public static void main(String[] args) throws InterruptedException {

        String s1 = JOptionPane.showInputDialog("Enter Height");
        int h = Integer.parseInt(s1); // height of the landscape

        String s2 = JOptionPane.showInputDialog("Enter Width");
        int w = Integer.parseInt(s2); // wdith of the landscape

        String s3 = JOptionPane.showInputDialog("Enter Scale");
        int s = Integer.parseInt(s3); // scale of the landscape

        String s4 = JOptionPane
                .showInputDialog("Enter density of the random initialization(a double between 0 and 1): ");
        double d = Double.parseDouble(s4);

        Control test = new Control(w, h, d);

        LandscapeDisplay display = new LandscapeDisplay(test.scape, s);
        String[] options = { "Pause", "Resume", "Stop" };
        int x = JOptionPane.showOptionDialog(null, "", "Control Panel", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        for (int i = 0; i < 100; i++) {
            Thread.sleep(250);
            test.scape.advance();
            display.repaint();
        }
    }
}