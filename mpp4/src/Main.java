import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        ListOfVectors train = new ListOfVectors();
        try {
             train = getVectors("iris_training.txt");
            System.out.println(train);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        System.out.println(train.getSetOfNames());
    }


    public static ListOfVectors getVectors(String path) throws IOException {
        ListOfVectors vectors = new ListOfVectors();
        List<String> lines = Files.readAllLines(Paths.get(path));
        for (String line : lines) {
            String[] parts = line.split("\\t");
            List<Double> values = new ArrayList<>();
            for (int i = 0; i < parts.length - 1; i++) {
                values.add(Double.parseDouble(parts[i].replace(",", ".")));
            }
            String name = parts[parts.length - 1];
            vectors.add(new VectorProject(values, name));
        }
        return vectors;
    }
}