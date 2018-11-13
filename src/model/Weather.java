package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class Weather {


    public String getWeather() throws IOException {
        BufferedReader br = null;

        try {
            String apikey = "&APPID=0c79c072fbe3f69e4557d04103f32675 "; //fill this in with the API key they email you
            String currentWeatherQuery = "https://api.openweathermap.org/data/2.5/weather?q=Vancouver,ca";
            String theURL=currentWeatherQuery+apikey;

            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }

            String str = sb.toString();
            ArrayList<String> sbs = splitOnSpace(str);
            ArrayList<String > weather = splitString(sbs.get(3));

            return (weather.get(1));
        } finally {

            if (br != null) {
                br.close();
            }
        }
    }
    private static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(",");
        return new ArrayList<>(Arrays.asList(splits));
    }
    private static ArrayList<String> splitString(String line){
        String[] splits = line.split(":");
        return new ArrayList<>(Arrays.asList(splits));
    }

}

