CREATE DATABASE echoDB;
USE echoDB;

CREATE TABLE Account (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    accountType VARCHAR(7) NOT NULL
);

CREATE TABLE Student (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    batch VARCHAR(2) NOT NULL,
    rollnumber VARCHAR(7) NOT NULL UNIQUE,
    phone VARCHAR(15) NOT NULL UNIQUE,
    account_id INT,
    FOREIGN KEY (account_id) REFERENCES Account(account_id)
);

CREATE TABLE Society (
    society_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    members INT NOT NULL,
    description TEXT,
    isApproved TINYINT(1) NOT NULL DEFAULT 0,
    account_id INT,
    FOREIGN KEY (account_id) REFERENCES Account(account_id)
);

CREATE TABLE SocietyMember (
    member_id INT AUTO_INCREMENT PRIMARY KEY,
    role VARCHAR(50) NOT NULL,
    society_id INT NOT NULL,
    student_id INT NOT NULL,
    status ENUM('Pending', 'Approved', 'Rejected') DEFAULT 'Pending',
    FOREIGN KEY (society_id) REFERENCES Society(society_id),
    FOREIGN KEY (student_id) REFERENCES Student(student_id)
);

CREATE TABLE Post (
    post_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT NOT NULL,
    content TEXT NOT NULL,
    date DATETIME DEFAULT NOW(),
    FOREIGN KEY (account_id) REFERENCES Account(account_id)
);

CREATE TABLE Announcement (
    announcement_id INT AUTO_INCREMENT PRIMARY KEY,
    society_id INT NOT NULL,
    content TEXT NOT NULL,
    date DATETIME DEFAULT NOW(),
    FOREIGN KEY (society_id) REFERENCES Society(society_id)
);

CREATE TABLE Venue (
    venue_id INT AUTO_INCREMENT PRIMARY KEY,
    venue_name VARCHAR(100) NOT NULL,
    location TEXT
);

CREATE TABLE Event (
    event_id INT AUTO_INCREMENT PRIMARY KEY,
    society_id INT NOT NULL,
    event_name VARCHAR(100) NOT NULL,
    event_description TEXT,
    venue_id INT,
    date DATE NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    FOREIGN KEY (society_id) REFERENCES Society(society_id),
    FOREIGN KEY (venue_id) REFERENCES Venue(venue_id)
);

CREATE TABLE Notification (
    notification_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    message TEXT NOT NULL,
    date DATETIME DEFAULT NOW(),
    FOREIGN KEY (student_id) REFERENCES Student(student_id)
);

CREATE TABLE EventScheduled (
    schedule_id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT NOT NULL,
    venue_id INT NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    FOREIGN KEY (event_id) REFERENCES Event(event_id),
    FOREIGN KEY (venue_id) REFERENCES Venue(venue_id)
);

CREATE TABLE Comment (
    comment_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    content TEXT NOT NULL,
    date DATETIME DEFAULT NOW(),
    FOREIGN KEY (student_id) REFERENCES Student(student_id)
);

CREATE TABLE PostComment (
    post_comment_id INT AUTO_INCREMENT PRIMARY KEY,
    post_id INT NOT NULL,
    comment_id INT NOT NULL,
    FOREIGN KEY (post_id) REFERENCES Post(post_id),
    FOREIGN KEY (comment_id) REFERENCES Comment(comment_id)
);

CREATE TABLE AnnouncementComment (
    announcement_comment_id INT AUTO_INCREMENT PRIMARY KEY,
    announcement_id INT NOT NULL,
    comment_id INT NOT NULL,
    FOREIGN KEY (announcement_id) REFERENCES Announcement(announcement_id),
    FOREIGN KEY (comment_id) REFERENCES Comment(comment_id)
);
