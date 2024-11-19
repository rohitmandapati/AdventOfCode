package year23.day20;

import java.util.Queue;
import year23.day20.Pulse.PulseType;

public interface Module {
    void receivePulse(PulseType pulseType, Queue<Pulse> pulseQueue, Module source);
    void addDestination(Module module);
    String toString();
    String getName();
}
