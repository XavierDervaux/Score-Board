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

public class GetScore extends AsyncTask<Score, String, InputStreamReader>
{
    private Context context;

    public GetScore(Context context){
        this.context = context;
    }

    @Override
    protected InputStreamReader doInBackground(Score... data) {
        InputStreamReader res = null;

        try{
            String query = String.format("score=%s&jeu=%s&id_pseudo=%s",
                    URLEncoder.encode(""+data[0].getScore(), "UTF-8"),
                    URLEncoder.encode(""+data[0].getGame(), "UTF-8"),
                    URLEncoder.encode(""+data[0].getUser(), "UTF-8") );
            URL url = new URL(RPC_FETCH.ADD_SCORE + "?" + query);
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
        try{
            if(result != null){
                BufferedReader br = new BufferedReader (result);
                code = Integer.parseInt(br.readLine());
                ((AddScore)this.context).scoreResponse(code); //Sending the response
                br.close();
                result.close();
            }
        } catch(Exception e){  e.printStackTrace();  }
    }
}
