package com.shaubert.ui.jumper;

import android.os.Parcelable;

import java.util.*;

public class DifferentListsArgs extends Args {
    List<String> strings;
    List<CharSequence> charSequences;
    List<Parcelable> parcelables;
    List<Integer> integers;

    public void initWithLinkedLists() {
        strings = new LinkedList<>();
        charSequences = new LinkedList<>();
        parcelables = new LinkedList<>();
        integers = new LinkedList<>();
    }

    public void initWithArrayLists() {
        strings = new ArrayList<>();
        charSequences = new ArrayList<>();
        parcelables = new ArrayList<>();
        integers = new ArrayList<>();
    }

    public void initWithSingletonLists() {
        strings = Collections.singletonList(null);
        charSequences = Collections.singletonList(null);
        parcelables = Collections.singletonList(null);
        integers = Collections.singletonList(null);
    }
}
