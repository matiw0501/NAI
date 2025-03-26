import java.util.Arrays;

public class Perceptron {


    private final String positive;
    private static final String under = "else";
    double[] weight;
    double threshold;

    public Perceptron(double[] weight, double threshold, String decision) {
        this.weight = weight;
        this.threshold = threshold;
        this.positive = decision.trim();
    }

    public String Compute(double[] inputs) {


        if (weight.length != inputs.length) {
            throw new IllegalArgumentException("Error: weight length does not match input length!");
        }

        double sum = 0;
        for (int i = 0; i < inputs.length; i++) {
            sum += (inputs[i] * weight[i]);
        }
        if (sum >= threshold) {
            return positive;
        } else {
            return under;
        }
    }
    public int counter=0;
    public void Learn(double[] inputs, String decision) {

        if (weight.length != inputs.length) {
            throw new IllegalArgumentException("Error: weight length does not match input length!");
        }


        while (!(Compute(inputs).equals(decision) || (!decision.equals(positive) && Compute(inputs).equals(under)))) {
            counter=counter+1;
            double[] tempInputs = Arrays.copyOf(inputs, inputs.length);

            int d = 0;
            int y = 1;

            if (decision.equals(positive)) {
                d = 1;
                y = 0;
            }
            for (int i = 0; i < inputs.length; i++) {
                double delta = (d - y) * tempInputs[i];
                weight[i] += delta;
            }
        }
    }

    public boolean Test(double[] inputs, String decision) {
        if (weight.length != inputs.length) {
            throw new IllegalArgumentException("Error: weight length does not match input length!");
        }

        return ((Compute(inputs).equals(decision) || (!decision.equals(positive) && Compute(inputs).equals(under))));

    }

    @Override
    public String toString() {
        return "Perceptron{" + "weight=" + Arrays.toString(weight) + ", threshold=" + threshold + '}';
    }
}
