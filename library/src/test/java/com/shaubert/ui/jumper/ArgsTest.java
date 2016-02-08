package com.shaubert.ui.jumper;

import android.os.Bundle;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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

}