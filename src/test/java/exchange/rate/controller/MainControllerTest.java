package exchange.rate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("현재 지원하지 않는 국가는 error 문자열을 반환해야 함")
    void 미지원_국가() throws Exception {
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

    @Test
    @DisplayName("ExchangeDc에서 검증 단을 거쳐야 함 (올바른 값)")
    void exChangeDC_검증_통과() throws Exception {
        //given
        String quote = "KRW";
        BigDecimal amount = BigDecimal.valueOf(100);

        Map<String, String> input = new HashMap<>();
        input.put("quote", quote);
        input.put("amount", String.valueOf(amount));

        //when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/transfer/quote/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andDo(print())
                .andReturn();

        String expectedNormal = result.getResponse().getContentAsString();
        //then
        Assertions.assertThat(expectedNormal).isNotEqualTo("error");
        log.info(expectedNormal);
    }
    @Test
    @DisplayName("ExchangeDc에서 검증 단을 거쳐야 함 (올바르지 않은 값 - amount < 0)")
    void exChangeDC_검증_오류() throws Exception {
        //given
        String quote = "KRW";
        BigDecimal amount = BigDecimal.valueOf(-1);

        Map<String, String> input = new HashMap<>();
        input.put("quote", quote);
        input.put("amount", String.valueOf(amount));

        //when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/transfer/quote/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andDo(print())
                .andReturn();

        String expectedNormal = result.getResponse().getContentAsString();
        //then
        Assertions.assertThat(expectedNormal).isEqualTo("error");
        log.info(expectedNormal);
    }

    @Test
    @DisplayName("ExchangeDc에서 검증 단을 거쳐야 함 (올바르지 않은 값 - amount > 10000)")
    void exChangeDC_검증_오류_2() throws Exception {
        //given
        String quote = "KRW";
        BigDecimal amount = BigDecimal.valueOf(10001);

        Map<String, String> input = new HashMap<>();
        input.put("quote", quote);
        input.put("amount", String.valueOf(amount));

        //when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/transfer/quote/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andDo(print())
                .andReturn();

        String expectedNormal = result.getResponse().getContentAsString();
        //then
        Assertions.assertThat(expectedNormal).isEqualTo("error");
        log.info(expectedNormal);
    }

    @Test
    @DisplayName("ExchangeDc에서 검증 단을 거쳐야 함 (올바르지 않은 값 - 국가)")
    void exChangeDC_검증_오류_3() throws Exception {
        //given
        String quote = "CNY";
        BigDecimal amount = BigDecimal.valueOf(100);

        Map<String, String> input = new HashMap<>();
        input.put("quote", quote);
        input.put("amount", String.valueOf(amount));

        //when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/transfer/quote/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andDo(print())
                .andReturn();

        String expectedNormal = result.getResponse().getContentAsString();
        //then
        Assertions.assertThat(expectedNormal).isEqualTo("error");
        log.info(expectedNormal);
    }

    @Test
    @DisplayName("ExchangeDc에서 검증 단을 거쳐야 함 (올바르지 않은 값 - 국가, 금액 < 0)")
    void exChangeDC_검증_오류_4() throws Exception {
        //given
        String quote = "CNY";
        BigDecimal amount = BigDecimal.valueOf(-1);

        Map<String, String> input = new HashMap<>();
        input.put("quote", quote);
        input.put("amount", String.valueOf(amount));

        //when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/transfer/quote/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andDo(print())
                .andReturn();

        String expectedNormal = result.getResponse().getContentAsString();
        //then
        Assertions.assertThat(expectedNormal).isEqualTo("error");
        log.info(expectedNormal);
    }

    @Test
    @DisplayName("ExchangeDc에서 검증 단을 거쳐야 함 (올바르지 않은 값 - 국가, 금액 > 10000)")
    void exChangeDC_검증_오류_5() throws Exception {
        //given
        String quote = "CNY";
        BigDecimal amount = BigDecimal.valueOf(10001);

        Map<String, String> input = new HashMap<>();
        input.put("quote", quote);
        input.put("amount", String.valueOf(amount));

        //when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/transfer/quote/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andDo(print())
                .andReturn();

        String expectedNormal = result.getResponse().getContentAsString();
        //then
        Assertions.assertThat(expectedNormal).isEqualTo("error");
        log.info(expectedNormal);
    }
}