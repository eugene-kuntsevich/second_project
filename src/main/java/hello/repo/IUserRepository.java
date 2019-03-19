package hello.repo;

import hello.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * interface for magic methods Spring JPA
 */
public interface IUserRepository extends CrudRepository<User, Long> {

    /**
     * @param name
     * @return {@link User}
     */
    User findByName(String name);

    /**
     * @param min value for {@link User}'s age
     * @param max value for {@link User}'s age
     * @return {@link List<User>}
     */
    List<User> findByAgeBetween(final Integer min, final Integer max);
}
