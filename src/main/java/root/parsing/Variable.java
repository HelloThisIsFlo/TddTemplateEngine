package root.parsing;

import root.MissingValueException;

import java.util.Map;
import java.util.Objects;

public class Variable implements Segment {

    private final String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    @Override
    public String evaluate(Map<String, String> variables) throws MissingValueException{
        if (!variables.containsKey(variable)) {
            throw new MissingValueException("No value for ${" + variable + "}");
        }
        return variables.get(variable);
    }

    @Override
    public String toString() {
        return "V:" + variable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable1 = (Variable) o;
        return Objects.equals(variable, variable1.variable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variable);
    }
}
