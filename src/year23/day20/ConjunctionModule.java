package year23.day20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import year23.day20.Pulse.PulseType;

public class ConjunctionModule implements Module {

    String name;
    Map<Module, PulseType> inputStore = new HashMap<Module, PulseType>();
    List<Module> destinations = new ArrayList<Module>();
    
    boolean isOn = false;

    public ConjunctionModule(String name) {
        this.name = name;
    } 

    @Override
    public void receivePulse(PulseType pulseType, Queue<Pulse> pulseQueue, Module source) {
        // Module inputModule = source;
        // inputStore.put(inputModule, pulseType);

        boolean allHigh = true;
        for (Module key : inputStore.keySet()) {
            if (inputStore.get(key) == PulseType.LOW) {
                allHigh = false;
            }
        }
        PulseType output;
        if (allHigh) output = PulseType.LOW;
        else output = PulseType.HIGH;

        for (Module destination : destinations) {
            pulseQueue.add(new Pulse(output, destination));
        }
    }

    @Override
    public void addDestination(Module module) {
        this.destinations.add(module);
    }   
    
    public void addInput(Module module) {
        this.inputStore.put(module, PulseType.LOW);
    }

    @Override
    public String toString() {
        return "&" + this.name;
    }

}
