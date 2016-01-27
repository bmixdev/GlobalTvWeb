package atua.anddev.globaltv.dao;

import atua.anddev.globaltv.entity.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user);
    User getUserById(int id);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(User user);
}
