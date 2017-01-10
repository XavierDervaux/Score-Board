package com.cours.dervaux.projetandroid.model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import com.cours.dervaux.projetandroid.controller.AddScore;
import com.cours.dervaux.projetandroid.model.Score;

public class GetScore extends AsyncTask<Score, String, InputStreamReader>
{
    private Context context;

    public GetScore(Context context){
        this.context = context;
    }

    @Override
    protected InputStreamReader doInBackground(Score... data) {
        InputStreamReader reader = null;

        try{
            String charset = "UTF-8";
            String query = String.format("score=%s&jeu=%s&id_pseudo=%s",
                    URLEncoder.encode(""+data[0].getScore(), charset),
                    URLEncoder.encode(""+data[0].getGame(), charset),
                    URLEncoder.encode(""+data[0].getId(), charset) );
            URL url = new URL(Access.URL_ADD_SCORE + "?" + query);
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
        int code = 1;
        try{
            if(result != null){
                BufferedReader br = new BufferedReader (result);
                code = Integer.parseInt(br.readLine());
                ((AddScore)this.context).scoreResponse(code);
                br.close();
                result.close();
            }
        } catch(IOException e){
            Log.e("TEXT", "Error parse text  : " + e.toString());
        }
    }
}
