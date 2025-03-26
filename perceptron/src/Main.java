import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        List<Map.Entry<double[], String>> treningFile = new ArrayList<>();
        String treningPath;
        Perceptron perceptron;

        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter trening path:     (-1 to use deafult)");
            treningPath = sc.nextLine();
            if (treningPath.equals("-1")) {
                treningPath = "iris_training.txt";
            }
            treningFile = getEntries(treningPath);


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("Now we enter perceptron: \n Enter weights {weight1, weight2, ...} [enter]");
        double[] weights = Arrays.stream(sc.nextLine().trim().split("\\s+"))
                .map(numb -> numb.replace(",", ".")).mapToDouble(Double::parseDouble).toArray();
        System.out.println("Enter threshold {number,numer} [enter]");
        double threshold = sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter what is your name for classification (if deafult -1)");

        String name = sc.nextLine();

        if (name.equals("-1")) {
            name = "Iris-setosa";
        }


        perceptron = new Perceptron(weights, threshold, name);


        final int[] checkedTrein = {0};
        final int[] allTrein = {treningFile.size()};
        int a=0;

        while (checkedTrein[0] != allTrein[0]) {
            checkedTrein[0] = 0;

            treningFile.forEach(
                    entry -> {
                        perceptron.Learn(entry.getKey(), entry.getValue());
                    }
            );
            treningFile.forEach(
                    entry -> {
                        if (perceptron.Test(entry.getKey(), entry.getValue()))
                            checkedTrein[0]++;
                    }
            );


        }
        System.out.println(perceptron);

        boolean apul = true;
        while (apul) {
            System.out.println("Tell me what to do: \n  {1} Insert test file to get how many of them is correct\n" +
                    "  {2} Insert vector manually to get expected classification\n " +
                    "{exit} Exit and stop the program");
            String n = sc.nextLine().trim();
            switch (n) {
                case "1":
                    System.out.println("Insert path {path} [enter] (-1 to use deafult)");
                    String testPath = sc.nextLine().trim();
                    List<Map.Entry<double[], String>> testFile;

                    try {
                        final int[] all = {0};
                        final int[] corr = {0};
                        if (testPath.equals("-1")) {
                            testPath = "iris_test.txt";
                        }
                        testFile = getEntries(testPath);

                        testFile.forEach(
                                entry -> {
                                    if (perceptron.Test(entry.getKey(), entry.getValue()))
                                        corr[0]++;
                                    all[0]++;

                                }
                        );
                        System.out.println("--------------> In your file: " + corr[0] + " corrects vectors for " + all[0] + " vectors which gave " + ((double) ((Math.round(((float) corr[0] / all[0]) * 100)))) + "%");


                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2":
                    System.out.println("Insert vector {number, number, ...} [enter]");
                    double [] val= Arrays.stream(sc.nextLine().trim().split("\\s+"))
                            .map(numb -> numb.replace(",", ".")).mapToDouble(Double::parseDouble).toArray();
                    System.out.println("--------------> Expected classification is :" + perceptron.Compute(val));
                    break;
                case "exit":
                    apul = false;
                    break;
                default:
                    System.out.println("--------------> Invalid input");
                    break;
            }
        }
    }

    private static List<Map.Entry<double[], String>> getEntries(String treningPath) throws IOException {
        return Files.readAllLines(Paths.get(treningPath)).stream().map(l -> l.trim().split("\\s+"))
                .map(part -> Map.entry(
                        Arrays.stream(part, 0, part.length - 1)
                                .mapToDouble(numb -> Double.parseDouble(numb.replace(",", ".")))
                                .toArray(), part[part.length - 1]
                )).toList();
    }
}
