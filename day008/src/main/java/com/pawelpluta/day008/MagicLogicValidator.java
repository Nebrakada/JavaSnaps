package com.pawelpluta.day008;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MagicLogicValidator implements ConstraintValidator<MagicLogicValidation, SampleRequest.NestedRequestData> {
    @Override
    public void initialize(MagicLogicValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(SampleRequest.NestedRequestData nestedRequestData, ConstraintValidatorContext context) {
        return !nestedRequestData.magicLogicFlag() || !nestedRequestData.magicLogicField().isBlank();
    }
}
