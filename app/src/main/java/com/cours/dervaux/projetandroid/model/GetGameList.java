package com.cours.dervaux.projetandroid.model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.cours.dervaux.projetandroid.controller.GameList;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetGameList extends AsyncTask<String, String, InputStreamReader> {
    private Context context;

    public GetGameList (Context context){
        this.context = context;
    }

    @Override
    protected InputStreamReader doInBackground(String... data) {
        InputStreamReader res = null;

        try{
            URL url = new URL(RPC_FETCH.LIST_GAME);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
                res = new InputStreamReader(connection.getInputStream());
            connection.disconnect();
        } catch(Exception e){  e.printStackTrace();  }
        return res;
    }

    @Override
    protected void onPostExecute(InputStreamReader result) {
        super.onPostExecute(result);
        String name;
        int code;
        List<String> list = new ArrayList<String>();

        try{
            if(result != null){
                JsonReader jsonReader  = new JsonReader(result);
                jsonReader.beginObject();

                if(jsonReader.hasNext()){
                    name = jsonReader.nextName();
                    if(name.equals("code")){
                        code = jsonReader.nextInt();
                        if(code == 0){
                            jsonReader.nextName();
                            jsonReader.beginArray();
                            while (jsonReader.hasNext()) {
                                jsonReader.beginObject();
                                jsonReader.nextName();
                                if(jsonReader.hasNext()){
                                    list.add(jsonReader.nextString());
                                }
                                jsonReader.endObject();
                            }
                            jsonReader.endArray();
                        }
                        ((GameList)this.context).gameListResponse(code,list); //Sending the response
                    }
                }
                jsonReader.endObject();
                result.close();
            }
        } catch(Exception e){  e.printStackTrace();  }
    }
}
