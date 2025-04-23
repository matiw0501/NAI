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
            boolean changed = false;

            double probability = (double) listOfVectors.vectors.stream()
                    .filter(v -> v.name.equals(label))
                    .count() / listOfVectors.vectors.size();

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
                if(i!=0) {
                    if (noSmoothProb > 0) {
                        System.out.printf("kolumna %d: bez wygładzania = %.8f%n", i, noSmoothProb);
                        probability *= noSmoothProb;

                    }
                    if (noSmoothProb == 0) {
                        double smoothedProb = (matchingCount + 1.0) / (totalLabelCount + distinctAttrValues);
                        System.out.printf("kolumna %d: bez wygładzania = 0.00000000 -> wygładzanie = %.8f%n", i, smoothedProb);
                        probability *= smoothedProb;
                        changed = true;
                    }
                }
                if(i==columns.size()-1 && !changed || i==columns.size()-1 && noSmoothProb ==0) {
                    double smoothedProb = (matchingCount + 1.0) / (totalLabelCount + distinctAttrValues);
                    System.out.printf("kolumna %d: bez wygładzania = 0.00000000 -> wygładzanie = %.8f%n", 0, smoothedProb);
                    probability *= smoothedProb;
                }
                else if(i==columns.size()-1) {
                    System.out.printf("kolumna %d: bez wygładzania = %.8f%n", 0, noSmoothProb);
                    probability *= noSmoothProb;
                }

            }

            if (probability > bestProb || resultName.isEmpty()) {
                bestProb = probability;
                resultName = label;
            }
            System.out.printf("%.20f%n", bestProb);
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