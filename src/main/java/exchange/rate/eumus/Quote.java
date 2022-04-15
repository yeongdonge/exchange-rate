package exchange.rate.eumus;

import lombok.Getter;

@Getter
public enum Quote {
    JPY("일본(USD)"),
    KRW("한국(KRW)"),
    PHP("필리핀(PHP)");

    final private String quote;
    private Quote(String source) {
        this.quote = source;
    }

    public static Quote quoteOf(String name) {
        for (Quote value : Quote.values()) {
            if(value.getQuote().equals(name)) return value;
        }
        return null;
    }
}
