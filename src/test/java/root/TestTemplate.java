package root;

import root.MissingValueException;
import root.Template;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

    @Test
    public void missingVariable_throwException() throws Exception {
        try {
            new Template("${foo}").evaluate();
            fail("MissingValueException expected but nothing thrown");
        } catch (MissingValueException expected) {
            assertEquals("No value for ${foo}", expected.getMessage());
        }
    }

    @Test
    public void valueFormattedAsVariable_variableGetProcessedOnlyOnce() throws Exception {
        template.set("one", "${one}");
        template.set("two", "${two}");
        template.set("three", "${three}");
        assertTemplateEvaluatesTo("${one}, ${two}, ${three}");
    }

    private void assertTemplateEvaluatesTo(String expected) throws Exception {
        assertEquals(expected, template.evaluate());
    }
}
