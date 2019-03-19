package hello.service;

import hello.entity.User;
import hello.repo.IUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private static final Long USER_ID = 123L;
    private static final User USER = new User();
    private static final String USER_NAME = "name";
    private static final Integer USER_AGE = 99;
    private static final String USER_PASSWORD = "12345";


    @Before
    public void init() {
        USER.setId(USER_ID);
        USER.setName(USER_NAME);
        USER.setPassword(USER_PASSWORD);
        USER.setAge(USER_AGE);
    }

    @Test
    public void findOne_ExistingUser_Test() {
        when(userRepository.findById(USER_ID)).thenReturn(java.util.Optional.of(USER));

        User userFromDB = userService.findOne(USER_ID);

        assertEquals(USER, userFromDB);
    }

    @Test
    public void findAllTest() {
    }

    @Test
    public void createTest() {
    }

    @Test
    public void updateTest() {
    }

    @Test
    public void deleteTest() {
    }

    @Test
    public void filterByAgeTest() {
    }

    @Test
    public void findByNameTest() {
    }
}