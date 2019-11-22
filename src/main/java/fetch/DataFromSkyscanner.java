package fetch;

import DTO.FlightInfoDTO;
import DTO.ItinerariesDTO;
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
import org.json.JSONArray;

/**
 *
 * @author jacobfolkehildebrandt
 */
public class DataFromSkyscanner {

    public DataFromSkyscanner() {
    }

    public String getSessionKey(String outboundDate, String cabinClass, String originPlace, String destinationPlace, int adults ) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/pricing/v1.0")
                .header("X-RapidAPI-Host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .header("X-RapidAPI-Key", "4dfa3d7cb0msh7701660655f1502p13c7cbjsn3a351650d218")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("outboundDate", outboundDate) // ("outboundDate", "2019-12-01")
                .field("cabinClass", cabinClass)  // ("cabinClass", "business")
                .field("children", 0)
                .field("infants", 0)
                .field("country", "DK")
                .field("currency", "DKK")
                .field("locale", "da-DK")
                .field("originPlace", originPlace) // ("originPlace", "SFO-sky")
                .field("destinationPlace", destinationPlace) //("destinationPlace", "LHR-sky")
                .field("adults", adults) // ("adults", 1)
                .asJson();
        
        String sessionKey = "";
        String location = "";

        for (Map.Entry<String, List<String>> entry : response.getHeaders().entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (key.equals("Location")) {
                location = value.toString();
            }

        }

            System.out.println("----- location: "+ location);
        for (int i = location.length() - 37; i < location.length() - 1; i++) {
            sessionKey += location.charAt(i);
        }
        
        return sessionKey;
    }
    

    public List<FlightInfoDTO> getFlightData(String outboundDate, String cabinClass, String originPlace, String destinationPlace, int adults) throws UnirestException {
        String sessionkey = getSessionKey(outboundDate, cabinClass, originPlace, destinationPlace, adults);
        HttpResponse<JsonNode> response = Unirest.get(
                "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/pricing/uk2/v1.0/"
                + sessionkey + "?pageIndex=0&pageSize=10")
                .header("X-RapidAPI-Host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .header("X-RapidAPI-Key", "4dfa3d7cb0msh7701660655f1502p13c7cbjsn3a351650d218")
                .asJson();

        JSONArray listItineraries = response.getBody().getObject().getJSONArray("Itineraries");
        JSONArray listLegs = response.getBody().getObject().getJSONArray("Legs");
        JSONArray listPricingOptions;
        String outboundLegId;
        double price;
        String deeplinkUrl;
        List<ItinerariesDTO> listItinerariesDTO = new ArrayList();

        // Flightinfo
        String id;
        String departure;
        String arrival;
        int duration;
        List<FlightInfoDTO> listFlightInfoDTO = new ArrayList();

        for (int i = 0; i < listItineraries.length(); i++) {
            listPricingOptions = (JSONArray) listItineraries.getJSONObject(i).get("PricingOptions");
            outboundLegId = listItineraries.getJSONObject(i).get("OutboundLegId").toString();
            for (int j = 0; j < listPricingOptions.length(); j++) {
                price = (double) listPricingOptions.getJSONObject(j).get("Price");
                deeplinkUrl = listPricingOptions.getJSONObject(j).get("DeeplinkUrl").toString();
                ItinerariesDTO itinerariesDTO = new ItinerariesDTO(outboundLegId, price, deeplinkUrl);
                listItinerariesDTO.add(itinerariesDTO);
                // System.out.println("DTO----------- " + itinerariesDTO);
            }
            //System.out.println("Legs: " + listLegs.length() + "---- Itine: " + listItineraries.length());
        }
        for (int i = 0; i < listLegs.length(); i++) {
            id = listLegs.getJSONObject(i).get("Id").toString();
            departure = listLegs.getJSONObject(i).get("Departure").toString();
            arrival = listLegs.getJSONObject(i).get("Arrival").toString();
            duration = (int) listLegs.getJSONObject(i).get("Duration");
            for (int j = 0; j < listItinerariesDTO.size(); j++) {
                if (listItinerariesDTO.get(j).getOutboundLegId().equals(id)) {
                    FlightInfoDTO flightInfoDTO = new FlightInfoDTO(id, originPlace, destinationPlace, departure, arrival, duration, listItinerariesDTO.get(j).getPrice(), listItinerariesDTO.get(j).getDeeplinkUrl());
                    listFlightInfoDTO.add(flightInfoDTO);
                }

            }

        }

        return listFlightInfoDTO;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, UnirestException {
        DataFromSkyscanner swap = new DataFromSkyscanner();
        System.out.println(swap.getFlightData("2019-12-01", "business", "SFO-sky", "LHR-sky", 1));

    }

}
