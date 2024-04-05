DROP DATABASE IF EXISTS student_sphere;
CREATE DATABASE student_sphere;
USE student_sphere;

CREATE TABLE Departments (
                             department_id INT AUTO_INCREMENT PRIMARY KEY,
                             department_name VARCHAR(50) NOT NULL
);

INSERT INTO Departments (department_name)
VALUES
    ('Computing'),
    ('Creative Media'),
    ('Business'),
    ('Engineering'),
    ('Science');

CREATE TABLE Courses (
                         course_id INT AUTO_INCREMENT PRIMARY KEY,
                         course_name VARCHAR(255) NOT NULL,
                         course_code VARCHAR(20) NOT NULL UNIQUE,
                         department_id INT NOT NULL,
                         credits INT NOT NULL,
                         level VARCHAR(50) NOT NULL,
                         FOREIGN KEY (department_id) REFERENCES Departments(department_id)
);

INSERT INTO Courses (course_name, course_code, department_id, credits, level)
VALUES
    ('BSc (Hons) in Computing in Software Development', 'SD', 1, 120, 'Bachelors'),
    ('BA (Hons) in Creative Media', 'CM123', 2, 180, 'Bachelors'),
    ('BB in Business and Technology', 'BT345', 3, 180, 'Bachelors'),
    ('Postgraduate Diploma in Solar Energy', 'SEA987', 5, 60, 'Postgraduate Diploma'),
    ('BSc in Computing Systems and Operations (Software Development and DevOps)', 'SO567', 1, 120, 'Bachelors'),
    ('Bachelor of Engineering (Hons) in Mechanical Engineering', 'ME456', 4, 240, 'Bachelors'),
    ('BSc (Hons) in Advanced Veterinary Nursing', 'VN123', 4, 180, 'Bachelors');


CREATE TABLE Modules (
                         module_id INT AUTO_INCREMENT PRIMARY KEY,
                         module_name VARCHAR(255) NOT NULL,
                         credits INT NOT NULL
);

INSERT INTO Modules (module_name, credits)
VALUES
    ('Introduction to Programming', 5),
    ('Object-Oriented Programming', 10),
    ('Software Engineering Principles', 15),
    ('Database Management Systems', 10),
    ('Web Development Fundamentals', 10),
    ('Software Testing and Quality Assurance', 5),
    ('Agile Project Management', 5),
    ('Machine Learning Fundamentals', 15),
    ('Introduction to Graphic Design', 5),
    ('Web Design and Development', 10),
    ('Digital Video Editing', 10),
    ('3D Modeling and Animation', 15),
    ('User Interface (UI) Design', 10),
    ('Storytelling for Media', 5),
    ('Marketing Fundamentals', 5),
    ('Media Law and Ethics', 5),
    ('Introduction to Business', 5),
    ('Financial Accounting', 10),
    ('Marketing Principles', 5),
    ('Business Communication', 5),
    ('Project Management', 10),
    ('Information Systems for Business', 15),
    ('Solar Energy Fundamentals', 10),
    ('Photovoltaic Systems Design and Installation', 15),
    ('Solar Thermal Energy Systems', 10),
    ('Energy Storage Technologies', 10),
    ('Renewable Energy Policy and Regulations', 5),
    ('Sustainable Energy Solutions', 15),
    ('Environmental Impact Assessment', 5),
    ('Operating Systems Fundamentals', 10),
    ('Computer Networks and Security', 15),
    ('Cloud Computing', 10),
    ('DevOps methodologies', 10),
    ('System Administration', 10),
    ('Scripting Languages', 5),
    ('Big Data Management', 15),
    ('Engineering Mechanics', 10),
    ('Thermodynamics', 10),
    ('Material Science', 5),
    ('Machine Design', 15),
    ('Manufacturing Processes', 10),
    ('Control Systems', 5),
    ('Finite Element Analysis', 15),
    ('Robotics', 10),
    ('Animal Anatomy and Physiology', 15),
    ('Veterinary Nursing Skills', 10),
    ('Animal Welfare and Ethics', 5),
    ('Pharmacology for Veterinary Professionals', 10),
    ('Medical and Surgical Nursing', 15),
    ('Small Animal and Large Animal Care', 10),
    ('Animal Nutrition and Behavior', 5);


