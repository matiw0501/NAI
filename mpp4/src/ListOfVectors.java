import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class ListOfVectors {
    List<VectorProject> vectors;
    LinkedHashSet<String> setOfNames= new LinkedHashSet<>();
    public ListOfVectors() {
        vectors = new ArrayList<>();
    }

    public LinkedHashSet<String> getSetOfNames() {
        return setOfNames;
    }

    public void add(VectorProject vector) {
        vectors.add(vector);
        setOfNames.add(vector.name);
    }

    @Override
    public String toString() {
        return "ListOfVectors{" +
                "vectors=" + vectors +
                '}';
    }
}
