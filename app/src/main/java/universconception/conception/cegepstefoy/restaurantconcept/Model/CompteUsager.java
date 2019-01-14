package universconception.conception.cegepstefoy.restaurantconcept.Model;

public class CompteUsager {

    private Personne personne;
    private Password password;
    private Courriel courriel;


    public CompteUsager(Courriel courriel, Password password) {
        this.password = password;
        this.courriel = courriel;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public Courriel getCourriel() {
        return courriel;
    }

    public void setCourriel(Courriel courriel) {
        this.courriel = courriel;
    }
}
