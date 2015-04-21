package com.shaubert.ui.jumper;

public interface Jumper {

    <T extends Args> Jump<T> to(Class<?> actClass);

}