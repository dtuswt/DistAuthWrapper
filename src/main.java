import brugerautorisation.transport.rmi.Brugeradmin;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import brugerautorisation.data.Bruger;

public class main {
    public static void main(String[] args) {
        Brugeradmin ba= null;

        try {
            ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
        } catch (MalformedURLException e) {
            System.err.println("Invalid url were given.");
        } catch (RemoteException|NotBoundException e) {
            System.err.println("Another error: " + e.getMessage());
        }

        if (ba == null) return;

        final String username = "s165241";
        final String password = "kodec7801k";

        Bruger b = null;

        try {
            b = ba.hentBruger(username, password);
        } catch (RemoteException e) {
            System.err.println("Failed to fetch a user: " + e.getMessage());
        }

        if (b == null) return;

        System.out.println(b);
    }
}
