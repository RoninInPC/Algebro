package com.task.algebro.ui.algebraic_simple.StateMashine;

public interface State<Simple>{

    void setState(Simple simple);
    boolean transit(Simple simple);

    State<Simple> nextState(Simple simple);
}
