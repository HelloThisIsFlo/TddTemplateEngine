import java.util.HashMap;
import java.util.Map;

public class Template {

    private final String templateText;
    private Map<String, String> variables;

    public Template(String templateText) {
        this.variables = new HashMap<>();
        this.templateText = templateText;
    }

    public void set(String variable, String value) {
        variables.put(variable, value);
    }

    public String evaluate() throws MissingValueException {
        String result = replaceVariablesWithValues();
        checkForMissingValuesAfterTemplateEvaluated(result);
        return result;
    }

    private String replaceVariablesWithValues() {
        String result = templateText;
        for (Map.Entry<String, String> entry: variables.entrySet()) {
            String variable = entry.getKey();
            String value = entry.getValue();
            String regex = makeFindRegex(variable);
            result = result.replaceAll(regex, value);
        }
        return result;
    }

    private void checkForMissingValuesAfterTemplateEvaluated(String evaluatedTemplate) throws MissingValueException {
        if (variablesNotMatchedLeft(evaluatedTemplate)) {
            throw new MissingValueException();
        }
    }

    private boolean variablesNotMatchedLeft(String text) {
        return text.matches(".*\\$\\{.+\\}.*");
    }

    private String makeFindRegex(String toFind) {
        return "\\$\\{" + toFind + "\\}";
    }
}
