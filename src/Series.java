public class Series extends WatchContent {
    private int episodes;

    public Series(String title, String genre, int duration, double rating, int episodes) {
        super(title, genre, duration, rating);
        this.episodes = episodes;
    }

    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("Type: Series");
        System.out.println("Episodes: " + episodes);
    }
}
