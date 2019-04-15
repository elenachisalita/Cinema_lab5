package java.Domain;

public class Movie extends Entity{

    private String title;
    private int year;
    private double price;
    private boolean avalible;



    public Movie(String id,String title, int year, double price, boolean avalible) {


        super(id);
        this.title = title;
        this.year = year;
        this.price = price;
        this.avalible = avalible;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvalible() {
        return avalible;
    }

    public void setAvalible(boolean avalible) {
        this.avalible = avalible;
    }




    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", avalible=" + avalible +
                '}';
    }
}
