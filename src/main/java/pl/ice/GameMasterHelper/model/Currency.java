package pl.ice.GameMasterHelper.model;

public abstract class Currency {

    public abstract Long convertToMostUsedCurrency();

    public abstract Long convertToMostExpensiveCurrency();

    public abstract Long convertToLeastExpensiveCurrency();
}
