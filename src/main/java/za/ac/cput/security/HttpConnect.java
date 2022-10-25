package za.ac.cput.security;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnect {
    public static String post(String urlDest, String urlParam)
    {
        try
        {
            HttpURLConnection connect = null;
            URL url = new URL(urlDest);
            connect = (HttpURLConnection)url.openConnection();
            connect.setRequestMethod("POST");
            connect.setRequestProperty("Content-Type",
                    "application/json");
            connect.setRequestProperty("Accept", "application/json");

            connect.setDoOutput(true);
            connect.setDoInput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(
                    connect.getOutputStream ());
            wr.writeBytes (urlParam);
            wr.flush ();
            wr.close ();



            //Get Response
            InputStream is = connect.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = rd.readLine()) != null)
            {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
