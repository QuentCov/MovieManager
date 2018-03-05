/*
 * Insert Addresses
 */
INSERT INTO Address
VALUES (1, '5487 S. Minesota Ave.', 'Suite #6', 'Sioux Falls', 'SD', 57106);
INSERT INTO Address
VALUES (2, '9837 Z Street', '', 'Lincoln', 'NE', 15489);
INSERT INTO Address
VALUES (3, '101 Independence Ave SE', '', 'Washington', 'DC', 20540);
INSERT INTO Address
VALUES (4, '879 N. North St.', '', 'Juneau', 'AL', 59754);
INSERT INTO Address
VALUES (5, '987 E. East St.', '', 'Denver', 'CO', 26489);
INSERT INTO Address
VALUES (6, '9001 Dragon Ref', 'Suite #B2', 'Kailua', 'HI', 59764);

/*
 * Insert Movies (The ThumbnailData will need to be added later).
 */

INSERT INTO Movie (ID, Name, Genre, ThumbnailName, Description, Runtime, Rating) 
VALUES (1, 'The Cat with the Kind and Reassuring Face - The Movie', 'Action', 'https://78.media.tumblr.com/21b015f2e221dce2edf87adb531dfd98/tumblr_inline_ovbvr6cmPh1ret3g1_500.jpg', 'This is an excuse to put this thumbnail here.', 999, 'PG');
INSERT INTO Movie (ID, Name, Genre, ThumbnailName, Description, Runtime, Rating) 
VALUES (2, 'The Shawshank Redemption', 'Drama', 'https://ae01.alicdn.com/kf/HTB1o.8xPVXXXXX_XXXXq6xXFXXX3/Dropship-The-Shawshank-Redemption-Nostalgia-classic-movie-kraft-paper-bar-poster-Retro-Poster-decorative-painting.jpg_640x640.jpg', 'Man uses a poster of Rita Hayworth in an unusual way.', 144, 'PG-13');
INSERT INTO Movie (ID, Name, Genre, ThumbnailName, Description, Runtime, Rating) 
VALUES (3, 'Get Out', 'Horror', 'https://images-na.ssl-images-amazon.com/images/M/MV5BMjUxMDQwNjcyNl5BMl5BanBnXkFtZTgwNzcwMzc0MTI@._V1_SY1000_CR0,0,675,1000_AL_.jpg', 'The State of American Minorities - The Movie.', 104, 'R');
INSERT INTO Movie (ID, Name, Genre, ThumbnailName, Description, Runtime, Rating) 
VALUES (4, 'The Shape of Water', 'Horror', 'https://images-na.ssl-images-amazon.com/images/M/MV5BNGNiNWQ5M2MtNGI0OC00MDA2LWI5NzEtMmZiYjVjMDEyOWYzXkEyXkFqcGdeQXVyMjM4NTM5NDY@._V1_SY1000_CR0,0,674,1000_AL_.jpg', 'Woman falls in love with fish.', 123, 'R');
INSERT INTO Movie (ID, Name, Genre, ThumbnailName, Description, Runtime, Rating) 
VALUES (5, 'A Hard Days Night', 'Comedy', 'http://www.theuncool.com/wp-content/uploads/2013/10/aharddaysnightposter.jpg', 'Ringo betrays the Beatles. Im serious', 92, 'G');

/*
 * Insert Users (Customers and Owners)
 */
INSERT INTO User
VALUES (1, 'QuentCov@gmail.com', 'stream_of_consciousness', 'Customer', 'Quentin Covert', 1, '6053601540');
INSERT INTO User
VALUES (2, 'David.Cao22@gmail.com', 'tempPass!', 'Customer', 'David Cao', 2, '4024200664');
INSERT INTO User
VALUES (3, 'Libray@of.congress', 'literally_a_library', 'Customer', 'Congress Library', 3, '4879875642');
INSERT INTO User
VALUES (4, 'MonopolyMan@broad.way', 'thats_his_name', 'Owner', 'Uncle Pennybags', 3, '9989989998');
INSERT INTO User
VALUES (5, 'WorldsStrongest@gmail.com', 'password', 'Owner', 'Hercule Satan', 4, '1000000001');

