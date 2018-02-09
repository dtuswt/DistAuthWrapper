package authwrapper.transport.soap;

import authwrapper.dto.User;
import authwrapper.helper.UserConverter;
import authwrapper.transport.IUserAdministration;
import authwrapper.transport.AuthenticationException;
import brugerautorisation.transport.soap.Brugeradmin;
import com.sun.xml.internal.ws.fault.ServerSOAPFaultException;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class UserAdministrationSoap implements IUserAdministration {
    private final URL url;
    private final QName qname;
    private final Service service;
    private final Brugeradmin ba;

    public UserAdministrationSoap() throws MalformedURLException {
        url = new URL("http://javabog.dk:9901/brugeradmin?wsdl");
        qname = new QName("http://soap.transport.brugerautorisation/", "BrugeradminImplService");
        service = Service.create(url, qname);
        ba = service.getPort(Brugeradmin.class);
    }

    @Override
    public User authenticateUser(String username, String password) throws AuthenticationException {
        try {
            return UserConverter.BrugerToUser(ba.hentBruger(username, password));
        } catch (ServerSOAPFaultException e) {
            throw new AuthenticationException("Wrong username or password.");
        }
    }

    @Override
    public User changePassword(String username, String oldPassword, String newPassword) throws AuthenticationException {
        try {
            return UserConverter.BrugerToUser(ba.ændrAdgangskode(username, oldPassword, newPassword));
        } catch (ServerSOAPFaultException e) {
            throw new AuthenticationException("Wrong username or password.");
        }
    }

    @Override
    public void sendEmail(String username, String password, String subject, String body) throws AuthenticationException {
        try {
            ba.sendEmail(username, password, subject, body);
        } catch (ServerSOAPFaultException e) {
            try { Thread.sleep(1500); } catch (InterruptedException e2) { /* Do nothing */ }
        }
    }

    @Override
    public void sendForgottenPasswordEmail(String username, String message) {
        try {
            ba.sendGlemtAdgangskodeEmail(username, message);
        } catch (ServerSOAPFaultException e) { /* Do nothing */ }
    }

    @Override
    public void setExtraField(String username, String password, String fieldName, Object value) throws AuthenticationException {
        try {
            ba.setEkstraFelt(username, password, fieldName, value);
        } catch (ServerSOAPFaultException e) {
            throw new AuthenticationException("Wrong username or password.");
        }
    }

    @Override
    public Object getExtraField(String username, String password, String fieldName) throws AuthenticationException {
        try {
            return ba.getEkstraFelt(username, password, fieldName);
        } catch (ServerSOAPFaultException e) {
            throw new AuthenticationException("Wrong username or password.");
        }
    }
}
