package ru.startandroid.telrosapps;

public class User {

    public String fullName, dateOfBirth, email, fullName2, emailRegister, patronymic, phone, password;

    public User (){

    }

    public User(String fullName, String dateOfBirth, String email, String fullName2, String emailRegister, String patronymic, String phone, String password){
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.fullName2 = fullName2;
        this.emailRegister = emailRegister;
        this.patronymic = patronymic;
        this.phone = phone;
        this.password = password;
    }
}
