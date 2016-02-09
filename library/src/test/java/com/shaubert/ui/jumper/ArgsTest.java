package com.shaubert.ui.jumper;

import android.os.Bundle;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;

@RunWith(MockitoJUnitRunner.class)
public class ArgsTest {

    @Mock Bundle bundle;

    @Test
    public void testStringLists() {
        StringListArgs args = new StringListArgs();
        args.toBundle(bundle);
        HashSet<Args.Type> types = new HashSet<>(StringListArgs.BundlerCache.get(StringListArgs.class).values());
        Assert.assertEquals("Type for all string collections should be the same", 1, types.size());
        Assert.assertEquals("Collection of strings must map to STRING_ARRAY_LIST", Args.Type.STRING_ARRAY_LIST, types.iterator().next());
    }

    @Test
    public void testWritingLinkedLists() {
        DifferentListsArgs args = new DifferentListsArgs();
        args.initWithLinkedLists();
        args.toBundle(bundle);
        verifyArrayLists();
    }

    @Test
    public void testWritingSingletonLists() {
        DifferentListsArgs args = new DifferentListsArgs();
        args.initWithSingletonLists();
        args.toBundle(bundle);
        verifyArrayLists();
    }

    private void verifyArrayLists() {
        Mockito.verify(bundle, Mockito.times(1)).putStringArrayList(Matchers.anyString(), Matchers.any(ArrayList.class));
        Mockito.verify(bundle, Mockito.times(1)).putIntegerArrayList(Matchers.anyString(), Matchers.any(ArrayList.class));
        Mockito.verify(bundle, Mockito.times(1)).putParcelableArrayList(Matchers.anyString(), Matchers.any(ArrayList.class));
        Mockito.verify(bundle, Mockito.times(1)).putCharSequenceArrayList(Matchers.anyString(), Matchers.any(ArrayList.class));
    }

}