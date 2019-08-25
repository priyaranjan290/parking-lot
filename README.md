## VEHICLE PARKING MANAGEMENT SYSTEM

### Features
 
 - Create a new parking lot with given specifications
 - Park a vehicle nearest to the entry point
 - Free a given slot
 - Issue ticket to the client while parking
 - Facilitate following queries
   + Get overall status of all slots in parking lot
   + Get vehicle registration numbers with given color
   + Get slot numbers where vehicle of particular color are parked
   + Get slot number for a given registration number of vehicle 


### Assumptions

+ Slot Ids are positive from 1 to N. For two slots Ids i and j, nearest slot to entry point of parking lot is i (if i < j)
+ Create parking lot command can be issued any number of times -> This will erase all data for current parking lot if any
+ Vehicles cannot have empty registration numbers and color
+ Only one vehicle can be parked in a given slot 

### Edge cases handled
+ In case of any invalid command issued, user gets corresponding message
+ Duplicate vehicle registration numbers will not be allowed to park
+ Cannot issue a leave  command on an empty slot


### Directory Structure
+ enums folder - holds commands, vehicleType etc
+ models folder - all models are present in this folder (Cars, Vehicle etc)
+ service folder -  this holds code responsible for interaction with the models and other services
+ exception folder - any custom exception created shall be put here
+ Application.java - Main entry point of the code is in this class


### Class Modelling

**Vehicle and Car**
+ Vehicle is the base class for all vehicle types
+ Car class extends base vehicle class

**Slots**
+ holds vehicle
+ can be occupied or left

**ParkingLot**
+ Has list of Slots

**Ticket**
+ Holds information of vehicle reg number, slot id, time spent in parking 
+ Issued and closed at time of park and unPark respectively

**ParkingManager**
+ Holds ParkingLot object 
+ Facilitates actions to be taken like park/unpark etc
+ Interacts with Parking Lot, Ticketing Service and Parking lot service for the same 


### Data Flow

Following major steps are followed.

+ Application.java: user interacts with system by issuing command here
+ ClientService : command is catered here and output is also printed here
+ ParkingManager : takes corresponding action issued from ClientService. It interacts with the model and other services to cater the clientService
+ ParkingLotService : responsible for creating new parking lot, called by ParkingManager
+ SlotService : responsible for creation of new slots
+ TicketService : ParkingManager calls to issue/close a ticket 



### Project build and run steps
 
 **Requirements**

 + jdk 1.8
 + gradle 4.8
 + linux machine
  
 **Run**  
   + ./bin/setup
   + ./bin/parking_lot                (for interactive mode)
   + ./bin/parking_lot file_path      (for file mode)
 




