package hello.repo;

import hello.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User, Long> {

    User findByName(String name);
}
