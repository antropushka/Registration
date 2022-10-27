package main;

import bean.User;
import service.exception.ServiceException;
import service.servicefactory.ServiceFactory;
import service.userservice.UserService;
import service.userservice_impl.UserServiceImpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UsersCreator {

    private Map<String, User> users = new HashMap<String, User>();

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    private static Map<String, User> createUsersDateBase() {

        Map<String, User> users = new HashMap<String, User>();
        User user1 = new User("antropova", "593032",
                "artiom185400@gmail.com", "+375339549300");
        User user2 = new User("bogdanovich", "5e31132",
                "bogdanovichV@mail.ru", "+375445683455");
        User user3 = new User("kondral", "59_8423032",
                "akondralAlex@yandex.ru", "+375293891995");
        User user4 = new User("tkachovAA", "5ec493_2",
                "artiomtkachoy@yandex.ru", "+375291631278");
        users.put(user1.getLogin(), user1);
        users.put(user2.getLogin(), user2);
        users.put(user3.getLogin(), user3);
        users.put(user4.getLogin(), user4);
        return users;
    }

    public static void writeItemsToFile() {

        File file = new File("usersBase.txt");
        BufferedWriter bf = null;
        try {
            Map<String, User> users = createUsersDateBase();
            bf = new BufferedWriter(new FileWriter(file));
            for (Map.Entry<String, User> entry : users.entrySet()) {
                bf.write(entry.getKey() + ":" + entry.getValue());
                bf.newLine();
            }

            bf.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeUserInBase(User user) throws ServiceException {
        if (ServiceFactory.getInstance().getUserService().isAvailableRegister(user)) {
            File file = new File("usersBase.txt");
            BufferedWriter bf = null;
            try {
                bf = new BufferedWriter(new FileWriter(file));
                bf.write(user.toString());
                bf.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
