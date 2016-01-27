package atua.anddev.globaltv.service;


import atua.anddev.globaltv.entity.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    User getUserById(int id);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(User user);
}
