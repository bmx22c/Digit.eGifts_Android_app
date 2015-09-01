package com.example.bmx22c.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import com.mopub.common.MoPub;
import io.fabric.sdk.android.Fabric;



public class Accueil extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new MoPub());
        setContentView(R.layout.activity_accueil);

        Resources res_bienvenue = getResources();
        String bienvenue = String.format(res_bienvenue.getString(R.string.bienvenue));
        CharSequence styledText_bienvenue = Html.fromHtml(bienvenue);

        Resources res_Intro = getResources();
        String Intro = String.format(res_Intro.getString(R.string.Intro));
        CharSequence styledText_Intro = Html.fromHtml(Intro);

        Resources res_MSG_Instru = getResources();
        String MSG_Instru = String.format(res_MSG_Instru.getString(R.string.MSG_Instru));
        CharSequence styledText_MSG_Instru = Html.fromHtml(MSG_Instru);

        final Button btn_CC_Trackmania2_18_04_2015 = (Button)findViewById(R.id.CC_Trackmania2_18_04_2015);
        btn_CC_Trackmania2_18_04_2015.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.CC_Trackmania2_18_04_2015:
                Intent intent = new Intent(this, CC_Trackmania2_18_04_2015.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accueil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.informations_param) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Informations");
            alertDialog.setMessage(getResources().getString(R.string.MSG_Instru));
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Add your code for the button here.
                }
            });
            alertDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }
}
