package ar.com.birra.model;

/**
 * @author Carlos
 */
public class Birras {
    private int numberOfBoxes;
    private int numberOfGuests;
    private String dateOfMeet;
    private Temperature temperature;

    public int getNumberOfBoxes() {
        return numberOfBoxes;
    }

    public void setNumberOfBoxes(int numberOfBoxes) {
        this.numberOfBoxes = numberOfBoxes;
    }

    public String getDateOfMeet() {
        return dateOfMeet;
    }

    public void setDateOfMeet(String dateOfMeet) {
        this.dateOfMeet = dateOfMeet;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }
}