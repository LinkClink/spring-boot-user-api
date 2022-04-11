package springboot.user.api.service;

import java.util.List;
import springboot.user.api.model.User;

public interface UserService {
    User save(User user);

    User update(User user);

    List<User> getAll();

    void delete(User user);

    User findById(Long id);
}
