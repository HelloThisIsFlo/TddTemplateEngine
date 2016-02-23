import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestTemplate {

    Template template;

    @Before
    public void setUp() throws Exception {
        template = new Template("${one}, ${two}, ${three}");
        template.set("one", "1");
        template.set("two", "2");
        template.set("three", "3");
    }

    @Test
    public void multipleVariables_returnFormatterText() throws Exception {
        assertTemplateEvaluatesTo("1, 2, 3");
    }

    @Test
    public void unknownVariable_ignoresIt() throws Exception {
        template.set("doesNotExist", "Hi");
        assertTemplateEvaluatesTo("1, 2, 3");
    }

    @Test (expected = MissingValueException.class)
    public void missingVariable_throwException() throws Exception {
        new Template("${foo}").evaluate();
    }

    private void assertTemplateEvaluatesTo(String expected) throws Exception {
        assertEquals(expected, template.evaluate());
    }
}
