package dao;

import bean.User;

public interface UserDAO {

    boolean isAvailableLogIn(String login, String password) throws DAOException;
    boolean isAvailableRegister(User user) throws DAOException;
}
