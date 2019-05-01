CREATE TABLE Locations (
    LocationID int,
    Label varchar(255),
);

CREATE TABLE DonationItems ( 
    ItemID int, 
    DonorID int, 
    Name varchar(255), 
    Description varchar(255),
);

CREATE TABLE Donors ( 
    DonorID int, 
    FirstName varchar(255),
    LastName varchar(255),
    Email varchar(255), 
    ContactNumber varchar(255),
    CreatedAt varchar(255),
    Lastlogin varchar(255), 
);

CREATE TABLE DonorHistory (
    ID int, 
    DonorID int, 
    DonationID int, 
    DonationBook varchar(255), 
    DonationCompletedAt varchar(255), 
); 

CREATE TABLE Bookings (
    BookingID int, 
    DonorID int, 
    CharityID int, 
    BookingDate varchar(255), 
    Cancelled varchar(255), 
);

CREATE TABLE CharityUsers (
    ID int, 
    CharityID int, 
    FirstName varchar(255),
    LastName varchar(255),
    ContactNumber varchar(255), 
    CreatedAt varchar(255),
    Lastlogin varchar(255), 
);


CREATE TABLE Charities ( 
    ID int, 
    Name int, 
    CharitySize int, 
    IndustryID int, 
    LocationID int,
    ContactNumber varchar(255), 
    CreatedAt varchar(255),
); 

CREATE TABLE Industries ( 
    IndustryID int, 
    Name varchar(255),
); 
