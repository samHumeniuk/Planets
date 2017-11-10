/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planets;

import java.awt.Color;

/**
 *
 * @author Sam
 */
public class CelestialBody {
    
    public double mass;
    public double xCoordinate;
    public double yCoordinate;
    public double xVelocity;
    public double yVelocity;
    public Color colour;
    public double radius;
    
    public CelestialBody (double mass, double xCoordinate, double yCoordinate, double XVelocity, double yVelocity, Color colour, int radius)
    {
        this.mass = mass;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.xVelocity = XVelocity;
        this.yVelocity = yVelocity;
        this.colour = colour;
        this.radius = radius;
        
        
    }
    
    
}
