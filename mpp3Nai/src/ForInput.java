import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class ForInput {

    public static List<PerceptronForLanguages> getOneLevelOfPerceptrons(String stringPath) {
        Path path  = Paths.get(stringPath);
        List<PerceptronForLanguages> perceptrons = new ArrayList<PerceptronForLanguages>();


        try {
            Files.walk(path).filter(e -> Files.isDirectory(e) && !e.equals(path)).forEach(
                    dir -> {
                        System.out.println(dir.getFileName());
                        try {
                            Files.walk(dir).filter(Files::isRegularFile).forEach(
                                    file -> {
                                        System.out.println(file.getFileName().toString());

                                    }
                            );
                        }
                        catch (Exception a){
                            a.printStackTrace();
                        }
                    }
            );
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return perceptrons;
    }





}
