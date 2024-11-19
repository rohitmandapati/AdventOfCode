package year23.day20;

import java.util.Queue;

import year23.day20.Pulse.PulseType;

/*
 * Made as stopper to pulse queue. If a node doesn't
 * exist as a FlipFlop or Conjuction module, it doesn't
 * connect anywhere so a dummy node is created. Does not
 * do anything.
 */ 
public class DummyModule implements Module{
    String name;

    DummyModule(String name) {
        this.name = name;
    }

    // Literally does nothing, adds no pulses to pulseQueue
    @Override
    public void receivePulse(PulseType pulseType, Queue<Pulse> pulseQueue, Module source) {
        return;
    }

    @Override
    public void addDestination(Module module) {
        return;
    }

    public String toString() {
        return this.name + " Dummy";
    }
}
