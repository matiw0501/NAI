import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        ListOfVectors train = new ListOfVectors();
        ListOfVectors test = new ListOfVectors();
        try {
            train = getVectors("iris_training.txt");
            test = getVectors("iris_test.txt");
            System.out.println(train.accuracy(test));
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Podaj 4 atrybuty (oddzielone spacją) lub wpisz 'stop':");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("stop")) break;

            String[] parts = input.trim().split(" ");
            if (parts.length != 4) {
                System.out.println("Podaj dokładnie 4 liczby.");
                continue;
            }

            List<Double> values = new ArrayList<>();
            for (String part : parts) values.add(Double.parseDouble(part));

            VectorProject userVector = new VectorProject(values, "");
            System.out.println("Klasyfikacja: " + userVector.goThrough(train));
        }
    }

    public static ListOfVectors getVectors(String path) throws IOException {
        ListOfVectors vectors = new ListOfVectors();
        List<String> lines = Files.readAllLines(Paths.get(path));
        for (String line : lines) {
            String[] parts = line.split("\\t");
            List<Double> values = new ArrayList<>();
            for (int i = 0; i < parts.length - 1; i++) {
                values.add(Double.parseDouble(parts[i].replace(",", ".").trim()));
            }
            String name = parts[parts.length - 1].trim();
            VectorProject vector = new VectorProject(values, name);
            vectors.add(vector);
        }
        return vectors;
    }
}
