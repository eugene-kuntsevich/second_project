package hello.service;

import hello.entity.User;
import hello.repo.IUserRepository;
import hello.web.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for {@link User}
 */
@Service
public class UserService implements IUserService {

    private IUserRepository userRepository;

    /**
     * Constructor for {@link IUserRepository}
     *
     * @param userRepository entity for operating in database
     */
    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Method for finding {@link User} in database by id
     *
     * @param id for {@link User} in database
     * @return {@link User}
     */
    @Override
    public User findOne(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Method for finding all {@link User}'s in database
     *
     * @return {@link List<User>}
     */
    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    /**
     * Method for creation one {@link User} in database
     *
     * @param request {@link UserRequest}
     * @return id for {@link User} in database
     */
    @Override
    public Long create(UserRequest request) {
        User user = userRepository.findByName(request.getName());
        if (user != null) {
            return -1L;
        }

        User newUser = new User();
        newUser.setName(request.getName());
        newUser.setAge(request.getAge());
        newUser.setPassword(request.getPassword());

        User savedUser = userRepository.save(newUser);
        return savedUser.getId();
    }


    /**
     * Method for updating one {@link User} in database
     *
     * @param request {@link UserRequest}
     * @param id      for object in database which needed to update
     * @return boolean value as success or fail in updating
     */
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

    /**
     * Method or deleting one {@link User} in database
     *
     * @param id for object in database which needed to delete
     * @return boolean value as success or fail in deleting
     */
    @Override
    public boolean delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            return false;
        }

        userRepository.deleteById(id);

        return true;
    }


    /**
     * Method for filtering {@link User}s in database by minimal and maximal boundary of age
     *
     * @param min minimal age for filtering
     * @param max maximal age for filtering
     * @return {@link List<User>}
     */
    @Override
    public List<User> filterByAge(Integer min, Integer max) {
        return userRepository.findByAgeBetween(min, max);
    }

    /**
     * Method for finding one {@link User} by name in database
     *
     * @param name {@link User}'s name
     * @return {@link User}
     */
    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }
}
