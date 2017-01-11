package com.cours.dervaux.projetandroid.model;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.cours.dervaux.projetandroid.controller.Connection;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

public class GetConnect extends AsyncTask<Player, String, InputStreamReader> {

    private Context context;

    public GetConnect(Context context){
        this.context = context;
    }

    @Override
    protected InputStreamReader doInBackground(Player... data) {
        InputStreamReader reader = null;
        try{
            URL url = new URL(RPC_FETCH.CONNECTION);
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
        int code = 1, id = -1;
        try{
            if(result != null){
                JsonReader jsonReader  = new JsonReader(result);
                jsonReader.beginObject();
                if(jsonReader.hasNext()){
                    String name = jsonReader.nextName();
                    if(name.equals("code")){
                        code = jsonReader.nextInt();
                        if(code == 0){
                            jsonReader.nextName();
                            id = jsonReader.nextInt();
                        }
                    }
                }
                ((Connection)this.context).connectResponse(id, code); //Sending the response
                jsonReader.endObject();
                result.close();
            }
        } catch(Exception e){  e.printStackTrace();  }
    }
}

