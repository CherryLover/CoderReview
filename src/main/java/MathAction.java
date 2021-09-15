import annotation.*;

public interface MathAction {

    @Add
    float add(float a, float b);

    @Minus
    float minus(float a, float b);

    @MultiTime
    float multiTime(float a, float b);

    @Division
    float division(float a, float b);
}
