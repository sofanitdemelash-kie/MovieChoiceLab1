public class DecisionTracker {
    private int hintsUsed;
    private int moviesViewed;
    private int filtersUsed;
    private int backtracks;
    private long startTime;
    private long endTime;

    public DecisionTracker() {
        hintsUsed = 0;
        moviesViewed = 0;
        filtersUsed = 0;
        backtracks = 0;
    }

    public void startTimer() {
        startTime = System.currentTimeMillis();
    }

    public void stopTimer() {
        endTime = System.currentTimeMillis();
    }

    public void recordHintUsed() {
        hintsUsed++;
    }

    public void recordMovieViewed() {
        moviesViewed++;
    }

    public void recordFilterUsed() {
        filtersUsed++;
    }

    public void recordBacktrack() {
        backtracks++;
    }

    public long getTimeTakenInSeconds() {
        return (endTime - startTime) / 1000;
    }

    public int calculateScore() {
        int score = 0;

        score += hintsUsed * 2;
        score += moviesViewed;
        score += filtersUsed * 2;
        score += backtracks * 2;

        if (getTimeTakenInSeconds() > 60) {
            score += 3;
        }

        return score;
    }

    public String getResult() {
        int score = calculateScore();

        if (score <= 4) {
            return "Satisficer";
        } else if (score <= 9) {
            return "Balanced Chooser";
        } else {
            return "Maximizer";
        }
    }

    public void showSummary() {
        System.out.println("\nDecision Tracking Summary");
        System.out.println("Hints used: " + hintsUsed);
        System.out.println("Movies viewed: " + moviesViewed);
        System.out.println("Filters used: " + filtersUsed);
        System.out.println("Backtracks: " + backtracks);
        System.out.println("Time taken: " + getTimeTakenInSeconds() + " seconds");
        System.out.println("Final result: " + getResult());
    }
}