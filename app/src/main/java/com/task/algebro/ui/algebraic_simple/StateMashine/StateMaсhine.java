package com.task.algebro.ui.algebraic_simple.StateMashine;

public interface StateMaсhine<Simple,StateSimple> {

    StateMaсhine<Simple,StateSimple> initialize(Simple back, Simple command);
    boolean setCommand(Simple back, Simple command);

    StateSimple toState(Simple back,Simple command);

}
