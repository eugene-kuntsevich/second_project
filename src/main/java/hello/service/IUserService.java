package hello.service;

import hello.entity.User;
import hello.web.request.UserRequest;

import java.util.List;

public interface IUserService {

    User findOne(Long id);

    List<User> findAll();

    Long create(UserRequest request);

    boolean update(UserRequest request);

    boolean delete(Long id);

}
