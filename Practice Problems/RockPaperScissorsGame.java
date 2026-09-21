public class RockPaperScissorsGame {

    static String playRound(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove)) {
            return "Draw";
        }
        if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
            (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
            (playerMove.equals("Scissors") && computerMove.equals("Paper"))) {
            return "Player Wins";
        }
        return "Computer Wins";
    }

    public static void main(String[] args) {
        String[] playerMoves = {"Rock", "Paper", "Scissors", "Paper", "Rock"};
        String[] computerMoves = {"Scissors", "Paper", "Rock", "Rock", "Paper"};

        String[] results = new String[playerMoves.length];
        int wins = 0, losses = 0, draws = 0;

        for (int i = 0; i < playerMoves.length; i++) {
            results[i] = playRound(playerMoves[i], computerMoves[i]);
            if (results[i].equals("Player Wins")) wins++;
            else if (results[i].equals("Computer Wins")) losses++;
            else draws++;
        }

        System.out.println("Round | Player Move | Computer Move | Result");
        for (int i = 0; i < playerMoves.length; i++) {
            System.out.println((i + 1) + " | " + playerMoves[i] + " | " + computerMoves[i] + " | " + results[i]);
        }

        double winPercentage = (wins / (double) playerMoves.length) * 100;
        System.out.printf("Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n", wins, losses, draws, winPercentage);
    }
}
