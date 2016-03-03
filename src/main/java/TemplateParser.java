import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateParser {

    private static final String regexVariable = "\\$\\{[^}]*\\}";

    public List<String> parse(String template) {
        List<String> segments = new ArrayList<>();
        int index = collectSegmentsAndReturnEndLastMatch(segments, template);
        addTail(segments, template, index);
        addEmptyStringIfTemplateWasEmpty(segments);
        return segments;
    }

    private int collectSegmentsAndReturnEndLastMatch(List<String> segments, String src) {
        Matcher matcher = Pattern.compile(regexVariable).matcher(src);
        int index = 0;
        while (matcher.find()) {
            addPrecedingPlainText(segments, src, matcher, index);
            addVariable(segments, src, matcher);
            index = matcher.end();
        }
        return index;
    }

    private void addPrecedingPlainText(List<String> segments, String src, Matcher matcher, int index) {
        if (matcher.start() != index) {
            segments.add(src.substring(index, matcher.start()));
        }
    }

    private void addVariable(List<String> segments, String src, Matcher matcher) {
        segments.add(src.substring(matcher.start(), matcher.end()));
    }

    private void addTail(List<String> segments, String src, int index) {
        if (index < src.length()) {
            segments.add(src.substring(index));
        }
    }

    private void addEmptyStringIfTemplateWasEmpty(List<String> segments) {
        if (segments.isEmpty()) {
            segments.add("");
        }
    }
}
