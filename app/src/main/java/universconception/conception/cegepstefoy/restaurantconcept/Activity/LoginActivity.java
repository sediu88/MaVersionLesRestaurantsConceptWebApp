package universconception.conception.cegepstefoy.restaurantconcept.Activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import universconception.conception.cegepstefoy.restaurantconcept.Data.DataBase;
import universconception.conception.cegepstefoy.restaurantconcept.Model.Courriel;
import universconception.conception.cegepstefoy.restaurantconcept.Model.Password;
import universconception.conception.cegepstefoy.restaurantconcept.R;

public class LoginActivity extends AppCompatActivity {

    private EditText courrielEditText;
    private EditText passwordEditText;
    private TextView mTextViewClicked;
    static boolean active = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        active = true;

        this.courrielEditText = findViewById(R.id.courrielLoginInput);
        this.passwordEditText = findViewById(R.id.passwordLoginInput);
        this.mTextViewClicked = findViewById(R.id.registerText);

    }

    public void onRegisterTextClicked(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public boolean validations() {
        //TODO ?

        return true;
    }

    public boolean loginCheck() {
        return DataBase.getInstance().checkLoginInfo(new Courriel(this.courrielEditText.getText().toString()), new Password(this.passwordEditText.getText().toString()));
    }

    public void onConnectButtonClicked(View view) {
        if (validations()) {
            if (loginCheck()) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                this.courrielEditText.setError(null);
                this.setCurrentUser();
                startActivity(intent);
            }
            else {
                this.courrielEditText.setError("Cette combinaison courriel/mot de passe n'existe pas!");
            }
        }
    }

    private void setCurrentUser() {
        DataBase.getInstance().setCurrentUser(DataBase.getInstance().getUser(new Courriel(this.courrielEditText.getText().toString())));
    }


    //MainMenuButton
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    //MainMenuButton
    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.accueil:
                Intent intent1 = new Intent(this, MainActivity.class);
                this.startActivity(intent1);
                return true;
            case R.id.login:
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
