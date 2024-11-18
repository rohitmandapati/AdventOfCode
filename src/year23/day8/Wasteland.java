package year23.day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

import java.util.List;
import java.util.ArrayList;

public class Wasteland {
    
    public static HashMap<String, String[]> map = new HashMap<String, String[]>();
    public static final char[] INSTRUCTIONS = readLine("src/year23/day8/instructions.txt").toCharArray();

    //for Part 2
    public static List<String> entries = new ArrayList<String>();

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

            // for Part 2
            if(input[0][0].lastIndexOf('A') == 2) {
                entries.add(input[0][0]);
            }

        }
        scan.close();
    }

    // for Part 2
    public static boolean checkEnd(Object[] l, char ch) {
        for (int i = 0; i < l.length; i++) {
            if (!(l[i].toString().charAt(2) == ch)) {
                return false;
            }
        }
        return true;
    }


    private static boolean checkLast(String s, char ch) {
        if (s.charAt(s.length() - 1) == ch) { return true; }
        else { return false; }
    }

    public static long findGCD(long a, long b) {
        if (b == 0)
            return a;
        return findGCD(b, a % b);
    }

    public static long findLCM(long a, long b) {
        return a * b / findGCD(a, b);
    }

    public static long findLCMArray(int[] arr) {
        long lcm = arr[0];
        for (int i = 1; i < arr.length; i++) {
            lcm = findLCM(lcm, arr[i]);
        }
        return lcm;
    }

    public static long lcm_of_array_elements(int[] element_array)
    {
        long lcm_of_array_elements = 1;
        int divisor = 2;
        
        while (true) {
            int counter = 0;
            boolean divisible = false;
            
            for (int i = 0; i < element_array.length; i++) {

                // lcm_of_array_elements (n1, n2, ... 0) = 0.
                // For negative number we convert into
                // positive and calculate lcm_of_array_elements.

                if (element_array[i] == 0) {
                    return 0;
                }
                else if (element_array[i] < 0) {
                    element_array[i] = element_array[i] * (-1);
                }
                if (element_array[i] == 1) {
                    counter++;
                }

                // Divide element_array by devisor if complete
                // division i.e. without remainder then replace
                // number with quotient; used for find next factor
                if (element_array[i] % divisor == 0) {
                    divisible = true;
                    element_array[i] = element_array[i] / divisor;
                }
            }

            // If divisor able to completely divide any number
            // from array multiply with lcm_of_array_elements
            // and store into lcm_of_array_elements and continue
            // to same divisor for next factor finding.
            // else increment divisor
            if (divisible) {
                lcm_of_array_elements = lcm_of_array_elements * divisor;
            }
            else {
                divisor++;
            }

            // Check if all element_array is 1 indicate 
            // we found all factors and terminate while loop.
            if (counter == element_array.length) {
                return lcm_of_array_elements;
            }
        }
    }
    

    public static void main(String[] args) throws FileNotFoundException {
        String inputLocation = "src/year23/day8/input.txt";
        File input = new File(inputLocation);
        createHash(input);

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
            steps++;
            index++;
        }

        System.out.println(steps);

        // ------------ Part 2 ------------

        steps = 0;
        index = 0;

        Object[] nodes__ = entries.toArray();
        String[] nodes = new String[nodes__.length];
        for (int i = 0; i < nodes__.length; i++) {
            nodes[i] = (String) nodes__[i];
        }

        int[] result = new int[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            index = 0;
            steps = 0;
            while(!(checkLast(nodes[i], 'Z')) )  {
                if (index == INSTRUCTIONS.length) {
                    index = 0;
                }
                if (INSTRUCTIONS[index] == 'L') {
                    nodes[i] = map.get(nodes[i])[0];
                } else {
                    nodes[i] = map.get(nodes[i])[1];
                }
                steps++;
                index++;
            }
            result[i] = steps;
        }
        System.out.println(findLCMArray(result));
    }
}
