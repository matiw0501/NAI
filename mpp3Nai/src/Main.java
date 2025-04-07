import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<PerceptronForLanguages> perceptronForLanguages = ForInput.getLearnedPerceptrons("languages/");
        perceptronForLanguages.forEach(e -> {
            System.out.println(e.positive + e.Test(ForInput.getRelativeAmount("Badania archeologiczne wskazują, że osadnictwo na obszarze zajmowanym przez dzisiejsze Marki występowało już w czasach prehistorycznych. Prace wykopaliskowe doprowadziły m.in. do odnalezienia przy ul. Hallera narzędzi krzemiennych datowanych na 5 tysiąclecie p.n.e. (kultura komornicka – rozwijająca się w okresie mezolitu najwcześniejsza znana kultura na obszarze Polski). Z późniejszych okresów odkryto m.in.: fragmenty ceramiki z epoki brązu, pozostałości osady z I–II w. n.e. oraz studnię z pnia dębowego datowaną na X wiek. Wieś królewska Pod koniec XVI wieku nadano z dóbr królewskich 2 włóki ziemi (ok. 34 hektarów) rodowi Marków (Markowiczów), skąd wzięła początek nazwa miejscowości. Wkrótce Jan i Wojciech Markowicze otrzymali też przywilej królewski na posiadanie młyna na rzeczce"), "Polish"));
        });
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
