package root.parsing;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateParser {

    private static final String regexVariable = "\\$\\{[^}]*\\}";

    public List<Segment> parse(String template) {
        List<Segment> segments = new ArrayList<>();
        int index = collectSegmentsAndReturnEndLastMatch(segments, template);
        addTail(segments, template, index);
        addEmptyStringIfTemplateWasEmpty(segments);
        return segments;
    }

    private int collectSegmentsAndReturnEndLastMatch(List<Segment> segments, String src) {
        Matcher matcher = Pattern.compile(regexVariable).matcher(src);
        int index = 0;
        while (matcher.find()) {
            addPrecedingPlainText(segments, src, matcher, index);
            addVariable(segments, src, matcher);
            index = matcher.end();
        }
        return index;
    }

    private void addPrecedingPlainText(List<Segment> segments, String src, Matcher matcher, int index) {
        if (matcher.start() != index) {
            String plainText = src.substring(index, matcher.start());
            segments.add(new PlainText(plainText));
        }
    }

    private void addVariable(List<Segment> segments, String src, Matcher matcher) {
        String variableWithDelimiter = src.substring(matcher.start(), matcher.end());
        String variable = extractVariable(variableWithDelimiter);
        segments.add(new Variable(variable));
    }

    private String extractVariable(String segment) {
        return segment.substring(2, segment.length() - 1);
    }

    private void addTail(List<Segment> segments, String src, int index) {
        if (index < src.length()) {
            String tail = src.substring(index);
            segments.add(new PlainText(tail));
        }
    }

    private void addEmptyStringIfTemplateWasEmpty(List<Segment> segments) {
        if (segments.isEmpty()) {
            segments.add(new PlainText(""));
        }
    }
}
