package fetch;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author jacobfolkehildebrandt
 */

public class DataFromSwappi {
    
    
    public DataFromSwappi() {
    }
    
    public String getKey() throws UnirestException{
        String sessionKey = "";
        DataFromSwappi swap = new DataFromSwappi();
        String location = "";
        
        for (Map.Entry<String, List<String>> entry : swap.getTestData().getHeaders().entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            if(key.equals("Location")){
                location = value.toString();
            }
            
        }
        
        for(int i = location.length()-37; i < location.length()-1; i++){
            sessionKey += location.charAt(i);
        }
       
        
        return sessionKey;
    }
    

    public List<String> getUKData() throws MalformedURLException, IOException {
        List<String> list = new ArrayList();
        URL url = new URL("http://partners.api.skyscanner.net/apiservices/pricing/v1.0");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("x-rapidapi-host",  "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com");
        con.setRequestProperty("x-rapidapi-key",  "4dfa3d7cb0msh7701660655f1502p13c7cbjsn3a351650d218");
        con.setRequestProperty("content-type",  "application/x-www-form-urlencoded");
        
        Map<String, List<String>> map = con.getHeaderFields();
        for(Map.Entry entry : map.entrySet()){
        list.add(entry.getKey() + " : " + entry.getValue());
    }
        
        
        return list;
    }
    public HttpResponse<JsonNode> getTestData() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/pricing/v1.0")
.header("X-RapidAPI-Host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
.header("X-RapidAPI-Key", "4dfa3d7cb0msh7701660655f1502p13c7cbjsn3a351650d218")
.header("Content-Type", "application/x-www-form-urlencoded")
.field("outboundDate", "2019-12-10")
.field("cabinClass", "business")
.field("children", 0)
.field("infants", 0)
.field("country", "US")
.field("currency", "USD")
.field("locale", "en-US")
.field("originPlace", "SFO-sky")
.field("destinationPlace", "LHR-sky")
.field("adults", 1)
.asJson();
      return response;
    }
    
    public JsonNode getFlightData() throws UnirestException{
        String sessionkey = getKey();
        HttpResponse<JsonNode> response = Unirest.get(
                "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/pricing/uk2/v1.0/"
                        + sessionkey + "?pageIndex=0&pageSize=10")
.header("X-RapidAPI-Host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
.header("X-RapidAPI-Key", "4dfa3d7cb0msh7701660655f1502p13c7cbjsn3a351650d218")
.asJson();
        
        
        return response.getBody();
    }
    
    
    public static void main(String[] args) throws InterruptedException, ExecutionException, UnirestException {
        DataFromSwappi swap = new DataFromSwappi();
//        String location = "";
//        
//        for (Map.Entry<String, List<String>> entry : swap.getTestData().getHeaders().entrySet()) {
//            Object key = entry.getKey();
//            Object value = entry.getValue();
//            System.out.println(key + ": " + value);
//            if(key.equals("Location")){
//                location = value.toString();
//            }
//            
//        }
//        String stuff = "";
//        System.out.println("----------------------36-------------");
//        for(int i = location.length()-37; i < location.length()-1; i++){
//            stuff += location.charAt(i);
//        }
//        System.out.println(stuff);
        
       // System.out.println(swap.getFlightData());
        
        
        
        
        
        
        //System.out.println("HEADER: " + swap.getTestData().getHeaders().toString());
        //System.out.println("RAWBODY: " + swap.getTestData().getRawBody().toString());
        //System.out.println("BODY" + swap.getTestData().getBody().getArray().toString());
        
     
     
    
        
        
        
    }

}
