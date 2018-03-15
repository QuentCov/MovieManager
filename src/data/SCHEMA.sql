CREATE TABLE IF NOT EXISTS Address
	(ID INT NOT NULL AUTO_INCREMENT,
	Address1 VARCHAR(255) NULL DEFAULT NULL,
	Address2 VARCHAR(255) NULL DEFAULT NULL,
	City VARCHAR(255) NULL DEFAULT NULL,
	StateAbbreviation VARCHAR(2) NULL DEFAULT NULL,
	ZipCode INT(5) NULL DEFAULT NULL,
	PRIMARY KEY ( ID ));

CREATE TABLE IF NOT EXISTS User
    (ID INT NOT NULL AUTO_INCREMENT,
    EmailAddress VARCHAR(255) NOT NULL,
    Password VARBINARY(255) NOT NULL,
    Type VARCHAR(255) NULL DEFAULT NULL,
    FullName VARCHAR(255) NULL DEFAULT NULL,
    AddressId INT,
    PhoneNumber VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY ( ID ),
    CONSTRAINT FK_AddressId FOREIGN KEY (AddressId) REFERENCES Address(ID));

CREATE TABLE IF NOT EXISTS CreditCard
	(ID INT NOT NULL AUTO_INCREMENT,
	OwnerId INT NOT NULL,
	CardType VARCHAR(255) NULL DEFAULT NULL,
	CardNumber VARCHAR(255) NULL DEFAULT NULL,
	CCV INT(3) NULL DEFAULT NULL,
	ExpirationMonth INT(2) NULL DEFAULT NULL,
	ExpirationYear INT(4) NULL DEFAULT NULL,
	Balance DECIMAL(15,2) NULL DEFAULT NULL,
	PRIMARY KEY ( ID ),
	CONSTRAINT FK_OwnerId FOREIGN KEY (OwnerId) REFERENCES User(ID));

CREATE TABLE IF NOT EXISTS Movie
    (ID INT NOT NULL AUTO_INCREMENT,
    Name VARCHAR(255) NULL DEFAULT NULL,
    Genre VARCHAR(255) NULL DEFAULT NULL,
    ThumbnailName VARCHAR(255) NULL DEFAULT NULL,
    ThumbnailData MEDIUMBLOB NULL DEFAULT NULL,
    Description VARCHAR(255) NULL DEFAULT NULL,
    Runtime INT(3) NULL DEFAULT NULL,
    Rating VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY ( ID ));

CREATE TABLE IF NOT EXISTS Review
	(ID INT NOT NULL AUTO_INCREMENT,
	ReviewerId INT,
	Review VARCHAR(255) NULL DEFAULT NULL,
	Rating INT(1),
	MovieId INT,
	PRIMARY KEY ( ID ),
	CONSTRAINT FK_ReviewerId FOREIGN KEY (ReviewerId) REFERENCES User(ID),
	CONSTRAINT FK_MovieId FOREIGN KEY (MovieId) REFERENCES Movie(ID));

CREATE TABLE IF NOT EXISTS Orders
    (ID INT NOT NULL AUTO_INCREMENT,
    OurId VARCHAR(36) NULL DEFAULT NULL,
    Date VARCHAR(255) NULL DEFAULT NULL,
    CustomerId INT,
    Cost DECIMAL(15,2) NULL DEFAULT NULL,
    CreditCardId INT,
    BillingAddressId INT,
    ShippingAddressId INT,
    Fulfilled BOOLEAN NULL DEFAULT FALSE,
    PRIMARY KEY ( ID ),
    CONSTRAINT FK_CustomerId FOREIGN KEY (CustomerId) REFERENCES User(ID),
    CONSTRAINT FK_BillingAddressId FOREIGN KEY (BillingAddressId) REFERENCES Address(ID),
    CONSTRAINT FK_ShippingAddressId FOREIGN KEY (ShippingAddressId) REFERENCES Address(ID),
    CONSTRAINT FK_CreditCardId FOREIGN KEY (CreditCardId) REFERENCES CreditCard(ID));

CREATE TABLE IF NOT EXISTS OrdersMovies
    (ID INT NOT NULL AUTO_INCREMENT,
    OrdersId INT,
    MovieShowingsId INT,
    NumTickets INT(3) NULL DEFAULT NULL,
    PRIMARY KEY ( ID ),
    CONSTRAINT FK_OrdersId FOREIGN KEY (OrdersId) REFERENCES Orders(ID),
    CONSTRAINT FK_MovieShowingsId FOREIGN KEY (MovieShowingsId) REFERENCES MovieShowing(ID));

CREATE TABLE IF NOT EXISTS Theatre
    (ID INT NOT NULL AUTO_INCREMENT,
    Name VARCHAR(255) NULL DEFAULT NULL,
    AddressId INT,
    OwnerId INT,
    PRIMARY KEY ( ID ),
    CONSTRAINT FK_TheatreAddressId FOREIGN KEY (AddressId) REFERENCES Address(ID),
    CONSTRAINT FK_TheatreOwnerId FOREIGN KEY (OwnerId) REFERENCES User(ID));

CREATE TABLE IF NOT EXISTS Showroom
    (ID INT NOT NULL AUTO_INCREMENT,
    Name VARCHAR(255) NULL DEFAULT NULL,
    Capacity INT(3),
    TheatreId INT,
    PRIMARY KEY ( ID ),
    CONSTRAINT FK_TheatreId FOREIGN KEY (TheatreId) REFERENCES Theatre(ID));

CREATE TABLE IF NOT EXISTS MovieShowing
    (ID INT NOT NULL AUTO_INCREMENT,
    MovieId INT,
    ShowroomId INT,
    StartTime VARCHAR(255) NULL DEFAULT NULL,
    EndTime VARCHAR(255) NULL DEFAULT NULL,
    NumTicketsSold INT(3) NULL DEFAULT NULL,
    Cost DECIMAL(15,2) NULL DEFAULT NULL,
    PRIMARY KEY ( ID ),
    CONSTRAINT FK_MovieInShowingId FOREIGN KEY (MovieId) REFERENCES Movie(ID),
    CONSTRAINT FK_ShowroomId FOREIGN KEY (ShowroomId) REFERENCES Showroom(ID));
