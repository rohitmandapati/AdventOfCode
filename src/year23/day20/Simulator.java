package year23.day20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

import year23.day20.Pulse.PulseType;

public class Simulator {

    public static Map<String, Module> parse(String filename) throws FileNotFoundException {
            File file = new File(filename);
            Scanner scan = new Scanner(file);

            Map<String, Module> modules = new HashMap<String, Module>();
            List<String[][]> connections = new ArrayList<String[][]>();
            
            while (scan.hasNextLine()) {
                String line = scan.nextLine();

                String[] parts = line.split("->");
                // System.out.println(Arrays.toString(parts));

                String mod = parts[0].trim();
                Module module;
                String moduleName;
                if (mod.charAt(0) == '%') {
                    moduleName = mod.substring(1);
                    module = new FlipFlopModule(moduleName);
                } else if (mod.charAt(0) == '&') {
                    moduleName = mod.substring(1);
                    module = new ConjunctionModule(moduleName);
                } else {
                    moduleName = mod;
                    module = new BroadcastModule(moduleName);
                }
                modules.put(moduleName, module);

                String[] out = parts[1].trim().split(",");
                for (int i = 0; i < out.length; i++) {
                    out[i] = out[i].trim();
                }
                connections.add(new String[][] {{moduleName}, out});                
            }

            for (String[][] connection : connections) {
                // System.out.println("aaaa");
                Module sourceModule = modules.get(connection[0][0]);
                for (int i = 0; i < connection[1].length; i++) {
                    // System.out.println("bbbb");
                    Module destination = modules.get(connection[1][i]);
                    if (destination == null) {
                        destination = new DummyModule(connection[1][i]);
                        modules.put(connection[1][i], destination);
                    }
                    modules.get(connection[0][0])
                        .addDestination(destination);

                    if (destination instanceof ConjunctionModule) {
                        ((ConjunctionModule) destination).addInput(sourceModule);
                    }
                }
            }
            scan.close();
            return modules;
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

    public static void main(String[] args) throws FileNotFoundException {
        String file = "src/year23/day20/input.txt";
        Map<String, Module> modules = parse(file);


        BroadcastModule broadcast = (BroadcastModule) modules.get("broadcaster");

        long totalLowPulse = 0l;
        long totalHighPulse = 0l;

        for (int i = 0; i < 1000; i++) {
            Queue<Pulse> pulseQueue = new LinkedList<Pulse>();
            pulseQueue.add(new Pulse(PulseType.LOW, broadcast, null));
            while (!pulseQueue.isEmpty()) {
                // for(Pulse p : pulseQueue) { 
                //     System.out.println(p.toString()); 
                // }
                // System.out.println("------------");
                Pulse p = pulseQueue.poll();
                if (p.pulseType == PulseType.LOW) {
                    totalLowPulse++;
                } else {
                    totalHighPulse++;
                }
                p.destination.receivePulse(p.pulseType, pulseQueue, p.source);
            }
        }

        long product = totalLowPulse * totalHighPulse;
        System.out.println(totalLowPulse);
        System.out.println(totalHighPulse);
        System.out.println(product);

        // ------------ Part 2 ------------

        modules = parse(file);
        System.out.println(modules.get("rs"));

        broadcast = (BroadcastModule) modules.get("broadcaster"); 
        List<Module> entries = broadcast.destinations;
        int[] buttonPresses = new int[entries.size()];
        System.out.println(entries);
        for (int i = 0; i < entries.size(); i++) {
            int presses = 0;
            Pulse p = new Pulse(PulseType.LOW, entries.get(i), broadcast);
            
            Queue<Pulse> pulseQueue = new LinkedList<Pulse>();
            while(!(p.destination == modules.get("rs") && p.pulseType == PulseType.HIGH)) {
                if (pulseQueue.isEmpty()) {
                    p = new Pulse(PulseType.LOW, entries.get(i), broadcast);
                    pulseQueue.add(p);
                    presses++;
                }
                p = pulseQueue.poll();
                p.destination.receivePulse(p.pulseType, pulseQueue, p.source);
            }

            buttonPresses[i] = presses;            
        }

        long lcm = findLCMArray(buttonPresses);
        System.out.println(Arrays.toString(buttonPresses));
        System.out.println(lcm);
    }
    
}
