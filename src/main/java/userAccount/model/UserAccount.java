package userAccount.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
@Component
@Entity
@Table(name = "userAccounts")
public class UserAccount implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String firstName,lastName;
    private String phoneNumber;

    private int age;
    private String email;

    public UserAccount() {
    }

    public UserAccount( String firstName, String lastName, String phoneNumber,  int age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return UserAccount.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserAccount userAccount = (UserAccount) target;
        String firstName = userAccount.getFirstName();
        String lastName = userAccount.getLastName();
        int age = userAccount.getAge();
        String phoneNumber = userAccount.getPhoneNumber();
        String email = userAccount.getEmail();
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"phoneNumber", "number.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email","email.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"firstName","name.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"lastName","name.empty");
        if(phoneNumber.length()>11 || phoneNumber.length()<10){
            errors.rejectValue("phoneNumber", "number.length");
        }
        if(!phoneNumber.startsWith("0")){
            errors.rejectValue("phoneNumber","number.startsWith");
        }
        if(!phoneNumber.matches("(^$|[0-9]*$)")){
            errors.rejectValue("phoneNumber","number.matches");
        }

        if(!email.matches("^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$")){
            errors.rejectValue("email", "email.matches");
        }

        if(firstName.length()<5 || lastName.length()<5 || firstName.length()>45 || lastName.length()>45){
            errors.rejectValue("firstName","name.lengtherror");
            errors.rejectValue("lastName","name.lengtherror");
        }
        if( age <18) {
            errors.rejectValue("age","age.minor");
        }
    }
}
