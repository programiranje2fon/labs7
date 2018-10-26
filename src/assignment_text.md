# Lab exercise 7

## Task 1
*(to be done by the tutor together with students)*

Create public class **Vehicle**, in package **task1**, with the following elements:

1. Private attribute **regNum** of type String that stands for the vehicle's registration number.
2. Public get and set methods for the **regNum** attribute.  
3. Redefined public method **equals** of the Object class. The method first checks if the input value is an object of type **Vehicle** and if it is not, the method returns FALSE. The method returns TRUE if the registration number of the vehicle is the same as the registration number of the input vehicle; otherwise, it returns FALSE. 
4. Redefined public method **toString** of the Object class. The method returns a string with the vehicle's registration number in the following format: "Registration number: ####"

Create public class **TestGarage** in package **task1**. In the main method of this class, create two vehicles with the following registration numbers: "BG123-AB" and "NS456-CD".

Create public class **ParkingSpace**, in package **task1**, with the following elements:

1. Private attribute **free** as an indicator the occupancy status of the parking space; it has value TRUE if the parking space is free, otherwise its value is FALSE.
2. Private attribute **vehicle**, an instance of the **Vehicle** class, that represents the vehicle parked at that space (if the space is occupied).
3. Public get and set methods for these two attributes.
4. Redefined public method **toString** of the Object class. The method returns a string with data about the parking space: if the space is free, the method returns "FREE"; if the space is occupied, the method returns: "OCCUPIED, registration number: ####". 

Create public class **Garage**, in package **task1**, with the following elements:

1. Private attribute **parkingSpaces**, an array of objects of the **ParkingSpace** class.
2. Public constructor having the capacity of the garage (i.e., the total number of parking spaces) as its input parameter. If the value of this parameter is greater than zero, the parkingSpaces array is initialized to that value. Otherwise, that is, if the value of the input parameter is less than or equal to zero, the capacity of the garage should be set to 40 and the "ERROR" message should be printed on the console. In either case, all parking spaces (i.e., the elements of the parkingSpaces array) should be initialized and set to be free.
3. Public constructor that receives an array of **ParkingSpace** objects as its input parameter. If this array is not NULL, it is assigned to the **parkingSpaces** attribute as its value; otherwise, an error message ("ERROR") is printed on the console. 
4. Public method **print** that prints the data about all parking spaces in the garage; data about each parking space is printed in a separate row and indexed by its ordinal number in the array, for example:
<ol>
  <li>OCCUPIED, registration number: BG123-AB</li>
  <li>FREE</li>
  <li>.... </li>
</ol>
<br/>   
5. Public method **anyFreeSpace** that checks if there is a free parking space; the method returns true if there is at least one free parking space, otherwise it returns false.
6. Public method **parkTheVehicle** for ‘parking’ a vehicle in the garage. The input parameter of this method is an object of the **Vehicle** class representing the vehicle to be parked. The vehicle should be ‘parked’ at the first free space. If there are no free spaces, an appropriate message ("THE GARAGE IS FULL") should be printed on the screen.
7. Public method **unparkVehicle** for removing a vehicle from the garage. The input parameter of the method is an object of the **Vehicle** class representing the vehicle to be unparked. If the vehicle is in the garage, it should be 'unparked', so that the parking space becomes free again. Removing a vehicle assumes that the parking space occupied by that vehicle is set free and the vehicle object is destroyed (set to NULL).

In the existing **TestGarage** class, create one object of the **Garage** class with the capacity of 10 parking spaces and 'park' two vehicles in this garage. Print the data about the garage. Then, unpark the first vehicle, and again print the data about the garage. 


## Task 2
*(students work on their own)*

In package **task2**, create class **FoodItem** with:

1. Private attributes **name** and **price**.
2. Public get and set methods for the two attributes.

In the same package (**task2**), create the **ConsumerBasket** class with:

1. Private attribute **food** that represents an array of objects of the **FoodItem** class.
2. Public constructor having as its input parameter the capacity of the consumer basket, that is, the maximum number of food items in the given consumer basket. If the value of this parameter is greater than zero and less than 20, the **food** array should be initialized to that value. Otherwise, instantiate **food** as an array of maximum 10 elements and print the "ERROR" message on the console.
3. Public method **addToTheBasket** that receives, as its input parameter, an object of the **FoodItem** class to be added to the consumer basket. The food item should be added at the first free place in the basket. If there is no more free places in the basket, print the message "THE BASKET IS FULL".
4. Public method **totalPrice** that computes and returns the total price of all the items in the basket. Note that the basket may not be full (that is, there may be empty spaces in the array).
5. Public method **theMostExpensive** that returns the **FoodItem** object with the highest price in the basket. If the basket is empty, the method returns NULL. If there are multiple items with the same price, return any of these. Note that the basket may not be full (that is, there may be empty spaces in the array).

Create public class **TestConsumerBasket** in package **task2**. In the main method of this class, create one object of the **ConsumerBasket** class that can hold at most 15 items. Add to the basket the following food items: "bread" with the price of 50 RSD, "milk" 80 RSD, "chocolate" 100 RSD. Print the total price of the items in the basket and the name of the most expensive item.  