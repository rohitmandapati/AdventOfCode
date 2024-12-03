package year24.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mul {

    static final String PATH = "src/year24/day3/input.txt";

    public static String parse(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scan = new Scanner(file);
        String input = "";
        while (scan.hasNextLine()) {
            input += scan.nextLine();
        }
        scan.close();
        return input;
    }
    
    public static int part1() throws FileNotFoundException {

        String input = parse(PATH);

        Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
        Matcher match = pattern.matcher(input);

        int sum = 0;
        while (match.find()) {
            sum += Integer.parseInt(match.group(1))
                    * Integer.parseInt(match.group(2));
        }
        return sum;
    }

    public static int part2() throws FileNotFoundException {

        String input = parse(PATH);

        Pattern pattern = Pattern.compile("(mul\\((\\d{1,3}),(\\d{1,3})\\))" + 
            "|(do\\(\\))" + 
            "|(don\'t\\(\\))");
        Matcher match = pattern.matcher(input);

        boolean on = true;
        int sum = 0;
        while (match.find()) {
            if (match.group(1) != null) {
                if (on) {
                    int x = Integer.parseInt(match.group(2));
                    int y = Integer.parseInt(match.group(3));
                    sum += x * y;
                }
            } else if (match.group(4) != null) { 
                on = true;
            } else if (match.group(5) != null) { 
                on = false;
            }
        }
        return sum;
    }



    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(part1());
        System.out.println(part2());
    }
}
