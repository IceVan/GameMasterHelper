package pl.ice.GameMasterHelper.model.dnd;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.ice.GameMasterHelper.model.Currency;

@Data
@AllArgsConstructor
public class DndCurrency extends Currency {

    private Integer copper = 0;

    private Integer silver = 0;

    private Integer electrum = 0;

    private Integer gold = 0;

    private Integer platinum = 0;

    @Override
    public Long convertToMostUsedCurrency() {
        return null;
    }

    @Override
    public Long convertToMostExpensiveCurrency() {
        return null;
    }

    @Override
    public Long convertToLeastExpensiveCurrency() {
        return null;
    }
}
