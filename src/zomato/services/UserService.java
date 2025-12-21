package zomato.services;

import zomato.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class UserService {
    private HashMap<String , User > users;
    public UserService() {
         users= new HashMap<>();
    }
    public User getUserById(String id)
    {
        return users.get(id);
    }
    public List<User> getAllUsers()
    {
        return new ArrayList<>(users.values());
    }
    public User updateUser(User user)
    {
        User old = users.get(user.getId());
        old.setName(user.getName());
        users.put(user.getId(), old);
        return old;
    }
    public User createUser(User user)
    {
        user.setId(UUID.randomUUID().toString());
        users.put(user.getId(), user);
        return user;
    }
}
