package year23.day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.LinkedList;
import java.util.Queue;

public class ScratchCard {
    private int[] winningNumbers;
    private int[] numbers;
    private int score;
    private int id;
    private int matches;

    public ScratchCard(int[] winningNumbers, int[] numbers, int id) {
        this.winningNumbers = winningNumbers;
        this.numbers = numbers;
        this.matches = this.numWinners();
        if (this.matches == 0) this.score = 0;
        else {
            this.score = (int) Math.pow(2,this.matches - 1);
        }
        this.id = id;
    }

    // for Part 2
    public ScratchCard(ScratchCard s) {
        this(s.getWinners(), s.getNums(), s.getId());
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

    //Part 2
    private int getId() { return this.id; }
    private int getMatches() { return this.matches; }
    private int[] getNums() { return this.numbers; }
    private int[] getWinners() { return this.winningNumbers; }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/year23/day4/input.txt");
        Scanner scan = new Scanner(file);

        // for Part 2
        List<ScratchCard> cardsList = new ArrayList<ScratchCard>();
        Queue<ScratchCard> cardsQueue = new LinkedList<ScratchCard>();
        
        
        int totalScore = 0;

        int id = 0;
        while(scan.hasNextLine()) {
            int[][] ret = parse(scan.nextLine());
            ScratchCard scratch = new ScratchCard(ret[0], ret[1], id++);
            totalScore += scratch.getScore();

            //for Part 2
            cardsList.add((ScratchCard) scratch);
            cardsQueue.add(scratch);
        }

        scan.close();

        System.out.println(totalScore);


        // ------------ Part 2 ------------

        int totalCards = 0;

        while (!cardsQueue.isEmpty()) {
            ScratchCard s = (ScratchCard) cardsQueue.remove();
            totalCards++;

            for (int i = 1; i <= s.getMatches(); i++) {
                cardsQueue.add(cardsList.get(s.getId() + i));
            }
        }

        System.out.println(totalCards);

    }
}

