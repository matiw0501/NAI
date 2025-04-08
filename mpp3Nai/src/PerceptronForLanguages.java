import java.util.Arrays;

public class PerceptronForLanguages {
    public final String positive;
    private static final String under = "else";
    double[] weight= new double[26];
    double threshold;
    public int counter = 0;

    public PerceptronForLanguages(String decision) {
        Arrays.fill(weight, 0.0);
        this.threshold = 1.0;
        this.positive = decision.trim();
    }

    public String Compute(double[] inputs) {

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

    public boolean Learn(double[] inputs, String decision) {


        String outcome = Compute(inputs);
        if (outcome.equals(decision)) {
            return false;
        }
        while (!(outcome.equals(decision))) {
            counter=counter+1;


            int d = 0;
            int y = 1;

            if (decision.equals(positive)) {
                d = 1;
                y = 0;
            }
            for (int i = 0; i < inputs.length; i++) {
                double delta = (d - y) * inputs[i];
                weight[i] += delta;
            }
            outcome = Compute(inputs);
        }
        return true;
    }

    public boolean Test(double[] inputs, String decision) {

        return Compute(inputs).equals(decision);

    }

    @Override
    public String toString() {
        return "Perceptron{" + "weight=" + Arrays.toString(weight) + ", threshold=" + threshold + ", decision=" + positive+ '}';
    }



}
