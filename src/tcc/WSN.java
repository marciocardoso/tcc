/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import java.util.ArrayList;

/**
 *
 * @author Labin-L1
 * @author Marcio Cardoso
 */
public class WSN {

    private static ArrayList sensorList = new ArrayList<Sensor>();

    public static Sensor getSensor(int index) {
        return (Sensor) sensorList.get(index);
    }

    public static ArrayList getSensorList() {
        return sensorList;
    }

    public static void addSensor(Sensor sensor) {
        sensorList.add(sensor);
    }

    public static int size() {
        return sensorList.size();
    }

}
