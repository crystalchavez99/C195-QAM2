package com.example.qaii.models;

public class Contact {
    public int contact_id;
    public String contact_name;
    public String email;

//    *
//    The constructor for the Contact
//    @param contact_id is ID of Contact
//    @param contact_name is Full Name of Contact
//    @param email is Email of Contact
//     */
    public Contact(int contact_id, String contact_name, String email){
        this.contact_id = contact_id;
        this.contact_name = contact_name;
        this.email = email;
    }

    /*
    Getter: gets to return contact id
    @return contact_id
     */
    public int getContact_id(){
        return contact_id;
    }

    /*
    Sets the contact_id for Contact
    @param contact_id the contact_id to set
     */
    public void setContact_id(int contact_id){
        this.contact_id = contact_id;
    }

    /*
    Getter: gets to return contact name
    @return contact_name
     */
    public String getContact_name(){
        return contact_name;
    }

    /*
    Sets the contact_name for Contact
    @param contact_name the contact_name to set
     */
    public void setContact_name(String contact_name){
        if (contact_name.length() <= 50) {
            this.contact_name = contact_name;
        }else {
            throw new IllegalArgumentException("Contact name exceeds the maximum length of 50 characters.");
        }
    }

    /*
    Getter: gets to return contact email
    @return email
     */
    public String getEmail() {
        return email;
    }

    /*
    Sets the email for Contact
    @param email the email to set
     */
    public void setEmail(String email) {
        if (email.length() <= 50) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email exceeds the maximum length of 50 characters.");
        }
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contact_id=" + contact_id +
                ", contact_name='" + contact_name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
