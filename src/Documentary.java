public class Documentary extends WatchContent {
    private String topic;

    public Documentary(String title, String genre, int duration, double rating, String topic) {
        super(title, genre, duration, rating);
        this.topic = topic;
    }

    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("Type: Documentary");
        System.out.println("Topic: " + topic);
    }
}
