package authwrapper.transport.rmi;

import authwrapper.helper.UserConverter;
import authwrapper.transport.ConnectivityException;
import authwrapper.transport.IUserAdministration;
import authwrapper.transport.AuthenticationException;
import brugerautorisation.transport.rmi.Brugeradmin;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import authwrapper.dto.User;

public class UserAdministrationRmi implements IUserAdministration {
    private final String lookupAddress = "rmi://javabog.dk/brugeradmin";
    private Brugeradmin ba;

    public UserAdministrationRmi() throws java.rmi.RemoteException, NotBoundException, MalformedURLException {
        this.ba = (Brugeradmin) Naming.lookup(lookupAddress);
    }

    @Override
    public User authenticateUser(String username, String password) throws AuthenticationException, ConnectivityException {
        try {
            return UserConverter.BrugerToUser(ba.hentBruger(username, password));
        } catch (RemoteException e) {
            throw new ConnectivityException(e.getMessage(), e.getCause());
        } catch(IllegalArgumentException e) {
            throw new AuthenticationException("Wrong username or password.");
        }
    }

    @Override
    public User changePassword(String username, String oldPassword, String newPassword) throws AuthenticationException, ConnectivityException {
        try {
            return UserConverter.BrugerToUser(ba.ændrAdgangskode(username, oldPassword, newPassword));
        } catch (RemoteException e) {
            throw new ConnectivityException(e.getMessage(), e.getCause());
        } catch(IllegalArgumentException e) {
            throw new AuthenticationException("Wrong username or password.");
        }
    }

    @Override
    public void sendEmail(String username, String password, String subject, String body) throws AuthenticationException, ConnectivityException {
        try {
            ba.sendEmail(username, password, subject, body);
        } catch (RemoteException e) {
            throw new ConnectivityException(e.getMessage(), e.getCause());
        } catch(IllegalArgumentException e) {
            throw new AuthenticationException("Wrong username or password.");
        }
    }

    @Override
    public void sendForgottenPasswordEmail(String username, String message) throws ConnectivityException {
        try {
            ba.sendGlemtAdgangskodeEmail(username, message);
        } catch (RemoteException e) {
            throw new ConnectivityException("Lost connection to server. " + e.getMessage(), e.getCause());
        } catch(IllegalStateException e) { /*  */}
    }

    @Override
    public void setExtraField(String username, String password, String fieldName, Object value) throws AuthenticationException, ConnectivityException {
        try {
            ba.setEkstraFelt(username, password, fieldName, value);
        } catch (RemoteException e) {
            throw new ConnectivityException(e.getMessage(), e.getCause());
        } catch(IllegalArgumentException e) {
            throw new AuthenticationException("Wrong username or password.");
        }
    }

    @Override
    public Object getExtraField(String username, String password, String fieldName) throws AuthenticationException, ConnectivityException {
        try {
            return ba.getEkstraFelt(username, password, fieldName);
        } catch (RemoteException e) {
            throw new ConnectivityException(e.getMessage(), e.getCause());
        } catch(IllegalArgumentException e) {
            throw new AuthenticationException("Wrong username or password.");
        }
    }
}
