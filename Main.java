package CLASS;

/**  Author : Christian Ivanov
 * Course : CPI 221
 * Assignment : Hw #1
 * Date: Sumbission Date 2/2/2025
 * Description: Yahtzee player game
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of player ");
        int numPlayers = scanner.nextInt();

        // Now the game will start

        game game = new game(numPlayers);
        game.play_game();

        scanner.close();
    }
}
