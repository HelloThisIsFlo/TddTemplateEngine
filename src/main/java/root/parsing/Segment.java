package root.parsing;

import root.MissingValueException;

import java.util.Map;

public interface Segment {

    String evaluate(Map<String, String> variables) throws MissingValueException;

}
