package authwrapper.transport.soap;

import authwrapper.dto.User;
import authwrapper.helper.UserConverter;
import authwrapper.transport.IUserAdministration;
import authwrapper.transport.SomethingWentWrongException;
import brugerautorisation.transport.soap.Brugeradmin;

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
    public User authenticateUser(String username, String password) throws SomethingWentWrongException {
        final User user = UserConverter.BrugerToUser(ba.hentBruger(username, password));

        if (user == null) throw new SomethingWentWrongException("User not found or wrong password.");

        return user;
    }

    @Override
    public User changePassword(String username, String oldPassword, String newPassword) {
        return UserConverter.BrugerToUser(ba.ændrAdgangskode(username, oldPassword, newPassword));
    }

    @Override
    public void sendEmail(String username, String password, String subject, String body) {
        ba.sendEmail(username, password, subject, body);
    }

    @Override
    public void sendForgottenPasswordEmail(String username, String message) {
        ba.sendGlemtAdgangskodeEmail(username, message);
    }

    @Override
    public void setExtraField(String username, String password, String fieldName, Object value) {
        ba.setEkstraFelt(username, password, fieldName, value);
    }

    @Override
    public Object getExtraField(String username, String password, String fieldName) {
        return ba.getEkstraFelt(username, password, fieldName);
    }
}
