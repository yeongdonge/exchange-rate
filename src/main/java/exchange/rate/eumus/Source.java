package exchange.rate.eumus;

import lombok.Getter;

@Getter
public enum Source {
    JPY("일본(USD)"),
    KRW("한국(KRW)"),
    PHP("필리핀(PHP)"),
    USD("미국(USD)");

    final private String source;
    private Source(String source) {
        this.source = source;
    }

    public static Source sourceOf(String name) {
        for (Source value : Source.values()) {
            if(value.getSource().equals(name)) return value;
        }
        return null;
    }
}
