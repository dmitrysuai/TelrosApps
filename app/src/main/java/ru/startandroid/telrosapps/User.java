package ru.startandroid.telrosapps;

public class User {

    public String fullName, dateOfBirth, email, fullName2, patronymic, phone, password;

    public User (){

    }

    public User(String fullName, String dateOfBirth, String email, String fullName2, String patronymic, String phone, String password){
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.fullName2 = fullName2;
        this.email = email;
        this.patronymic = patronymic;
        this.phone = phone;
        this.password = password;
    }
}
