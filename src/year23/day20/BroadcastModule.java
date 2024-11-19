package year23.day20;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import year23.day20.Pulse.PulseType;

public class BroadcastModule implements Module {

    String name;
    List<Module> destinations = new ArrayList<Module>();

    BroadcastModule(String name) {
        this.name = name;
    }

    @Override
    public void receivePulse(PulseType pulseType, Queue<Pulse> pulseQueue, Module source) {
        for (Module destination : destinations) {
            pulseQueue.add(new Pulse(pulseType, destination, this));
        }
    }

    @Override
    public void addDestination(Module module) {
        this.destinations.add(module);
    }
    
    @Override
    public String toString() {
        return this.getName();
    }

    public String getName() { return name; }

    public boolean equals(Module other) {
        return (this.getName().equals(other.getName()));
    }

}
