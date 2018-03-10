package dk.localghost.authwrapper.transport;

import dk.localghost.authwrapper.dto.User;

public interface IUserAdministration {
    User authenticateUser(String username, String password) throws AuthenticationException, ConnectivityException;
    User changePassword(String username, String oldPassword, String newPassword) throws AuthenticationException, ConnectivityException;
    void sendEmail(String username, String password, String subject, String body) throws AuthenticationException, ConnectivityException;
    void sendForgottenPasswordEmail(String username, String message) throws ConnectivityException;
    void setExtraField(String username, String password, String fieldName, Object value) throws AuthenticationException, ConnectivityException;
    Object getExtraField(String username, String password, String fieldName) throws AuthenticationException, ConnectivityException;
}
