package dk.localghost.authwrapper.transport;

import dk.localghost.authwrapper.dto.Speed;
import dk.localghost.authwrapper.helper.UserAdministrationFactory;
import org.junit.Before;
import org.junit.Test;

public class IUserAdministrationTest {
    IUserAdministration userAdminRmi;
    IUserAdministration userAdminSoap;

    final String correctUsername = "s165241";
    final String incorrectUsername = "This is some ridiculous username with spelling mistkes.";
    final String incorrectPassword = "MyWrongPassword";

    @Before
    public void setUp() throws ConnectivityException {
        userAdminRmi = UserAdministrationFactory.getUserAdministration(Speed.LUDICROUS_SPEED);
        userAdminSoap = UserAdministrationFactory.getUserAdministration(Speed.SLOW);
    }

    @Test(expected = AuthenticationException.class)
    public void authenticateUser_wrongPassword_Rmi() throws AuthenticationException, ConnectivityException {
        userAdminRmi.authenticateUser(correctUsername, incorrectPassword);
    }

    @Test(expected = AuthenticationException.class)
    public void authenticateUser_wrongUser_Rmi() throws AuthenticationException, ConnectivityException {
        userAdminRmi.authenticateUser(incorrectUsername, incorrectPassword);
    }

    @Test(expected = AuthenticationException.class)
    public void authenticateUser_wrongPassword_Soap() throws AuthenticationException, ConnectivityException {
        userAdminSoap.authenticateUser(correctUsername, incorrectPassword);
    }

    @Test(expected = AuthenticationException.class)
    public void authenticateUser_wrongUser_Soap() throws AuthenticationException, ConnectivityException {
        userAdminSoap.authenticateUser(incorrectUsername, incorrectPassword);
    }

    @Test
    public void resetPassword_wrongUser_Rmi() throws ConnectivityException {
        userAdminRmi.sendForgottenPasswordEmail(incorrectUsername, "Some Message");
    }
}