CREATE TABLE CourseModules (
                               course_module_id INT AUTO_INCREMENT PRIMARY KEY,
                               course_id INT NOT NULL,
                               module_id INT NOT NULL,
                               FOREIGN KEY (course_id) REFERENCES Courses(course_id),
                               FOREIGN KEY (module_id) REFERENCES Modules(module_id)
);

INSERT INTO CourseModules (course_id, module_id)
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (1, 5),
    (1, 6),
    (1, 7),
    (1, 8),
    (2, 9),
    (2, 10),
    (2, 11),
    (2, 12),
    (2, 13),
    (2, 14),
    (2, 15),
    (2, 16),
    (3, 17),
    (3, 18),
    (3, 19),
    (3, 20),
    (3, 21),
    (3, 22),
    (3, 4),
    (3, 8),
    (4, 23),
    (4, 24),
    (4, 25),
    (4, 26),
    (4, 27),
    (4, 28),
    (4, 29),
    (5, 30),
    (5, 31),
    (5, 32),
    (5, 33),
    (5, 34),
    (5, 35),
    (5, 36),
    (6, 37),
    (6, 38),
    (6, 39),
    (6, 40),
    (6, 41),
    (6, 42),
    (6, 43),
    (6, 44),
    (7, 45),
    (7, 46),
    (7, 47),
    (7, 48),
    (7, 49),
    (7, 50),
    (7, 51);

DROP TABLE IF EXISTS students;

CREATE TABLE Students (
                          student_id INT AUTO_INCREMENT PRIMARY KEY,
                          first_name VARCHAR(50) NOT NULL,
                          last_name VARCHAR(50) NOT NULL,
                          birth_date DATE NOT NULL,
                          student_email VARCHAR(100) NOT NULL,
                          student_phone VARCHAR(20) NOT NULL,
                          address VARCHAR(255) NOT NULL,
                          graduation_year INT NOT NULL,
                          has_paid_full_fee BOOLEAN NOT NULL,
                          current_gpa DECIMAL(3,2) NOT NULL,
                          course_id INT NOT NULL,
                          FOREIGN KEY (course_id) REFERENCES Courses(course_id)
);

INSERT INTO Students (
    first_name,
    last_name,
    birth_date,
    student_email,
    student_phone,
    address,
    graduation_year,
    has_paid_full_fee,
    current_gpa,
    course_id
)
VALUES ('Dominic', 'Mpofu', '2000-05-15', 'd00253426@student.dkit.ie', '0875199110', '15 Main Street, Dundalk, County Louth, Ireland', 2026, true, 3.8, 1),
       ('Lance', 'Gonsalves', '2001-02-20', 'd00253427@student.dkit.ie', '0875199121', '32 Elmwood Avenue, Drogheda, County Louth, Ireland', 2026, true, 3.6, 2),
       ('Sherom', 'Dabre', '1999-11-10', 'd00253428@student.dkit.ie', '0875199132', '7 Oakview Park, Ardee, County Louth, Ireland', 2027, false, 3.4, 3),
       ('Tanmay', 'Goyal', '2002-07-05', 'd00253429@student.dkit.ie', '0875199143', '45 Willow Grove, Castlebellingham, County Louth, Ireland', 2025, true, 3.9, 5),
       ('Vikram', 'Singh', '2000-09-25', 'd00253430@student.dkit.ie', '0875199154', '20 Maple Drive, Drogheda, County Louth, Ireland', 2028, false, 3.5, 1),
       ('Darryl', 'Noone', '2001-04-30', 'd00253431@student.dkit.ie', '0875199165', '3 Birch Lane, Dundalk, County Louth, Ireland', 2024, true, 3.7, 6),
       ('Meghana', 'Rathnam', '2000-12-12', 'd00253432@student.dkit.ie', '0875199176', '28 Beechwood Road, Drogheda, County Louth, Ireland', 2028, true, 3.8, 7),
       ('Kaylee', 'Jacqueline', '2002-03-18', 'd00253433@student.dkit.ie', '0875199187', '9 Pinehurst Terrace, Dundalk, County Louth, Ireland', 2025, false, 3.6, 4),
       ('Harjappan', 'Singh', '2001-08-08', 'd00253434@student.dkit.ie', '0875199198', '51 Cedarwood Park, Dundalk, County Louth, Ireland', 2024, true, 3.9, 5),
       ('Conor', 'Gilbert', '2000-06-22', 'd00253435@student.dkit.ie', '0875199109', '14 Ashfield Court, Drogheda, County Louth, Ireland', 2024, false, 3.7, 2);
