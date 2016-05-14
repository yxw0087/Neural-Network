package weatherforecast;

public class Output {
    double value;
    double target;
    
    public Output() {
        value = 0;
        target = 0;
    }
    
    public void set(double value, double target) {
        this.value = value;
        this.target = target;
    }
}
