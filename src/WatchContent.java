public class WatchContent {
    private String title;
    private String genre;
    private int duration;
    private double rating;

    public WatchContent(String title, String genre, int duration, double rating) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
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

    public boolean matchesGenre(String genre) {
        return this.genre.equalsIgnoreCase(genre);
    }

    public void showDetails() {
        System.out.println("\nTitle: " + title);
        System.out.println("Genre: " + genre);
        System.out.println("Duration: " + duration + " minutes");
        System.out.println("Rating: " + rating);
    }

    public String getHint() {
        return "This is a " + genre + " option. Check if it matches what you want.";
    }

    public String getHint(String mood) {
        return "Since you feel " + mood + ", think about whether " + title + " fits your mood.";
    }
}
