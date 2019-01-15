package universconception.conception.cegepstefoy.restaurantconcept.Activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.MenuPopupWindow;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import universconception.conception.cegepstefoy.restaurantconcept.Data.DataBase;
import universconception.conception.cegepstefoy.restaurantconcept.Model.Mets;
import universconception.conception.cegepstefoy.restaurantconcept.R;

public class CommanderMetsActivity extends AppCompatActivity {

    private String member_name;
    private int imageNumber;
    private ImageView mView;
    private Button addToOrderButton;
    private Spinner spinner;
    static boolean active = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commander_mets);
        active = true;
        this.addToOrderButton = findViewById(R.id.ajouter);
        this.spinner = findViewById(R.id.drop_down_option);

        if (DataBase.getInstance().isAdminModeEnabled()) {
            this.addToOrderButton.setText("Retirer cet item du menu");
            this.addToOrderButton.setBackgroundColor(Color.parseColor("#ff0000"));
        }

        Spinner spinner = findViewById(R.id.drop_down_option);
        mView = findViewById(R.id.view);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.les_option_quantite, android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Intent intent = getIntent();
        member_name = intent.getStringExtra("member_name");
        imageNumber = intent.getIntExtra("imageNumber", 1);

        mView.setImageResource(imageNumber);

    }

    public void removeItemFromMenu() {
        DataBase.getInstance().removeFromMenu(member_name);
    }


    public void onAddToOrderButtonClicked(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        if (DataBase.getInstance().isAdminModeEnabled()) {
            DataBase.getInstance().removeFromMenu(member_name);
            startActivity(intent);
        }
        else {
            float price = Float.parseFloat(getIntent().getStringExtra("prix"));
            DataBase.getInstance().addToOrder(new Mets(1, member_name, Integer.parseInt(this.spinner.getSelectedItem().toString()),price,1));
            startActivity(intent);
        }
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
