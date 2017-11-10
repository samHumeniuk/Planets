/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Sam
 */
public class PlanetsUI 
{

    public PlanetsUI() throws InterruptedException 
    {
        drawSolarSystem();
    }
    
    public void drawSolarSystem() throws InterruptedException
    {

        JFrame f = new JFrame("Solar System");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        MyPanel panel = new MyPanel();
        f.add(panel);
        f.pack();
        panel.move();
         
    }
     
    class MyPanel extends JPanel {

        ArrayList<CelestialBody> solarSystem = new ArrayList();
        
        public MyPanel() throws InterruptedException 
        {
            setBorder(BorderFactory.createLineBorder(Color.black));
            createPlanets();
            
            
            
        }
        
        public void createPlanets()
        {

            CelestialBody sun = new CelestialBody(1000, 250, 250, 0, 0, Color.ORANGE, 50);
            solarSystem.add(sun);
            CelestialBody earth = new CelestialBody(1, 250, 20, 2.1, 0, Color.cyan, 10);
            solarSystem.add(earth);
            CelestialBody moon = new CelestialBody(0.01, 250.00001, 12, 2.3, 0, Color.gray, 5);
            solarSystem.add(moon);

        }
        public void move() throws InterruptedException
        {
            int moveCount = 0;
            
            while(moveCount < 10000)
            {
                
                for(CelestialBody celestialBodyOne: solarSystem)
                {
                    double changeInXVelocity = 0;
                    double changeInYVelocity = 0;

                    for(CelestialBody celestialBodyTwo: solarSystem)
                    {
                        if (celestialBodyOne != celestialBodyTwo)
                        {
                            double distanceX = (celestialBodyOne.xCoordinate - celestialBodyTwo.xCoordinate);
                            double distanceY = (celestialBodyOne.yCoordinate - celestialBodyTwo.yCoordinate);
                            double distance = Math.sqrt(distanceX*distanceX + distanceY*distanceY);
                            double force = (celestialBodyOne.mass * celestialBodyTwo.mass)/(distance*distance);
                            double acceleration = force/celestialBodyOne.mass;
                            double differenceX = celestialBodyTwo.xCoordinate - celestialBodyOne.xCoordinate;
                            int isXPositive= 1;
                            int isYPositive = 1;
                            double differenceY = celestialBodyTwo.yCoordinate - celestialBodyOne.yCoordinate;
                            if(differenceX <0)
                            {
                                isXPositive = -1;
                            }
                            if(differenceY <0)
                            {
                                isYPositive = -1;
                            }
                            double differenceXSquared = differenceX * differenceX;
                            double differenceYSquared = differenceY * differenceY;
                            double accelerationSquared = acceleration * acceleration;
                            double accelerationX = isXPositive * Math.sqrt(accelerationSquared*differenceXSquared/(differenceXSquared + differenceYSquared));
                            double accelerationY = isYPositive * Math.sqrt(accelerationSquared*differenceYSquared/(differenceXSquared + differenceYSquared));

                            changeInXVelocity = changeInXVelocity + accelerationX;
                            changeInYVelocity = changeInYVelocity + accelerationY;
                        }

                    }
                    celestialBodyOne.xVelocity = celestialBodyOne.xVelocity + changeInXVelocity;
                    celestialBodyOne.yVelocity = celestialBodyOne.yVelocity + changeInYVelocity;

                    celestialBodyOne.xCoordinate = celestialBodyOne.xCoordinate + celestialBodyOne.xVelocity;
                    celestialBodyOne.yCoordinate = celestialBodyOne.yCoordinate + celestialBodyOne.yVelocity;
                    repaint();

                }
                
                moveCount = moveCount + 1;
                Thread.sleep(10);
            }
            
            
        }

        public Dimension getPreferredSize() 
        {
            return new Dimension(500,500);
        }

        public void paintComponent(Graphics g) 
        {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            
            g.fillRect(0, 0, 500, 500);
            

            // Draw Text
            for(CelestialBody c: solarSystem)
            {
                g.setColor(c.colour);
                double x = c.xCoordinate-(c.radius/2);
                double y = c.yCoordinate-(c.radius/2);
                int xInt = (int)Math.round(x);
                int yInt = (int)Math.round(y);
                int radius = (int)Math.round(c.radius);
                g.fillOval(xInt, yInt, radius, radius);
            }
            
        }  
    }
}
    
