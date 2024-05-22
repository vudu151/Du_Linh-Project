package org.example.film.commons.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.film.models.dtos.RegisterDto;

public class MatchPasswordValidator implements ConstraintValidator<MatchPassword, RegisterDto> {

    @Override
    public boolean isValid(RegisterDto registerDto, ConstraintValidatorContext constraintValidatorContext) {
        return registerDto != null && registerDto.getPassword() != null && registerDto.getRePassword().equals(registerDto.getRePassword());
    }
}
