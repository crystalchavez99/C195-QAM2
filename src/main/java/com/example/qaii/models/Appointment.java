package com.example.qaii.models;

import java.time.LocalDateTime;

public class Appointment {
    private  String startString;
    private  String user;
    private String customer_name;
    private int appointment_id;

    private String title;

    private String description;

    private String location;

    private String type;

    private LocalDateTime start;

    private LocalDateTime end;

    private LocalDateTime create_date;

    private String created_by;

    private LocalDateTime last_update;

    private String last_update_by;

    private int customer_id;

    private int user_id;

    private Contact contact_id;

    /*
    The constructor for the Appointment
    @param appointment_id is id of the Appointment
    @param title is title of the Appointment
    @param description is the description of the Appointment
    @param location is the location of the Appointment
    @param type is the type of the Appointment
    @param start is the start of the Appointment
    @param end is the end of the Appointment
    @param create_date is the day the Appointment is created
    @param created_by is the creation  of Appointment
    @param last_update is the  last update of the Appointment
    @param last_update_by is last update by someone for the Appointment
    @param customer_id is the customer id of the Appointment
    @param user_id is the user id of the Appointment
    @param contact_id is the contact id of the Appointment
     */
    public Appointment(int appointment_id, String title, String description, String location, String type,  LocalDateTime start, LocalDateTime end, LocalDateTime create_date, String created_by, LocalDateTime last_update, String last_update_by, int customer_id, int user_id, Contact contact_id) {
        this.appointment_id = appointment_id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.create_date = create_date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_update_by = last_update_by;
        this.customer_id = customer_id;
        this.user_id = user_id;
        this.contact_id = contact_id;
    }

    public Appointment(int appointment_id, int customer_id, int user_id, String title, String description, String location, String contact, String type,
                        String startString, String endString, String customer_name, String user){

        this.appointment_id = appointment_id;
        this.customer_id = customer_id;
        this.user_id = user_id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.startString = startString;
        this.endString = endString;
        this.customer_name = customer_name;
        this.user = user;


    }

    /*
    Getter: gets to return appointment_id
    @return appointment_id
     */
    public int getAppointmentId() {
        return appointment_id;
    }

    /*
    Setter: Sets the appointment_id for Appointment
    @param appointment_id
     */
    public void setAppointmentId(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    /*
    Getter: gets to return title
    @return title
     */
    public String getTitle() {
        return title;
    }

    /*
    Setter: Sets the title for Appointment
    @param title
     */
    public void setTitle(String title) {
        if(title.length() <= 50){
            this.title = title;
        }else {
            throw new IllegalArgumentException("Title exceeds the maximum length of 50 characters.");
        }
    }

    /*
    Getter: gets to return description
    @return description
     */
    public String getDescription() {
        return description;
    }

    /*
    Setter: Sets the description for Appointment
    @param description
     */
    public void setDescription(String description) {
        if(description.length() <= 50){
            this.description = description;
        }else {
            throw new IllegalArgumentException("Description exceeds the maximum length of 50 characters.");
        }
    }

    /*
    Getter: gets to return location
    @return location
     */
    public String getLocation() {
        return location;
    }

    /*
    Sets the location for Appointment
    @param location
     */
    public void setLocation(String location) {
        if(location.length() <= 50){
            this.location = location;
        }else {
            throw new IllegalArgumentException("Location exceeds the maximum length of 50 characters.");
        }
    }

    /*
   Getter: gets to return type
    @return type
    */
    public String getType() {
        return type;
    }

    /*
    Sets the type for Appointment
    @param type
     */
    public void setType(String type) {
        if(type.length() <= 50){
            this.type = type;
        }else {
            throw new IllegalArgumentException("Type exceeds the maximum length of 50 characters.");
        }
    }

    /*
   Getter: gets to return start
    @return start
    */
    public LocalDateTime getStart() {
        return start;
    }

    /*
    Sets the start for Appointment
    @param start
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /*
   Getter: gets to return end
    @return end
    */
    public LocalDateTime getEnd() {
        return end;
    }

    /*
    Sets the end for Appointment
    @param end
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /*
   Getter: gets to return create_date
    @return create_date
    */
    public LocalDateTime getCreateDate() {
        return create_date;
    }

    /*
    Sets the create_date for Appointment
    @param create_date
     */
    public void setCreateDate(LocalDateTime create_date) {
        this.create_date = create_date;
    }

    /*
   Getter: gets to return created_by
    @return created_by
    */
    public String getCreatedBy() {
        return created_by;
    }

    /*
    Sets the created_by for Appointment
    @param created_by
     */
    public void setCreatedBy(String created_by) {
        if(created_by.length() <= 50){
            this.created_by = created_by;
        }else {
            throw new IllegalArgumentException("Created By exceeds the maximum length of 50 characters.");
        }
    }

    /*
   Getter: gets to return last_update
    @return last_update
    */
    public LocalDateTime getLastUpdate() {
        return last_update;
    }

    /*
    Sets the last_update for Appointment
    @param last_update
     */
    public void setLastUpdate(LocalDateTime last_update) {
        this.last_update = last_update;
    }

    /*
   Getter: gets to return last_update_by
    @return last_update_by
    */
    public String getLastUpdatedBy() {
        return last_update_by;
    }

    /*
    Sets the last_updated_by for Appointment
    @param last_updated_by
     */
    public void setLastUpdatedBy(String last_updated_by) {
        if(last_update_by.length() <= 50){
            this.last_update_by = last_updated_by;
        }else {
            throw new IllegalArgumentException("Last Update By exceeds the maximum length of 50 characters.");
        }
    }

    /*
   Getter: gets to return customer_id
    @return customer_id
    */
    public int getCustomerId() {
        return customer_id;
    }

    /*
    Sets the customer_id for Appointment
    @param customer_id
     */
    public void setCustomerId(int customer_id) {
        this.customer_id = customer_id;
    }

    /*
  Getter: gets to return user_id
   @return user_id
   */
    public int getUserId() {
        return user_id;
    }

    /*
    Sets the user_id for Appointment
    @param user_id
     */
    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    /*
 Getter: gets to return contact_id
  @return contact_id
  */
    public Contact getContact() {
        return contact_id;
    }

    /*
    Sets the contact_id for Appointment
    @param contact_id
     */
    public void setContact(Contact contact_id) {
        this.contact_id = contact_id;
    }

};