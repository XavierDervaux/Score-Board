package com.cours.dervaux.projetandroid.model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import com.cours.dervaux.projetandroid.controller.UserList;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetUserList extends AsyncTask<String, String, InputStreamReader> {
    private Context context;

    public GetUserList (Context context){
        this.context = context;
    }

    @Override
    protected InputStreamReader doInBackground(String... data) {
        InputStreamReader res = null;

        try{
            URL url = new URL(RPC_FETCH.LIST_USER);
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
        List<String> list = new ArrayList<String>();

        try{
            if(result != null){
                JsonReader jsonReader  = new JsonReader(result);
                jsonReader.beginObject();

                if(jsonReader.hasNext()){
                    String name = jsonReader.nextName();
                    if(name.equals("code")){
                        int code = jsonReader.nextInt();
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
                        ((UserList)this.context).userListResponse(code,list); //Sending the response
                    }
                }
                jsonReader.endObject();
                result.close();
            }
        } catch(Exception e){  e.printStackTrace();  }
    }
}
