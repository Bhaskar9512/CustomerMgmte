package com.customer.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    private String baseUrl;
    private String authEndpoint;

    @Value("${api.customer-list-endpoint}")
    private String customerListEndpoint;

    public String authenticateAndGetToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String authUrl = baseUrl + authEndpoint;
        String credentials = "{\"login_id\":\"test@sunbasedata.com\",\"password\":\"Test@123\"}";

        HttpEntity<String> request = new HttpEntity<>(credentials, headers);
        ResponseEntity<String> response = new RestTemplate().postForEntity(authUrl, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }

        return null;
    }

    public String getCustomerList(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        String customerListUrl = baseUrl + customerListEndpoint + "?cmd=get_customer_list";

        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = new RestTemplate().exchange(customerListUrl, HttpMethod.GET, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }

        return null;
    }
}
