package com.health.SchoolHealth.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

        private Pattern pattern;
        private Matcher matcher;
        private static final String EMAIL_PATTERN = "^$|(^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$)";
        @Override
        public void initialize(ValidEmail constraintAnnotation) {
        }
        @Override
        public boolean isValid(String email, ConstraintValidatorContext context){

//            context.buildConstraintViolationWithTemplate("Невалиден e-mail.");
            if (validateEmail(email)) {
                System.out.println("VALID EMAIL");
                return true;
            }
            //context.disableDefaultConstraintViolation();
            //context.buildConstraintViolationWithTemplate("Невалиден e-mail.").addConstraintViolation();;
            return false;
        }

        private boolean validateEmail(String email) {
            pattern = Pattern.compile(EMAIL_PATTERN);
            System.out.println("PATERN " + pattern +"email " + email);
            matcher = pattern.matcher(email);
            return matcher.matches();
        }

    }