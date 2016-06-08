package com.example.santo_000.mysql_prac;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by santo_000 on 6/5/2016.
 */
public class BackgroundTask extends AsyncTask<String,Void,String>
{
    Context ctx;

    BackgroundTask(Context ctx)
    {
        this.ctx = ctx;
    }

    protected void onPreExecute()
    {
        super.onPreExecute();
    }

    protected  String doInBackground(String...params)
    {
        String reg_url = "http://192.168.1.81/webapp/register.php";
        String fake_url = "unity/webapp/register.php";
        String login_url = "http://10.0.2.2/webapp/login.php";

        String method = params[0];
        if(method.equals("register"))
        {
            String name = params[1];
            try
            {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data = URLEncoder.encode("user_id","UTF-8") +"="+ URLEncoder.encode(name,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                return "Registration Success...yo";
            }
           catch (MalformedURLException e)
           {
               e.printStackTrace();
               return ("friends of england");
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return("Ultimate Failure");
            }

        }
        return method;
    }

    protected void onProgressUpdate(Void...values)
    {
        super.onProgressUpdate(values);
    }

    protected void onPostExecute(String result)
    {
        Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
    }


}
