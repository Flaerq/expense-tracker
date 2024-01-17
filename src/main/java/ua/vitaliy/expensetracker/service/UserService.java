package ua.vitaliy.expensetracker.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.vitaliy.expensetracker.exception.UserNotFoundException;
import ua.vitaliy.expensetracker.model.User;
import ua.vitaliy.expensetracker.repository.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing User entities.
 * Provides methods for CRUD operations and user-related functionalities.
 */
@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserService(){

    }

    /**
     * Retrieves a list of all users from the database.
     *
     * @return List of User entities representing all users.
     */
    @Transactional
    public List<User> readAll(){
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their unique identifier (ID).
     *
     * @param id The unique identifier of the user to retrieve.
     * @return An Optional containing the User entity if found, or empty if not found.
     */
    @Transactional
    public Optional<User> readById(Integer id){
        return userRepository.findById(id);
    }

    /**
     * Deletes a user by their unique identifier (ID).
     * Throws UserNotFoundException if the user does not exist.
     *
     * @param id The unique identifier of the user to delete.
     */
    @Transactional
    public void deleteById(int id){
        if (!userRepository.existsById(id)){
            throw new UserNotFoundException();
        }

        userRepository.deleteById(id);
    }

    /**
     * Deletes a user entity.
     * Throws UserNotFoundException if the user does not exist.
     *
     * @param user The User entity to delete.
     */
    @Transactional
    public void delete(User user){
        if (!userRepository.existsById(user.getUserId())){
            throw new UserNotFoundException();
        }

        userRepository.delete(user);
    }

    /**
     * Updates an existing user's information.
     * Throws UserNotFoundException if the user does not exist.
     *
     * @param id   The unique identifier of the user to update.
     * @param user The User entity containing updated information.
     * @return The updated User entity.
     */
    @Transactional
    public User update(Integer id, User user){
        Optional<User> maybeUser = userRepository.findById(id);

        if (maybeUser.isPresent()) {
            User updatingUser = maybeUser.get();

            updatingUser.setUserName(user.getUserName());
            updatingUser.setEmail(user.getEmail());
            updatingUser.setPassword(user.getPassword());

            userRepository.save(updatingUser);  // Save changes to the database

            return updatingUser;
        } else {
            throw new UserNotFoundException();
        }
    }

    /**
     * Saves new user in database.
     *
     * @param user The user entity than is going to be saved in database.
     * @return The save user as in params.
     */
    @Transactional
    public User save(User user){
        return userRepository.save(user);
    }

    /**
     * Gives users count in database.
     *
     * @return Number of users in database.
     */
    @Transactional
    public long usersCount(){
        return userRepository.count();
    }

}