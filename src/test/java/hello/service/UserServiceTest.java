package hello.service;

import hello.entity.User;
import hello.repo.IUserRepository;
import hello.web.request.UserRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private static final Long USER_ID_1 = 123L;
    private static final Long USER_ID_2 = 321L;
    private static final User USER1 = new User();
    private static final User USER2 = new User();
    private static final String USER_NAME_1 = "name1";
    private static final String USER_NAME_2 = "name2";
    private static final Integer USER_AGE_1 = 99;
    private static final Integer USER_AGE_2 = 10;
    private static final String USER_PASSWORD = "12345";


    @Before
    public void init() {
        USER1.setId(USER_ID_1);
        USER1.setName(USER_NAME_1);
        USER1.setPassword(USER_PASSWORD);
        USER1.setAge(USER_AGE_1);

        USER2.setId(USER_ID_2);
        USER2.setName(USER_NAME_2);
        USER2.setPassword(USER_PASSWORD);
        USER2.setAge(USER_AGE_2);
    }

    @Test
    public void findOneTest() {
        when(userRepository.findById(USER_ID_1)).thenReturn(java.util.Optional.of(USER1));

        User userFromDB = userService.findOne(USER_ID_1);

        assertEquals(USER1, userFromDB);
    }

    @Test
    public void findAllTest() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(USER1, USER2));

        List<User> usersFromDB = userService.findAll();

        assertThat(usersFromDB, containsInAnyOrder(USER1, USER2));
    }

    @Test
    public void create_userNameNotExist_Test() {
        when(userRepository.findByName(USER_NAME_1)).thenReturn(null);
        when(userRepository.save(USER1)).thenReturn(USER1);

        UserRequest request = new UserRequest();
        request.setName(USER_NAME_1);
        request.setPassword(USER_PASSWORD);
        request.setAge(USER_AGE_1);

        Long id = userService.create(request);

        assertEquals(USER_ID_1, id);
    }

    @Test
    public void create_userNameExist_Test() {
        when(userRepository.findByName(USER_NAME_1)).thenReturn(USER1);

        UserRequest request = new UserRequest();
        request.setName(USER_NAME_1);
        request.setPassword(USER_PASSWORD);
        request.setAge(USER_AGE_1);

        Long id = userService.create(request);

        assertEquals(-1L, (long) id);
    }

    @Test
    public void update_userExist_Test() {
        when(userRepository.findById(USER_ID_1)).thenReturn(java.util.Optional.of(USER1));

        UserRequest request = new UserRequest();
        request.setName(USER_NAME_2);
        request.setPassword(USER_PASSWORD);
        request.setAge(USER_AGE_2);

        boolean isUpdated = userService.update(request, USER_ID_1);

        assertTrue(isUpdated);
    }

    @Test
    public void update_userNotFound_Test() {
        when(userRepository.findById(USER_ID_1)).thenReturn(java.util.Optional.empty());

        UserRequest request = new UserRequest();
        request.setName(USER_NAME_2);
        request.setPassword(USER_PASSWORD);
        request.setAge(USER_AGE_2);

        boolean isUpdated = userService.update(request, USER_ID_1);

        assertFalse(isUpdated);
    }

    @Test
    public void delete_userExist_Test() {
        when(userRepository.findById(USER_ID_1)).thenReturn(java.util.Optional.of(USER1));

        boolean isDeleted = userService.delete(USER_ID_1);

        assertTrue(isDeleted);
    }

    @Test
    public void delete_userNotExist_Test() {
        when(userRepository.findById(USER_ID_1)).thenReturn(java.util.Optional.empty());

        boolean isDeleted = userService.delete(USER_ID_1);

        assertFalse(isDeleted);
    }

    @Test
    public void filterByAgeTest() {
        when(userRepository.findByAgeBetween(5, 15)).thenReturn(Arrays.asList(USER2));

        List<User> users = userService.filterByAge(5, 15);

        assertEquals(users.get(0), USER2);
    }

    @Test
    public void findByNameTest() {
        when(userRepository.findByName(USER_NAME_1)).thenReturn(USER1);

        User user = userService.findByName(USER_NAME_1);

        assertEquals(user, USER1);
    }
}