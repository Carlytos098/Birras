package ar.com.birra.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

/**
 * @author Carlos
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Meetup {
    private String id;
    private String nameOfMeet;
    private Date dateOfMeet;
    private int numberOfGuests;
    private List<String> guests;
    private List<String> checkIn;
    private String owner;
    private String address;

    private Meetup(String id, String nameOfMeet, Date dateOfMeet, int numberOfGuests, List<String> guests, List<String> checkIn, String owner, String address) {
        this.id = id;
        this.nameOfMeet = nameOfMeet;
        this.dateOfMeet = dateOfMeet;
        this.numberOfGuests = numberOfGuests;
        this.guests = guests;
        this.checkIn = checkIn;
        this.owner = owner;
        this.address = address;
    }

    public static class Builder {
        private String id;
        private String nameOfMeet;
        private Date dateOfMeet;
        private int numberOfGuest;
        private List<String> guests;
        private List<String> checkIn;
        private String owner;
        private String address;

        public Builder() {
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setNameOfMeet(String nameOfMeet) {
            this.nameOfMeet = nameOfMeet;
            return this;
        }

        public Builder setDateOfMeet(Date dateOfMeet) {
            this.dateOfMeet = dateOfMeet;
            return this;
        }

        public Builder setNumberOfGuest(int numberOfGuest) {
            this.numberOfGuest = numberOfGuest;
            return this;
        }

        public Builder setGuests(List<String> guests) {
            this.guests = guests;
            return this;
        }

        public Builder setCheckIn(List<String> checkIn) {
            this.checkIn = checkIn;
            return this;
        }

        public Builder setOwner(String owner) {
            this.owner = owner;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Meetup build() {
            return new Meetup(id, nameOfMeet, dateOfMeet, numberOfGuest, guests, checkIn, owner, address);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameOfMeet() {
        return nameOfMeet;
    }

    public void setNameOfMeet(String nameOfMeet) {
        this.nameOfMeet = nameOfMeet;
    }

    public Date getDateOfMeet() {
        return dateOfMeet;
    }

    public void setDateOfMeet(Date dateOfMeet) {
        this.dateOfMeet = dateOfMeet;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public List<String> getGuests() {
        return guests;
    }

    public void setGuests(List<String> guests) {
        this.guests = guests;
    }

    public List<String> getCheckIn() {
        return checkIn;
    }

    public Meetup setCheckIn(List<String> checkIn) {
        this.checkIn = checkIn;
        return this;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}