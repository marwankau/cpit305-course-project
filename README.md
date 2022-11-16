# CPIT305 Course Project
## Project idea: 
- Hospital Reservation 


## Group Members
- Abdulmajeed Alharbi
- Abdullah Aldaheri
- Mohammed Alnajrani 

## Project description
Hospital Reservation System that allows adding and managing doctors and patients to the database, in order for the receptionist to book appointments for the patients by choosing one of the available doctors with ease with their respective time while ensuring a doctor cannot have two appointments at the same time, as well as displaying all the appointments and canceling the appointment using their respective id.

## Functions Implemented
### Doctor
- [x] Display all doctors
- [x] Add doctors
- [x] Delete doctors
- [x] Update doctors
- [x] Get total doctors
- [ ] Search Doctors

### Patient 
- [x] Display all patients
- [x] Add patients
- [x] Delete patients
- [x] Update patients
- [x] Get total patients
- [ ] Search patients

### Appointment
- [x] Generate 7 days starting from current day
- [x] Validate entered doctor ID
- [x] Validate entered patient ID
- [x] Check if a patient has already booked an appointment
- [x] Check if a choosen date and time is already booked for the doctor
- [x] Add appointment
- [x] Delete appointment
- [x] Display appointments
- [ ] Update appointments



## ER Diagram

Doctor has 3 attribute { doctorID(PK) , doctorName , doctorspecialtly }
Appointment has 4 attribute { appointmentID(PK), appointmentDate , Doctor_id(FK), Patient_id(FK) }
Patient has 3 attribute { patientID(PK) , patientAge , patientName }

-each doctor has many appointment and each patient appoint one appointment 

![ER-DIAGRAM](/images/ERD.png)

## Class Diagram
We have 3 classes Doctor,Patient and Appoint each one of them has own attributes and methods and the relationship between them are association

![UML Class Diagram](/images/UML.jpg)


## Screen Capture
Here some screen capture from our project:

![Main menu](/images/capture01.png)
