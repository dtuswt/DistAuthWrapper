package authwrapper.helper;

import authwrapper.dto.Speed;
import authwrapper.transport.IUserAdministration;
import authwrapper.transport.SomethingWentWrongException;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class UserAdministrationFactory {
    public static IUserAdministration getUserAdministration(Speed speed) throws SomethingWentWrongException {
        if (speed == Speed.LUDICROUS_SPEED) {
            // rmi
            try {
                return new authwrapper.transport.rmi.UserAdministrationRmi();
            } catch (RemoteException | NotBoundException | MalformedURLException e) {
                throw new SomethingWentWrongException("Failed to connect to server. " + e.getMessage(), e.getCause());
            }
        } else  if (speed == Speed.SLOW){
            try {
                return new authwrapper.transport.soap.UserAdministrationSoap();
            } catch (MalformedURLException e) {
                throw new SomethingWentWrongException("Failed to connect to server. " + e.getMessage(), e.getCause());
            }
        }

        throw new SomethingWentWrongException("Something went.... you guessed it.");
    }
}
