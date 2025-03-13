package CLASS;

public class Scoresheet {
    // these are the 13 Catgories in this game thaat leads to scoreboard
    public static String[] CATEGORIES = {
            "Ones", "Twos", "Threes", "Fours", "Fives", "Sixes",
            "Triple", " Quad", "Full House", "Small Straight", "Large Straight",
            "Yahtzee", "Chance"
    };

    private int[] scores = new int[13];

    public Scoresheet() {
        for (int i = 0; i < 13; i++) {
            scores[i] = -1;
        }
    }

    public void displayScorecard() {
        System.out.println("\nScorecard:");
        for (int i = 0; i < 13; i++) {
            System.out.println(CATEGORIES[i] + ": " + (scores[i] == -1 ? "[--]" : "[" + scores[i] + "]"));
        }

        System.out.println("Total: " + getTotalScore());
    }

    // if user enters the same number twice there will be an error
    public void updateScore(int categoryIndex, int score) {
        if (categoryIndex >= 0 && categoryIndex < 13 && scores[categoryIndex] == -1) {
            scores[categoryIndex] = score;
        } else {
            System.out.println("Error category had already been scored");
        }
    }

    public int getTotalScore() {
        int total = 0;
        for (int score : scores) {
            if (score != -1) {
                total += score;
            }
        }
        return total;
    }

    // once you enter your category number it will display the score on next round
    public int getScoreForCategory(int index) {
        if (index >= 0 && index < scores.length) {
            return scores[index];
        } else {
            return -1;
        }
    }

    public int calculateTotalScore() {
        int totalScore = 0;
        for (int score : scores) {
            if (score != -1) {
                totalScore += score; // Only add valid scores
            }
        }
        return totalScore;
    }
}
