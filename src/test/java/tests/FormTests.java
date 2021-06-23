package tests;

import com.github.javafaker.Faker;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

import pages.RegistrationPage;

public class FormTests extends TestBase {

    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            gender = "Male",
            phoneNumber = faker.phoneNumber().subscriberNumber(10),
            month = "July",
            year = "1993",
            day = "26",
            subject = "Computer Science",
            hobby = "Music",
            pathname = "src/test/resources/1.jpeg",
            picture = "1.jpeg",
            address = faker.address().fullAddress(),
            state = "NCR",
            city = "Delhi";

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void successSubmitFormTest() {
        step("Open students registration form", () -> {
            registrationPage.openPage();
        });

        step("Fill students registration form", () -> {
            step("Fill common data", () -> {
                registrationPage.typeFirstName(firstName);
                registrationPage.typeLastName(lastName);
                registrationPage.typeEmail(email);
                registrationPage.chooseGender(gender);
                registrationPage.typeUserNumber(phoneNumber);
            });
            step("Set date", () -> {
                registrationPage.chooseDateOfBirth(day, month, year);
            });
            step("Set subjects", () -> {
                registrationPage.chooseSubject(subject);
            });
            step("Set hobbies", () -> {
                registrationPage.chooseHobby(hobby);
            });
            step("Upload image", () -> {
                    registrationPage.upLoadPicture(pathname);
            });
            step("Set address", () -> {
        registrationPage.typeAddress(address);
        registrationPage.chooseState(state);
        registrationPage.chooseCity(city);
            });

        step("Verify successful form submit", () -> {
        registrationPage.submitForm();
        registrationPage.checkRegistrationResults(firstName, lastName, email, gender, phoneNumber, day, month, year, subject, hobby, picture, address, state, city);
        });
        });
}}