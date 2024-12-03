package year24.day2;

import java.io.*;
import java.util.*;

public class Data {
    private boolean isAscending;
    private boolean isDescending;
    private ArrayList<Integer> arr;
    private boolean isSafe;
    
    // for Part 2
    private boolean isSafeDampened;
    public static int numSafeDampened = 0;


    public static int numSafe = 0;
    public static ArrayList<Data> data = new ArrayList<Data>();

    Data(ArrayList<Integer> arr) {
        this.arr = arr;
        ListIterator<Integer> iter =  this.arr.listIterator();

        int i = iter.next();

        isAscending = true;
        isDescending = true;

        while (iter.hasNext()) {
            int j = iter.next();
            if (i > j) {
                isAscending = false;
            } 
            if (i < j) {
                isDescending = false;
            }
            i = j;
        }

        this.isSafe = this.isSafe();
        if (this.isSafe) { numSafe++; }

        // for Part 2
        this.isSafeDampened = this.isSafeDampened();
        if (this.isSafeDampened) { numSafeDampened++; }
    }

    public String toString() {
        return Arrays.toString(this.arr.toArray());
    }

    

    public boolean isSafe() { 
        if (isAscending || isDescending) {
            boolean out = true;
            for (int i = 1; i < this.arr.size(); i++) {
                int diff = Math.abs(this.arr.get(i) - this.arr.get(i - 1));
                if (!(diff <= 3 && diff >= 1)) {
                    out = false;
                }
            }
            return out;
        } else {
            return false;
        }
    }
    
    // for Part 2
    public boolean isSafeDampened() {
        if (isAscending || isDescending) {
            int problems = 0;
            for (int i = 1; i < this.arr.size(); i++) {
                int diff = Math.abs(this.arr.get(i) - this.arr.get(i - 1));
                if (!(diff <= 3 && diff >= 1)) {
                    problems++;
                }
            }
            return (problems >= 2);
        } else {
            return false;
        }
    }

    public static void parse(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] nums = line.split(" ");

            ArrayList<Integer> intList = new ArrayList<>();
            for (String s : nums) {
                intList.add(Integer.parseInt(s));
            }
            @SuppressWarnings("unused")
            Data e = new Data(intList);
        }
        scan.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String path = "src/year24/day2/input.txt";
        File file = new File(path);
        parse(file);
        System.out.println(numSafe);

        // for Part 2
        System.out.println(numSafeDampened);
    }
}