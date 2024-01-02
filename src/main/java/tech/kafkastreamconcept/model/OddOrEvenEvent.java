package tech.kafkastreamconcept.model;

public class OddOrEvenEvent {

    private Double number;

    public OddOrEvenEvent() {
    }

    public OddOrEvenEvent(Double number) {
        this.number = number;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "OddOrEvenEvent{" +
                "number=" + number +
                '}';
    }
}
