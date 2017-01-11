package com.cours.dervaux.projetandroid.model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import com.cours.dervaux.projetandroid.controller.Top10;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class GetTopTen extends AsyncTask<String, String, InputStreamReader> {
    private Context context;
    private String game = null;

    public GetTopTen (Context context){
        this.context = context;
    }

    @Override
    protected InputStreamReader doInBackground(String... data) {
        InputStreamReader res = null;
        try{
            this.game = data[0];
            String query = String.format("jeu=%s", URLEncoder.encode(""+data[0], "UTF-8"));
            URL url = new URL(RPC_FETCH.TOP_TEN + "?" + query);
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
        int code = 1;
        String name;
        List<Score> list = new ArrayList<Score>();


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
                                list.add(readGame(jsonReader));
                            }
                            jsonReader.endArray();
                        }
                        ((Top10)this.context).topTenResponse(code,list); //Sending the response
                    }
                }
                jsonReader.endObject();
                result.close();
            }
        } catch(Exception e){  e.printStackTrace();  }
    }

    public Score readGame(JsonReader reader) throws IOException{
        Score res = null;

        reader.beginObject();
        if(reader.hasNext()){
            reader.nextName();
                String user = reader.nextString();
            reader.nextName();
                int score = reader.nextInt();
            res = new Score(user, this.game, score);
        }
        reader.endObject();
        return res;
    }
}
