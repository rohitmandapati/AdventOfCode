package year23.day20;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import year23.day20.Pulse.PulseType;

public class FlipFlopModule implements Module {

    String name;
    List<Module> destinations = new ArrayList<Module>();
    
    boolean isOn = false;

    public FlipFlopModule(String name) {
        this.name = name;
    } 

    @Override
    public void receivePulse(PulseType pulseType, Queue<Pulse> pulseQueue, Module source) {
        if (pulseType == PulseType.HIGH) {
            return;
        }

        isOn = !isOn;
        PulseType output;
        if(isOn) output = PulseType.HIGH;
        else output = PulseType.LOW;

        for (Module destination : destinations) {
            pulseQueue.add(new Pulse(output, destination, source));
        }
    }

    @Override
    public void addDestination(Module module) {
        this.destinations.add(module);
    }

    @Override
    public String toString() {
        return "%" + this.name;
    }
    
}
