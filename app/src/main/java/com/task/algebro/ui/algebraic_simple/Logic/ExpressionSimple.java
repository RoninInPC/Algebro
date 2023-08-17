package com.task.algebro.ui.algebraic_simple.Logic;

import androidx.annotation.NonNull;

import com.task.algebro.ui.algebraic_simple.StateMashine.StateExpression;
import com.task.algebro.ui.algebraic_simple.StateMashine.StateMaсhine;
import com.task.algebro.ui.algebraic_simple.StateMashine.StateMaсhineExpression;
import com.task.algebro.ui.algebraic_simple.StateMashine.StateMaсhineModule;

public class ExpressionSimple implements Expression<ExpressionSimple.Write,String> {
    public enum Write{
        EXPRESSION,
        MODULE
    }

    private String expression_="";

    private String module_="";


    private final StateMaсhine<String, StateExpression.States> stateMaсhineExpression_ = new StateMaсhineExpression();

    private final StateMaсhine<String, StateExpression.States> stateMaсhineModule_ = new StateMaсhineModule();


    public void addCommand(Write write, String command){
        switch(write){
            case EXPRESSION:
                addCommandOnString(expression_,stateMaсhineExpression_,command);
                break;
            case MODULE:
                addCommandOnString(module_,stateMaсhineModule_,command);
                break;
        }
    }

    private String getBack(String string){
        if(string==null || string.length()==0){
            return "";
        }
        return string.substring(string.length()-1);
    }
    private void addCommandOnString(String string, StateMaсhine<String, StateExpression.States> stateMaсhine, String command){
        if(stateMaсhine.setCommand(getBack(string),command)){
            switch(command){
                case "clear":
                    string = "";
                    break;
                case "back":
                    string = string.substring(0,string.length()-2);
                case "staples":
                    String last = getBack(string);
                    if("0123456789".contains(last) || last.equals(")")){
                        string+=")";
                    }else{
                        string+="(";
                    }
                default:
                    string+=command;
            }
        }
    }

    @NonNull
    @Override
    public String toString() {
        return expression_ + String.format(" (mod %s)",module_);
    }
    @Override
    public void fromString(String fullExpression){
        String[] strings = fullExpression.split(" ");
        if(strings.length!=3)
            return;
        expression_ = strings[0];
        module_ = strings[2].substring(0,strings[2].length()-2);
    }
}
