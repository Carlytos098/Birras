package ar.com.birra.repo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * @author Carlos Moreno
 */
@Document(collection = "Meetup")
public class Meetup {
    @Id
    private String id;
    private String name;
    private Date date;
    private String owner;
    private List<String> guestsUsers;
    private List<String> checkinUsers;
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<String> getGuestsUsers() {
        return guestsUsers;
    }

    public List<String> getCheckinUsers() {
        return checkinUsers;
    }

    public void setCheckinUsers(List<String> checkinUsers) {
        this.checkinUsers = checkinUsers;
    }

    public void setGuestsUsers(List<String> guestsUsers) {
        this.guestsUsers = guestsUsers;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}