import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LearningTestRegex {

    String haystack;

    @Before
    public void setUp() throws Exception {
        haystack = "The needle shop sells needles";
    }

    @Test
    public void testHowGroupCountWorks() throws Exception {
        String regex = "(needle)";
        Matcher matcher = Pattern.compile(regex).matcher(haystack);
        int numberOfGroupInRegexNotMatchesInText = 1;
        assertEquals(numberOfGroupInRegexNotMatchesInText, matcher.groupCount());
    }

    @Test
    public void testFindStartAndEnd() throws Exception {
        String regex = "(needle)";
        Matcher matcher = Pattern.compile(regex).matcher(haystack);
        assertTrue(matcher.find());
        assertEquals("Wrong start index for the result of the first find", 4, matcher.start());
        assertEquals("Wrong end index for the result of the first find", 10, matcher.end());

        assertTrue(matcher.find());
        assertEquals("Wrong start index for the result of the second find", 22, matcher.start());
        assertEquals("Wrong end index for the result of the second find", 28, matcher.end());

        assertFalse(matcher.find());
    }

    @Test
    public void testGetResultOfAMatch() throws Exception {
        String regex = "(needle)";
        Matcher matcher = Pattern.compile(regex).matcher(haystack);
        assertTrue(matcher.find());
        assertEquals("needle", matcher.group());
    }
}
