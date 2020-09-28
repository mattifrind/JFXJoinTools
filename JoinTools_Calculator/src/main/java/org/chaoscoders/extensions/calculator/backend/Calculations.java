package org.chaoscoders.extensions.calculator.backend;

import java.util.ArrayList;

public class Calculations {

    public static void evaluate(String input){
        //char []inputCharArray = new char[input.length()];
        ArrayList<Integer> indeces;
        int countNormOpen=0, countNormClose=0, countSquaredOpen=0, countSquaredClose=0, countBraceOpen=0, countBraceClose=0;
        for (int i = 0; i<input.length(); i++){
            //inputCharArray[i] = input.charAt(i);
            if(input.charAt(i)=='('){
                countNormOpen++;
                indeces.add(i);
            }if(input.charAt(i)==')'){
                countNormClose++;
                indeces.add(i);
            }if(input.charAt(i)=='['){
                countSquaredOpen++;
                indeces.add(i);
            }if(input.charAt(i)==']'){
                countSquaredClose++;
                indeces.add(i);
            }if(input.charAt(i)=='{'){
                countBraceOpen++;
                indeces.add(i);
            }if(input.charAt(i)=='}'){
                countBraceClose++;
                indeces.add(i);
            }
        }
        if(countBraceClose!= countBraceOpen || countNormClose!=countNormOpen || countSquaredClose!= countSquaredOpen){
            System.exit(1);
            //alert einfuegen
        }
        if(countBraceClose==0 && countBraceOpen==0 && countNormClose==0 && countNormOpen==0 && countSquaredClose==0 && countSquaredOpen==0){
            calculation(input);
        }
        for (int i=0;i < indeces.size()-1;i++) {
            if(indeces.get(i)=='(' && indeces.get(i+1)==']'||indeces.get(i)=='('){

            }
        }

    }
    public static int calculation(String task){

        return 0;
    }

}
