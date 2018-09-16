package com.box.io.bus;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;


public class BusProvider {
    public static final Bus INSTANCE = new Bus(ThreadEnforcer.ANY);


    private BusProvider() {
    }

    public static void register(Object object) {
        INSTANCE.register(object);
    }

    public static void unregister(Object object) {
        INSTANCE.unregister(object);
    }

    public static void post(Object event) {
        INSTANCE.post(event);
    }
}
