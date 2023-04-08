package com.example.coco_spring.Auth;

import com.example.coco_spring.Entity.Role;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class RoleValidator implements ConstraintValidator<ValidRole, Role> {

    @Override
    public boolean isValid(Role value, ConstraintValidatorContext context) {
        return value != null && Arrays.asList(Role.values()).contains(value);
    }

}
