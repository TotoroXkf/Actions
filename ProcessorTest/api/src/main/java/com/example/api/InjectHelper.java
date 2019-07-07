package com.example.api;

import android.app.Activity;

import java.lang.reflect.Constructor;

public class InjectHelper {
    public static void inject(Activity host) {
        // 1、
        String classFullName = host.getClass().getName() + "$$ViewInjector";
        try {
            // 2、
            Class proxy = Class.forName(classFullName);
            // 3、
            Constructor constructor = proxy.getConstructor(host.getClass());
            // 4、
            constructor.newInstance(host);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
