package universconception.conception.cegepstefoy.restaurantconcept.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import universconception.conception.cegepstefoy.restaurantconcept.Data.DataBase;
import universconception.conception.cegepstefoy.restaurantconcept.Model.CompteUsager;
import universconception.conception.cegepstefoy.restaurantconcept.Model.Courriel;
import universconception.conception.cegepstefoy.restaurantconcept.Model.Password;
import universconception.conception.cegepstefoy.restaurantconcept.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText courrielInput;
    private EditText passwordInput;
    private EditText confirmPasswordInput;
    private CheckBox checkBoxConditionTrue;

    private List<Courriel> mCourriels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.courrielInput = findViewById(R.id.NameRegisterInput);
        this.passwordInput = findViewById(R.id.courrielRegisterInput);
        this.confirmPasswordInput = findViewById(R.id.confirmPasswordInput);
        this.checkBoxConditionTrue = findViewById(R.id.checkBoxConditionTrue);

        this.checkBoxConditionTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkBoxConditionTrue.setError(null);
            }
        });
    }


    public void onLoginTextClicked(View view) {
        if(this.checkBoxConditionTrue.isChecked()){
            Intent intent = new Intent(RegisterActivity.this, SecondRegisterActivity.class);
            startActivity(intent);
        }else {
            this.checkBoxConditionTrue.setError("Is not checked");
        }
    }

    public boolean validations() {
        //TODO
        Courriel courriel = new Courriel(this.courrielInput.getText().toString());
        if(mCourriels.contains(courriel)){
            this.courrielInput.setError("this user already exists");
            return false;
        }
        this.courrielInput.setError(null);
        return true;
    }

    public void registerUser() {
        if (validations()){
            CompteUsager compteUsager = new CompteUsager(new Courriel(this.courrielInput.getText().toString()),
                                                         new Password(this.passwordInput.getText().toString()));
            DataBase.getInstance().addUser(compteUsager);
        }
    }

    public void passwordDoNotMatch() {
        if (this.passwordInput.getText().toString().equals(this.confirmPasswordInput.getText().toString())) {
            //PASSWORDS FINE
        }
        else {
            // TODO : ERROR - PASSWORD DONT MATCH
        }
    }

}
