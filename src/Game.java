import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Scanner input;
    private Player player;
    private ArrayList<WatchContent> contents;
    private DecisionTracker tracker;
    private WatchContent finalChoice;
    private boolean running;

    public Game() {
        input = new Scanner(System.in);
        contents = new ArrayList<>();
        tracker = new DecisionTracker();
        running = true;

        loadContents();
    }

    public void start() {
        welcome();
        createPlayer();

        tracker.startTimer();

        while (running) {
            showMenu();

            int choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {
                viewContents(contents);
            } else if (choice == 2) {
                filterByGenre();
            } else if (choice == 3) {
                askForHint();
            } else if (choice == 4) {
                tracker.recordBacktrack();
                System.out.println("You went back to the main menu.");
            } else if (choice == 5) {
                running = false;
            } else {
                System.out.println("Invalid choice.");
            }
        }

        tracker.stopTimer();
        showFinalResult();
    }

    private void welcome() {
        System.out.println("Welcome to Movie Choice Lab!");
        System.out.println("Choose one thing to watch tonight.");
    }

   private void createPlayer() {
    System.out.print("\nEnter your name: ");
    String name = input.nextLine();

    System.out.println("\nChoose your mood:");
    System.out.println("1. Relaxed");
    System.out.println("2. Curious");
    System.out.println("3. Excited");
    System.out.println("4. Tired");
    System.out.println("5. Stressed");

    System.out.print("Choose: ");
    int moodChoice = input.nextInt();
    input.nextLine();

    String mood;

    if (moodChoice == 1) {
        mood = "Relaxed";
    } else if (moodChoice == 2) {
        mood = "Curious";
    } else if (moodChoice == 3) {
        mood = "Excited";
    } else if (moodChoice == 4) {
        mood = "Tired";
    } else {
        mood = "Stressed";
    }

    player = new Player(name, mood);

    System.out.println("\nHello, " + player.getName() + "!");
    System.out.println("Mood: " + player.getMood());
}

    private void showMenu() {
        System.out.println("\nMain Menu");
        System.out.println("1. View all content");
        System.out.println("2. Filter by genre");
        System.out.println("3. Ask for hint");
        System.out.println("4. Go back");
        System.out.println("5. Finish");
        System.out.print("Choose: ");
    }

    private void loadContents() {
        contents.add(new Movie("The Silent Planet", "Sci-Fi", 145, 8.7));
        contents.add(new Movie("Final Laugh", "Comedy", 95, 7.4));
        contents.add(new Movie("Dragon Valley", "Fantasy", 130, 8.2));

        contents.add(new Series("Mystery House", "Mystery", 50, 8.4, 8));
        contents.add(new Series("Funny Days", "Comedy", 30, 7.5, 10));

        contents.add(new Documentary("Ocean Truth", "Documentary", 80, 8.0, "Ocean life"));
        contents.add(new Documentary("Planet Future", "Sci-Fi", 70, 8.3, "Technology and future"));
    }

    private void viewContents(ArrayList<WatchContent> list) {
        if (list.isEmpty()) {
            System.out.println("No content found.");
            return;
        }

        showContentTitles(list);

        System.out.print("Choose one to view details: ");
        int choice = input.nextInt();
        input.nextLine();

        int index = choice - 1;

        if (index >= 0 && index < list.size()) {
            WatchContent selected = list.get(index);

            selected.showDetails();
            tracker.recordMovieViewed();

            System.out.print("\nMake this your final choice? yes/no: ");
            String answer = input.nextLine();

            if (answer.equalsIgnoreCase("yes")) {
                finalChoice = selected;
                System.out.println("Final choice saved.");
                running = false;
            }
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private void filterByGenre() {
        tracker.recordFilterUsed();

        System.out.print("Enter genre: ");
        String genre = input.nextLine();

        ArrayList<WatchContent> filtered = new ArrayList<>();

        for (WatchContent item : contents) {
            if (item.matchesGenre(genre)) {
                filtered.add(item);
            }
        }

        viewContents(filtered);
    }

    private void askForHint() {
        tracker.recordHintUsed();

        showContentTitles(contents);

        System.out.print("Choose content for hint: ");
        int choice = input.nextInt();
        input.nextLine();

        int index = choice - 1;

        if (index >= 0 && index < contents.size()) {
            WatchContent selected = contents.get(index);

            System.out.println(selected.getHint());
            System.out.println(selected.getHint(player.getMood()));
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private void showContentTitles(ArrayList<WatchContent> list) {
        System.out.println("\nAvailable Content:");

        for (int i = 0; i < list.size(); i++) {
            WatchContent item = list.get(i);
            System.out.println((i + 1) + ". " + item.getTitle() + " - " + item.getGenre());
        }
    }

    private void showFinalResult() {
        System.out.println("\nGame finished.");

        if (finalChoice != null) {
            System.out.println("You chose: " + finalChoice.getTitle());
        } else {
            System.out.println("You did not choose a final item.");
        }

        tracker.showSummary();
    }
}