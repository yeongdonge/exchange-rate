package exchange.rate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exchange.rate.dc.ExchangeResultDc;
import exchange.rate.service.ExchangeRateServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class IndexControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Main Index 뷰 비정상 호출 테스트
     */
    @Test
    @DisplayName("현재 지원하지 않는 국가는 error 문자열을 반환해야 함")
    void 지원하지_않는_국가_호출() throws Exception {
        //given
        String quote = "NOPE";

        //when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/transfer/quote/?quote=" + quote)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        String expectedError = result.getResponse().getContentAsString();
        //then
        Assertions.assertThat(expectedError).isEqualTo("error");
    }

    /**
     * Main Index 뷰 정상 호출 테스트
     */
    @Test
    @DisplayName("현재 지원하는 국가는 환율을 반환해야 함")
    void 지원하는_국가_호출() throws Exception {
        //given
        String quote = "KRW";

        //when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/transfer/quote/?quote=" + quote)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        String expectedNormal = result.getResponse().getContentAsString();
        //then
        Assertions.assertThat(expectedNormal).isNotEqualTo("error");
        log.info(expectedNormal);
    }
}