/*
 * Insert Theatres
 */
INSERT INTO Theatre
VALUES (1, 'Quaint Theatre', 5, 4);
INSERT INTO Theatre
VALUES (2, 'The Greatest Theatre', 6, 5);

/*
 * Insert CreditCards
 */
INSERT INTO CreditCard
VALUES (1, 1, Visa, 2529252925292529, 805, 05, 2019);
INSERT INTO CreditCard
VALUES (2, 2, Visa, 1111111111111111, 876, 12, 2020);
INSERT INTO CreditCard
VALUES (3, 2, MasterCard, 6147614761476147, 645, 01, 2019);
INSERT INTO CreditCard
VALUES (4, 3, Visa, 8017801780178017, 128, 09, 2021);

/*
 * Insert Reviews
 */
INSERT INTO Review
VALUES (1, 1, 'This movie is simply divine; I watch it while I murder people.', 5, 2);
INSERT INTO Review
VALUES (2, 1, '10/10, an indisputible masterpiece.', 1, 1);
INSERT INTO Review
VALUES (3, 2, '1/10, I recommend this movie only to masochists.', 4, 1);

/*
 * Insert Orders
 */
INSERT INTO Orders
VALUES (1, 'a3ccf74f-bfc4-4875-b176-ab6ff0e669b5', 'Thu Jan 06 10:52:56 IST 2018', 2, 1477.24, 1, 4, 5);
INSERT INTO Orders
VALUES (2, '50274b68-0a98-4e84-ac24-935881d23f9c', 'Thu Jan 06 11:52:56 IST 2018', 2, 6611.49, 2, 1, 1);
INSERT INTO Orders
VALUES (3, '0ee4e1ce-681f-45f9-9352-90a159228385', 'Thu Jan 07 10:52:56 IST 2018', 3, 765, 4, 2, 3);

/*
 * Insert Showrooms
 */
INSERT INTO Showroom
VALUES (1, 'Quaint Theatre #1', 500, 1);
INSERT INTO Showroom
VALUES (2, 'The Greatest Showroom', 1500, 2);
INSERT INTO Showroom
VALUES (3, 'The Less-Great Showroom', 900, 2);

/*
 * Insert OrderMovies
 */
INSERT INTO OrdersMovies 
VALUES (1, 1, 4, 2);
INSERT INTO OrdersMovies 
VALUES (2, 1, 3, 300);
INSERT INTO OrdersMovies 
VALUES (3, 2, 1, 51);
INSERT INTO OrdersMovies 
VALUES (4, 2, 2, 50);
INSERT INTO OrdersMovies 
VALUES (4, 2, 5, 800);
INSERT INTO OrdersMovies 
VALUES (5, 3, 5, 100);

/*
 * Insert MovieShowings
 */
INSERT INTO MovieShowing
VALUES (1, 1, 1, 'Thu Jan 08 10:52:00 IST 2018', 'Thu Jan 09 3:28:00 IST 2015', 450, 6.99);
INSERT INTO MovieShowing
VALUES (2, 1, 1, 'Thu Jul 08 10:52:00 IST 2018', 'Thu Jul 09 3:28:00 IST 2015', 400, 3.50);
INSERT INTO MovieShowing
VALUES (3, 2, 1, 'Thu Sep 08 10:52:00 IST 2018', 'Thu Sep 09 3:28:00 IST 2015', 200, 4.87);
INSERT INTO MovieShowing
VALUES (4, 3, 1, 'Thu Aug 08 10:52:00 IST 2018', 'Thu Aug 09 3:28:00 IST 2015', 500, 8.12);
INSERT INTO MovieShowing
VALUES (5, 4, 2, 'Thu Feb 08 10:52:56 IST 2018', 'Thu Feb 08 1:16:56 IST 2015', 1400, 7.65);
INSERT INTO MovieShowing
VALUES (6, 5, 2, 'Thu Dec 08 10:52:56 IST 2018', 'Thu Dec 08 1:16:56 IST 2015', 1, 8.45);