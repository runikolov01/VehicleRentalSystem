# VehicleRentalSystem
This project aims to design a simple yet functional vehicle rental system that manages the rental and return processes of different types of vehicles.

# Vehicle class
We have 3 types of vehicles as assignment: car, motorcycle and cargo van. Following OOP principles, I created a Vehicle abstract class that is extended by the different vehicle objects. In the case of vehicles that have common methods and behaviors, an abstract class is more appropriate than an interface because an abstract class allows us to define common characteristics and behaviors that are shared by all vehicles. The vehicle class contains the basic methods and fields that must be present in every single object that extends the vehicle.

# Car, Motorcycle and CargoVan classes
Car, Motorcycle and CargoVan classes are the objects that extends the Vehicle class. They overrides the insurance and rent calculation methods, using different parameters to change the values ​​that are given by assignment.

# Invoice class
In the Invoice class I handle the printing of the invoice that the user receives after renting the vehicle. This class handles how the invoice content is printed and the various values ​​are calculated.

# VehicleRentalSystem class
VehicleRentalSystem is the main class from which the program starts. Here the user is invited to enter his personal data, the details of the rented vehicle and the rental period. Data validation is performed in this class - any information entered by the user is checked for technical correctness. Otherwise, the user is notified of the error and prompted to enter valid data to proceed.

After the user has entered all the required input data, a new object of the Invoice class is created on which the print invoice method is called and the user recieves his invoice for the rented vehicle.
