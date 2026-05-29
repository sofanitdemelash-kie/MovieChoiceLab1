public class Movie extends WatchContent {
    public Movie(String title, String genre, int duration, double rating) {
        super(title, genre, duration, rating);
    }

    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("Type: Movie");
    }
}
