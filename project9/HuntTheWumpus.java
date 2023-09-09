/*
Template created by Bruce A. Maxwell and Stephanie R Taylor

Luhang Sun
CS231 Project 9
HuntTheWumpus.java
*/
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.util.*;

public class HuntTheWumpus {
    private JFrame win;
    private LandscapePanel canvas;    
    private Landscape scape; 
    private int scale;
    private int numRow, numCol; // the number of the row and column in the graph
    private Graph graph;
    private Hunter hunter;
    private Wumpus wumpus;
    private Random ran;
    private Vertex [][]map;

    JLabel fieldX, fieldY; // X and Y location of the mouse 
    private enum PlayState { PLAY, STOP }
    private PlayState state;

    public HuntTheWumpus(){
        // The game should begin in the play state.
        this.state = PlayState.PLAY; 

        //sets up the graph and characters
        this.setGame();
        
        // Make the main window
        this.win = new JFrame("Hunt the Wumpus");
        win.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);

        // make the main drawing canvas (this is the usual
        // LandscapePanel we have been using). and put it in the window
        this.canvas = new LandscapePanel( this.scape.getWidth(),this.scape.getHeight());
        this.win.add( this.canvas, BorderLayout.CENTER );
        this.win.pack();

        // make the labels and a button and put them into the frame
        // below the canvas.
        this.fieldX = new JLabel("X");
        this.fieldY = new JLabel("Y");
        JButton quit = new JButton("Quit");
        JButton restart = new JButton("Restart"); //making a restart button
        JPanel panel = new JPanel( new FlowLayout(FlowLayout.RIGHT));
        panel.add( this.fieldX );
        panel.add( this.fieldY );
        panel.add( quit);
        panel.add(restart);
        this.win.add( panel, BorderLayout.SOUTH);
        this.win.pack();

        Control control = new Control();
        this.win.addKeyListener(control);
        this.win.setFocusable(true);
        this.win.requestFocus();

        quit.addActionListener( control );
        restart.addActionListener(control);

        // for mouse control
        MouseControl mc = new MouseControl();
        this.canvas.addMouseListener( mc );
        this.canvas.addMouseMotionListener( mc );

