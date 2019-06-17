package com.dayannn.RSOI2.usersservice;

import com.dayannn.RSOI2.usersservice.repository.UserRepository;
import com.dayannn.RSOI2.usersservice.service.UsersService;
import com.dayannn.RSOI2.usersservice.service.UsersServiceImpl;
import org.junit.Before;
import org.mockito.Mock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.MockitoAnnotations.initMocks;

public class UsersServiceTest {
    private UsersService usersService;

    @Mock
    UserRepository userRepository;

    @Before
    public void setUp(){
        initMocks(this);
        usersService = new UsersServiceImpl(userRepository);
    }

//    @Test
//    public void shouldReturnAllUsers(){
//        List<User> users= new ArrayList<>();
//        User user= new User();
//        user.setRating(5);
//        user.setLastName("Popovich");
//        user.setName("Alesha");
//        user.setLogin("alesha994");
//        user.setReviewsNum(4);
//
//        given(userRepository.findAll()).willReturn(users);
//        List<User> usersReturned = usersService.getAllUsers();
//        assertThat(usersReturned, is(users));
//    }
//
//    @Test
//    public void shouldCreateUser(){
//        User user = new User();
//        user.setRating(5);
//        user.setLastName("Popovich");
//        user.setName("Alesha");
//        user.setLogin("alesha994");
//        user.setReviewsNum(4);
//
//        given(userRepository.save(user)).willReturn(user);
//        assertThat(userRepository.save(user), is(user));
//    }
//
//    @Test
//    public void shouldFindUserById() throws UserNotFoundException {
//        User user = new User();
//        user.setRating(5);
//        user.setLastName("Popovich");
//        user.setName("Alesha");
//        user.setLogin("alesha994");
//        user.setReviewsNum(4);
//
//        given(userRepository.save(user)).willReturn(user);
//        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
//
//        usersService.createUser(user);
//        assertThat(usersService.findUserById(user.getId()), is(user));
//    }
//
//    @Test
//    public void shouldFindUserByLogin() throws UserNotFoundException {
//        User user = new User();
//        user.setRating(5);
//        user.setLastName("Popovich");
//        user.setName("Alesha");
//        user.setLogin("alesha994");
//        user.setReviewsNum(4);
//
//        given(userRepository.save(user)).willReturn(user);
//        given(userRepository.findByLogin("alesha994")).willReturn(user);
//
//        usersService.createUser(user);
//        assertThat(usersService.findUserByLogin("alesha994"), is(user));
//    }
}
