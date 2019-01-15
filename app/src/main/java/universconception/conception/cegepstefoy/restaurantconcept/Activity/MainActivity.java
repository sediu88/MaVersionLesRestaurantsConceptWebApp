package universconception.conception.cegepstefoy.restaurantconcept.Activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import universconception.conception.cegepstefoy.restaurantconcept.Data.DataBase;
import universconception.conception.cegepstefoy.restaurantconcept.Model.Courriel;
import universconception.conception.cegepstefoy.restaurantconcept.R;

public class MainActivity extends AppCompatActivity {

    private AppCompatButton registerButton;
    private AppCompatButton loginButton;
    static boolean active = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        active = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.registerButton = findViewById(R.id.RegisterButton);
        this.loginButton = findViewById(R.id.LoginButton);
        generateAdmin();
        loginCheck();
    }

    private void loginCheck() {
        if (DataBase.getInstance().isUserLoggedIn()) {
           if (DataBase.getInstance().isAdminModeEnabled()) {
               this.loginButton.setText("Gerer les commandes");
           }
           else {
               this.loginButton.setVisibility(View.INVISIBLE);
           }
           this.registerButton.setText("Se deconnecter");
       }
    }

    private void generateAdmin() {
        if (DataBase.getInstance().checkIfMailIsInDatabase(new Courriel("gerant"))) {
            //OK
        }
        else {
            DataBase.getInstance().generateBasicAppPrototypeNeeds();
        }
    }

    public void onConsultMenuClicked(View view) {
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    public void onLoginButtonClicked(View view) {
        if (DataBase.getInstance().isAdminModeEnabled()) {
            Intent intent = new Intent(MainActivity.this, AccepterCommandeActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    public void onRegisterButtonClicked(View view) {
        if (DataBase.getInstance().isUserLoggedIn()) {
            DataBase.getInstance().logout();
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    }

    public void userCreatedPopup() {
        Intent intent = getIntent();
        if (intent.getStringExtra("userCreated").toString().length()>0) {
            //TODO SNACKBAR USER CREATED
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.accueil:
                return true;
            case R.id.login:
                Intent intent2 = new Intent(this, LoginActivity.class);
                this.startActivity(intent2);
                return true;
            case R.id.consultMenu:
                Intent intent3 = new Intent(this, MenuActivity.class);
                this.startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
