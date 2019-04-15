package java.Domain;

public class Client extends Entity{

    private String lastName;
    private String firstName;
    private String CNP;
    private String dateOfBirth;
    private String dateOfRegistration;
    private int bonusPoints;

    public Client(String id, String lastName, String firstName, String CNP, String dateOfBirth, String dateOfRegistration, int bonusPoints) {
        super(id);
        this.lastName = lastName;
        this.firstName = firstName;
        this.CNP = CNP;
        this.dateOfBirth = dateOfBirth;
        this.dateOfRegistration = dateOfRegistration;
        this.bonusPoints = bonusPoints;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(int bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    @Override
    public String toString() {
        return "Client{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", CNP='" + CNP + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", dateOfRegistration='" + dateOfRegistration + '\'' +
                ", bonusPoints=" + bonusPoints +
                '}';
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }
}
