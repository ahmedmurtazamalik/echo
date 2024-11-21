USE echoDB;

DELIMITER $$

-- ********** Student Procedures **********
CREATE PROCEDURE CreateStudent(
    IN p_username VARCHAR(50),
    IN p_password VARCHAR(100),
    IN p_accountType VARCHAR(7),
    IN p_name VARCHAR(50),
    IN p_email VARCHAR(25),
    IN p_batch VARCHAR(2),
    IN p_rollnumber VARCHAR(7),
    IN p_phone VARCHAR(15)
)
BEGIN
    -- Create an account for the student
    DECLARE new_account_id INT;

    INSERT INTO Account (username, password, accountType)
    VALUES (p_username, p_password, p_accountType);

    -- Get the newly created account's ID
    SET new_account_id = LAST_INSERT_ID();

    -- Now insert the student data and link it to the account
    INSERT INTO Student (name, email, batch, rollnumber, phone, account_id)
    VALUES (p_name, p_email, p_batch, p_rollnumber, p_phone, new_account_id);
END$$

CREATE PROCEDURE GetStudentById(IN p_student_id INT)
BEGIN
    SELECT * FROM Student WHERE student_id = p_student_id;
END$$

CREATE PROCEDURE GetAllStudents()
BEGIN
    SELECT * FROM Student;
END$$

CREATE PROCEDURE UpdateStudent(
    IN p_student_id INT,
    IN p_name VARCHAR(50),
    IN p_email VARCHAR(25),
    IN p_batch VARCHAR(2),
    IN p_rollnumber VARCHAR(7),
    IN p_phone VARCHAR(15)
)
BEGIN
    UPDATE Student
    SET name = p_name, email = p_email, batch = p_batch, rollnumber = p_rollnumber, phone = p_phone
    WHERE student_id = p_student_id;
END$$

CREATE PROCEDURE DeleteStudent(IN p_student_id INT)
BEGIN
    DELETE FROM Student WHERE student_id = p_student_id;
END$$

-- ********** Society Procedures **********
-- ********** Society Procedures **********
CREATE PROCEDURE CreateSociety(
    IN p_username VARCHAR(50),
    IN p_password VARCHAR(100),
    IN p_accountType VARCHAR(7),
    IN p_name VARCHAR(50),
    IN p_email VARCHAR(25),
    IN p_members INT,
    IN p_description TEXT,
    IN p_isApproved TINYINT(1)
)
BEGIN
    -- Create an account for the society
    DECLARE new_account_id INT;

    INSERT INTO Account (username, password, accountType)
    VALUES (p_username, p_password, p_accountType);

    -- Get the newly created account's ID
    SET new_account_id = LAST_INSERT_ID();

    -- Now insert the society data and link it to the account
    INSERT INTO Society (name, email, members, description, isApproved, account_id)
    VALUES (p_name, p_email, p_members, p_description, p_isApproved, new_account_id);
END$$

CREATE PROCEDURE GetSocietyById(IN p_society_id INT)
BEGIN
    SELECT * FROM Society WHERE society_id = p_society_id;
END$$

CREATE PROCEDURE GetAllSocieties()
BEGIN
    SELECT * FROM Society;
END$$

CREATE PROCEDURE UpdateSociety(
    IN p_society_id INT,
    IN p_name VARCHAR(50),
    IN p_email VARCHAR(25),
    IN p_members INT,
    IN p_description TEXT,
    IN p_isApproved TINYINT(1)
)
BEGIN
    UPDATE Society
    SET
        name = p_name,
        email = p_email,
        members = p_members,
        description = p_description,
        isApproved = p_isApproved
    WHERE society_id = p_society_id;
END$$

CREATE PROCEDURE DeleteSociety(IN p_society_id INT)
BEGIN
    DELETE FROM Society WHERE society_id = p_society_id;
END$$

-- ********** SocietyMember Procedures **********
CREATE PROCEDURE AddSocietyMember(
    IN p_role VARCHAR(50),
    IN p_society_id INT,
    IN p_student_id INT
)
BEGIN
    INSERT INTO SocietyMember (role, society_id, student_id)
    VALUES (p_role, p_society_id, p_student_id);
END$$

CREATE PROCEDURE UpdateSocietyMemberRole(
    IN p_student_id INT,
    IN p_society_id INT,
    IN p_role VARCHAR(50)
)
BEGIN
    UPDATE SocietyMember
    SET role = p_role
    WHERE student_id = p_student_id AND society_id = p_society_id;
END$$

CREATE PROCEDURE GetSocietyMembers(IN p_society_id INT)
BEGIN
    SELECT sm.member_id, st.name AS student_name, sm.role
    FROM SocietyMember sm
             JOIN Student st ON sm.student_id = st.student_id
    WHERE sm.society_id = p_society_id;
END$$

