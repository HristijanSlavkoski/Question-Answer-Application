package first.project.service;

import java.util.ArrayList;

import first.project.dto.UserDTO;
import first.project.exceptions.UserValidationException;
import first.project.model.User;

public interface AuthenticationService
{
    boolean checkEmail(String email);

    boolean checkUserName(String username);

    boolean checkPassword(String password);

    ArrayList<String> validateAndSave(User user);

    int checkLoginEmail(String email);

    User checkLoginPassword(String password, int id);

    User validateAndLogin(UserDTO userDTO) throws UserValidationException;

    User findByEmail(String email);
}
