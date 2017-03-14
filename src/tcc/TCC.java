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
        
        Sensor sn1 = new Sensor(10, 20); WSN.addSensor(sn1);
        Sensor sn2 = new Sensor(10, 70); WSN.addSensor(sn2);
        Sensor sn3 = new Sensor(10, 110); WSN.addSensor(sn3);
        Sensor sn4 = new Sensor(10, 20); WSN .addSensor(sn4);
        Sensor sn5 = new Sensor(130, 70); WSN.addSensor(sn5);
        Sensor sn6 = new Sensor(80, 40); WSN.addSensor(sn6);
        Sensor sn7 = new Sensor(30, 100); WSN .addSensor(sn7);
        Sensor sn8 = new Sensor(70, 110); WSN.addSensor(sn8);
        Sensor sn9 = new Sensor(40, 50); WSN.addSensor(sn9);
        Sensor sn10 = new Sensor(50, 90); WSN.addSensor(sn10);
        Sensor sn11 = new Sensor(140, 110); WSN.addSensor(sn11);
        Sensor sn12 = new Sensor(100, 80); WSN.addSensor(sn12);
        Sensor sn13 = new Sensor(60, 60); WSN.addSensor(sn13);
        Sensor sn14 = new Sensor(50, 30); WSN.addSensor(sn14);
        Sensor sn15 = new Sensor(140, 30); WSN.addSensor(sn15);
        
        for (int i = 0; i < WSN.size(); i++) {
            System.out.println(WSN.getSensor(i));
        }
    }

}
