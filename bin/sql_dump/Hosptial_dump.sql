PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE Patient (
	patient_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	patient_name TEXT NOT NULL,
	patient_dob TEXT
);
INSERT INTO Patient VALUES(1,'Khalid Ahmad','2000-10-10');
INSERT INTO Patient VALUES(5,'Abdullah Alharbi','1999-10-19');
INSERT INTO Patient VALUES(6,'Ammar Alsurahey','1999-10-20');
CREATE TABLE Doctor (
	doctor_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	doctor_name TEXT,
	doctor_speciality TEXT
);
INSERT INTO Doctor VALUES(1,'Ahmad Khalid','Dentist');
INSERT INTO Doctor VALUES(2,'Mohammed Ahmad','Ears');
CREATE TABLE BookAppointment (
	appointment_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	appointment_date TEXT NOT NULL,
	doctor_id INTEGER NOT NULL,
	patient_id INTEGER NOT NULL,
	CONSTRAINT Appointment_FK FOREIGN KEY (doctor_id) REFERENCES Doctor(doctor_id),
	CONSTRAINT Appointment_FK_1 FOREIGN KEY (patient_id) REFERENCES Patient(patient_id)
);
INSERT INTO BookAppointment VALUES(4,'2022-11-16 1 PM',2,5);
INSERT INTO BookAppointment VALUES(5,'2022-11-16 1 PM',1,1);
INSERT INTO BookAppointment VALUES(6,'2022-11-16 0 PM',1,1);
DELETE FROM sqlite_sequence;
INSERT INTO sqlite_sequence VALUES('Doctor',3);
INSERT INTO sqlite_sequence VALUES('Patient',8);
INSERT INTO sqlite_sequence VALUES('BookAppointment',15);
COMMIT;
