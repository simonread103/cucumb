package com.cuc;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Matchers.anySetOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Simon on 15/11/2016.
 */
public class TitleLookupServiceTest {
    private TitleLookupService service;

    @Before
    public void setUp() throws Exception {
        service = new TitleLookupService();
    }

    @Test
    public void getTitleById_5() throws Exception {

        Optional<String> t = service.getTitleById("5");
        assertThat(t.get(),is(equalTo("eaque aut omnis a")));
    }

    @Test
    public void getTitleById_null() throws Exception {

        Optional<String> t = service.getTitleById(null);
        assertFalse(t.isPresent());
    }

    @Test(expected=IOException.class)
    public void getTitleById_exc() throws Exception {

        service.setAlbumsUri("https://jsonplaceholder.typicode.cm/albums?id=");
        Optional<String> t = service.getTitleById("5");
    }

    @Test
    public void getJsonResponse() {

        HttpClient httpClient = mock(HttpClient.class);
        service.setClient(httpClient);
        HttpGet httpGet = mock(HttpGet.class);
        HttpResponse httpResponse = mock(HttpResponse.class);
        HttpEntity httpEntity = null;
        try {
            httpEntity = new StringEntity("x");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            when(httpClient.execute(httpGet)).thenReturn(httpResponse);
            when(httpResponse.getEntity()).thenReturn(httpEntity);
            Optional<String> opt = service.getJSONresponse(httpGet);
            assertThat(opt.get(),is(equalTo("x")));

        } catch (IOException e) {
            fail();
        }

    }

}