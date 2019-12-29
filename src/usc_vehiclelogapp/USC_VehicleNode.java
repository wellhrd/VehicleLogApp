package usc_vehiclelogapp;

/**
 * @author Mickael Walters
 */
public class USC_VehicleNode {
    //DATA FIELDS
    public String regPlate;
    public String Fname;
    public String Lname;
    public String make;
    public String model;
    public String color;
    public String currentDate;
    public String timeOfEntry;
    
    //REFERENCE
    public USC_VehicleNode link;
    
    //DEFAULT CONSTRUCTOR
    public USC_VehicleNode(){
        regPlate = "";
        Fname = "";
        Lname = "";
        make = "";
        model = "";
        color = "";
        currentDate = "";
        timeOfEntry = "";
        link = null;
    }//END of CONSTRUCTOR
    
    //OVERLOADED CONSTRUCTOR
    public USC_VehicleNode(String plate,String fn, String ln, String mk, String mdl, String col, String date, String time, USC_VehicleNode lnk){
        regPlate = plate;
        Fname = fn;
        Lname = ln;
        make = mk;
        model = mdl;
        color = col;
        currentDate = date;
        timeOfEntry = time;
        link = lnk;
    } //END of OVERLOADED CONSTRUCTOR
    
} //END of NODE CLASS
