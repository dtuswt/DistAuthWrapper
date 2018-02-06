package authwrapper.transport.rmi;

import authwrapper.dto.Speed;
import authwrapper.helper.UserAdministrationFactory;
import authwrapper.helper.UserConverter;
import authwrapper.transport.IUserAdministration;
import authwrapper.transport.SomethingWentWrongException;
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
    public User authenticateUser(String username, String password) throws SomethingWentWrongException {
        try {
            return UserConverter.BrugerToUser(ba.hentBruger(username, password));
        } catch (RemoteException e) {
            throw new SomethingWentWrongException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public User changePassword(String username, String oldPassword, String newPassword) throws SomethingWentWrongException {
        try {
            return UserConverter.BrugerToUser(ba.ændrAdgangskode(username, oldPassword, newPassword));
        } catch (RemoteException e) {
            throw new SomethingWentWrongException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void sendEmail(String username, String password, String subject, String body) throws SomethingWentWrongException {
        try {
            ba.sendEmail(username, password, subject, body);
        } catch (RemoteException e) {
            throw new SomethingWentWrongException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void sendForgottenPasswordEmail(String username, String message) throws SomethingWentWrongException {
        try {
            ba.sendGlemtAdgangskodeEmail(username, message);
        } catch (RemoteException e) {
            throw new SomethingWentWrongException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void setExtraField(String username, String password, String fieldName, Object value) throws SomethingWentWrongException {
        try {
            ba.setEkstraFelt(username, password, fieldName, value);
        } catch (RemoteException e) {
            throw new SomethingWentWrongException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Object getExtraField(String username, String password, String fieldName) throws SomethingWentWrongException {
        try {
            return ba.getEkstraFelt(username, password, fieldName);
        } catch (RemoteException e) {
            throw new SomethingWentWrongException(e.getMessage(), e.getCause());
        }
    }
}
