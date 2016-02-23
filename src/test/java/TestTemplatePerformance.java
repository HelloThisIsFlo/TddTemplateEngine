import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestTemplatePerformance {

    Template template;

    @Before
    public void setUp() throws Exception {
        // Generated with python script
        String templateText = "${Lorem} ipsum dolor sit amet ${consectetur} adipiscing elit Vestibulum odio ${odio}" +
                " ornare ultricies enim suscipit, ${consectetur} finibus ipsum. Vestibulum egestas ${dictum} urna, " +
                "sed vehicula metus ${feugiat} in. Praesent eu magna ${et} nulla tempus mollis. Vivamus ${lobortis}" +
                " consectetur nisi sed molestie. ${Phasellus} ut lorem sem. Mauris ${leo} sem, euismod ac imperdiet " +
                "${vel} vulputate id sapien. Nunc ${malesuada} eros venenatis eleifend pulvinar, ${purus} tellus " +
                "scelerisque arcu, a ${tempor} erat est vitae neque. ${Suspendisse} potenti. Vivamus justo tortor," +
                " ${bibendum} a vehicula volutpat, pretium ${sit} amet odio. Nunc congue ${faucibus} urna sed imperdiet." +
                " Pellentesque ${habitant} morbi tristique senectus et ${netus} et malesuada fames ac.";

        // Generated and formatted with python script
        String[] variables = new String[] {
                "Lorem",
                "consectetur",
                "odio",
                "consectetur",
                "dictum",
                "feugiat",
                "et",
                "lobortis",
                "Phasellus",
                "leo",
                "vel",
                "malesuada",
                "purus",
                "tempor",
                "Suspendisse",
                "bibendum",
                "sit",
                "faucibus",
                "habitant",
                "netus"
        };

        // Generated and formatted with python script
        String[] values = new String[] {
                "Hello",
                "dolor",
                "amet",
                "adipiscing",
                "Vestibulum",
                "odio",
                "ultricies",
                "suscipit,",
                "finibus",
                "Vestibulum",
                "dictum",
                "sed",
                "metus",
                "in.",
                "eu",
                "et",
                "tempus",
                "Vivamus",
                "consectetur",
                "sed2",
        };

        template = new Template(templateText);
        for (int i = 0; i < variables.length; i++) {
            template.set(variables[i], values[i]);
        }
    }

    @Test
    public void templateWith100WordsAnd20Variables_takesLessThan200ms() throws Exception {
        /*
            This test is non-deterministic but is a great evaluation to monitor the performance of our template engine.
         */
        long expectedDuration = 200L;
        long startTime = System.currentTimeMillis();
        template.evaluate();
        long duration = System.currentTimeMillis() - startTime;

        assertTrue(
                "Rendering template took " + duration + "ms while the target was " + expectedDuration + "ms",
                duration <= expectedDuration
        );

    }
}
