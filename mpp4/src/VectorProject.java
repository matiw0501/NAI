import java.util.LinkedHashSet;
import java.util.List;

public class VectorProject {
    //vector for storage x columns of double and one string decision
    String name;
    List<Double> columns;

    @Override
    public String toString() {
        return "Vector{" +
                "name='" + name + '\'' +
                ", columns=" + columns +
                '}';
    }


    public VectorProject(List<Double> columns, String name) {
        this.name = name;
        this.columns = columns;
    }

    public void goThrough(List<VectorProject> vectorProjects) {

        for (VectorProject v : vectorProjects) {
            check(v);
        }
    }

    public boolean check(VectorProject vectorProject) {

        return true;

    }

}
