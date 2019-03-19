package hello.service;

import hello.entity.User;
import hello.web.request.UserRequest;

import java.util.List;


/**
 * interface for operating with entity {@link User}
 */
public interface IUserService {

    /**
     * @param id
     * @return {@link User}
     */
    User findOne(Long id);

    /**
     * @return {@link List<User>}
     */
    List<User> findAll();

    /**
     * @param request
     * @return id for object in database
     */
    Long create(UserRequest request);

    /**
     * @param request as {@link UserRequest}
     * @param id for object in database which needed to update
     * @return
     */
    boolean update(UserRequest request, Long id);

    /**
     * @param id for object in database which needed to delete
     * @return
     */
    boolean delete(Long id);

    /**
     *
     * @param from
     * @param to
     * @return
     */
    List<User> filterByAge(Integer from, Integer to);

    User findByName(String name);

}
