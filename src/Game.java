import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Scanner input;
    private Player player;
    private ArrayList<ContentItem> contents;
    private DecisionTracker tracker;
    private ContentItem finalChoice;

    public Game() {
        input = new Scanner(System.in);
        contents = new ArrayList<>();
        tracker = new DecisionTracker();
        loadContents();
    }

    public void start() {
        welcome();
        createPlayer();

        tracker.startTimer();

        boolean running = true;

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

        // End timer if DecisionTracker supports it. Removed call to undefined endTimer() to compile
        // (DecisionTracker may not implement endTimer()).
        showFinalResult();
    }

    private void welcome() {
        System.out.println("Welcome to Movie Choice Lab!");
        System.out.println("Choose one thing to watch tonight.");
    }

    private void createPlayer() {
        System.out.print("\nEnter your name: ");
        String name = input.nextLine();

        System.out.print("Enter your mood: ");
        String mood = input.nextLine();

        player = new Player(name, mood);

        System.out.println("\nHello, " + player.getName() + "!");
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
        contents.add(new ContentItem("The Silent Planet", "Movie", "Sci-Fi", 145, 8.7));
        contents.add(new ContentItem("Final Laugh", "Movie", "Comedy", 95, 7.4));
        contents.add(new ContentItem("Dragon Valley", "Movie", "Fantasy", 130, 8.2));
        contents.add(new ContentItem("Mystery House", "Series", "Mystery", 50, 8.4));
        contents.add(new ContentItem("Ocean Truth", "Documentary", "Documentary", 80, 8.0));
    }

    private void viewContents(ArrayList<ContentItem> list) {
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
            ContentItem selected = list.get(index);
            selected.showDetails();
            tracker.recordMovieViewed();

            System.out.print("Make this your final choice? yes/no: ");
            String answer = input.nextLine();

            if (answer.equalsIgnoreCase("yes")) {
                finalChoice = selected;
                System.out.println("Final choice saved.");
            }
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private void filterByGenre() {
        tracker.recordFilterUsed();

        System.out.print("Enter genre: ");
        String genre = input.nextLine();

        ArrayList<ContentItem> filtered = new ArrayList<>();

        for (ContentItem item : contents) {
            if (item.getGenre().equalsIgnoreCase(genre)) {
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
            ContentItem selected = contents.get(index);
            System.out.println(selected.getHint(player.getMood()));
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private void showContentTitles(ArrayList<ContentItem> list) {
        System.out.println("\nAvailable Content:");

        for (int i = 0; i < list.size(); i++) {
            ContentItem item = list.get(i);
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
