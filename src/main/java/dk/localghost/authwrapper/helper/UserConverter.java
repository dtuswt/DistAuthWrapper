package dk.localghost.authwrapper.helper;
import brugerautorisation.data.Bruger;
import dk.localghost.authwrapper.dto.User;

public class UserConverter {
    public static Bruger UserToBruger(User user) {
        Bruger bruger = new Bruger();

        bruger.brugernavn = user.getUsername();
        bruger.email = user.getEmail();
        bruger.sidstAktiv = user.getLastActive();
        bruger.campusnetId = user.getCampusnetId();
        bruger.fornavn = user.getFirstname();
        bruger.efternavn = user.getLastname();
        bruger.adgangskode = user.getPassword();
        bruger.ekstraFelter = user.getExtraFields();

        return bruger;
    }

    public static User BrugerToUser(Bruger bruger) {
        User user = new User();

        user.setUsername(bruger.brugernavn);
        user.setEmail(bruger.email);
        user.setLastActive(bruger.sidstAktiv);
        user.setCampusnetId(bruger.campusnetId);
        user.setStudyRetning(bruger.studeretning);
        user.setFirstname(bruger.fornavn);
        user.setLastname(bruger.efternavn);
        user.setPassword(bruger.adgangskode);
        user.setExtraFields(bruger.ekstraFelter);

        return user;
    }
}
