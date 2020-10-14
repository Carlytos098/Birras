import ar.com.birra.BirraApplication;
import ar.com.birra.controller.BirrasApiController;
import ar.com.birra.controller.MeetApiController;
import ar.com.birra.exceptions.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

/**
 * @author Carlos
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BirraApplication.class)
@SpringBootTest
public class BirraApplicationTests {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private BirrasApiController birrasApiController;
    private MeetApiController meetApiController;

    String[] meetIds = {"5f84b6441c7e2c4bb59a372b", "123456789"};

    @Test
    public void testNumberOfBoxesToBuy() {
        for (String id : meetIds) {
            try {
                final String uri = "http://localhost:8080/api/birra/number-of-boxes-to-buy?meetupID=%s";
                System.out.printf((uri) + "%n", id);
                ResponseEntity<String> response = restTemplate.exchange(format(uri, id),
                        HttpMethod.GET, getHeaders(),
                        String.class);
                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals("{\"numberOfBoxes\":1,\"numberOfGuests\":1,\"dateOfMeet\":\"Tue Oct 20 10:15:00 ART 2020\",\"temperature\":{\"date\":\"Tue Oct 20 10:15:00 ART 2020\",\"temperature\":21.03,\"feels_like\":17.76}}", response.getBody());
            } catch (HttpClientErrorException e) {
                assertEquals(HttpStatus.NOT_FOUND, HttpStatus.valueOf(e.getRawStatusCode()));
            }
        }
    }

    @Test
    public void testTemperaturePerDay() {
        for (String id : meetIds) {
            try {
                final String uri = "http://localhost:8080/api/birra/temperature-per-meetup?meetupID=%s";
                ResponseEntity<String> response = restTemplate.exchange(format(uri, id),
                        HttpMethod.GET, getHeaders(),
                        String.class);
                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals("{\"date\":\"Tue Oct 20 10:15:00 ART 2020\",\"temperature\":21.03,\"feels_like\":17.76}", response.getBody());
            } catch (HttpClientErrorException e) {
                assertEquals(HttpStatus.NOT_FOUND, HttpStatus.valueOf(e.getRawStatusCode()));
            }
        }
    }



    private HttpEntity<String> getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("username", "camoreno");
        headers.add("token", "asd12312312as123123");
        return new HttpEntity<>(headers);
    }
}