USE echoDB;

-- Insert Dummy Accounts for Students
INSERT INTO Account (username, password, accountType) VALUES
                                                          ('zainasad','123','1'),
                                                          ('ahmedhanan','foodfoodfood','1'),
                                                          ('ahmedmurtaza','president','1'),
                                                          ('hamzamir','dumb','1'),
                                                          ('alihamza','gojowillbeback','1'),
                                                          ('mohibullah','gay1','1'),
                                                          ('azfarbilal','gay2','1'),
                                                          ('maryamfarooq','elevator','1'),
                                                          ('ayishahchaudhry','nietzsche','1'),
                                                          ('aashirhameed','autism','1');

-- Insert Dummy Accounts for Societies
INSERT INTO Account (username, password, accountType) VALUES
                                                          ('fastcomputingsociety','3vicepresidents','2'),
                                                          ('fastliterarysociety','2generalsecretaries','2'),
                                                          ('fastquransunnahsociety','directorprivilege','2');

-- Insert Dummy Accounts for Admins
INSERT INTO Account (username, password, accountType) VALUES
                                                          ('junaidsheikh','inameeting','3'),
                                                          ('amirrehman','thegoat','3'),
                                                          ('usmantariq','thegoat2','3');

-- Insert Dummy Students and Link to Account
INSERT INTO Student (name, email, batch, rollnumber, phone, account_id) VALUES
                                                                            ('Zain Asad','i221240@nu.edu.pk','22','i221240','03175881919',1),
                                                                            ('Ahmed Hanan','i220943@nu.edu.pk','22','i220943','03331495251',2),
                                                                            ('Ahmed Murtaza','i220985@nu.edu.pk','22','i220985','03309509572',3),
                                                                            ('Hamza Mir','i221003@nu.edu.pk','22','i221003','03456910939',4),
                                                                            ('Ali Hamza','i222126@nu.edu.pk','22','i222126','03312879796',5),
                                                                            ('Mohib Ullah','i221044@nu.edu.pk','22','i221044','03335510630',6),
                                                                            ('Azfar Bilal','i220950@nu.edu.pk','22','i220950','03186414112',7),
                                                                            ('Maryam Farooq','i221217@nu.edu.pk','22','i221217','03490005081',8),
                                                                            ('Ayishah Chaudhry','i220957@nu.edu.pk','22','i220957','03208585468',9),
                                                                            ('Aashir Hameed','i220920@nu.edu.pk','22','i220920','03178063780',10);

-- Insert Dummy Societies and Link to Account
INSERT INTO Society (name, email, members, description, isApproved, account_id) VALUES
                                                                                    ('Fast Computing Society','fastcomputingsociety@nu.edu.pk',200,'Biggest society on campus. Tech-related events and workshops.',0,11),
                                                                                    ('Fast Literary Society','fastmakarsclub.isb@nu.edu.pk',122,'Literature related events, niche workshops, fun competitions, engaging book-clubs, illuminating mushairahs.',0,12),
                                                                                    ('Fast Quran and Sunnah Society', 'fqss@nu.edu.pk',200,'Campus-level Society, Quran and Sunnah.',0,13);

-- Insert Dummy Society Members
INSERT INTO SocietyMember (role, society_id, student_id, status) VALUES
                                                                     ('President',2,3,'Approved'),
                                                                     ('President',1,2,'Approved'),
                                                                     ('President',3,4,'Approved'),
                                                                     ('Member', 1, 2, 'Approved'),    -- Jane Smith as Member of Science Club
                                                                     ('Treasurer', 2, 3, 'Approved'), -- Alice Johnson as Treasurer of Literature Society
                                                                     ('Member', 2, 1, 'Pending'),     -- John Doe applied to Literature Society
                                                                     ('Vice President', 3, 2, 'Pending'), -- Jane Smith applied to Music Band
                                                                     ('Member', 3, 3, 'Rejected');     -- Alice Johnson rejected from Music Band

INSERT INTO Venue (venue_name, location) VALUES
                                            ('Wisdom Tree','Outside A Block'),
                                            ('Auditorium','A-Block Ground Floor'),
                                            ('LRC','C-Block First Floor'),
                                            ('CS Lawn','Adjacent Cafe Courtyard'),
                                            ('Volleyball Court','Left of Gate 2 Entry'),
                                            ('Basketball Court','Adjacent CS Lawn'),
                                            ('Cricket Ground','Near Gate 3 / Left Side of Uni'),
                                            ('Futsal Court','Left of Gate 2'),
                                            ('Cafe','C Block Basement'),
                                            ('Library','C Block Ground Floor'),
                                            ('GPU Lab','C Block 5th Floor'),
                                            ('Masjid','Near Gate 2 Entry'),
                                            ('One Stop','A Block Ground Floor'),
                                            ('CS Academic Office','C Block Second Floor'),
                                            ('NasCon Room','A Block 2nd Floor');


