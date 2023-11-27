CREATE TABLE Authors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    authorName VARCHAR(255) NOT NULL,
    DOB DATE
);
CREATE TABLE Genres (
    id INT AUTO_INCREMENT PRIMARY KEY,
    genre VARCHAR(100) NOT NULL
);
CREATE TABLE Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL 
);


CREATE TABLE Books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    userId INT,
    genreId INT,
    authorId INT,
    publish_date DATE,
    FOREIGN KEY (userId) REFERENCES Users(id),
    FOREIGN KEY (genreId) REFERENCES Genres(id),
    FOREIGN KEY (authorId) REFERENCES Authors(id)
);


CREATE TABLE BorrowedBooks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    bookId INT,
    userId INT,
    borrowDate DATE,
    FOREIGN KEY (bookId) REFERENCES Books(id),
    FOREIGN KEY (userId) REFERENCES Users(id)
);
