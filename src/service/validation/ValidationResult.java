package service.validation;

import main.UserDateBaseReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationResult{

    private boolean errorsExist;
    private ServiceValidationException exception;

    public ValidationResult(Validator validator) {

        if (validator.getErrors() != null && !validator.getErrors().isEmpty()) {
            errorsExist = true;
            exception = new ServiceValidationException("Errors", validator.getErrors());

        }
    }

    public static class Validator implements ObjectBuilder<ValidationResult>  {

        private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        private static final String LOGIN_PATTERN = "^[a-zA-Z][a-zA-Z0-9-_\\.]{2,15}$";
        private static final String PASSWORD_PATTERN = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,20}$"; //одна заглавная, одна строчная, одно число, спец символ, не менее 8 знаков
        private static final String PHONE_PATTERN =  "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
        private static final Pattern patternEmail = Pattern.compile(EMAIL_PATTERN);
        private static final Pattern patternLogin = Pattern.compile(LOGIN_PATTERN);
        private static final Pattern patternPassword = Pattern.compile(PASSWORD_PATTERN);
        private static final Pattern patternPhone = Pattern.compile(PHONE_PATTERN);

        private Map<ParameterName, ErrorKey> errors = new HashMap<>();


        public Validator emailValidator(String email) throws IOException {
            Matcher m = patternEmail.matcher(email);
            if (!m.matches()) {
                errors.put(ParameterName.EMAIL, ErrorKey.INCORRECT_EMAIL);
            }
            if (email == null) {
                errors.put(ParameterName.EMAIL, ErrorKey.EMPTY_EMAIL);
            }

            if (UserDateBaseReader.readFileByFilter("usersBase", email) ) {
                errors.put(ParameterName.EMAIL, ErrorKey.EMAIL_EXIST);
            }
            return this;
        }

        public Validator loginValidator(String login) throws IOException {
            Matcher m = patternLogin.matcher(login);
            if (!m.matches()) {
                errors.put(ParameterName.LOGIN, ErrorKey.INCORRECT_STRUCTURE_LOGIN);
            }
            if (login == null) {
                errors.put(ParameterName.LOGIN, ErrorKey.EMPTY_LOGIN);
            }
            if (UserDateBaseReader.readFileByFilter("usersBase", login) ) {
                errors.put(ParameterName.LOGIN, ErrorKey.LOGIN_EXIST);
            }
            return this;
        }

        public Validator passwordValidator(String password) {
            Matcher m = patternLogin.matcher(password);
            if (!m.matches()) {
                errors.put(ParameterName.PASSWORD, ErrorKey.INCORRECT_PASSWORD);
            }
            if (password == null ) {
                errors.put(ParameterName.PASSWORD, ErrorKey.EMPTY_PASSWORD);
            }
            return this;
        }

        public Validator phoneNumValidator(String phoneNum) throws IOException {
            Matcher m = patternLogin.matcher(phoneNum);
            if (!m.matches()) {
                errors.put(ParameterName.PHONE_NUMBER, ErrorKey.INCORRECT_PHONENUM);
            }
            if (phoneNum == null) {
                errors.put(ParameterName.PHONE_NUMBER, ErrorKey.EMPTY_RHOHENUM);
            }
            if (UserDateBaseReader.readFileByFilter("usersBase", phoneNum) ) {
                errors.put(ParameterName.PHONE_NUMBER, ErrorKey.TELNUM_EXIST);
            }
            return this;
        }

        public Map<ParameterName, ErrorKey> getErrors() {
            return errors;
        }

        @Override
        public ValidationResult build() {
            return new ValidationResult(this);
        }
    }






}
