package inforepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;

import constants.HospitalFloors;
import models.Floor;

public class FloorService {
	private static FloorService service = null;
    private ArrayList<Floor> floors;

    private FloorService()
    {
    	floors = new ArrayList<Floor>();
    }
    
    public static synchronized FloorService getInstance()
    {
        if (service == null)
        	service = new FloorService();
  
        return service;
    }
   
    public synchronized void addFloor(Floor p) {
    	floors.add(p);
    }
    
	public ArrayList<Floor> getFloors() {
		return floors;
	}
	
	public Floor getFloorFromName(String name) throws Exception {
		for (Floor f: floors) {
			if (f.getName().equals(name)) {
				return f;
			}
		}
		throw new Exception("Invalid floor name");
	}
	
	public Floor getFloorFromNumber (int number) throws Exception {
		if (number < 1 || number > HospitalFloors.count) throw new Exception("Invalid floor number");
		return floors.get(number - 1);
	}
}
