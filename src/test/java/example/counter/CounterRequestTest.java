package example.counter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CounterRequestTest {
    
    private static Validator validator;
    
    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    public void testValidationFailsOnNullValues() {
        CounterRequest request = new CounterRequest(null, null);
        Set<ConstraintViolation<CounterRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }
    @Test
    public void testValidationPassesOnNonNullValues() {
        CounterRequest request = new CounterRequest(2, 3);
        Set<ConstraintViolation<CounterRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }
}
