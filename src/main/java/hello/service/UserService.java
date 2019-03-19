package hello.service;

import hello.entity.User;
import hello.repo.IUserRepository;
import hello.web.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

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
        return (List<User>) userRepository.findAll();
    }

    @Override
    public Long create(UserRequest request) {
        User user = userRepository.findByName(request.getName());
        if (user != null) return -1L;

        User newUser = new User();
        newUser.setName(request.getName());
        newUser.setAge(request.getAge());
        newUser.setPassword(request.getPassword());

        return userRepository.save(newUser).getId();
    }

    @Override
    public boolean update(UserRequest request, Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {

            User updatedUser = userOptional.get();
            updatedUser.setName(request.getName());
            updatedUser.setPassword(request.getPassword());
            updatedUser.setAge(request.getAge());
            userRepository.save(updatedUser);
            return true;
        } else return false;
    }

    @Override
    public boolean delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) return false;

        userRepository.deleteById(id);

        return true;
    }

    @Override
    public List<User> filterByAge(Integer min, Integer max) {
        return userRepository.findByAgeBetween(min, max);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }
}
