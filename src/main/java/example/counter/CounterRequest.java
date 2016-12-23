package example.counter;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CounterRequest {
    @NotNull
    private Integer int1;
    @NotNull
    private Integer int2;
}
