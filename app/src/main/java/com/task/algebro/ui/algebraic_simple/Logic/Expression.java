package com.task.algebro.ui.algebraic_simple.Logic;

public interface Expression<WriteSimple,Simple>{
    void addCommand(WriteSimple write, Simple command);

    public void fromString(String fullExpression);
}
