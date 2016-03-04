package root.parsing;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestTemplateParser {


    @Test
    public void emptyTemplate_renderAsEmptyString() throws Exception {
        List<Segment> segments = parse("");
        assertSegments(segments, new PlainText(""));
    }

    private List<Segment> parse(String toParse) {
        return new TemplateParser().parse(toParse);
    }

    private void assertSegments(List<?> actual, Object... expected) {
        assertEquals(Arrays.asList(expected), actual);
    }

    @Test
    public void plainText_returnText() throws Exception {
        String plainText = "Hello this is plain text";
        List<Segment> segments = parse(plainText);
        assertSegments(segments, new PlainText(plainText));
    }

    @Test
    public void templateWithSingleVariable_renderAllTheDifferentSegments() throws Exception {
        List<Segment> segments = parse("Hello ${name} how are you ?");
        assertSegments(segments,
                new PlainText("Hello "),
                new Variable("name"),
                new PlainText(" how are you ?")
        );
    }

    @Test
    public void templateWithMultipleVariables_renderAllTheDifferentSegments() throws Exception {
        List<Segment> segments = parse("Hello ${name}, what is your plan for ${day} ? ");
        assertSegments(segments,
                new PlainText("Hello "),
                new Variable("name"),
                new PlainText(", what is your plan for "),
                new Variable("day"),
                new PlainText(" ? ")
        );
    }

    @Test
    public void templateWithNoPrecedingPlainText() throws Exception {
        List<Segment> segments = parse("${test} no preceding text");
        assertSegments(segments,
                new Variable("test"),
                new PlainText(" no preceding text")
        );
    }

    @Test
    public void templateWithNoTail() throws Exception {
        List<Segment> segments = parse("test with no tail ${variable}");
        assertSegments(segments,
                new PlainText("test with no tail "),
                new Variable("variable")
        );
    }

}
