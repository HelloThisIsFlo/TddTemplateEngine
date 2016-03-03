import java.util.HashMap;
import java.util.List;
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
        TemplateParser parser = new TemplateParser();
        List<String> segments = parser.parse(templateText);
        return concatenate(segments);
    }

    private String concatenate(List<String> segments) throws MissingValueException {
        StringBuilder result = new StringBuilder();
        for (String segment : segments) {
            append(segment, result);
        }
        return result.toString();
    }

    private void append(String segment, StringBuilder result) throws MissingValueException {
        if (isAVariable(segment)) {
            appendVariable(segment, result);
        } else {
            appendPlainText(segment, result);
        }
    }

    private void appendVariable(String segment, StringBuilder result) throws MissingValueException {
        String variable = extractVariable(segment);
        String value = getValue(variable);
        appendPlainText(value, result);
    }

    private StringBuilder appendPlainText(String segment, StringBuilder result) {
        return result.append(segment);
    }

    private String getValue(String variable) throws MissingValueException {
        if (!variables.containsKey(variable)) {
            throw new MissingValueException("No value for ${" + variable + "}");
        }
        return variables.get(variable);
    }

    private String extractVariable(String segment) {
        return segment.substring(2, segment.length() - 1);
    }

    private boolean isAVariable(String segment) {
        return segment.startsWith("${") && segment.endsWith("}");
    }

    private String makeFindRegex(String toFind) {
        return "\\$\\{" + toFind + "\\}";
    }
}
