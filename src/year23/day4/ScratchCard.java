package year23.day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class ScratchCard {
    private int[] winningNumbers;
    private int[] numbers;
    private int score;

    public ScratchCard(int[] winningNumbers, int[] numbers) {
        this.winningNumbers = winningNumbers;
        this.numbers = numbers;
        if (this.numWinners() == 0) this.score = 0;
        else {
            this.score = (int) Math.pow(2,this.numWinners() - 1);
        }
    }

    private int numWinners() {
        int winners = 0;
        for (int i = 0; i < this.numbers.length; i++) {
            for (int j = 0; j < this.winningNumbers.length; j++) {
                if (this.numbers[i] == this.winningNumbers[j]) {
                    winners++;
                }
            }
        }
        return winners;
    }    

    private static int[][] parse(String str) {
        int[][] out = new int[2][];
        out[0] = new int[10];
        out[1] = new int[25];
        String winners = str.substring(str.indexOf(':') + 2, str.indexOf('|')).trim() + " ";
        for (int i = 0; i < out[0].length; i++) {
            out[0][i] = Integer.parseInt(winners.substring(0, winners.indexOf(' ')));
            // System.out.println(i + " " + out[0][i]);
            winners = winners.substring(winners.indexOf(' ') + 1).trim() + " ";
        }
        String numbers = str.substring(str.indexOf('|') + 2).trim() + " ";
        for (int i = 0; i < out[1].length; i++) {
            out[1][i] = Integer.parseInt(numbers.substring(0, numbers.indexOf(' ')));
            numbers = numbers.substring(numbers.indexOf(' ') + 1).trim() + " ";
        }
        return out;        
    }

    private int getScore() { return this.score; }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/year23/day4/input.txt");
        Scanner scan = new Scanner(file);

        List cards = new ArrayList<ScratchCard>(218);
        int totalScore = 0;

        while(scan.hasNextLine()) {
            int[][] ret = parse(scan.nextLine());
            ScratchCard scratch = new ScratchCard(ret[0], ret[1]);
            totalScore += scratch.getScore();
        }

        System.out.println(totalScore);


    }
}

