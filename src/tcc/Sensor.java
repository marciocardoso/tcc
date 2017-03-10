/*
 * Simula um SENSOR
 */
package tcc;

/**
 *
 * @author Labin-L1
 */
public class Sensor {
    
    //coordenadas do sensor no Plano
    int x;
    int y;
    
    //atribui uma posição aleatória ao sensor;
    public Sensor()
    {
        this.x = (int)(Math.random()*100);
        this.y = (int)(Math.random()*100);
    }
    
    //gets para as coordenadas
    public int getX()
    {
        return this.x;
    }
    
    public int getY()
    {
        return this.y;
    }
    
    
    //calcula e retorna a distance deste sensor até qualquer outro        
    public double distanceTo (Sensor sensor)
    {
        int xDistance = Math.abs( getX() - sensor.getX() );
        int yDistance = Math.abs( getY() - sensor.getY() );
        
        double distance = Math.sqrt( xDistance*xDistance + yDistance*yDistance );
        
        return distance;
    }
    
}
