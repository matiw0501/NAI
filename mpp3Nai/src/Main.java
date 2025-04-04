import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ForInput.getOneLevelOfPerceptrons("languages");

        String path = "languages/Polish/Warszawa.txt";
        List<String> a = new ArrayList<String>();
        try {
        a = Files.readAllLines(Paths.get(path));
            System.out.println(a);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        a.forEach(e -> {
            System.out.println(Normalizer.normalize(e, Normalizer.Form.NFKD).replaceAll("[^\\p{ASCII}]", ""));
            // cos z replacem mozna uzyc biblioteki zewnetrznej itd. ale chyba zrob na sztywno zamiane znakow ktorych to nie obsługuje ł ss niemieckie itd.
        });

    }

}
