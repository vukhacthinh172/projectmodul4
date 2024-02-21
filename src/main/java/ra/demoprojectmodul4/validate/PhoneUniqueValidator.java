package ra.demoprojectmodul4.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.demoprojectmodul4.serviceimpl.CustomerServiceImpl;

@Component
public class PhoneUniqueValidator implements ConstraintValidator<PhoneUnique, String> {
    @Autowired
    private CustomerServiceImpl customerService;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !customerService.isPhoneExits(value);
    }
}
