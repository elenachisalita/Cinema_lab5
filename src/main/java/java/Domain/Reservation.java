package java.Domain;



public class Reservation extends Entity{


    private String idMovie;
    private String idClient;
    private final String date;
    private final String time;


    public Reservation(String id, String idMovie, String idClient, String date, String time) {

        super(id);
        this.idMovie = idMovie;
        this.idClient = idClient;
        this.date = date;
        this.time = time;
    }

    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String idMovie) {
        this.idMovie = idMovie;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
