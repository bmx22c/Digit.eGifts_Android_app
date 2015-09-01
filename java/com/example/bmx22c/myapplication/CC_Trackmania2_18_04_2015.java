package com.example.bmx22c.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.services.concurrency.AsyncTask;


public class CC_Trackmania2_18_04_2015 extends ActionBarActivity {

    String name;
    InputStream is=null;
    String result=null;
    String line=null;
    int code;
    String deja_pris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(R.string.TITLE_CC_Trackmania2_18_04_2015);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cc__trackmania2_18_04_2015);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    public void Btn_Participer(View view){

        final EditText Username = (EditText) findViewById(R.id.Twitter_Username);
        final String Username_f = Username.getText().toString();

        if(Username_f.startsWith("@") && Username.length() < 15 && Username.length() > 3) {
            insert();
        }else{

        }

    }

    public void insert(){

        final EditText Username = (EditText) findViewById(R.id.Twitter_Username);
        final String Username_f = Username.getText().toString();

        new DownloadWebpageTask().execute("http://digit-egifts.22web.org/Android/insert.php", Username_f);
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String ...urls){

            // params comes from the execute() call: params[0] is the url.
            try {
                // Initialize your parameters
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("name",urls[1]));

                // Make your HTTP Request
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(urls[0]);
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                is = entity.getContent();

                // Read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "");
                }
                is.close();
                result = sb.toString();

                // Return the response.
                return result;
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }

        }

        // onPostExecute gets the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            // Read your response into a JSONObject and do stuff...

            try {
                JSONObject json_data = new JSONObject(result);
                code = (json_data.getInt("code"));

                    if(code == 1){

                        Toast.makeText(getBaseContext(), "Inserted Successfully", Toast.LENGTH_SHORT).show();
                    }else{
                        /*
                        AlertDialog.Builder NOP = new AlertDialog.Builder(CC_Trackmania2_18_04_2015.this);
                        NOP.setTitle(R.string.Erreur);
                        NOP.setMessage(getResources().getString(R.string.NOP_Participation));
                        */
                        Toast.makeText(getBaseContext(), "Sorry, Try Again", Toast.LENGTH_LONG).show();
                        }
                    }catch (JSONException e){
                    Log.e("lol", "JSONException", e);
            }
        }

    }



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_cc__trackmania2_18_04_2015, container, false);
            return rootView;
        }
    }
}
