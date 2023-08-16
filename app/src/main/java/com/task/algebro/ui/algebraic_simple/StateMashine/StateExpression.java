package com.task.algebro.ui.algebraic_simple.StateMashine;

import java.util.List;
import java.util.Map;

public class StateExpression implements State<StateExpression.States>{
    public enum States{
        STAPLES,
        FIRST_ZERO,
        MINES,
        PLUS,
        DEL,
        MUL,
        NUMBER,
        BACK,
        CLEAR
    }

    private States state_now_ = States.CLEAR;

    private final Map<States, List<States>> states_ = Map.of(
            States.STAPLES,
            List.of(States.STAPLES,States.MINES,States.FIRST_ZERO,States.NUMBER,States.BACK,States.CLEAR),
            States.PLUS,
            List.of(States.STAPLES,States.FIRST_ZERO,States.NUMBER,States.BACK,States.CLEAR),
            States.MINES,
            List.of(States.STAPLES,States.FIRST_ZERO,States.NUMBER,States.BACK,States.CLEAR),
            States.DEL,
            List.of(States.STAPLES,States.FIRST_ZERO,States.NUMBER,States.BACK,States.CLEAR),
            States.MUL,
            List.of(States.STAPLES,States.FIRST_ZERO,States.NUMBER,States.BACK,States.CLEAR),
            States.FIRST_ZERO,
            List.of(States.STAPLES,States.PLUS,States.MINES, States.DEL,States.MUL,States.NUMBER,States.BACK,States.CLEAR),
            States.NUMBER,
            List.of(States.STAPLES,States.PLUS,States.MINES, States.DEL,States.MUL,States.NUMBER,States.BACK,States.CLEAR),
            States.BACK,
            List.of(),
            States.CLEAR,
            List.of(States.STAPLES,States.FIRST_ZERO,States.NUMBER,States.BACK,States.CLEAR));

    @Override
    public void setState(States states) {
        state_now_ = states;
    }

    public States getState(){
        return state_now_;
    }

    @Override
    public boolean transit(States states) {
        return states_.get(state_now_).contains(states);
    }

    @Override
    public State<States> nextState(States states) {
        if(transit(states))
            state_now_ = states_.get(state_now_).get(states_.get(state_now_).indexOf(states));
        return this;
    }

}
