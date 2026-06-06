package com.airtribe.learntrack.entity;

public class Trainer extends Person {
    private String expertise;

    public Trainer() {
        super();
    }

    public Trainer(int id, String firstName, String lastName, String email, String expertise) {
        super(id, firstName, lastName, email);
        this.expertise = expertise;
    }

    @Override
    public String getDisplayName() {
        return getFirstName() + " " + getLastName() + " [Trainer | Expertise: " + expertise + "]";
    }

    public String getExpertise() { return expertise; }
    public void setExpertise(String expertise) { this.expertise = expertise; }

    @Override
    public String toString() {
        return "Trainer ID: " + getId() + " | " + getDisplayName() + " | Email: " + getEmail();
    }
}
