package authwrapper.transport;

import authwrapper.dto.User;

public interface IUserAdministration {
    User authenticateUser(String username, String password) throws SomethingWentWrongException;
    User changePassword(String username, String oldPassword, String newPassword) throws SomethingWentWrongException;
    void sendEmail(String username, String password, String subject, String body) throws SomethingWentWrongException;
    void sendForgottenPasswordEmail(String username, String message) throws SomethingWentWrongException;
    void setExtraField(String username, String password, String fieldName, Object value) throws SomethingWentWrongException;
    Object getExtraField(String username, String password, String fieldName) throws SomethingWentWrongException;
}
