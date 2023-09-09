/*
Luhang Sun
CS 231 Project 2
NewCell.java
*/
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

public class NewCell extends Cell{
    public NewCell(){
        super();
    }

    public void draw(Graphics g, int x, int y, int scale) {
        if (this.life) {
            g.setColor(Color.gray);
            g.fillRect(x, y, scale, scale);
        } else {
            g.setColor(Color.black);
            g.fillRoundRect(x, y, scale, scale, 2, 2);
        }
    }
}