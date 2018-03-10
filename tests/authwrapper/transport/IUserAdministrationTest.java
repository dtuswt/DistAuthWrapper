package authwrapper.transport;

import org.junit.Before;
import org.junit.Test;

public class IUserAdministrationTest {
    authwrapper.transport.IUserAdministration userAdminRmi;
    authwrapper.transport.IUserAdministration userAdminSoap;

    final String correctUsername = "s165241";
    final String incorrectUsername = "This is some ridiculous username with spelling mistkes.";
    final String incorrectPassword = "MyWrongPassword";

    @Before
    public void setUp() throws authwrapper.transport.ConnectivityException {
        userAdminRmi = authwrapper.helper.UserAdministrationFactory.getUserAdministration(authwrapper.dto.Speed.LUDICROUS_SPEED);
        userAdminSoap = authwrapper.helper.UserAdministrationFactory.getUserAdministration(authwrapper.dto.Speed.SLOW);
    }

    @Test(expected = authwrapper.transport.AuthenticationException.class)
    public void authenticateUser_wrongPassword_Rmi() throws authwrapper.transport.AuthenticationException, authwrapper.transport.ConnectivityException {
        userAdminRmi.authenticateUser(correctUsername, incorrectPassword);
    }

    @Test(expected = authwrapper.transport.AuthenticationException.class)
    public void authenticateUser_wrongUser_Rmi() throws authwrapper.transport.AuthenticationException, authwrapper.transport.ConnectivityException {
        userAdminRmi.authenticateUser(incorrectUsername, incorrectPassword);
    }

    @Test(expected = authwrapper.transport.AuthenticationException.class)
    public void authenticateUser_wrongPassword_Soap() throws authwrapper.transport.AuthenticationException, authwrapper.transport.ConnectivityException {
        userAdminSoap.authenticateUser(correctUsername, incorrectPassword);
    }

    @Test(expected = authwrapper.transport.AuthenticationException.class)
    public void authenticateUser_wrongUser_Soap() throws authwrapper.transport.AuthenticationException, authwrapper.transport.ConnectivityException {
        userAdminSoap.authenticateUser(incorrectUsername, incorrectPassword);
    }

    @Test
    public void resetPassword_wrongUser_Rmi() throws authwrapper.transport.ConnectivityException {
        userAdminRmi.sendForgottenPasswordEmail(incorrectUsername, "Some Message");
    }
}