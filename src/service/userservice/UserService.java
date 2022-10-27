package service.userservice;

import bean.User;
import service.exception.ServiceException;

public interface UserService {

    public boolean isAvailableLogIn(String login, String password) throws ServiceException;

    public boolean isAvailableRegister(User user) throws ServiceException;

    public boolean isAvailableLogOut(String login) throws ServiceException;
}
