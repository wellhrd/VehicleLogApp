/*
 * The Security department of the University of the Southern Caribbean (USC) 
   needs an application to log vehicle entry and departure, to and from the university. 
 * This application will solve this problem.
 */
package usc_vehiclelogapp;
/*
 * @author Mickael Walters - 2016111033
 */
import java.util.Comparator;
import java.util.*;
import java.text.*;
import java.io.*;

public class USc_VehicleLogApp {

    public static void main(String[] args) {
        //OBJECTS
        Scanner input = new Scanner(System.in); //Accepts input from User
        USC_VehicleLinkedList vehicles = new USC_VehicleLinkedList();
        DateFormatSymbols symbols = new DateFormatSymbols();
        Date date = new Date();
     
        //VARIABLES
        String regPlate = "";
        String Fname = "";
        String Lname = "";
        String make = "";
        String model = "";
        String color = "";
        String currentDate = "";
        String timeOfEntry = "";
        int menuOption = 0;
        boolean end = false;
        String searchItem = "";
        String departTime = "";
        
        //Current Date
        DateFormat full = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
        System.out.println(full.format(date));
        
        System.out.println("^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^");
        System.out.println("Welcome to the **VEHICLE LOG APPLICATION** of University of the Southern Caribbean ");
        System.out.println("^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^");
        
        System.out.println("************MENU OPTIONS**********");
        System.out.println("1 - Add a vehicle log");
        System.out.println("2 - Update / Search");
        System.out.println("3 - Vehicle count");
        System.out.println("4 - Display Vehicles");
        System.out.println("5 - Vehicle Log History");
        System.out.println("6 - EXIT ");
        System.out.println("*************END of MENU***********");
        System.out.println(); //Line space
     
        while(menuOption != 6){ //Runs till user terminiates program
            //Check if user actually enters and integer or is witin the specified range.
            do {
                try {
                    System.out.println("Select an options 1 - 6");
                    menuOption = input.nextInt(); //Acepts only integers 
                    end = true;
                }
                catch(Exception e){
                    System.err.println("You did not enter an Integer!");
			input.next();//capture '\n' character
			end = false;
		}//end try-catch 
                }while(!end);//end do-while for error check */
            
            if(menuOption == 1){
                try {
		//PrintWriter instance to write data to file
		FileWriter outFile = new FileWriter("C:\\Users\\Mickael Walter\\Documents\\JAVA Files\\uSc_VehicleLogApp\\src\\usc_vehiclelogapp\\vehicle_data_file.txt",true);
                
                do{   
                System.out.println("Please enter the Registration Plate of Vehicle");
                regPlate = input.next();
            
                System.out.println("Please enter the Driver's First and Last name");
                Fname = input.next();
                Lname = input.next();
                System.out.println("Enter vehicle MAKE");
                make = input.next();
                System.out.println("Enter vehicle MODEL");
                model = input.next();
                System.out.println("Enter vehicle COLOR");
                color = input.next();
                System.out.println("Please enter today's date - MM/DD/YYYY");
                currentDate = input.next();
                System.out.println("Enter the time of Entry - HH:MM");
                timeOfEntry = input.next();
                
                //Send Data to insert Method
                vehicles.insertBack(regPlate, Fname, Lname, make, model, color, currentDate, timeOfEntry);               
                //Write data to file
                    outFile.write("\n REGISTRATION PLATE: " + regPlate
                                + "\n FIRST NAME: "+ Fname + " LAST NAME: " + Lname
                                + "\n VEHICLE MAKE: " + make
                                + "\n VEHICLE MODEL: " + model 
                                + "\n VEHICLE COLOR: " + color
                                + "\n CURRENT DATE: " + currentDate
                                + "\n ENTRY TIME: " + timeOfEntry + "\n");
                //End writing to file
		}while(!end); //End do while
                
                   outFile.close();//close file
		}
		catch (IOException e){
                    System.err.println(" Unable to open file.");
		} //end try-catch
            } //end-if menuOption 1
            
            else if (menuOption == 2){
                System.out.println("Search for the exiting vehicle registration plate number");
                searchItem = input.next();
                System.out.println("Our search found item to be " + vehicles.search(searchItem));
                    //Update info. for when vehicle exist compound, creates vehicle_log_history file
                    try {
                        FileWriter outPut = new FileWriter("C:\\Users\\Mickael Walter\\Documents\\JAVA Files\\uSc_VehicleLogApp\\src\\usc_vehiclelogapp\\vehicle_log_history.txt", true);
                    do {
                        System.out.println("Enter the departure time - HH:MM of exiting vehicle");
                        departTime = input.next();
                        //Store data of exiting vehicle into vehicle_log_history file
                        outPut.write("\nVEHICLE NUMBER: " + regPlate + "  | DEPARTURE TIME : " + departTime
                                + "\n DRIVER's NAME: " + Fname + " " + Lname
                                + "\n VEHICLE MAKE: " + make
                                + "\n VEHICLE COLOR: " + color
                                + "\n CURRENT DATE: " + currentDate
                                + "\n TIME OF ENTRY: " +timeOfEntry + "\n");
                    }while(!end); //end-do-while
                    outPut.close(); //Close file
                    }
                    catch (IOException e){
                    System.err.println(" Unable to open file.");
		} //end try-catch
                    //Delete this entry from the linkList
                        vehicles.deleteNode(searchItem);
                    //Delete same entry from vehicle_log_file
                    //********* code for this goes here*****
                        System.out.println("Vehicle data have been successfully appended!");
                } //end-if menuOption 2
            
            else if (menuOption == 3){
                vehicles.vehicleCount(); //Displays the number of vehicles on compound.
            } //end-if menuOption 3
            
            else if (menuOption == 4){
                vehicles.displayList(); //This displays the vehicles data
                System.out.println(); //Line space
                System.out.println("Sorted Display of Vehicles By Registration plate:");
                vehicles.head = vehicles.mergeSort(vehicles.head); //Call merge sort method
                vehicles.displayMergeSort(vehicles.head); //Prints List Sorted! Thank You Jesus!
            } //end-if menuOption 4
            
            else if (menuOption == 5){
                //This displays all log entries from the vehicle_log_history file
                try {
                    //New instance of Scanner and FileReader object
                    Scanner inFile = new Scanner( new FileReader("C:\\Users\\Mickael Walter\\Documents\\JAVA Files\\uSc_VehicleLogApp\\src\\usc_vehiclelogapp\\vehicle_log_history.txt") );
                    
                    //Iterates through file for the respective data   
                    for (int i = 1; inFile.hasNext(); i++){
                        //Read data from file
                        regPlate = inFile.nextLine(); 
                        departTime = inFile.nextLine(); 
                        Fname = inFile.nextLine();
                        Lname = inFile.nextLine();
                        color = inFile.nextLine();
                        currentDate = inFile.nextLine();
                        timeOfEntry = inFile.nextLine();
                        //End Reaading & Start Printing From file
                        System.out.println("\n VEHICLE NUMBER: " + regPlate + "  | DEPARTURE TIME : " + departTime    
                                + "\n DRIVER's NAME: " + Fname + " " + Lname
                                + "\n VEHICLE MAKE: " + make
                                + "\n VEHICLE COLOR: " + color
                                + "\n CURRENT DATE: " + currentDate
                                + "\n TIME OF ENTRY: " + timeOfEntry );   
                    } //end-for
		}
		catch (FileNotFoundException e) {
                    System.err.println("Unable to open file.");
		}               
            } //end-if menuOption 5
            
            else if (menuOption == 6){
                System.out.println("Goodbye! This application will now close.");
            } //end-if menuOption 6
            
            else{
                System.err.println("Your selection is out of the specified range!");
            }
        System.out.println(); //1 Line space    
        System.out.println("************MENU OPTIONS**********");
        System.out.println("1 - Add a vehicle log");
        System.out.println("2 - Update / Search");
        System.out.println("3 - Vehicle count");
        System.out.println("4 - Display Vehicles");
        System.out.println("5 - Vehicle Log History");
        System.out.println("6 - EXIT ");
        System.out.println("*************END of MENU***********");
        System.out.println(); // 1 Line space
        } //END-menOption-WHILE
        
    } //END MAIN
    
} //END CLASS
