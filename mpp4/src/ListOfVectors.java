import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class ListOfVectors {
    List<VectorProject> vectors;
    LinkedHashSet<String> setOfNames = new LinkedHashSet<>();

    public String accuracy(ListOfVectors testList) {
        int correct = 0;
        List<String> labels = new ArrayList<>(this.setOfNames);
        int[][] matrix = new int[labels.size()][labels.size()];

        for (VectorProject vector : testList.getVectors()) {
            String predicted = vector.goThrough(this);
            String actual = vector.name;

            int actualIndex = labels.indexOf(actual);
            int predictedIndex = labels.indexOf(predicted);

            if (predicted.equals(actual)) {
                correct++;
            }


            if (actualIndex >= 0 && predictedIndex >= 0) {
                matrix[actualIndex][predictedIndex]++;
            }
        }

        int total = testList.getVectors().size();
        double accuracy = 100.0 * correct / total;


        StringBuilder sb = new StringBuilder();
        sb.append("\n Ilość poprawnie zaklasyfikowanych: ").append(correct).append("/").append(total)
                .append("\nCzyli dokładność w procentach: ").append(String.format("%.2f", accuracy)).append("%\n");

        sb.append("--- Macierz omyłek ---\n");
        sb.append(String.format("%20s", ""));
        for (String label : labels) {
            sb.append(String.format("%20s", label));
        }
        sb.append("\n");

        for (int i = 0; i < labels.size(); i++) {
            sb.append(String.format("%20s", labels.get(i)));
            for (int j = 0; j < labels.size(); j++) {
                sb.append(String.format("%20s", matrix[i][j]));
            }
            sb.append("\n");
        }

        return sb.toString();
    }


    public ListOfVectors() {
        vectors = new ArrayList<>();
    }

    public LinkedHashSet<String> getSetOfNames() {
        return setOfNames;
    }

    public void add(VectorProject vector) {
        vectors.add(vector);
        setOfNames.add(vector.name);
    }


    public List<VectorProject> getVectors() {
        return vectors;
    }


    @Override
    public String toString() {
        return "ListOfVectors{" +
                "vectors=" + vectors +
                '}';
    }
}
