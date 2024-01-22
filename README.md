**Andrea García-González Manchón - Prueba Técnica 2:**

# Shift Operator Assessment Guidelines 

``` mermaid
classDiagram

class Person {
  -id: Long
  -username: String
  -password: String
}

class Citizen {
    super: 
    - id: Long
    - username: String
    - password: String
    ______________________
  + name: String
  + lastName:String
  + dateOfBirth: LocalDate
  + shifts: List<Shift>
  + getusername(): String
  + getpassword(): String
}

class Administrator {
      super: 
    - id: Long
    - username: String
    - password: String
    ______________________
  +procedures: List<ProcedureEntity>
}

class ProcedureEntity {
  -id: Long
  -title: String
  -description: String
  -requirements: String
  +admin: Administrator
  +shifts: List<Shift>
}

class Shift {
  -id: Long
  -dateHour: LocalDateTime
  -shiftStatus: boolean
  -additionalInformation: String
  +procedure: ProcedureEntity
  +citizen: Citizen
}

Person <|-- Citizen: extends from Person
Person <|-- Administrator: extends from Person
Administrator --|> ProcedureEntity: 1 to n
ProcedureEntity --|> Shift : 1 to n
ProcedureEntity --|> Administrator : 1 to 1
Shift --|> ProcedureEntity : 1 to 1
Shift --|> Citizen : 1 to 1
Citizen --|> Shift: 1 to n
```


## User Citizen:
- User can sign in and:
	- enter wrong parameters input, because of the validation in:
		- Name, Last Name, date Birth, username, password.
    - EXAMPLE:
    ![sign in citizen and errors](https://github.com/Andrea0o0/Andrea0o0-Garcia-Gonzalez-Manchon-Andrea_pruebatec2/assets/116817220/382eb8c8-1237-4b79-9da5-2d1dc6093e12)

- User can login:
  - also, can have validation from username and password, to check if user exists 
    ![login citizen](https://github.com/Andrea0o0/Andrea0o0-Garcia-Gonzalez-Manchon-Andrea_pruebatec2/assets/116817220/8bf9c2fa-c643-422e-930e-99ab94c576f7)


- User can add shifts:
- ![shifts](https://github.com/Andrea0o0/Andrea0o0-Garcia-Gonzalez-Manchon-Andrea_pruebatec2/assets/116817220/f58a4eaa-3435-43ef-a377-16a9c39f3385)
- View own shifts, filter by state and date
- Can see all the procedures, and also individual
- Can logout

  ## User Admin:
- User can sign in and:
	- enter wrong parameters input, because of the validation in:
		- username, password.
    - EXAMPLE:
- User can login:
  - also, can have validation from username and password, to check if user exists 
- User can see shifts
- View own shifts, filter by state and date
- Add procedures, see all, edit, delete and see one also:
- ![procedures](https://github.com/Andrea0o0/Andrea0o0-Garcia-Gonzalez-Manchon-Andrea_pruebatec2/assets/116817220/7f548634-f3d8-45d1-b702-ae78c0776365)
- Can logout

## Important:
The state change automatically without doing anything, the time transcurred only is available in case of waiting state. 
Only change the state inside the tables shifts.

## Requirements:

1. Clone the repository
2. Use IDE Netbeans 17
3. Use JDK 17 or less (to avoid mistakes)
4. Download MySQL dependency 
5. Import Shift_Operator Database data to your Database
6. Run the code

## Assumptions:

### Sign in Admin:
If you want to add admin profile, you have to "force it" at the url: [signin Admin](http://localhost:8080/Assessment2/signinAdmin.jsp)

### Sign in Admin:
admin will be more careful when entering data, you know that the description title or input to display has to be well written.

### Users careful:
All users will make a correct use of the application.

### Employee Database MySql:
I have exported the database information in [mysqlShift]([https://github.com/Andrea0o0/Garcia-Gonzalez-Manchon-Andrea_pruebatec1/tree/main/src/mysqlEmployee](https://github.com/Andrea0o0/Andrea0o0-Garcia-Gonzalez-Manchon-Andrea_pruebatec2/tree/main/mysql)https://github.com/Andrea0o0/Andrea0o0-Garcia-Gonzalez-Manchon-Andrea_pruebatec2/tree/main/mysql) inside the folder.
