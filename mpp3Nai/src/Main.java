import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ForInput.getLearnedPerceptrons("languages/");

        String path = "languages/Polish/Warszawa.txt";
        List<String> a = new ArrayList<String>();
        try {
        a = Files.readAllLines(Paths.get(path));


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        a.forEach(e -> {
           String temp = e
                    .replace("ł", "l").replace("Ł", "L")
                    .replace("ß", "ss")
                    .replace("ø", "o").replace("Ø", "O")
                    .replace("æ", "ae").replace("Æ", "AE")
                    .replace("ı", "i").replace("İ", "I")
                    .replace("ț", "t").replace("Ț", "T")
                    .replace("ș", "s").replace("Ș", "S");
            e = Normalizer.normalize(temp, Normalizer.Form.NFKD).replaceAll("[^\\p{ASCII}]", "");
        });

    }

}
