package weatherforecast;

public class Bias {
    double value = 0.0;
    double weight;
    
    public Bias() {
        weight = (int) (Math.random()*10) / 10.0;
    }
}
