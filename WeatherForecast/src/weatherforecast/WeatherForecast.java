package weatherforecast;

import java.io.*;
import java.util.*;

public class WeatherForecast {

    public static void main(String[] args) {
        
        int inputSize = 3;
        int numDimensions = 5;
        int trainingSize = 4402;
        String fileName = "";
        double[] maxTemp = new double[trainingSize];
        double[] minTemp = new double[trainingSize];
        double[] wind = new double[trainingSize];
        double[] humidity = new double[trainingSize];
        double[] rain = new double[trainingSize];
        int[] maxtemp = new int[trainingSize];
        int[] mintemp = new int[trainingSize];
        int[] wind_ori = new int[trainingSize];
        int[] humidity_ori = new int[trainingSize];
        double[] rain_ori = new double[trainingSize];
        Input[] inputs = new Input[inputSize * numDimensions];
        Hidden[] hiddens = new Hidden[inputSize];
        Output[] outputs = new Output[numDimensions];

        try {
            fileName = "max-temp.txt";
            FileReader fileReader = new FileReader(fileName);
            Scanner scanner = new Scanner(fileReader);

            for (int i = 0; i < trainingSize; i++) {
                maxtemp[i] = scanner.nextInt();
                scanner.nextLine();
            }

            maxTemp = normalize(maxtemp);

            fileName = "min-temp.txt";
            fileReader = new FileReader(fileName);
            scanner = new Scanner(fileReader);

            for (int i = 0; i < trainingSize; i++) {
                mintemp[i] = scanner.nextInt();
                scanner.nextLine();
            }

            minTemp = normalize(mintemp);

            fileName = "wind-speed.txt";
            fileReader = new FileReader(fileName);
            scanner = new Scanner(fileReader);

            for (int i = 0; i < trainingSize; i++) {
                wind_ori[i] = scanner.nextInt();
                scanner.nextLine();
            }

            wind = normalize(wind_ori);

            fileName = "humidity.txt";
            fileReader = new FileReader(fileName);
            scanner = new Scanner(fileReader);

            for (int i = 0; i < trainingSize; i++) {
                humidity_ori[i] = scanner.nextInt();
                scanner.nextLine();
            }

            humidity = normalize(humidity_ori);

            fileName = "rain-fall.txt";
            fileReader = new FileReader(fileName);
            scanner = new Scanner(fileReader);

            for (int i = 0; i < trainingSize; i++) {
                rain_ori[i] = scanner.nextDouble();
                scanner.nextLine();
            }

            rain = normalize(rain_ori);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Input input1 = new Input();
        Input input2 = new Input();
        Input input3 = new Input();
        Input input4 = new Input();
        Input input5 = new Input();
        Bias bias1 = new Bias();
        Hidden hidden1 = new Hidden();
        Hidden hidden2 = new Hidden();
        Hidden hidden3 = new Hidden();
        Bias bias2 = new Bias();
        Output output1 = new Output();
        Output output2 = new Output();
        Output output3 = new Output();
        Output output4 = new Output();
        Output output5 = new Output();

        // Leaves the last 3 data sets because we need at least 3 previous data
        // sets to predict the next day weather
        for (int i = 0; i < trainingSize - inputSize; i++) {
            int k = i;
            int l = 0;
            for (int j = 0; j < inputs.length; j += numDimensions) {
                
                input1.set(maxTemp[k]);
                inputs[j] = input1;
                input2.set(minTemp[k]);
                inputs[j + 1] = input2;
                input3.set(wind[k]);
                input3.weight = 0;
                inputs[j + 2] = input3;                
                input4.set(humidity[k]);
                inputs[j + 3] = input4;
                input5.set(rain[k]);
                input5.weight = 0;
                inputs[j + 4] = input5;
                k++;
            }

            // Calculate net input to hidden neurons
            double net_hidden
                    = inputs[0].value * inputs[0].weight
                    + inputs[1].value * inputs[1].weight
                    + inputs[2].value * inputs[2].weight
                    + inputs[3].value * inputs[3].weight
                    + inputs[4].value * inputs[4].weight
                    + bias1.value * bias1.weight;

            // Goes thru sigmoid function
            net_hidden = 1 / (1 + Math.exp(net_hidden));

            hidden1.set(net_hidden);
            hiddens[0] = hidden1;

            // Calculate net input to hidden neurons
            double net_hidden2
                    = inputs[5].value * inputs[5].weight
                    + inputs[6].value * inputs[6].weight
                    + inputs[7].value * inputs[7].weight
                    + inputs[8].value * inputs[8].weight
                    + inputs[9].value * inputs[9].weight
                    + bias1.value * bias1.weight;

            // Goes thru sigmoid function
            net_hidden2 = 1 / (1 + Math.exp(net_hidden2));

            hidden2.set(net_hidden2);
            hiddens[1] = hidden2;

            // Calculate net input to hidden neurons
            double net_hidden3
                    = inputs[10].value * inputs[10].weight
                    + inputs[11].value * inputs[11].weight
                    + inputs[12].value * inputs[12].weight
                    + inputs[13].value * inputs[13].weight
                    + inputs[14].value * inputs[14].weight
                    + bias1.value * bias1.weight;

            // Goes thru sigmoid function
            net_hidden3 = 1 / (1 + Math.exp(net_hidden3));

            hidden3.set(net_hidden3);
            hiddens[2] = hidden3;

            double net_in1 = 0;
            double net_in2 = 0;
            double net_in3 = 0;
            double net_in4 = 0;
            double net_in5 = 0;

            for (int j = 0; j < hiddens.length; j++) {
                net_in1 += hiddens[j].value * hiddens[j].weight[0];
                net_in2 += hiddens[j].value * hiddens[j].weight[1];
                net_in3 += hiddens[j].value * hiddens[j].weight[2];
                net_in4 += hiddens[j].value * hiddens[j].weight[3];
                net_in5 += hiddens[j].value * hiddens[j].weight[4];
            }
            
            net_in1 += bias2.value * bias2.weight;
            net_in2 += bias2.value * bias2.weight;
            net_in3 += bias2.value * bias2.weight;
            net_in4 += bias2.value * bias2.weight;
            net_in5 += bias2.value * bias2.weight;

            // Goes thru sigmoid function
            net_in1 = 1 / (1 + Math.exp(net_in1));
            net_in2 = 1 / (1 + Math.exp(net_in2));
            net_in3 = 1 / (1 + Math.exp(net_in3));
            net_in4 = 1 / (1 + Math.exp(net_in4));
            net_in5 = 1 / (1 + Math.exp(net_in5));

            output1.set(net_in1, maxTemp[i + 3]);
            output2.set(net_in2, minTemp[i + 3]);
            output3.set(net_in3, wind[i + 3]);
            output4.set(net_in4, humidity[i + 3]);
            output5.set(net_in5, rain[i + 3]);

            outputs[0] = output1;
            outputs[1] = output2;
            outputs[2] = output3;
            outputs[3] = output4;
            outputs[4] = output5;

            updateWeights(inputs, hiddens, outputs);
                       
        } 
        
        output1.value = denormalize(output1.value, maxtemp);
        output2.value = denormalize(output2.value, mintemp);
        output3.value = denormalize(output3.value, wind_ori);
        output4.value = denormalize(output4.value, humidity_ori);
        output5.value = denormalize(output5.value, rain_ori);

        output1.target = denormalize(output1.target, maxtemp);
        output2.target = denormalize(output2.target, mintemp);
        output3.target = denormalize(output3.target, wind_ori);
        output4.target = denormalize(output4.target, humidity_ori);
        output5.target = denormalize(output5.target, rain_ori);
        
        //System.out.println(input1.weight);
        //System.out.println(input3.weight);
        //System.out.println(hidden1.weight[0]);
        //System.out.println(hidden2.weight[0]);
        //System.out.println(hidden3.weight[0]);
        System.out.println("Error percentage for max temperature: "
                + ((Math.abs(output1.target - output1.value)) / (output1.target)));
        System.out.println("Error percentage for min temperature: "
                + ((Math.abs(output2.target - output2.value)) / (output2.target)));
        System.out.println("Error percentage for wind speed: "
                + ((Math.abs(output3.target - output3.value)) / (output3.target)));
        System.out.println("Error percentage for humidity: "
                + ((Math.abs(output4.target - output4.value)) / (output4.target)));
        System.out.println("Error percentage for precipitation: "
                + ((Math.abs(output5.target - output5.value)) / (output5.target)));
        System.out.println("Maximum temperature predicted at the end of training: " + output1.value + " versus target value: " + output1.target);        
        System.out.println("Minimum temperature predicted at the end of training: " + output2.value + " versus target value: " + output2.target);
        System.out.println("Wind speed predicted at the end of training: " + output3.value + " versus target value: " + output3.target);
        System.out.println("Humidity predicted at the end of training: " + output4.value + " versus target value: " + output4.target);
        System.out.println("Precipitation predicted at the end of training: " + output5.value + " versus target value: " + output5.target);
        
    }

    public static double[] normalize(int[] array) {

        int min = array[0];
        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            } else if (array[i] > max) {
                max = array[i];
            }
        }

