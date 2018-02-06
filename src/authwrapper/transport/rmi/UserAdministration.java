package authwrapper.transport.rmi;

import authwrapper.helper.UserConverter;
import brugerautorisation.transport.rmi.Brugeradmin;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import authwrapper.dto.User;

public class UserAdministration  {
    private final String lookupAddress = "rmi://javabog.dk/brugeradmin";
    private Brugeradmin ba;

    public UserAdministration() throws java.rmi.RemoteException, NotBoundException, MalformedURLException {
        this.ba = (Brugeradmin) Naming.lookup(lookupAddress);
    }

    public User retrieveUser(String username, String password) throws RemoteException {
        return UserConverter.BrugerToUser(ba.hentBruger(username, password));
    }

    public User changePassword(String username, String oldPassword, String newPassword) throws RemoteException {
        return UserConverter.BrugerToUser(ba.ændrAdgangskode(username, oldPassword, newPassword));
    }

    public void sendEmail(String username, String password, String subject, String body) throws RemoteException {
        ba.sendEmail(username, password, subject, body);
    }

    public void sendForgottenPasswordEmail(String username, String message) throws RemoteException {
        ba.sendGlemtAdgangskodeEmail(username, message);
    }

    public void setExtraField(String username, String password, String fieldName, Object value) throws RemoteException {
        ba.setEkstraFelt(username, password, fieldName, value);
    }

    public Object getExtraField(String username, String password, String fieldname) throws RemoteException {
        return ba.getEkstraFelt(username, password, fieldname);
    }
}
