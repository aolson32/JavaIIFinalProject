/**
 * This class is used to set up the calling of the apis
 * 
 * @author Anna Olson
 */
package javaiifinalprojectforrealmaybe;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class ApiBit 
{
    private Gson gson;
    
    public ApiBit()
    {
        gson = new Gson();
    }
 
    /**
     * this section assigns the url for the Class functions and determines if the api link is valid
     * returns the list of classes in arrayList format
     * @return 
     */
    public ArrayList<RaceAndClass> getClasses()
    {
        String apiUrl = "https://www.dnd5eapi.co/api/classes";
        String json = callApi(apiUrl);
        ArrayList<RaceAndClass> classes;
        Result result;
        
        if(json.startsWith("{"))
        {
            result = gson.fromJson(json, new TypeToken<Result>(){}.getType());
            classes = result.results;
            
            return(classes);
        }
        else
        {
            System.out.println(json);
            return new ArrayList<>();
        }
    }
    /**
     * this section assigns the url for the Race functions and determines if the api link is valid
     * returns the list of races in arrayList format
     * @return 
     */
    public ArrayList<RaceAndClass> getRaces()
    {
        String apiUrl = "https://www.dnd5eapi.co/api/races";
        String json = callApi(apiUrl);
        ArrayList<RaceAndClass> races;
        Result result;
        
        if(json.startsWith("{"))
        {
            result = gson.fromJson(json, new TypeToken<Result>(){}.getType());
            races = result.results;
            
            return(races);
        }
        else
        {
            System.out.println(json);
            return new ArrayList<>();
        }
    }
    
    
    
    /**
     * this calls the api and helps determine if the url is valid
     * @param apiUrl
     * @return 
     */
    public String callApi(String apiUrl)
    {
        // Set up variables to call the URL and read the result.
        URL url;
        InputStream inputStream;
        InputStreamReader inputStreamReader;
        BufferedReader reader = null;
        String jsonResult = "";

        try
        {
            // Create the URL with the address to the server.
            url = new URL(apiUrl);
            
            // Call the url and create a Buffered Reader on the result.
            inputStream = url.openStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
                
            // Keep reading lines while more still exist.
            // Append the result to a String.  Should use a StringBuilder if we
            // are expecting a lot of lines.
            while (line != null) {
                jsonResult += line;
                line = reader.readLine();
            }
        }
        catch (MalformedURLException malformedURLException) {
            // URL was bad.
            jsonResult = malformedURLException.getMessage();
        }
        catch (IOException ioException) {
            // An error occurred during the reading process.
            jsonResult = ioException.getMessage();
        }
        finally
        {
            // Close the reader and the underlying objects.
            try
            {
                if (reader != null)
                {
                    reader.close();
                }
            }
            catch (IOException ioException) {
                jsonResult += ioException.getMessage();
            }
        }
        
        return jsonResult;
    }
    
}
