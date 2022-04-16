package exchange.rate.dc;


import com.google.gson.Gson;
import exchange.rate.controller.MainController;
import exchange.rate.service.ExchangeRateService;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ExchangeDcTest {

    @InjectMocks
    private MainController target;

    @Mock
    private ExchangeRateService exchangeRateService;
    private MockMvc mockMvc;
    private Gson gson;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(target)
                .alwaysExpect(MockMvcResultMatchers.status().isOk()).build();
        gson = new Gson();
    }

    /**
     * Dc Valid Test
     */
    @Test
    @DisplayName("Valid Test")
    void validTest() throws Exception
    {
        //given
        String quote = "KRW";
        BigDecimal amount = BigDecimal.valueOf(-1);
        //when
        final ExchangeDc krw = new ExchangeDc(quote, amount);
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/quote")
                        .content(gson.toJson(krw))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest()).andDo(print());
        //then

    }
}