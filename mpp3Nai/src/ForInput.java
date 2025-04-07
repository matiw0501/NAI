import java.io.IOException;
import java.nio.file.*;
import java.text.Normalizer;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ForInput {

    public static List<PerceptronForLanguages> getLearnedPerceptrons(String stringPath)  {
        Path path  = Paths.get(stringPath);
        List<PerceptronForLanguages> perceptrons = new ArrayList<>();
        Map<String, List<String>> file = new HashMap<>();


        try {
            Files.walk(path).filter(e -> Files.isDirectory(e) && !e.equals(path)).forEach(
                    dir -> {
                            perceptrons.add( new PerceptronForLanguages(dir.getFileName().toString()));
                        try {

                               List<String> list = new ArrayList<>();
                            try {
                                Files.walk(dir).filter(Files::isRegularFile).forEach(
                                        fileName -> {


                                            try {

                                                list.add(Normalizer.normalize(((Files.readString(fileName))
                                                        .replace("ł", "l").replace("Ł", "L")
                                                        .replace("ß", "ss")
                                                        .replace("ø", "o").replace("Ø", "O")
                                                        .replace("æ", "ae").replace("Æ", "AE")
                                                        .replace("ı", "i").replace("İ", "I")
                                                        .replace("ț", "t").replace("Ț", "T")
                                                        .replace("ș", "s").replace("Ș", "S")), Normalizer.Form.NFKD).replaceAll("[^\\p{ASCII}]", ""));



                                              //  list.add(String.valueOf(Files.readAllLines(fileName)));


                                            } catch (IOException ex) {
                                                throw new RuntimeException(ex);
                                            }

                                                });
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                            file.put(dir.getFileName().toString(), list);
                                    }

                        catch (Exception exception){
                            System.out.println(exception.toString());
                        }
                        });
                    }
                        catch (Exception a){
                            a.printStackTrace();
                        }

//moze
        for (int i = 0; i < 10; i++) {


            perceptrons.forEach(perceptron -> {

                AtomicInteger all = new AtomicInteger(0);
                AtomicInteger correct = new AtomicInteger(0);

                do {
                    all.set(0);
                    correct.set(0);
                    file.forEach((k, v) -> {
                        for (String l : v) {
                            String expected = k.equals(perceptron.positive) ? perceptron.positive : "else";
                            all.incrementAndGet();
                            perceptron.Learn(getRelativeAmount(l), expected);
                            if (perceptron.Test(getRelativeAmount(l), expected)) {
                                correct.incrementAndGet();
                            }

                        }


                    });
                }
                while (all.get() != correct.get());
            });
        }

        return perceptrons;
    }


    public static double [] getRelativeAmount(String text){
        double [] alphabet= new double [26];
        int counter=0;
        Arrays.fill(alphabet, 0);
        for (int i = 0; i < text.length(); i++) {
            char c = text.toLowerCase().charAt(i);
            if (c >= 'a' && c<='z') {
                alphabet[c-'a']++;
                counter++;
            }
        }
        if (counter != 0) {
            for (int i = 0; i < alphabet.length; i++) {
                alphabet[i] /= counter;
            }
        }


        return alphabet;
    }







}
