package org.chaoscoders.extensions.numbersystemconverter;

import javafx.util.StringConverter;

import java.util.regex.Pattern;

public class Converter {
    private NumberSystem originSys, targetSys;
    private static String pointRegEx = "(//.|,)";
    private int hornerFactor, divisior;
    private double decimalValue;
    private void originSysEval(){
        switch (originSys) {
            case BINARY -> hornerFactor = 2;
            case OCTAL -> hornerFactor = 8;
            case DECIMAL -> hornerFactor = 10;
            case HEX -> hornerFactor = 16;
        }
    }

    private void targetSysEval(){
        switch (targetSys) {
            case BINARY -> divisior = 2;
            case OCTAL -> divisior = 8;
            case DECIMAL -> divisior = 10;
            case HEX -> divisior = 16;
        }
    }

    private boolean validString(String input){
        String regularExpression;
        switch (originSys){
            case BINARY -> regularExpression = "[^01\\.,]";
            case OCTAL -> regularExpression = "[^01234567\\.,]";
            case DECIMAL -> regularExpression = "[^123456789\\.,,]";
            case HEX -> regularExpression = "[^1-9]";
            default -> regularExpression = ".";
        }
        boolean correctPoints = true;
        String[] pointSplit = input.split(pointRegEx);

        if (pointSplit.length > 2){
            correctPoints = false;
        }
        return (!Pattern.matches(regularExpression, input)) && correctPoints;

    }

    public Converter(){
        originSys = NumberSystem.DECIMAL;
        targetSys = NumberSystem.DECIMAL;
        hornerFactor = 10;
        divisior = 10;
    }

    public Converter(NumberSystem ori, NumberSystem tar){
        originSys = ori;
        targetSys = tar;
        originSysEval();
        targetSysEval();
    }

    private boolean evaluateInput(String inNumber){ // Calculates Decimal Value of Input
        inNumber.trim();
        double value = 0;
        if (validString(inNumber)){
            //Konvertierung von Vorkommastellen
            String[] frags = inNumber.split(pointRegEx);
            int i = 0;
            for (int j = frags[0].length()-1; j>=0; j-- ){
                char digit = frags[0].charAt(j);
                digit -='0';
                value += digit*Math.pow(hornerFactor, i++);
            }
            //ggf. Konvertierung von Nachkommastellen
            if (frags.length == 2){
                i = 1;
                for (int j = 0; j <frags[1].length(); j++){
                    char digit = frags[1].charAt(j);
                    digit -= '0';
                    value += digit*Math.pow(hornerFactor, -(i++));
                }
            }
            decimalValue = value;
            return true;
        }
        return false;


    }

   public boolean testFunctionnality(String numString){
        boolean success = evaluateInput(numString);
        if (success) System.out.println(decimalValue);
        return success;
    }



}
