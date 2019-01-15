package universconception.conception.cegepstefoy.restaurantconcept.Activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import universconception.conception.cegepstefoy.restaurantconcept.Data.DataBase;
import universconception.conception.cegepstefoy.restaurantconcept.Model.CustomerAdapter;
import universconception.conception.cegepstefoy.restaurantconcept.Model.Mets;
import universconception.conception.cegepstefoy.restaurantconcept.R;

public class MenuActivity extends AppCompatActivity {

    List<Mets> mMets = new ArrayList<>();
    List<String> mets_names = new ArrayList();
    TypedArray profile_pics;
    List<String> price = new ArrayList<>();
    ListView mylistview;
    private FloatingActionButton addToMenuButton;
    private Button orderButton;
    CustomerAdapter adapter;
    static boolean active = false;
    private static final int PICK_IMAGE = 100;
    Mets met;
    Uri imageUri;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        active = true;


        this.addToMenuButton = findViewById(R.id.AddToMenuButton);
        this.orderButton = findViewById(R.id.readyToOrderButton);

        adminCheck();
        if (DataBase.getInstance().isUserLoggedIn() && DataBase.getInstance().getCommande().getMetsCommande().size()>0 && !DataBase.getInstance().isAdminModeEnabled()) {
            //FINE
        }
        else {
            this.orderButton.setVisibility(View.INVISIBLE);
        }

        mMets.add(new Mets(1, "Poulet", 1, 5.88f, 0));
        mMets.add(new Mets(2, "Boueuf", 1, 3.13f, 1));
        mMets.add(new Mets(3, "Boissons", 1, 1.20f, 2));
        mMets.add(new Mets(4, "Salades", 1, 2.50f, 3));
        mMets.add(new Mets(5, "Desserts", 1, 5.50f, 4));


        profile_pics = getResources().obtainTypedArray(R.array.profile_pics);
        for(int i = 0; i < mMets.size(); i ++){
            mets_names.add(mMets.get(i).getNomMet());
            price.add(mMets.get(i).getPrice().toString());
            mMets.get(i).setProfile_pic_id(profile_pics.getResourceId(i, -1));
        }


        mylistview = findViewById(R.id.list);
        adapter = new CustomerAdapter(this, mMets);
        mylistview.setAdapter(adapter);


        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String member_name = mMets.get(position).getNomMet();
                int imageNumber = mMets.get(position).getProfile_pic_id();

                Intent intent = new Intent(MenuActivity.this, CommanderMetsActivity.class);
                intent.putExtra("member_name", member_name);
                intent.putExtra("prix",Float.toString(mMets.get(position).getPrice()));
                intent.putExtra("imageNumber", imageNumber);

                startActivity(intent);
            }
        });

    }

    private void adminCheck() {
        if (DataBase.getInstance().isAdminModeEnabled()) {
            // OK
        }
        else {
            //Fonctionne malgre l'erreur -- NE PAS CORRIGER
            this.addToMenuButton.setVisibility(View.INVISIBLE);
        }
    }





    public void onAddToMenuButton(View view) {
        // Intent galery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        // startActivityForResult(galery, PICK_IMAGE);
        met = new Mets(6, "Nouveau Met", 8, 13.88f, mMets.get(3).getProfile_pic_id());
        mMets.add(met);

        mylistview.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_OK && resultCode == PICK_IMAGE){
            imageUri = data.getData();
//            met.setProfile_pic_id();

        }
    }

    public void onReadyToOrderButtonClicked(View view) {
        Intent intent = new Intent(this, PasserUneCommande.class);
        startActivity(intent);
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
                    return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}