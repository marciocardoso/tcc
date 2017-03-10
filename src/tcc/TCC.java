/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

/**
 *
 * @author Labin-L1
 */
public class TCC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        WSN wsn = new WSN();
        
        wsn.findConnectedSensors();
        
        wsn.findConnectedSensors();
        
        for( int i = 0; i < wsn.getNumberOfSensores(); i++ )
        {
            System.out.println("O sensor " + i + " está conectado a " + wsn.getSensor(i).getNumberOfConnectedSensors() );
        }
    }
    
}
