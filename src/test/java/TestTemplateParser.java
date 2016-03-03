import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestTemplateParser {


    @Test
    public void emptyTemplate_renderAsEmptyString() throws Exception {
        List<String> segments = parse("");
        assertSegments(segments, "");
    }

    private List<String> parse(String toParse) {
        return new TemplateParser().parse(toParse);
    }

    private void assertSegments(List<?> actual, Object... expected) {
        assertEquals(Arrays.asList(expected), actual);
    }

    @Test
    public void plainText_returnText() throws Exception {
        String plainText = "Hello this is plain text";
        List<String> segments = parse(plainText);
        assertSegments(segments, plainText);
    }

    @Test
    public void templateWithSingleVariable_renderAllTheDifferentSegments() throws Exception {
        List<String> segments = parse("Hello ${name} how are you ?");
        assertSegments(segments, "Hello ", "${name}", " how are you ?");
    }

    @Test
    public void templateWithMultipleVariables_renderAllTheDifferentSegments() throws Exception {
        List<String> segments = parse("Hello ${name}, what is your plan for ${day} ? ");
        assertSegments(segments, "Hello ", "${name}", ", what is your plan for ", "${day}", " ? ");
    }

    @Test
    public void templateWithNoPrecedingPlainText() throws Exception {
        List<String> segments = parse("${test} no preceding text");
        assertSegments(segments, "${test}", " no preceding text");
    }

    @Test
    public void templateWithNoTail() throws Exception {
        List<String> segments = parse("test with no tail ${variable}");
        assertSegments(segments, "test with no tail ", "${variable}");
    }
}
