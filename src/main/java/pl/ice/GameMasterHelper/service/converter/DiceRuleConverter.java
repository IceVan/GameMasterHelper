package pl.ice.GameMasterHelper.service.converter;

import lombok.extern.java.Log;
import pl.ice.GameMasterHelper.model.DiceRule;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Log
@Converter
public class DiceRuleConverter implements AttributeConverter<DiceRule, String> {
    @Override
    public String convertToDatabaseColumn(DiceRule diceRule) {
        return diceRule == null ? null : diceRule.toString();
    }

    @Override
    public DiceRule convertToEntityAttribute(String s) {
        try{
            return new DiceRule(s);
        }
        catch(IllegalArgumentException e){
            log.warning(e.getMessage());
        }

        return null;
    }
}
