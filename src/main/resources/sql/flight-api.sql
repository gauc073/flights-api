/* DB Queries */
/**************/

/* Set-Up queries */
/*****************/

CREATE TABLE FLIGHT (
				Flight_Id	VARCHAR(50) PRIMARY KEY,
				Airline_Name VARCHAR(50) NOT NULL,
				Flight_Number VARCHAR(100) NOT NULL UNIQUE,
				Origin_Airport VARCHAR(50) NOT NULL,
				Origin_Airport_Code VARCHAR(50) NOT NULL,
				Destination_Airport VARCHAR(50) NOT NULL,
                Destination_Airport_Code VARCHAR(50) NOT NULL,
                Departure_Date_And_Time VARCHAR(50) NOT NULL,
                Arrival_Date_And_Time VARCHAR(50) NOT NULL,
                Cabins VARCHAR(50) NOT NULL,
				Cabin_Class ENUM('Business', 'Economy', 'First') NOT NULL,
				Number_Of_Seats INT,
);
