package root;

import root.parsing.Segment;
import root.parsing.TemplateParser;

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
        List<Segment> segments = parser.parse(templateText);
        return concatenate(segments);
    }

    private String concatenate(List<Segment> segments) throws MissingValueException {
        StringBuilder result = new StringBuilder();
        for (Segment segment : segments) {
            append(segment, result);
        }
        return result.toString();
    }

    private void append(Segment segment, StringBuilder result) throws MissingValueException {
        String evaluated = segment.evaluate(variables);
        result.append(evaluated);
    }
}
