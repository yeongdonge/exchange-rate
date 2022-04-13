package exchange.rate.service;

import exchange.rate.dc.ExchangeResultDc;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApiHandlerImplTest {

    @Autowired
    ApiHandler apiHandler;

    /**
     * API 호출 테스트
     */
    @Test
    @DisplayName("API 호출 테스트")
    void getApi() throws Exception
    {
        //given
        ExchangeResultDc api = apiHandler.getApi();
        //when
        System.out.println("api.getSource() = " + api.getSource());


        //then
    }

}