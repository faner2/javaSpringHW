package org.example.web.dto;

public class RegisterForm {

    private String firstName;
    private String secondName;
    private String username;
    private String password;
    private String male;

    public RegisterForm() {
        //FIXME o_O зачем этот код? Прочитай про конструктор по умолчанию
        this.firstName = firstName;
        this.secondName = secondName;
        this.username = username;
        this.password = password;
        this.male = male;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    @Override
    public String toString() {
        return "RegisterForm{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", male='" + male + '\'' +
                '}';
    }
}
