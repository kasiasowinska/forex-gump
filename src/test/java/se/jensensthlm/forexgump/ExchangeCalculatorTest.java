package se.jensensthlm.forexgump;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)

public class ExchangeCalculatorTest {
    @Test
    @DisplayName("Buy 100 GBP")
    public void TestBuy() {
        // Given the exchange rate 10 SEK / GBP
        // To buy 100 GBP
        // You need to pay 1000 SEK
        var targetCurrency = "GBP";
        var amount = 100.0;
        var rate = 10.0;

        when(exchangeProvider.get("SEK", targetCurrency))
                .thenReturn(new ExchangeDetails("SEK", "GBP", rate));
        var expectedPriceInSek = amount * rate;
        var actualPriceInSek = exchangeCalculator.calculateBuy(targetCurrency, amount);
        assertEquals(expectedPriceInSek, actualPriceInSek);

    }

    @Test
    @DisplayName("Sell 100 SEK to get GBP")
    public void TestSell() {
        // Given the exchange rate 10 SEK / GBP
        // Selling 100 SEK
        // Gives you 10 GBP
        var targetCurrency = "GBP";
        var amount = 100.0;
        var rate = 10.0;

        when(exchangeProvider.get("SEK", targetCurrency))
                .thenReturn(new ExchangeDetails("SEK", "GBP", rate));

        var expectedPriceInSek = amount / rate;
        var actualPriceInSek = exchangeCalculator.calculateSell(targetCurrency, amount);
        assertEquals(expectedPriceInSek, actualPriceInSek);
    }

    @InjectMocks
    ExchangeCalculator exchangeCalculator;
    @Mock
    ExchangeProvider exchangeProvider;
}
