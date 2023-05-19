package inforepository;
//package infoRepository;
//
//import java.util.ArrayList;
//
//import constants.HospitalFloors;
//import controllers.FloorController;
//import models.Floor;
//
//public class FloorControllerService {
//
//	private static FloorControllerService service = null;
//    private ArrayList<FloorController> floors;
//
//    private FloorControllerService()
//    {
//    	floors = new ArrayList<FloorController>();
//    }
//    
//    public static synchronized FloorControllerService getInstance()
//    {
//        if (service == null)
//        	service = new FloorControllerService();
//  
//        return service;
//    }
//
//    public synchronized void addFloor(Floor p) {
//    	floors.add(new FloorController(p));
//    }
//    
//    public synchronized void addFloor(FloorController p) {
//    	floors.add(p);
//    }
//    
//	public ArrayList<FloorController> getFloors() {
//		return floors;
//	}
//	
//	public FloorController getFloorFromName(String name) throws Exception {
//		for (FloorController f: floors) {
//			if (f.getName().equals(name)) {
//				return f;
//			}
//		}
//		throw new Exception("Invalid floor name");
//	}
//	
//	public FloorController getFloorFromNumber (int number) throws Exception {
//		if (number < 1 || number > HospitalFloors.count) throw new Exception("Invalid floor number");
//		return floors.get(number - 1);
//	}
//}
