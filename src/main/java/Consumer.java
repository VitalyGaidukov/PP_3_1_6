import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class Consumer {
    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();

    private static String GET ="http://94.198.50.185:7081/api/users";

    private static String POST ="http://94.198.50.185:7081/api/users";

    private static String PUT ="http://94.198.50.185:7081/api/users";

    private static String DELETE ="http://94.198.50.185:7081/api/users/3";
    private static String cookie;
    public  void getUsers(){
        // с помощью этого объекты мы делаем запросы к удаленному сервесу

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(GET, HttpMethod.GET, entity,
                String.class);
        cookie = result.getHeaders().getFirst("Set-Cookie");
        System.out.println(result);
    }
    public void createUser(){
        // с помощью этого объекты мы делаем запросы к удаленному сервесу
        User user = new User(3,"James","Brown",36);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Cookie",cookie);
        HttpEntity<User> entity = new HttpEntity<User>(user, headers);

        ResponseEntity<String> result = restTemplate.exchange(POST, HttpMethod.POST, entity,
                String.class);

        System.out.println(result.getBody());
    }
    public void updateUser(){
        // с помощью этого объекты мы делаем запросы к удаленному сервесу
        User user1 = new User(3,"Thomas","Shelby",36);

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        headers.set("Cookie",cookie);

        HttpEntity<User> entity = new HttpEntity<User>(user1, headers);

        ResponseEntity<String> result = restTemplate.exchange(PUT, HttpMethod.PUT, entity,
                String.class);

        System.out.println(result.getBody());
    }
    public void deleteUser(){
        // с помощью этого объекты мы делаем запросы к удаленному сервесу

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        headers.set("Cookie",cookie);

        HttpEntity<User> entity = new HttpEntity<User>(headers);

        ResponseEntity<String> result = restTemplate.exchange(DELETE, HttpMethod.DELETE, entity,
                String.class);

        System.out.println(result.getBody());
    }

    public static void main(String[] args) {
        Consumer consumer = new Consumer();

        consumer.getUsers();
        consumer.createUser();
        consumer.updateUser();
        consumer.deleteUser();




    }
}
