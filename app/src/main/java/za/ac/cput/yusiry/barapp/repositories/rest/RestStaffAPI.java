package za.ac.cput.yusiry.barapp.repositories.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import za.ac.cput.yusiry.barapp.model.Staff;
import za.ac.cput.yusiry.barapp.repositories.RestAPI;

/**
 * Created by Yusiry on 8/30/2016.
 */
public class RestStaffAPI implements RestAPI<Staff, Integer> {

    final String BASE_URL = "http://app-constructionma.rhcloud.com/api/";

    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public Staff get(Integer id) {
        final String url = BASE_URL+"staff/"+id.toString();
        HttpEntity<Staff> requestEntity = new HttpEntity<Staff>(requestHeaders);
        ResponseEntity<Staff> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Staff.class);
        Staff staff = responseEntity.getBody();
        return staff;
    }

    @Override
    public String post(Staff entity) {
        final String url = BASE_URL+"staff/create";
        HttpEntity<Staff> requestEntity = new HttpEntity<Staff>(entity, requestHeaders);
        HttpEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String put(Staff entity) {
        final String url = BASE_URL+"staff/update/"+entity.getId();
        HttpEntity<Staff> requestEntity = new HttpEntity<Staff>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String delete(Staff entity) {
        final String url = BASE_URL+"/staff/delete/"+entity.getId().toString();
        HttpEntity<Staff> requestEntity = new HttpEntity<Staff>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
        return responseEntity.getBody();
    }

    @Override
    public List<Staff> getAll() {
        List<Staff> staff = new ArrayList<>();
        final String url = BASE_URL+"staff/";
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<Staff[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Staff[].class);
        Staff[] results = responseEntity.getBody();

        for(Staff staff_ : results){
            staff.add(staff_);
        }

        return staff;
    }
}
