use echodb;

-- Insert Dummy Accounts for Students
INSERT INTO Account (username, password, accountType) VALUES
                                                          ('student1', 'password1', '1'),
                                                          ('student2', 'password2', '1'),
                                                          ('student3', 'password3', '1');

-- Insert Dummy Accounts for Societies
INSERT INTO Account (username, password, accountType) VALUES
                                                          ('society1', 'password1', '2'),
                                                          ('society2', 'password2', '2'),
                                                          ('society3', 'password3', '2');

-- Insert Dummy Accounts for Admins
INSERT INTO Account (username, password, accountType) VALUES
                                                          ('admin1', 'adminpass1', '3'),
                                                          ('admin2', 'adminpass2', '3'),
                                                          ('admin3', 'adminpass3', '3');

-- Insert Dummy Students and Link to Account
INSERT INTO Student (name, email, batch, rollnumber, phone, account_id) VALUES
                                                                            ('John Doe', 'john.doe@student.com', 'A1', '1234567', '9876543210', 1),
                                                                            ('Jane Smith', 'jane.smith@student.com', 'B2', '2345678', '9876543211', 2),
                                                                            ('Alice Johnson', 'alice.johnson@student.com', 'C3', '3456789', '9876543212', 3);

-- Insert Dummy Societies and Link to Account
INSERT INTO Society (name, email, members, description, isApproved, account_id) VALUES
                                                                                    ('Science Club', 'science.club@society.com', 50, 'A club for science enthusiasts.', 1, 4),
                                                                                    ('Literature Society', 'literature.society@society.com', 40, 'A society for book lovers.', 1, 5),
                                                                                    ('Music Band', 'music.band@society.com', 30, 'A society for music lovers.', 0, 6);
