package model;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author auswise
 */
public class LetterPanel extends JPanel
{
    private int x;
    private int y;
    private int z;
    private BufferedImage bimg;
    
    
    
    //String bla = "ble";
    
    public LetterPanel()
    {
        bimg=new BufferedImage(300,300, BufferedImage.TYPE_INT_ARGB);;
        addMouseMotionListener(new MouseMotionListener() {
            
            @Override
            public void mouseDragged(MouseEvent me) 
            {
                Graphics g= bimg.createGraphics();
                g.setColor(Color.BLACK);
                g.drawLine(x,y,me.getX(),me.getY());
                x=me.getX();
                y=me.getY();
                repaint();
//                System.out.println("AAAAAAAAA");
            }

            @Override
            public void mouseMoved(MouseEvent me) 
            {
                x=me.getX();
                y=me.getY();
////                System.out.println(x+" "+y);
            }
        });
        
        
        
        clear();
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d=(Graphics2D) g;
	g2d.drawImage(bimg, null, 0, 0);
  
    }
    
    public void clear()
    {
        Graphics g= bimg.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 300, 300);
        repaint();
    }
    
    public BufferedImage getBimg()
    {
        return bimg;
    }
    
    
}
