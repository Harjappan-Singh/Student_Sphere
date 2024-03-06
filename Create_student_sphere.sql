DROP DATABASE IF EXISTS student_sphere;
CREATE DATABASE student_sphere;

USE student_sphere;
DROP TABLE IF EXISTS students;

CREATE TABLE students (
        student_id INT AUTO_INCREMENT PRIMARY KEY,
        first_name VARCHAR(20) NOT NULL,
		last_name VARCHAR(20) NOT NULL,
		birth_date DATE NOT NULL,
		student_email VARCHAR(50) NOT NULL UNIQUE,
		student_phone VARCHAR(20) UNIQUE,
		address VARCHAR(255) NOT NULL,
        course_full_name VARCHAR(100) NOT NULL,
		course_status VARCHAR(30) DEFAULT 'full-time',
		has_paid_full_fee boolean,
		class_group VARCHAR(5),
		graduation_year YEAR NOT NULL,
		current_gpa DOUBLE
		);

INSERT INTO students (first_name, last_name, birth_date, student_email, student_phone, address, course_full_name, course_status, graduation_year, has_paid_full_fee, class_group, current_gpa)
VALUES
    ('Dominic', 'Mpofu', '2000-05-15', 'd00253426@student.dkit.ie', '0875199110', '15 Main Street, Cork City, Co. Cork, T12 ABCD', 'BB in Business and Technology', 'full-time', 2026, true, 'BT3A', 3.8),
    ('Lance', 'Gonsalves', '2001-02-20', 'd00253427@student.dkit.ie', '0875199121', '32 Elmwood Avenue, Galway City, Co. Galway, H91 XYZ1', 'BA (Hons) in Creative Media', 'full-time', 2026, true, 'CM2A', 3.6),
    ('Sherom', 'Dabre', '1999-11-10', 'd00253428@student.dkit.ie', '0875199132', '7 Oakview Park, Limerick City, Co. Limerick, V94 1234', 'BB in Business and Technology', 'part-time', 2027, false, 'BT2A', 3.4),
    ('Tanmay', 'Goyal', '2002-07-05', 'd00253429@student.dkit.ie', '0875199143', '45 Willow Grove, Waterford City, Co. Waterford, X91 5678', 'Postgraduate Diploma in Solar Energy', 'full-time', 2025, true, 'SEA', 3.9),
    ('Vikram', 'Singh', '2000-09-25', 'd00253430@student.dkit.ie', '0875199154', '20 Maple Drive, Drogheda, Co. Louth, A92 EFGH', 'BSc (Hons) in Computing in Software Development', 'full-time', 2028, false, 'SD2A', 3.5),
    ('Emily', 'Jones', '2001-04-30', 'd00253431@student.dkit.ie', '0875199165', '3 Birch Lane, Bray, Co. Wicklow, A98 IJKL', 'MSc in Computing in Medical Device Software Engineering', 'part-time', 2024, true, 'MDSEA', 3.7),
    ('Daniel', 'Murphy', '2000-12-12', 'd00253432@student.dkit.ie', '0875199176', '28 Beechwood Road, Kilkenny City, Co. Kilkenny, R95 MNOP', 'BSc in Computing Systems and Operations (Software Development and DevOps)', 'full-time', 2028, true, 'SO3A', 3.8),
    ('Nick', 'Mooney', '2002-03-18', 'd00253433@student.dkit.ie', '0875199187', '9 Pinehurst Terrace, Sligo Town, Co. Sligo, F91 QRST', 'Bachelor of Engineering (Hons) in Mechanical Engineering', 'part-time', 2025, false, 'ME2A', 3.6),
    ('Matthew', 'Anderson', '2001-08-08', 'd00253434@student.dkit.ie', '0875199198', '51 Cedarwood Park, Tralee, Co. Kerry, V92 UVWX', 'Bachelor of Engineering (Hons) in Mechanical Engineering', 'full-time', 2024, true, 'ME2A', 3.9),
    ('Jhefferson', 'Garcia', '2000-06-22', 'd00253435@student.dkit.ie', '0875199109', '14 Ashfield Court, Athlone, Co. Westmeath, N37 YZAB', 'BSc (Hons) in Advanced Veterinary Nursing', 'full-time', 2024, false, 'VN4A', 3.7);