        double[] normalizedArray = new double[array.length];

        for (int i = 0; i < array.length; i++) {
            normalizedArray[i] = (double) (array[i] - min) / (max - min);
        }
        //System.out.println("max is " + max);
        //System.out.println("min is " + min);
        return normalizedArray;
    }

    public static double[] normalize(double[] array) {

        double min = array[0];
        double max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            } else if (array[i] > max) {
                max = array[i];
            }
        }
        
        double[] normalizedArray = new double[array.length];

        for (int i = 0; i < array.length; i++) {
            normalizedArray[i] = (array[i] - min) / (max - min);
        }
        //System.out.println("max is " + max);
        //System.out.println("min is " + min);
        return normalizedArray;
    }
    
    public static double denormalize(double value, int[] array) {

        int min = array[0];
        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            } else if (array[i] > max) {
                max = array[i];
            }
        }
        
        value = value*(max-min) + min;
        return value;
    }
    
    public static double denormalize(double value, double[] array) {

        double min = array[0];
        double max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            } else if (array[i] > max) {
                max = array[i];
            }
        }
        
        value = value*(max-min) + min;
        return value;
    }

    // Backpropagation weights updating with sum of square error
    public static void updateWeights(Input[] inputs, Hidden[] hiddens, Output[] outputs) {
        
        double learningRate = 2.0;

        int l = 0;
        for (int i = 0; i < inputs.length; i += 5) {           
    
            for (int k = 0; k < 5; k++) {
                double sum_of_error = 0.0;
                for (int j = 0; j < outputs.length; j++) {
                    double a = -(outputs[j].target - outputs[j].value);
                    double b = outputs[j].value * (1 - outputs[j].value);
                    double c = hiddens[l].weight[j];
                    sum_of_error += a * b * c;
                }
                double d = hiddens[l].value * (1 - hiddens[l].value);
                double e = inputs[k].value;

                double delta_w = sum_of_error * d * e;
                inputs[k].weight -= learningRate * delta_w;                
            }
            l++;
        }

        for (int i = 0; i < outputs.length; i++) {

            for (int j = 0; j < hiddens.length; j++) {

                double a = -(outputs[i].target - outputs[i].value);
                double b = outputs[i].value * (1 - outputs[i].value);
                double c = hiddens[j].value;
                double delta_w = a * b * c;
                hiddens[j].weight[i] -= learningRate * delta_w;
            }
        }
    }
}
