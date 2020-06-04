package pl.ice.GameMasterHelper.model;

import lombok.Data;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class DiceRule {

    int diceNumber;

    int diceMax;

    int multiplication;

    int addition;

    public DiceRule(int diceNumber, int diceMax){
        this(diceNumber,diceMax,1,0);
    }

    public DiceRule(int diceNumber, int diceMax, int multiplication, int addition){
        this.diceNumber = diceNumber;
        this.diceMax = diceMax;
        this.multiplication = multiplication;
        this.addition = addition;
    }

    //parsing DiceRule from string
    //pattern   diceNumber[d]diceMax[X]multiplication[+]addition
    public DiceRule(String rule){
            parseString(rule);
    }

    private void parseString(String rule){
        Pattern pattern = Pattern.compile("(\\d+)d(\\d+)x(\\d+)p(\\d+)");
        Matcher matcher = pattern.matcher(rule == null ? "":rule);

        if(matcher.matches()){
            this.diceNumber = Integer.parseInt(matcher.group(1));
            this.diceMax = Integer.parseInt(matcher.group(2));
            this.multiplication = Integer.parseInt(matcher.group(3));
            this.addition = Integer.parseInt(matcher.group(4));
        }
        else{
            throw new IllegalArgumentException("Provided string does not correspond with the pattern: " + rule);
        }
    }

    public int generateValue(){
        Random r = new Random();
        int toReturn = 0;

        for(int i = 0; i < diceNumber; i++){
            toReturn+= r.nextInt(diceMax)+1;
        }

        toReturn*=multiplication;
        toReturn+=addition;

        return toReturn;
    }

    public String toString(){
        return new String(diceNumber+"d"+diceMax+"x"+multiplication+"p"+addition);
    }
}
