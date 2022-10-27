package main;

import bean.User;
import service.exception.ServiceException;
import service.servicefactory.ServiceFactory;
import service.userservice.UserService;


public class Main {

    public static void main(String[] args) throws ServiceException {


        UsersCreator.writeItemsToFile();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();


        User user5 = new User("kondral", "", "dvvrbd", "604-3t");

        userService.isAvailableRegister(user5);


    }
}
