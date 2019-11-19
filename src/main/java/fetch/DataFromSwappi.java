package fetch;


import DTO.PersonDTO;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import net.minidev.json.JSONObject;

/**
 *
 * @author jacobfolkehildebrandt
 */

public class DataFromSwappi {
    
    
    public DataFromSwappi() {
    }
    
    public List<PersonDTO> getPeople() throws InterruptedException, ExecutionException{
    final List<PersonDTO> persons = new ArrayList();
    final Integer[] hostID = {1,2,3,4,5};
    final ExecutorService threadpool = Executors.newCachedThreadPool();
    Queue<Future<String>> futures = new ArrayBlockingQueue(hostID.length);
        for (int i = 0; i < hostID.length; i++) {
            final int ID = hostID[i];
            Future<String> future = threadpool.submit(new Callable() {
                @Override
                public String call() throws Exception {
                    return getSwappiData(ID);
                }
            });
            futures.add(future);
        }
        
        List<String> result = new ArrayList();
        while(!futures.isEmpty()){
            Future<String> cp = futures.poll();
            if(cp.isDone()){
                Gson g = new Gson();
                PersonDTO person = g.fromJson(cp.get(), PersonDTO.class);
                persons.add(person);
                
            }else{
                futures.add(cp);
            }
        }
       
        
        threadpool.shutdown();
        
        return persons;
    }
    

    public String getSwappiData(int id) throws MalformedURLException, IOException {
        URL url = new URL("https://swapi.co/api/people/" + id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        con.setRequestProperty("User-Agent", "server"); //remember if you are using SWAPI
        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = null;
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
        }
        scan.close();
        return jsonStr;
    }
    
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        DataFromSwappi swap = new DataFromSwappi();

        swap.getPeople();
        
    }

}