CREATE PROCEDURE ApplyForRole(
    IN p_role VARCHAR(50),
    IN p_society_id INT,
    IN p_student_id INT
)
BEGIN
    INSERT INTO SocietyMember (role, society_id, student_id, status)
    VALUES (p_role, p_society_id, p_student_id, 'Pending');
END$$

-- Procedure to update the status of a role application
CREATE PROCEDURE UpdateRoleApplicationStatus(
    IN p_member_id INT,
    IN p_status ENUM('Pending', 'Approved', 'Rejected')
)
BEGIN
    UPDATE SocietyMember
    SET status = p_status
    WHERE member_id = p_member_id;
END$$

-- Procedure to get all role applications for a society
CREATE PROCEDURE GetRoleApplications(
    IN p_society_id INT
)
BEGIN
    SELECT sm.member_id, st.name AS student_name, sm.role, sm.status
    FROM SocietyMember sm
             JOIN Student st ON sm.student_id = st.student_id
    WHERE sm.society_id = p_society_id AND sm.status = 'Pending';
END$$

-- Procedure to get all approved members for a society
CREATE PROCEDURE GetApprovedMembers(
    IN p_society_id INT
)
BEGIN
    SELECT sm.member_id, st.name AS student_name, sm.role
    FROM SocietyMember sm
             JOIN Student st ON sm.student_id = st.student_id
    WHERE sm.society_id = p_society_id AND sm.status = 'Approved';
END$$

-- ********** Account Procedures **********
CREATE PROCEDURE CreateAccount(
    IN p_username VARCHAR(50),
    IN p_password VARCHAR(100),
    IN p_accountType VARCHAR(7)
)
BEGIN
    INSERT INTO Account (username, password, accountType)
    VALUES (p_username, p_password, p_accountType);
END$$

CREATE PROCEDURE GetAccountById(IN p_account_id INT)
BEGIN
    SELECT * FROM Account WHERE account_id = p_account_id;
END$$

CREATE PROCEDURE GetAllAccounts()
BEGIN
    SELECT * FROM Account;
END$$

CREATE PROCEDURE UpdateAccount(
    IN p_account_id INT,
    IN p_username VARCHAR(50),
    IN p_password VARCHAR(100),
    IN p_accountType VARCHAR(7)
)
BEGIN
    UPDATE Account
    SET username = p_username, password = p_password, accountType = p_accountType
    WHERE account_id = p_account_id;
END$$

CREATE PROCEDURE DeleteAccount(IN p_account_id INT)
BEGIN
    DELETE FROM Account WHERE account_id = p_account_id;
END$$

-- ********** Post Procedures **********
CREATE PROCEDURE CreatePost(
    IN p_account_id INT,
    IN p_content TEXT
)
BEGIN
    INSERT INTO Post (account_id, content)
    VALUES (p_account_id, p_content);
END$$

CREATE PROCEDURE GetPostById(IN p_post_id INT)
BEGIN
    SELECT * FROM Post WHERE post_id = p_post_id;
END$$

CREATE PROCEDURE GetAllPosts()
BEGIN
    SELECT * FROM Post;
END$$

CREATE PROCEDURE DeletePost(IN p_post_id INT)
BEGIN
    DELETE FROM Post WHERE post_id = p_post_id;
END$$

-- ********** Event Procedures **********
CREATE PROCEDURE CreateEvent(
    IN p_society_id INT,
    IN p_event_name VARCHAR(100),
    IN p_event_description TEXT,
    IN p_venue_id INT,
    IN p_date DATE,
    IN p_start_time DATETIME,
    IN p_end_time DATETIME
)
BEGIN
    INSERT INTO Event (society_id, event_name, event_description, venue_id, date, start_time, end_time)
    VALUES (p_society_id, p_event_name, p_event_description, p_venue_id, p_date, p_start_time, p_end_time);
END$$

CREATE PROCEDURE GetEventById(IN p_event_id INT)
BEGIN
    SELECT * FROM Event WHERE event_id = p_event_id;
END$$

CREATE PROCEDURE GetAllEvents()
BEGIN
    SELECT * FROM Event;
END$$

CREATE PROCEDURE DeleteEvent(IN p_event_id INT)
BEGIN
    DELETE FROM Event WHERE event_id = p_event_id;
END$$

-- ********** Announcement Procedures **********
CREATE PROCEDURE CreateAnnouncement(
    IN p_society_id INT,
    IN p_content TEXT
)
BEGIN
    INSERT INTO Announcement (society_id, content)
    VALUES (p_society_id, p_content);
END$$

CREATE PROCEDURE GetAnnouncementById(IN p_announcement_id INT)
BEGIN
    SELECT * FROM Announcement WHERE announcement_id = p_announcement_id;
END$$

CREATE PROCEDURE GetAllAnnouncements()
BEGIN
    SELECT * FROM Announcement;
END$$

CREATE PROCEDURE DeleteAnnouncement(IN p_announcement_id INT)
BEGIN
    DELETE FROM Announcement WHERE announcement_id = p_announcement_id;
END$$

DELIMITER ;
