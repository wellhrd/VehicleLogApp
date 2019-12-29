package usc_vehiclelogapp;
/**
 * @author Mickael Walters
 */
public class USC_VehicleLinkedList {
    //REFERENCE OBJECTS
    USC_VehicleNode head; //Starting of our list
    USC_VehicleNode tail; //End of our list
    USC_VehicleNode current; //Variable to trail our list
    USC_VehicleNode trailCurrent; //Transverse variable
        
    //VARIABLES
    int listLength; //Variable used in finding the length of our list
    String regPlate;
    String Fname;
    String Lname;
    String make;
    String model;
    String color;
    String currentDate;
    String timeOfEntry;
    
    //DEFAULT CONSTRUCTOR
    public USC_VehicleLinkedList(){
        head = null;
        listLength = 0;
    } //END of CONTRUCTOR
    
    //INSERT / ADD METHOD
    public void insertBack(String plate, String fn, String ln, String mk, String mdl, String col, String date, String time){
        //Creation of new node object
        USC_VehicleNode vehicleData = new USC_VehicleNode();
        
        //Storing of data in node
        vehicleData.regPlate = plate;
        vehicleData.Fname = fn;
        vehicleData.Lname = ln;
        vehicleData.make = mk;
        vehicleData.model = mdl;
        vehicleData.color = col;
        vehicleData.currentDate = date;
        vehicleData.timeOfEntry = time;
        
        //If list was empty - vehicles will be last node in the list
        if (head == null) {
            head = vehicleData;
            tail = vehicleData;
        }
        else {
            tail.link = vehicleData;
            tail = vehicleData;
        }
        listLength++; //Increments list
    } //END of INSERT
    
    //DISPLAYS THE NUMBER OF VEHICLES ON COMPOUND
    public void vehicleCount(){
        System.out.println(" At present, there is a total of " + listLength + " vehicle(s) on compound!");
    } //END of VEHICLE COUNT
    
    //SEARCH METHOD - using vehicle's registration plate - returns true if found
    public boolean search(String searchItem) {
        boolean found = false;
        current = head; //Point current to head to start
        while(current != null && !found) {
            if(current.regPlate.equals(searchItem))
                found = true;
            else{
                current = current.link;
            } //end-if
        } //end-while
        return found;
    } //END of SEARCH
    
    //DELETE ITEM METHOD - Delete using registration plate number
    public void deleteNode(String deleteItem){
        USC_VehicleNode current, trailCurrent; //Transverse variables
        boolean found = false;
        
        //First - We check for an empty list
        if (head == null) 
            System.err.println("Deletion from an empty list is prohibited!");
        else {
            //Check to see if item is in the first node?
            if (head.regPlate.equals(deleteItem)) {
                head = head.link;
                
                //the list had one node
                if (head == null) 
                    tail = null;
                
            listLength--;
        } //END IF
        else {
            trailCurrent = head;
            current = head.link;
              
            while (current != null && !found) {
                if (current.regPlate.equals(deleteItem)) { 
                    found = true;
                }
                    else {
                        trailCurrent = current; // points to 1st node
                        current = current.link; //points to 2nd node
                    }
                } //END WHILE
            if (found){
                listLength--;
                //Remove node from the list
                trailCurrent.link = current.link;
                
                //Deleted node was the last node
                if(tail == current) 
                    tail = trailCurrent;
            } 
                else {
                    System.out.println("I'm sorry your item to be deleted was not found in the list!");
                }
            } //END if-else
        } //END if-else
    } //END of DELETE METHOD
    
    //DISPLAY VEHICLES ON COMPOUND METHOD
    public final void displayList() {
        current = head; //Start by pointing current to head
        while (current != null) {
            System.out.println("--------Details of Vehicles--------" + "\n"
                    + "\n REGISTRATION PLATE NUMBER : " + current.regPlate
                    + "\n DRIVER'S NAME : " + current.Fname + " " + current.Lname
                    + "\n VEHICLE MAKE : " + current.make
                    + "\n VEHICLE MODEL : " +current.model
                    + "\n VEHICLE COLOR : " + current.color
                    + "\n CURRENT DATE : " + current.currentDate
                    + "\n TIME OF ENTRY : " + current.timeOfEntry );
            System.out.println("------End of Vehicle Details------");
            current = current.link; 
        } //end-while
    } //END of DISPLAY METHOD
    
    //SORT METHOD - by Registration Plate
    
    //METHOD TO FIND MIDDLE OF LINKLIST
    public USC_VehicleNode centerNode(USC_VehicleNode head){
        //Base case
        if (head == null)
            return null;
        USC_VehicleNode first = head.link;
        USC_VehicleNode second = head;
        //Variable second will point to middle node
        while (first != null){
            first = first.link;
            if(first != null) {
                second = second.link;
            } //end-if
        } //end-while
        return second;
    } //END Center Method
    
    public USC_VehicleNode mergeSort(USC_VehicleNode head){
        //Base case is head is null
        if (head == null || head.link == null)
            return head;
        //Get middle of list
        USC_VehicleNode mid = centerNode(head);
        USC_VehicleNode secHalf = mid.link;
        //Set next reference of middle to null
        mid.link = null;
        //Apply mergeSort to left & right list
        USC_VehicleNode left = mergeSort(head);
        USC_VehicleNode right = mergeSort(secHalf);
        
        USC_VehicleNode sorted = merge(left, right);
        return sorted;
    } //end-mergeSort method
    
    public USC_VehicleNode merge(USC_VehicleNode A, USC_VehicleNode B){
        USC_VehicleNode result = null;
        if( A == null)
            return B;
        if (B == null)
            return A;
        if (A.regPlate.compareTo(B.regPlate) <= B.regPlate.compareTo(A.regPlate) ) {
            result = A;
            result.link = merge(A.link, B);
        }
        else{
            result = B;
            result.link = merge(A, B.link);
        }
        return result;
    } //end Merge method
    
    public void displayMergeSort(USC_VehicleNode p){
        while (p != null){
            System.out.println("VEHICLE PLATE NUMBER :" + p.regPlate + " "
                                + "\n NAME OF DRIVER :" + p.Fname + " " + p.Lname
                                + "\n VEHICLE MAKE :" + p.make
                                + "\n VEHICLE MODEL :" + p.model
                                + "\n COLOR :" + p.color
                                + "\n CURRENT DATE :" + p.currentDate
                                + "\n ENTRY TIME :" + p.timeOfEntry
                                + "n ---------------------------------------");
            p = p.link;
        }
    } //end-display MergeSort
    
} //END of LINKED LIST
