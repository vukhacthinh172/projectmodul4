package ra.demoprojectmodul4.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = PhoneUniqueValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PhoneUnique {
    String message() default "phone is exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
