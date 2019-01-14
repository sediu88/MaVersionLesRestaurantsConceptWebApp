package universconception.conception.cegepstefoy.restaurantconcept.Model;

public class Gerant extends Personne{


    private int gerantId;

    public Gerant(String prenom, String nom, Courriel courriel, Password password, int gerantId) {
        super(prenom, nom, courriel, password);
        this.gerantId = gerantId;
    }

    public int getGerantId() {
        return gerantId;
    }

    public void setGerantId(int gerantId) {
        this.gerantId = gerantId;
    }
}
