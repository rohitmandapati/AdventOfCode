package year23.day20;

public class Pulse {
    public enum PulseType {
        LOW,
        HIGH 
    }
    
    Module destination;
    Module source;
    PulseType pulseType;

    Pulse(PulseType pulseType, Module destination, Module source) {
        this.pulseType = pulseType;
        this.destination = destination;
        this.source = source;
    }

    Pulse(PulseType pulseType, Module destination) {
        this(pulseType, destination, null);
    }

    public String toString() {
        return this.pulseType.toString() + " " + this.destination.toString();
    }

    public boolean equals(Pulse other) {
        return (this.destination.equals(other.destination)
            && this.pulseType == other.pulseType);
    }
}
