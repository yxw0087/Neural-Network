package weatherforecast;

public class Input {
    double value;
    double weight;
    
    public Input() {
        weight = (int) (Math.random()*10) / 10.0;
    }
    
    public void set(double value) {
        this.value = value;            
    }
}
