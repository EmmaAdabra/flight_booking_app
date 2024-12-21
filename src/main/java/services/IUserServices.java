package services;

import models.User;
import utils.ObjectResponse;
import utils.Response;

import java.util.List;

public interface IUserServices {
    Response createUser(User User);
    ObjectResponse<List<User>> getUsers();
    ObjectResponse<User> getUSerByID(int id);
    Response updateUserEmail(String newEmail, int userID);
    Response deleteUSer(int id);
}
