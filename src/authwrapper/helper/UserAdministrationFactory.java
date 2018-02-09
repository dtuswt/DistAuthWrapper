package authwrapper.helper;

import authwrapper.dto.Speed;
import authwrapper.transport.ConnectivityException;
import authwrapper.transport.IUserAdministration;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class UserAdministrationFactory {
    public static IUserAdministration getUserAdministration(Speed speed) throws ConnectivityException {
        if (speed == Speed.LUDICROUS_SPEED) { // RMI
            try {
                return new authwrapper.transport.rmi.UserAdministrationRmi();
            } catch (RemoteException | NotBoundException | MalformedURLException e) {
                throw new ConnectivityException("Failed to connect to server. " + e.getMessage(), e.getCause());
            }
        } else  if (speed == Speed.SLOW) { // SOAP
            try {
                return new authwrapper.transport.soap.UserAdministrationSoap();
            } catch (MalformedURLException e) {
                throw new ConnectivityException("Failed to connect to server. " + e.getMessage(), e.getCause());
            }
        }

        return null;
    }
}
