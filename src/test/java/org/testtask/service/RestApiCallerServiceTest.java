package org.testtask.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.testtask.model.dto.LoginResponse;
import org.testtask.model.dto.StoreDto;
import org.testtask.model.dto.StoreResponse;
import org.testtask.util.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RestApiCallerServiceTest {
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private RestApiCaller service = new RestApiCallerService();
    private final TestUtils testUtils = new TestUtils();
    private final String testApiPath = "https://testapi.com";
    private final String responseBodyIsEmptyErrorMessage = "Response body is empty";
    private final String wrongResponseStatusErrorMessage = "Wrong Response status: " + HttpStatus.I_AM_A_TEAPOT +
            ", expected: " + HttpStatus.OK;

    @BeforeEach
    private void setTestApiPath() {
        ReflectionTestUtils.setField(service, "loginApiPath", testApiPath);
        ReflectionTestUtils.setField(service, "storesApiPath", testApiPath);
    }

    @Test
    void getBearerToken_OK() {
        String token = RandomStringUtils.random(120, true, true);
        LoginResponse response = new LoginResponse(token, 86400, "bearer", new ArrayList<>());
        String testApiPath = "https://testapi.com";
        doReturn(new ResponseEntity<>(response, HttpStatus.OK)).when(restTemplate).exchange(
                eq(testApiPath),
                eq(HttpMethod.POST),
                any(),
                Mockito.<Class<LoginResponse>>any()
        );
        String result = service.getBearerToken();

        assertEquals(token, result);
    }

    @Test
    void testGetBearerToken_ResponseBodyEmpty() {
        doReturn(new ResponseEntity<>(null, HttpStatus.OK)).when(restTemplate).exchange(
                eq(testApiPath),
                eq(HttpMethod.POST),
                any(),
                Mockito.<Class<LoginResponse>>any()
        );
        IllegalStateException result = assertThrows(IllegalStateException.class, () -> service.getBearerToken());

        assertEquals(responseBodyIsEmptyErrorMessage, result.getMessage());
    }

    @Test
    void testGetBearerToken_WrongResponseStatus() {
        doReturn(new ResponseEntity<>(null, HttpStatus.I_AM_A_TEAPOT)).when(restTemplate).exchange(
                eq(testApiPath),
                eq(HttpMethod.POST),
                any(),
                Mockito.<Class<LoginResponse>>any()
        );
        IllegalStateException result = assertThrows(IllegalStateException.class, () -> service.getBearerToken());

        assertEquals(wrongResponseStatusErrorMessage, result.getMessage());
    }

    @Test
    void testGetStoreslist_OK() {
        List<StoreDto> dtoList = generateStoreDtoList();
        StoreResponse response = new StoreResponse(1, 1, dtoList.size(), dtoList);
        String testApiPath = "https://testapi.com";
        ReflectionTestUtils.setField(service, "token", RandomStringUtils.random(120, true, true));
        ResponseEntity<StoreResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        doReturn(responseEntity).when(restTemplate).exchange(
                eq(testApiPath),
                eq(HttpMethod.GET),
                Mockito.<HttpEntity>any(),
                Mockito.<Class<StoreResponse>>any()
        );
        List<StoreDto> result = service.getStoresList();

        assertEquals(dtoList, result);
    }

    private List<StoreDto> generateStoreDtoList() {
        List<StoreDto> result = new ArrayList<>();
        result.add(testUtils.generateTestStoreDto());
        result.add(testUtils.generateTestStoreDto());
        result.add(testUtils.generateTestStoreDto());
        result.add(testUtils.generateTestStoreDto());
        return result;
    }

    @Test
    void testGetStoreslist_ResponseBodyEmpty() {
        String testApiPath = "https://testapi.com";
        ReflectionTestUtils.setField(service, "token", RandomStringUtils.random(120, true, true));
        ResponseEntity<StoreResponse> responseEntity = new ResponseEntity<>(null, HttpStatus.OK);
        doReturn(responseEntity).when(restTemplate).exchange(
                eq(testApiPath),
                eq(HttpMethod.GET),
                Mockito.<HttpEntity>any(),
                Mockito.<Class<StoreResponse>>any()
        );
        IllegalStateException result = assertThrows(IllegalStateException.class, () -> service.getStoresList());

        assertEquals(responseBodyIsEmptyErrorMessage, result.getMessage());
    }

    @Test
    void testGetStoreslist_WrongResponseStatus() {
        String testApiPath = "https://testapi.com";
        ReflectionTestUtils.setField(service, "token", RandomStringUtils.random(120, true, true));
        ResponseEntity<StoreResponse> responseEntity = new ResponseEntity<>(null, HttpStatus.I_AM_A_TEAPOT);
        doReturn(responseEntity).when(restTemplate).exchange(
                eq(testApiPath),
                eq(HttpMethod.GET),
                Mockito.<HttpEntity>any(),
                Mockito.<Class<StoreResponse>>any()
        );
        IllegalStateException result = assertThrows(IllegalStateException.class, () -> service.getStoresList());

        assertEquals(wrongResponseStatusErrorMessage, result.getMessage());
    }
}