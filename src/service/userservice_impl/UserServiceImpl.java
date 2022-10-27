package service.userservice_impl;

import bean.User;
import dao.DAOException;
import dao.DAOFactory;
import dao.UserDAO;
import service.exception.ServiceException;
import service.userservice.UserService;
import service.validation.ServiceValidationException;
import service.validation.ValidationResult;

import java.io.IOException;

public class UserServiceImpl implements UserService {

    @Override
    public boolean isAvailableLogIn(String login, String password) throws ServiceException {

        boolean isSuccessfulCheck;

        ValidationResult.Validator validator = new ValidationResult.Validator();
        try {
            ValidationResult validationResult = validator.loginValidator(login).passwordValidator(password).build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (validator.getErrors() != null) {
            throw new ServiceValidationException("errors", validator.getErrors());
        }

        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoObjectFactory.getUserDAO();
            isSuccessfulCheck = userDAO.isAvailableLogIn(login, password);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return isSuccessfulCheck;
    }

    @Override
    public boolean isAvailableRegister(User user) throws ServiceException {
        boolean isSuccessfulCheck;

        String login = user.getLogin();
        String password = user.getPassword();
        String email = user.getEmail();
        String phoneNumber = user.getTelephoneNum();

        ValidationResult.Validator validator = new ValidationResult.Validator();
        try {
            ValidationResult validationResult = validator.loginValidator(login).passwordValidator(password).
                    emailValidator(email).phoneNumValidator(phoneNumber).build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (validator.getErrors() != null) {
            throw new ServiceValidationException("errors", validator.getErrors());
        }

        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoObjectFactory.getUserDAO();
            isSuccessfulCheck = userDAO.isAvailableRegister(user);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return isSuccessfulCheck;
    }

    @Override
    public boolean isAvailableLogOut(String login) throws ServiceException {
        return false;
    }
}
