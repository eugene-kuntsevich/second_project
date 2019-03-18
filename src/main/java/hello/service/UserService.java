package hello.service;

import hello.entity.User;
import hello.repo.IUserRepository;
import hello.web.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    private IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        List<User> users = (List<User>) userRepository.findAll();
        return null;
    }

    @Override
    public Long create(UserRequest request) {
        User user = userRepository.findByName(request.getName());
        if (user != null) return -1L;

        User newUser = new User();
        newUser.setName(request.getName());
        newUser.setAge(request.getAge());
        newUser.setPassword(request.getPassword());

        User savedUser = userRepository.save(newUser);

        if (savedUser != null) return savedUser.getId();

        return -1L;
    }

    @Override
    public boolean update(UserRequest request) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
