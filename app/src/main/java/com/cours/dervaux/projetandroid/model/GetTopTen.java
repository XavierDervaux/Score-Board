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
        InputStreamReader reader = null;

        try{
            this.game = data[0];
            String charset = "UTF-8";
            String query = String.format("jeu=%s",
                    URLEncoder.encode(""+data[0], charset));
            URL url = new URL(Access.URL_TOP_GAME + "?" + query);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                reader = new InputStreamReader(connection.getInputStream());
            }
            connection.disconnect();
        } catch(SocketTimeoutException e){ //Le serveur ne répond pas
            Log.e("Connection", "Error time out server : " + e.toString());
        } catch (IOException e){
            Log.e("Connection", "Error communication server : " + e.toString());
        } catch(Exception e){
            Log.e("Connection", "Error connection server : " + e.toString());
        }

        return reader;
    }

    @Override
    protected void onPostExecute(InputStreamReader result) {
        super.onPostExecute(result);
        List<Score> list = new ArrayList<Score>();
        int code = 1;
        String name;

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
                        ((Top10)this.context).topTenResponse(code,list);
                    }
                }
                jsonReader.endObject();
                result.close();
            }
        } catch(IOException e){
            Log.e("JSON", "Error parse json  : " + e.toString());
        }
    }

    public Score readGame(JsonReader reader) throws IOException{
        String user = null;
        int score = -1;

        reader.beginObject();
        if(reader.hasNext()){
            reader.nextName();
            user = reader.nextString();
            reader.nextName();
            score = reader.nextInt();
        }
        reader.endObject();

        return new Score(user, this.game, score);
    }
}
