package hello.service;

import hello.entity.User;
import hello.web.request.UserRequest;

import java.util.List;


/**
 * interface for operating with entity {@link User}
 */
public interface IUserService {

    /**
     * Method for finding {@link User} in database by id
     *
     * @param id for {@link User} in database
     * @return {@link User}
     */
    User findOne(Long id);

    /**
     * Method for finding all {@link User}'s in database
     *
     * @return {@link List<User>}
     */
    List<User> findAll();

    /**
     * Method for creation one {@link User} in database
     *
     * @param request {@link UserRequest}
     * @return id for {@link User} in database
     */
    Long create(UserRequest request);

    /**
     * Method for updating one {@link User} in database
     *
     * @param request {@link UserRequest}
     * @param id      for object in database which needed to update
     * @return boolean value as success or fail in updating
     */
    boolean update(UserRequest request, Long id);

    /**
     * Method or deleting one {@link User} in database
     * @param id for object in database which needed to delete
     * @return boolean value as success or fail in deleting
     */
    boolean delete(Long id);

    /**
     * Method for filtering {@link User}s in database by minimal and maximal boundary of age
     *
     * @param min minimal age for filtering
     * @param max maximal age for filtering
     * @return {@link List<User>}
     */
    List<User> filterByAge(Integer min, Integer max);

    /**
     * Method for finding one {@link User} by name in database
     *
     * @param name {@link User}'s name
     * @return {@link User}
     */
    User findByName(String name);
}
