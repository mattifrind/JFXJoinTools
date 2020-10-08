package org.chaoscoders.extensions.numbersystemconverter;

import java.util.regex.Pattern;


/*This Class is primarily for the conversion of String-Representations of floating-point numbers in the common
* number-systems to string-representations in another common number-system. It can also return the double value of
* a string-representation.
* Additionally, it can convert char typed digit-literals to their decimal values and vice versa.*/
public class NumberSysConverter {
    private NumberSystem originSys, targetSys;
    private static String pointRegEx = "([\\.,])";
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
            case BINARY -> regularExpression = "[01//.,]*";
            case OCTAL -> regularExpression = "[01234567\\.,]*";
            case DECIMAL -> regularExpression = "[0123456789\\.,]*";
            case HEX -> regularExpression = "[0123456789abcdefABCDEF\\.,]*";
            default -> regularExpression = ".*";
        }
        boolean correctPoints = true;
        String[] pointSplit = input.split(pointRegEx);

        if (pointSplit.length > 2){
            correctPoints = false;
        }
        boolean correctChars = Pattern.matches(regularExpression, input);
        return correctChars && correctPoints;

    }



    private boolean evaluateInput(String inNumber){ // Calculates Decimal Value of Input
        inNumber = inNumber.trim();
        double value = 0;
        if (validString(inNumber)){
            //Konvertierung von Vorkommastellen
            String[] frags = inNumber.split(pointRegEx);
            int i = 0;
            for (int j = frags[0].length()-1; j>=0; j-- ){
                char digit = frags[0].charAt(j);
                digit = hexLiteralToDecValue(digit);
                value += digit*Math.pow(hornerFactor, i++);
            }
            //ggf. Konvertierung von Nachkommastellen
            if (frags.length == 2){
                i = 1;
                for (int j = 0; j <frags[1].length(); j++){
                    char digit = frags[1].charAt(j);
                    digit = hexLiteralToDecValue(digit);
                    value += digit*Math.pow(hornerFactor, -(i++));
                }
            }
            decimalValue = value;
            return true;
        }
        return false;
    }



    public NumberSysConverter(){
        originSys = NumberSystem.DECIMAL;
        targetSys = NumberSystem.DECIMAL;
        hornerFactor = 10;
        divisior = 10;
    }

    public NumberSysConverter(NumberSystem ori, NumberSystem tar){
        originSys = ori;
        targetSys = tar;
        originSysEval();
        targetSysEval();
    }

    //String-Repräsentation zu Dezimalem wert
    public static double stringToValue (NumberSystem originSys, String numberString){
        NumberSysConverter internalNumberSysConverter = new NumberSysConverter(originSys, NumberSystem.DECIMAL);
        return internalNumberSysConverter.decimalValue;
    }
    //String-Repräsentation
    public static String instantConversion(NumberSystem originSys, NumberSystem targetSys, String numberString){
        NumberSysConverter internalNumberSysConverter = new NumberSysConverter(originSys, targetSys);
        return internalNumberSysConverter.convertString(numberString);
    }
    //Default ist zu Dezimal
    public static String instantConversion(NumberSystem originSys, String numberString){
        NumberSysConverter internalNumberSysConverter = new NumberSysConverter(originSys, NumberSystem.DECIMAL);
        return internalNumberSysConverter.convertString(numberString);
    }

    public static char decValueToHexLiteral(int value){
        char newValue = (char) value;
        if(value > 9){
            switch (newValue){
                case 10 -> newValue = 'A';
                case 11 -> newValue = 'B';
                case 12 -> newValue = 'C';
                case 13 -> newValue = 'D';
                case 14 -> newValue = 'E';
                case 15 -> newValue = 'F';
            }
        } else {
            newValue += '0';
        }
        return newValue;
    }

    public static char hexLiteralToDecValue(char digit){
        //Takes a Digit up to Hex and returns decimal value
        if (digit > '9'){
            switch (digit){
                case 'a', 'A'-> digit = 10;
                case 'b', 'B'-> digit = 11;
                case 'c', 'C'-> digit = 12;
                case 'd', 'D'-> digit = 13;
                case 'e', 'E'-> digit = 14;
                case 'f', 'F'-> digit = 15;
            }
        } else {
            digit -= '0';
        }
        return digit;
    }

    public String convertString(String numberString){
        String result;
        if(evaluateInput(numberString)){
            int naturalFrag = (int) decimalValue;
            double fractionFrag = decimalValue % 1;
            String beforePoint = "";
            String afterPoint = "";
            while (naturalFrag > 0){
                beforePoint = decValueToHexLiteral(naturalFrag % divisior) + beforePoint;
                naturalFrag /= divisior;
            }
            int precision = 6;                                                          //PRECISION CAN BE SET HERE
            int tempIntResidue;
            while (fractionFrag >= 0 && precision != 0){
                fractionFrag *= divisior;
                tempIntResidue = (int) fractionFrag;
                afterPoint = afterPoint + decValueToHexLiteral(tempIntResidue);
                fractionFrag = fractionFrag % 1;
                precision --;
            }
            result = beforePoint + '.' + afterPoint;
        } else {
            result = "Faulty Input";
        }
        return result;
    }

   /*public boolean testFunctionnality(String numString){
        boolean success = evaluateInput(numString);
        if (success) System.out.println(decimalValue);
       System.out.println(getString(numString));
        return success;
    }*/



}
