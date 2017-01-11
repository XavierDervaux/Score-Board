package com.cours.dervaux.projetandroid.model;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import com.cours.dervaux.projetandroid.controller.Connection;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetRegister extends AsyncTask<Player, String, InputStreamReader> {

    private Context context;

    public GetRegister(Context context){
        this.context = context;
    }

    @Override
    protected InputStreamReader doInBackground(Player... data) {
        InputStreamReader reader = null;
        try{
            URL url = new URL(RPC_FETCH.REGISTER);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(15000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("pseudo", data[0].getPseudo())
                    .appendQueryParameter("mdp", data[0].getMdp());
            String query = builder.build().getEncodedQuery();
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
                reader = new InputStreamReader(connection.getInputStream());
            connection.disconnect();
        } catch(Exception e){  e.printStackTrace();  }
        return reader;
    }

    protected void onPostExecute(InputStreamReader result) {
        super.onPostExecute(result);
        try{
            if(result != null){
                BufferedReader br = new BufferedReader(result);
                int code = Integer.parseInt(br.readLine());
                ((Connection)this.context).registerResponse(code); //Sending the response
                br.close();
                result.close();
            }
        } catch(Exception e){  e.printStackTrace();  }
    }
}

