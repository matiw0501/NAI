import java.util.List;

public class VectorProject {
    String name;
    List<Double> columns;

    public VectorProject(List<Double> columns, String name) {
        this.name = name;
        this.columns = columns;
    }

    public String goThrough(ListOfVectors listOfVectors) {
        String resultName = "";
        double bestProb = -1;

        for (String label : listOfVectors.getSetOfNames()) {
            double prior = (double) listOfVectors.vectors.stream()
                    .filter(v -> v.name.equals(label))
                    .count() / listOfVectors.vectors.size();

            double probability = prior;

            System.out.println("\n---    ---");

            for (int i = 0; i < columns.size(); i++) {
                final int index = i;
                double attrValue = columns.get(i);

                long matchingCount = listOfVectors.vectors.stream()
                        .filter(v -> v.name.equals(label))
                        .filter(v -> Math.abs(v.columns.get(index) - attrValue) < 0.01)
                        .count();

                long totalLabelCount = listOfVectors.vectors.stream()
                        .filter(v -> v.name.equals(label))
                        .count();

                long distinctAttrValues = listOfVectors.vectors.stream()
                        .map(v -> v.columns.get(index)).distinct().count();

                double noSmoothProb = totalLabelCount == 0 ? 0 : (double) matchingCount / totalLabelCount;

                if (noSmoothProb > 0) {
                    System.out.printf("kolumna %d: bez wygładzania = %.8f%n", i, noSmoothProb);
                    probability *= noSmoothProb;
                } else {
                    double smoothedProb = (matchingCount + 1.0) / (totalLabelCount + distinctAttrValues);
                    System.out.printf("kolumna %d: bez wygładzania = 0.00000000 -> wygładzanie = %.8f%n", i, smoothedProb);
                    probability *= smoothedProb;
                }
            }

            if (probability > bestProb || resultName.equals("")) {
                bestProb = probability;
                resultName = label;
            }
            System.out.println(String.format("%.20f", bestProb));
        }
        return resultName;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "name='" + name + '\'' +
                ", columns=" + columns +
                '}';
    }
}