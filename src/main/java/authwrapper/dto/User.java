package authwrapper.dto;

import java.io.Serializable;
import java.util.HashMap;

public class User {
    private String username;
    private String email;
    private long lastActive;
    private String campusnetId;
    private String studyRetning;
    private String firstname;
    private String lastname;
    private String password;
    private HashMap<String, Object> extraFields = new HashMap<>();

    public User() { }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getLastActive() {
        return lastActive;
    }

    public void setLastActive(long lastActive) {
        this.lastActive = lastActive;
    }

    public String getCampusnetId() {
        return campusnetId;
    }

    public void setCampusnetId(String campusnetId) {
        this.campusnetId = campusnetId;
    }

    public String getStudyRetning() {
        return studyRetning;
    }

    public void setStudyRetning(String studyRetning) {
        this.studyRetning = studyRetning;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HashMap<String, Object> getExtraFields() {
        return extraFields;
    }

    public void setExtraFields(HashMap<String, Object> extraFields) {
        this.extraFields = extraFields;
    }
}
