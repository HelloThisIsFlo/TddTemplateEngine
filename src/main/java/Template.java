import java.util.HashMap;
import java.util.Map;

public class Template {

    private final String templateText;
    private Map<String, String> variables;

    private String currentText;

    public Template(String templateText) {
        this.variables = new HashMap<>();
        this.templateText = templateText;
    }

    public void set(String variable, String value) {
        variables.put(variable, value);
    }

    public String evaluate() {
        currentText = templateText;
        for (Map.Entry<String, String> entry: variables.entrySet()) {
            replaceSelectedVariableWithValueInCurrentText(entry.getKey(), entry.getValue());
        }
        return currentText;
    }

    private void replaceSelectedVariableWithValueInCurrentText(String variable, String value) {
        String regex = makeFindRegex(variable);
        currentText = currentText.replaceAll(regex, value);
    }

    private String makeFindRegex(String toFind) {
        return "\\$\\{" + toFind + "\\}";
    }
}
