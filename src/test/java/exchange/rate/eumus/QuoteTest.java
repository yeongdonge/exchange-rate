package exchange.rate.eumus;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuoteTest {

    /**
     * Enum 출력 테스트
     */
    @Test
    @DisplayName("")
    void printEnum() throws Exception
    {
        //given
        ArrayList<Quote> quotes = new ArrayList<>(List.of(Quote.KRW, Quote.PHP, Quote.JPY));
        //when
        System.out.println("quotes = " + quotes);
        //then
    }


}