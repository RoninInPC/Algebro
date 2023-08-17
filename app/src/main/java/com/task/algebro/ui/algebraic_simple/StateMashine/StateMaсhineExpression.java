package com.task.algebro.ui.algebraic_simple.StateMashine;

public class StateMaсhineExpression implements StateMaсhine<String,StateExpression.States> {
    private State<StateExpression.States> state_ = new StateExpression();

    @Override
    public StateMaсhine<String,StateExpression.States> initialize(String back, String command) {
        state_.setState(toState(back, command));
        return this;
    }

    @Override
    public boolean setCommand(String back, String command){
        StateExpression.States states = toState(back,command);
        boolean isChange = state_.transit(states);
        state_ = state_.nextState(states);
        return isChange;
    }

    public StateExpression.States toState(String back, String command){
        StateExpression.States statesBack = toStateCommand(back);
        if(command.equals("back")){
            return toStateCommand(back);
        }
        if(statesBack!= StateExpression.States.NUMBER && command.equals("0")){
            return StateExpression.States.FIRST_ZERO;
        }
        return toStateCommand(command);
    }

    private StateExpression.States toStateCommand(String command){
        StateExpression.States states = StateExpression.States.CLEAR;
        switch (command){
            case ")":
            case "(":
            case "staples":
                states = StateExpression.States.STAPLES;
                break;
            case "-":
                states = StateExpression.States.MINES;
                break;
            case "+":
                states = StateExpression.States.PLUS;
                break;
            case "/":
                states = StateExpression.States.DEL;
                break;
            case "×":
                states = StateExpression.States.MUL;
                break;
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                states = StateExpression.States.NUMBER;
                break;
            case "back":
                states = StateExpression.States.BACK;
                break;
            case "clear":
                states = StateExpression.States.CLEAR;
                break;
        }
        return states;
    }
}
