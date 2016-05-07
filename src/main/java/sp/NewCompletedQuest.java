package sp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by dawid on 07.05.16.
 */
public class NewCompletedQuest {

    private ArrayList<Input> categoriesInput;

    public ArrayList<Input> getCategoriesInput() {
        return categoriesInput;
    }

    public void setCategoriesInput(ArrayList<Input> categoriesInput) {
        this.categoriesInput = categoriesInput;
    }

    public Collection<String> getCompletedVariant(int index){
        return this.categoriesInput.get(index).getValue().values();
    }
}

class Input{
    private String id;
    private String name;
    private Map<String, String> value;

    Input() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getValue() {
        return value;
    }

    public void setValue(Map<String, String> value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Input{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
