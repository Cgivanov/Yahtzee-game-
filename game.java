package CLASS;

import java.util.Scanner;

public class game {

    private Player[] players;
    private int currentTurn;
    private int currentRound;
    private Scanner scanner;

    public game(int numPlayers) {
        players = new Player[numPlayers];
        System.out.println("The players");
        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player("Christian" + (i + 1));
            System.out.println("Christian :" + players[i].getName());
        }

        currentRound = 1;
        currentTurn = 0;
        scanner = new Scanner(System.in);
    }

    public void play_game() {
        while (currentRound <= 13) {
            System.out.println("\n--- Round " + currentRound + " ---");
            play_round();
            currentRound++;
        }
        System.out.println("Game over!");
        for (Player player : players) {
            System.out.println(player.getName() + "; Final Score: " + player.getTotalScore());
        }
    }

    public void play_round() {

        Player currentPlayer = players[currentTurn];
        System.out.println(currentPlayer.getName() + "'s turn!");

        // dice will roll

        currentPlayer.resetDice();

        currentPlayer.rollDice();
        currentPlayer.printDice();

        System.out.println("\nWhich die do you want to keep again? (Enter 1-5 to roll again or -1 to skip)");
        for (int i = 0; i < 5; i++) {
            System.out.print((i + 1) + ": " + currentPlayer.getDice()[i].getValue() + "n : ");
        }

        for (int i = 0; i < 5; i++) {
            System.out.print("\nEnter 1-5 to keep that die or -1 to roll again: ");
            int choice = scanner.nextInt();
            if (choice == -1) {
                break; // Roll again if -1 is entered
            } else if (choice >= 1 && choice <= 5) {
                currentPlayer.getDice()[choice - 1].toggleKeep(); // Toggle the keep state of the selected die
            }
        }

        currentPlayer.rollDice(); // Reroll the dice that are not kept
        currentPlayer.printDice();

        // Ask the player in which of the following catorigiers to score the roll

        System.out.println("\nWhere would you like to score this roll?");
        for (int i = 0; i < 13; i++) {
            System.out.printf("%d - %s: %s\n", i + 1, Scoresheet.CATEGORIES[i],
                    (currentPlayer.getScoreSheet().getScoreForCategory(i) == -1 ? "[--]"
                            : "[" + currentPlayer.getScoreSheet().getScoreForCategory(i) + "]"));
        }

        System.out.print("Choose 1-13 to score your roll: ");
        int categoryChoice = scanner.nextInt();

        if (categoryChoice >= 1 && categoryChoice <= 13) {
            int score = calculateCategoryScore(currentPlayer, categoryChoice - 1);
            currentPlayer.updateScore(categoryChoice - 1, score);
        } else {
            System.out.println(" Does not work this choice");
        }

        currentTurn = (currentTurn + 1) % players.length;
    }

    private int calculateCategoryScore(Player currentPlayer, int categoryIndex) {
        int score = 0;
        int[] diceValues = new int[6];
        Die[] dice = currentPlayer.getDice();
        // Count how many times each die value (1-6) appears
        for (Die die : dice) {
            diceValues[die.getValue() - 1]++;
        }

        switch (categoryIndex) {
            case 0: // "Ones"
                score = diceValues[0] * 1;
                break;
            case 1: // "Twos"
                score = diceValues[1] * 2;
                break;
            case 2: // "Threes"
                score = diceValues[2] * 3;
                break;
            case 3: // "Fours"
                score = diceValues[3] * 4;
                break;
            case 4: // "Fives"
                score = diceValues[4] * 5;
                break;
            case 5: // "Sixes"
                score = diceValues[5] * 6;
                break;
            case 6: // "Triple"
                for (int i = 0; i < 6; i++) {
                    if (diceValues[i] >= 3) {
                        score = 0;
                        for (Die die : dice) {
                            score += die.getValue(); // Add the value of all dice
                        }
                        break;
                    }
                }
                break;
            case 7: // "Quad"
                for (int i = 0; i < 6; i++) {
                    if (diceValues[i] >= 4) {
                        score = 0;
                        for (Die die : dice) {
                            score += die.getValue(); // Add the value of all dice
                        }
                        break;
                    }
                }
                break;
            case 8: // "Full House"
                boolean threeOfAKind = false;
                boolean pair = false;
                for (int i = 0; i < 6; i++) {
                    if (diceValues[i] == 3) {
                        threeOfAKind = true;
                    }
                    if (diceValues[i] == 2) {
                        pair = true;
                    }
                }
                if (threeOfAKind && pair) {
                    score = 25; // Full House gives 25 points
                }
                break;
            case 9: // "Small Straight"

                if ((diceValues[0] > 0 && diceValues[1] > 0 && diceValues[2] > 0 && diceValues[3] > 0) || // 1-2-3-4
                        (diceValues[1] > 0 && diceValues[2] > 0 && diceValues[3] > 0 && diceValues[4] > 0) || // 2-3-4-5
                        (diceValues[2] > 0 && diceValues[3] > 0 && diceValues[4] > 0 && diceValues[5] > 0)) { // 3-4-5-6
                    score = 30; // Small straight gives 30 points
                }
                break;
            case 10: // "Large Straight"

                if ((diceValues[0] > 0 && diceValues[1] > 0 && diceValues[2] > 0 && diceValues[3] > 0
                        && diceValues[4] > 0) || // 1-2-3-4-5
                        (diceValues[1] > 0 && diceValues[2] > 0 && diceValues[3] > 0 && diceValues[4] > 0
                                && diceValues[5] > 0)) { // 2-3-4-5-6
                    score = 40; // Large straight gives 40 points
                }
                break;
            case 11: // "Yahtzee"
                for (int i = 0; i < 6; i++) {
                    if (diceValues[i] == 5) {
                        score = 50; // Yahtzee gives 50 points if all five dice are the same
                        break;
                    }
                }
                break;
            case 12: // "Chance"
                // Sum all dice for Chance category
                for (Die die : dice) {
                    score += die.getValue();
                }
                break;
            default:
                System.out.println("Invalid category");
        }

        return score;
    }
}