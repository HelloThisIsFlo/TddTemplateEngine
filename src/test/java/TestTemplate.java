import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestTemplate {

    @Test
    public void oneVariable_returnFormattedText() throws Exception {
        Template template = new Template("Hello, ${name}");
        template.set("name", "Reader");
        assertEquals("Hello, Reader", template.evaluate());
    }

    @Test
    public void differentTemplate_returnFormattedText() throws Exception {
        Template template = new Template("Hi, ${name}");
        template.set("name", "Someone else");
        assertEquals("Hi, Someone else", template.evaluate());
    }

    @Test
    public void multipleVariables_returnFormatterText() throws Exception {
        Template template = new Template("${one}, ${two}, ${three}");
        template.set("one", "1");
        template.set("two", "2");
        template.set("three", "3");
        assertEquals("1, 2, 3", template.evaluate());
    }

    @Test
    public void unknownVariable_ignoresIt() throws Exception {
        Template template = new Template("Hello, ${name}");
        template.set("name", "Reader");
        template.set("doesNotExist", "Hi");
        assertEquals("Hello, Reader", template.evaluate());
    }
}
