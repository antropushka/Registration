package dao;

import bean.User;

public class UserDAOImpl implements UserDAO{


    @Override
    public boolean isAvailableLogIn(String login, String password) throws DAOException {
        return false;
    }

    @Override
    public boolean isAvailableRegister(User user) throws DAOException {
        return false;
    }
}
