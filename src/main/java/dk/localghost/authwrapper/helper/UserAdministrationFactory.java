package dk.localghost.authwrapper.helper;

import dk.localghost.authwrapper.dto.Speed;
import dk.localghost.authwrapper.transport.ConnectivityException;
import dk.localghost.authwrapper.transport.IUserAdministration;
import dk.localghost.authwrapper.transport.rmi.UserAdministrationRmi;
import dk.localghost.authwrapper.transport.soap.UserAdministrationSoap;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class UserAdministrationFactory {
    public static IUserAdministration getUserAdministration(Speed speed) throws ConnectivityException {
        if (speed == Speed.LUDICROUS_SPEED) { // RMI
            try {
                return new UserAdministrationRmi();
            } catch (RemoteException | NotBoundException | MalformedURLException e) {
                throw new ConnectivityException("Failed to connect to server. " + e.getMessage(), e.getCause());
            }
        } else  if (speed == Speed.SLOW) { // SOAP
            try {
                return new UserAdministrationSoap();
            } catch (MalformedURLException e) {
                throw new ConnectivityException("Failed to connect to server. " + e.getMessage(), e.getCause());
            }
        }

        return null;
    }
}
