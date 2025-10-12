package org.example.hms.models;

import java.time.LocalDateTime;

public class Patient {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String address;
    private String email;
    private String bloodGroup;
    private LocalDateTime dateOfRegistration;
    private boolean hasChronicDisease;

    public boolean isHasChronicDisease() {
        return hasChronicDisease;
    }

    public void setHasChronicDisease(boolean hasChronicDisease) {
        this.hasChronicDisease = hasChronicDisease;
    }

    public Patient(int id, String firstName, String lastName, int age, String gender, String address, String email, String bloodGroup, LocalDateTime dateOfRegistration, boolean hasChronicDisease) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.bloodGroup = bloodGroup;
        this.dateOfRegistration = dateOfRegistration;
        this.hasChronicDisease = hasChronicDisease;
    }

    public Patient() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public LocalDateTime getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDateTime dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", dateOfRegistration=" + dateOfRegistration +
                '}';
    }
}
