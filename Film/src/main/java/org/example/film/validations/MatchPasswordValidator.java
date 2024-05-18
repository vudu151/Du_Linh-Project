package org.example.film.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.film.dtos.AccountsDto;

public class MatchPasswordValidator implements ConstraintValidator<MatchPassword, AccountsDto> {

    @Override
    public boolean isValid(AccountsDto accountsDto, ConstraintValidatorContext constraintValidatorContext) {
        return accountsDto != null && accountsDto.getPassword() != null && accountsDto.getRePassword().equals(accountsDto.getRePassword());
    }
}
