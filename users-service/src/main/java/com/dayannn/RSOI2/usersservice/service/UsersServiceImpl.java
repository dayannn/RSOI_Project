package com.dayannn.RSOI2.usersservice.service;

import com.dayannn.RSOI2.usersservice.entity.User;
import com.dayannn.RSOI2.usersservice.exception.UserNotFoundException;
import com.dayannn.RSOI2.usersservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService{
    private final UserRepository userRepository;

    @Autowired
    public UsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) throws UserNotFoundException{
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User findUserByLogin(String login) throws UserNotFoundException{
        User user = userRepository.findByLogin(login);
        if (user == null)
            throw  new UserNotFoundException(login);
        return user;
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

//    @Override
//    public void setRating(Long id, Integer rating) throws UserNotFoundException{
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException(id));
//        user.setRating(rating);
//        userRepository.save(user);
//    }

//    @Override
//    public void increaseRating(Long id, Integer rating) throws UserNotFoundException{
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException(id));
//        user.setRating(user.getRating() + rating);
//        userRepository.save(user);
//    }
//
//    @Override
//    public void increaseRating(Long id) throws UserNotFoundException{
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException(id));
//        user.setRating(user.getRating() + 1);
//        userRepository.save(user);
//    }
//
//    @Override
//    public void decreaseRating(Long id, Integer rating) throws UserNotFoundException{
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException(id));
//        user.setRating(user.getRating() - rating);
//        userRepository.save(user);
//    }
//
//    @Override
//    public void decreaseRating(Long id) throws UserNotFoundException{
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException(id));
//        user.setRating(user.getRating() - 1);
//        userRepository.save(user);
//    }

    @Override
    public void setReviewsNum(Long id, int reviewsNum) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.save(user);
    }

    @Override
    public ResponseEntity healthCheck() {
        return ResponseEntity.ok("The usersService is up");
    }
}