        // The last thing to do is make it all visible.
        this.win.setVisible( true );
    }

    public void setGame(){
        // Create the elements of the Landscape and the game.
        this.scale = 64; //size of the grid
        this.numRow = 4;
        this.numCol = 4;
        this.scape = new Landscape(scale*this.numRow, scale*this.numCol);
        this.graph = new Graph();
        this.ran = new Random();
        this.map = new Vertex [this.numCol][this.numRow];

        for (int i=0; i<this.numCol; i++){
            for (int j=0; j<this.numRow; j++){
                Vertex v = new Vertex(i, j);
                this.map[i][j] = v;
                // v.setVisible(true);
                if (j != 0) {
                    if (ran.nextInt(10) != 1) //randomly block some of the paths
                        this.graph.addEdge(v, Direction.NORTH, this.map[i][j-1]);
                }
                if (i != 0){
                    if (ran.nextInt(10) != 1)
                        this.graph.addEdge(v, Direction.WEST, this.map[i-1][j]);
                }
                this.scape.addBackgroundAgent(v);
            }
        }

        // generate random locations for hunter and wumpus
        int hunterX = ran.nextInt(this.numCol); 
        int hunterY = ran.nextInt(this.numRow);
        int wumpusX = ran.nextInt(this.numCol); 
        int wumpusY = ran.nextInt(this.numRow);

        // reselect the position of the hunter if it was initialized into a vertex that has few neighbors
        while (this.map[hunterX][hunterY].neighbors().size() < 2){
            hunterX = ran.nextInt(this.numCol);
            hunterY = ran.nextInt(this.numRow);
        }

        // make sure that the hunter and wumpus are not initialized into the same vertex
        // and the wumpus is not in a vertex that has few neighbors
        while ((hunterX == wumpusX && hunterY == wumpusY)
        || this.map[wumpusX][wumpusY].neighbors().size() < 2){
            wumpusX = ran.nextInt(this.numCol);
            wumpusY = ran.nextInt(this.numRow);
        }

        this.hunter = new Hunter(hunterX, hunterY, this.map[hunterX][hunterY]);
        this.wumpus = new Wumpus(wumpusX, wumpusY, this.map[wumpusX][wumpusY]);
        scape.addForegroundAgent(hunter);
        scape.addForegroundAgent(wumpus);

        graph.shortestPath(wumpus.getWumpusPosition());
    }

    //restart the game
    public void reset(){
        this.setGame();
        this.win.setFocusable(true);
        this.win.requestFocus();
    }
    
    private class LandscapePanel extends JPanel {
		
        /**
         * Creates the drawing canvas
         * @param height the height of the panel in pixels
         * @param width the width of the panel in pixels
         **/
        public LandscapePanel(int height, int width) {
            super();
            this.setPreferredSize( new Dimension( width, height ) );
            this.setBackground(Color.white);
        }

        /**
         * Method overridden from JComponent that is responsible for
         * drawing components on the screen.  The supplied Graphics
         * object is used to draw.
         * 
         * @param g		the Graphics object used for drawing
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            scape.draw( g, scale );
        }
    } // end class LandscapePanel


    private class Control extends KeyAdapter implements ActionListener {
    
        public void keyTyped(KeyEvent e) {
            System.out.println( "Key Pressed: " + e.getKeyChar() );
            if( ("" + e.getKeyChar()).equalsIgnoreCase("q") ) {
                state = PlayState.STOP;
            }

            if (hunter.life()){
                //original positions of the hunter
                int x0 = hunter.getCol();
                int y0 = hunter.getRow();

                // a for going left
                if (("" + e.getKeyChar()).equalsIgnoreCase("a")){
                    if (x0 > 0) {
                        if (graph.isConnected(map[x0][y0], Direction.WEST, map[x0-1][y0]))
                            updateHunter(x0-1, y0);
                            else System.out.println("You can't go left");
                    }
                }
                //w for going up
                else if (("" + e.getKeyChar()).equalsIgnoreCase("w")){
                    if (y0 > 0) {
                        if (graph.isConnected(map[x0][y0], Direction.NORTH, map[x0][y0-1]))
                            updateHunter(x0, y0-1);
                        else System.out.println("You can't go up");
                    }
                }
                // d for going right
                else if (("" + e.getKeyChar()).equalsIgnoreCase("d")){
                    if (x0 < numCol - 1) {
                        if (graph.isConnected(map[x0][y0], Direction.EAST, map[x0+1][y0]))
                            updateHunter(x0+1, y0);
                        else System.out.println("You can't go right");
                    }
                }
                // s for going down
                else if (("" + e.getKeyChar()).equalsIgnoreCase("s")){
                    if (y0 < numRow - 1) {
                        if (graph.isConnected(map[x0][y0], Direction.SOUTH, map[x0][y0+1]))
                            updateHunter(x0, y0+1);
                            else System.out.println("You can't go down");
                    }
                }

                // press the spacebar to activate/deactivate armed mode
                else if (("" + e.getKeyChar()).equalsIgnoreCase(" ")){
                    hunter.setArmed(!hunter.isArmed());
                    if (hunter.isArmed())  System.out.println("You are armed!");
                    else  System.out.println("You are disarmed!");                    
                }
            }
            repaint();
        }

        public void actionPerformed(ActionEvent event) {
            // If the Quit button was pressed
            if( event.getActionCommand().equalsIgnoreCase("Quit") ) {
		        System.out.println("Quit button clicked");
                state = PlayState.STOP;
            }

            if (event.getActionCommand().equalsIgnoreCase("Restart")){
                System.out.println("Restarting the game.");
                reset();
            }
        }
    } // end class Control


    // This is the class where you define functions that are 
    // executed when certain mouse events take place.
    private class MouseControl extends MouseInputAdapter {
        public void mouseMoved(MouseEvent e) {
            fieldX.setText( "" + e.getPoint().x );
            fieldY.setText( "" + e.getPoint().y );
        }

        public void mouseDragged(MouseEvent e) {
            fieldX.setText( "" + e.getPoint().x );
            fieldY.setText( "" + e.getPoint().y );
        }
        
        public void mousePressed(MouseEvent e) {
            System.out.println( "Pressed: " + e.getClickCount() );
        }

        public void mouseReleased(MouseEvent e) {
            System.out.println( "Released: " + e.getClickCount());
        }

        public void mouseEntered(MouseEvent e) {
            System.out.println( "Entered: " + e.getPoint() );
        }

        public void mouseExited(MouseEvent e) {
            System.out.println( "Exited: " + e.getPoint() );
        }

        public void mouseClicked(MouseEvent e) {
    	    System.out.println( "Clicked: " + e.getClickCount() );
        }
    } // end class MouseControl

    public void repaint() {
    	this.win.repaint();
    }

    public void dispose() {
	    this.win.dispose();
    }

    // update the hunter's motion: either changing position or fire
    public void updateHunter (int newX, int newY){
        Vertex w = wumpus.getWumpusPosition();
        this.map[newX][newY].setVisible(true);

        if (!this.hunter.isArmed()){
            hunter.updatePosition(newX, newY);
            hunter.setHunterVertex(this.map[newX][newY]);

            if (w.getCol() == newX && w.getRow() == newY){ //hunter got eaten by the wumpus
                hunter.setLife(false);
                JOptionPane.showMessageDialog(null, "You Are Kidnapped by Gargamel :(");
                state = PlayState.STOP;
            }
        }
        else{ //fire at a direction
            if (w.getCol() == newX && w.getRow() == newY){ //check if the wumpus is in the direction of firing
                wumpus.setLife(false);
                JOptionPane.showMessageDialog(null, new JLabel("YES! You Hit Gargamel!", JLabel.CENTER));
            }
            else{ // the hunter dies
                hunter.setLife(false);
                w.setVisible(true);
                JOptionPane.showMessageDialog(null, new JLabel("<html>Gargamel is Not There!<br>You Hit Yourself :(<html>", JLabel.CENTER));
            }
            state = PlayState.STOP;
        }
    }

    public static void main(String[] argv) throws InterruptedException {
        HuntTheWumpus game = new HuntTheWumpus();
        while (game.state == PlayState.PLAY) {
            game.repaint();
            Thread.sleep(10);
        }
        System.out.println("Disposing window");
        game.dispose();
    }
}