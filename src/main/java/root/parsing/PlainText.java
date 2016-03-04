package root.parsing;

import java.util.Map;
import java.util.Objects;

public class PlainText implements Segment {

    private final String text;

    public PlainText(String text) {
        this.text = text;
    }

    @Override
    public String evaluate(Map<String, String> variables) {
        return text;
    }

    @Override
    public String toString() {
        return "P:" + text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlainText plainText = (PlainText) o;
        return Objects.equals(text, plainText.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
