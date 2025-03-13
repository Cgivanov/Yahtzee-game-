package CLASS;

public class Player {
    private String name;
    private Scoresheet scoreSheet;
    private Die[] dice;

    public Player(String name) {
        this.name = name;
        this.scoreSheet = new Scoresheet();
        this.dice = new Die[5]; // each player has 5 dice
        for (int i = 0; i < dice.length; i++) {
            this.dice[i] = new Die();
        }
    }

    // Socreboard of the plaayer
    public Scoresheet getScoreSheet() {
        return scoreSheet;
    }

    // player name to get in this case Christian1
    public String getName() {
        return name;
    }

    // total score at the end
    public int getTotalScore() {
        return scoreSheet.getTotalScore();
    }

    // player dice to roll
    public Die[] getDice() {
        return dice;
    }

    public void updateScore(int categoryIndex, int score) {
        scoreSheet.updateScore(categoryIndex, score);

    }

    public void displayScorecard() {
        scoreSheet.displayScorecard();
    }

    // to roll all the five dice but after each round to reset
    public void rollDice() {
        for (Die Die : dice) {
            Die.roll();
        }
    }

    // print all the vlaues that dice shows
    public void printDice() {
        for (int i = 0; i < 5; i++) {
            System.out.print(dice[i].getValue() + " "); // Print value of each die
        }
        System.out.println();
    }

    // will reset after each round fresh set of dice
    public void resetDice() {
        for (Die die : dice) {
            die.setKept(false);
        }
    }
}