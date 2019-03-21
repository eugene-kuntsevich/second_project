package hello.web;

import hello.entity.User;
import hello.service.IUserService;
import hello.web.dto.IdDTO;
import hello.web.request.UserFilterByAgeRequest;
import hello.web.request.UserFindByNameRequest;
import hello.web.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static hello.web.dto.DtoConverter.convertListUsersToDtos;
import static hello.web.dto.DtoConverter.convertUserToDto;

/**
 * Controller for operating with entity {@link User}
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private IUserService userService;

    /**
     * @param userService
     */
    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * Methods finding {@link User} by id
     *
     * @param id for {@link User} in database
     * @return {@link ResponseEntity} and {@link HttpStatus}
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        User user = userService.findOne(id);
        if (user != null) {
            return new ResponseEntity<>(convertUserToDto(user), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Method for finding one {@link User} by name
     *
     * @param request as {@link UserFindByNameRequest}
     * @return {@link ResponseEntity} and {@link HttpStatus}
     */
    @GetMapping("/findByName")
    public ResponseEntity<?> findByName(@RequestBody @Valid UserFindByNameRequest request) {
        User user = userService.findByName(request.getName());
        if (user != null) return new ResponseEntity<>(convertUserToDto(user), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Method for creation one {@link User}
     *
     * @param request as {@link UserRequest}
     * @return {@link ResponseEntity} and {@link HttpStatus}
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid UserRequest request) {
        Long id = userService.create(request);
        if (id != -1L) return new ResponseEntity<>(new IdDTO(id), HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Method for updating one {@link User}
     *
     * @param request as {@link UserRequest}
     * @param id      for {@link User} in database
     * @return {@link ResponseEntity} and {@link HttpStatus}
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid UserRequest request,
                                    @PathVariable Long id) {
        boolean isUpdated = userService.update(request, id);
        if (isUpdated) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    /**
     * Method or deleting one {@link User}
     *
     * @param id for {@link User} in database
     * @return {@link ResponseEntity} and {@link HttpStatus}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean isDeleted = userService.delete(id);
        if (isDeleted) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Method for finding all {@link User}'s
     *
     * @return {@link ResponseEntity} and {@link HttpStatus}
     */
    @GetMapping("/getAll")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(convertListUsersToDtos(userService.findAll()), HttpStatus.OK);
    }

    /**
     * Method for filtering {@link User}s by minimal and maximal boundary of age
     *
     * @param req as {@link UserFilterByAgeRequest}
     * @return {@link ResponseEntity} and {@link HttpStatus}
     */
    @GetMapping("/filterByAge")
    public ResponseEntity<?> filterByAge(@Valid @RequestBody UserFilterByAgeRequest req) {
        return new ResponseEntity<>(convertListUsersToDtos(userService.filterByAge(req.getMin(), req.getMax())), HttpStatus.OK);
    }
}
