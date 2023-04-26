package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ValidateInputTest {
    @Test
    public void whenInvalidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"one", "1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Select: ");
        assertThat(selected).isEqualTo(1);
    }

    @Test
    public void whenCorrectInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"1"}
        );
        Input input = new ValidateInput(out, in);
        int selected = input.askInt("Select: ");
        assertThat(selected).isEqualTo(1);
    }

    @Test
    public void whenMultiCorrectInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"1", "3", "5"}
        );
        Input input = new ValidateInput(out, in);
        int selected = input.askInt("Select: ");
        assertThat(selected).isEqualTo(1);

        selected = input.askInt("Select: ");
        assertThat(selected).isEqualTo(3);

        selected = input.askInt("Select: ");
        assertThat(selected).isEqualTo(5);
    }

    @Test
    public void whenMinus1Input() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"-1"}
        );
        Input input = new ValidateInput(out, in);
        int selected = input.askInt("Select: ");
        assertThat(selected).isEqualTo(-1);
    }
}