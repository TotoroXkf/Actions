package com.example.my_processor;

import javax.lang.model.element.VariableElement;

public class VariableInfo {
    private int viewId;
    private VariableElement variableElement;

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }

    public VariableElement getVariableElement() {
        return variableElement;
    }

    public void setVariableElement(VariableElement variableElement) {
        this.variableElement = variableElement;
    }
}
