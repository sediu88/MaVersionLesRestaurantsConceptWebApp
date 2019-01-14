package universconception.conception.cegepstefoy.restaurantconcept.Data;

import android.arch.persistence.room.Database;

import java.util.ArrayList;
import java.util.List;

import universconception.conception.cegepstefoy.restaurantconcept.Model.CompteUsager;
import universconception.conception.cegepstefoy.restaurantconcept.Model.Courriel;
import universconception.conception.cegepstefoy.restaurantconcept.Model.Menu;
import universconception.conception.cegepstefoy.restaurantconcept.Model.Password;

public class DataBase {

    private static final DataBase instance = new DataBase();
    private  List<CompteUsager> compteUsagers;
    private  Menu menu;


    private DataBase() {
        //Singleton
        this.compteUsagers = new ArrayList<>();
        this.menu = new Menu();
    }

    public static DataBase getInstance() {
        if (instance == null) {
            return new DataBase();
        }
        else {
            return instance;
        }
    }

    public void addUser(CompteUsager pCompteUsager){
        this.compteUsagers.add(pCompteUsager);
    }


    public void addUser(Courriel courriel, Password password) {
        this.compteUsagers.add(new CompteUsager(courriel,password));
    }

    public boolean checkLoginInfo(Courriel courriel, Password password) {
        for (CompteUsager compte : compteUsagers){
            if (compte.getCourriel().getCourriel().equals(courriel.getCourriel())) {
                if (compte.getPassword().getPassword().equals(password.getPassword())){
                    return true;
                }
            }
        }
        return false;
    }

}
