package year23.day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

public class Wasteland {
    
    private static HashMap<String, String[]> map = new HashMap<String, String[]>();

    private static final char[] INSTRUCTIONS = readLine("src/year23/day8/instructions.txt").toCharArray();

    private static String readLine(String location) {
        File file = new File(location);
        try {
            Scanner scan = new Scanner(file);
            if (scan.hasNextLine()) {
                scan.close();
                return scan.nextLine();
            } else {
                scan.close();
                return null;
            }
        } catch(FileNotFoundException e) {
            System.out.println("File is not found");
            return null;
        }
    }

    private static String[][] parse(String line) {
        String[][] out = new String[2][];
        out[0] = new String[1];
        out[1] = new String[2];
        out[0][0] = line.substring(0,3);
        out[1][0] = line.substring(7,10);
        out[1][1] = line.substring(12,15);
        return out;
    }

    public static void createHash(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            String[][] input = parse(scan.nextLine());
            map.put(input[0][0], input[1]);
        }
        scan.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String inputLocation = "src/year23/day8/input.txt";
        File input = new File("src/year23/day8/input.txt");
        createHash(input);

        // String entry = readLine(inputLocation).substring(0,3);
        String entry = "AAA";

        int steps = 0;
        int index = 0;
        while(!(entry.equals("ZZZ"))) {
            if (index == INSTRUCTIONS.length) {
                index = 0;
            }
            if (INSTRUCTIONS[index] == 'L') {
                entry = map.get(entry)[0];
            } else {
                entry = map.get(entry)[1];
            }

            // if (entry.equals("LHV")) break;
            steps++;
            index++;
            System.out.println(entry);
        }

        System.out.println(steps);

    }
    
}
