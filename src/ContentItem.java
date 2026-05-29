public class ContentItem {
    private String title;
    private String type;
    private String genre;
    private int duration;
    private double rating;

    public ContentItem(String title, String type, String genre, int duration, double rating) {
        this.title = title;
        this.type = type;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    public double getRating() {
        return rating;
    }

    public void showDetails() {
        System.out.println("\nTitle: " + title);
        System.out.println("Type: " + type);
        System.out.println("Genre: " + genre);
        System.out.println("Duration: " + duration + " minutes");
        System.out.println("Rating: " + rating);
    }
       public String getHint(String mood) {
        return "Since you feel " + mood + ", think about whether " + title + " matches your mood.";
    }
}


