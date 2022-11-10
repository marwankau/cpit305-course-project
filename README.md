# CPIT305 Course Project
## Project idea: 
- Hospital Reservation 


## Group Members
- Abdulmajeed Alharbi
- Abdullah Aldaheri
- Mohammed Alnajrani 

## Project description
Hospital Reservation System that allows adding doctors and patients to the database, in order for the patients to book appointments by choosing one of the available doctors with ease with their respective time, as well as printing the appointment in a file and canceling the appointment using their respective id.

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
