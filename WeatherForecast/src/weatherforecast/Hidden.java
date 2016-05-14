package weatherforecast;

public class Hidden {
    double value;
    double[] weight = new double[5];
    
    public Hidden() {
        weight[0] = (int) (Math.random()*10) / 10.0;
        weight[1] = (int) (Math.random()*10) / 10.0;
        weight[2] = (int) (Math.random()*10) / 10.0;
        weight[3] = (int) (Math.random()*10) / 10.0;
        weight[4] = (int) (Math.random()*10) / 10.0;
    }
    
    public void set(double value) {
        this.value = value;          
    }
}
