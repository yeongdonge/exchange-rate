package exchange.rate.controller;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new IndexController()).build();
    }
    
    /**
     * 환율 계산 정상 작동 테스트
     */
    @Test
    @DisplayName("정상적으로 불러온 환율 값을 가져와야 함")
    void getExchangeRateTest() throws Exception
    {
        //given
        mockMvc.perform(MockMvcRequestBuilders.get("/quotes?quotes=KRW")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        //when
    
        //then
    }
        

}