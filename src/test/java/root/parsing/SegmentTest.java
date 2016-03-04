package root.parsing;

import org.junit.Test;
import root.MissingValueException;
import root.Template;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class SegmentTest {

    @Test
    public void plainText_evaluatesAsPlainText() throws Exception {
        Map<String, String> variables = new HashMap<>();
        String text = "abs def";
        Segment plainText = new PlainText(text);
        assertEquals(text, plainText.evaluate(variables));
    }

    @Test
    public void variableText_evaluateByReplaceingTheVariableWithTheValue() throws Exception {
        String variableText = "name";
        String value = "Greg";

        Map<String, String> variables = new HashMap<>();
        variables.put(variableText, value);

        Segment variable = new Variable(variableText);
        assertEquals(value, variable.evaluate(variables));
    }


    @Test
    public void variableText_valueMissing_throwsException() throws Exception {
        String variableText = "name";
        Map<String, String> variables = new HashMap<>();

        Segment variable = new Variable(variableText);

        try {
            variable.evaluate(variables);
            fail("MissingValueException expected but nothing thrown");
        } catch (MissingValueException expected) {
            assertEquals("No value for ${name}", expected.getMessage());
        }
    }



}