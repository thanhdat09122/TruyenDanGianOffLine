package com.example.mypc.truyenoffline.utils;

import com.squareup.otto.Bus;

public class GlobalBus {
    private static Bus sBus;

    // singleton => create only single object bus
    public synchronized static Bus getsBus () {
        if (sBus == null) {
            sBus = new Bus();
        }
        return sBus;
    }
}
