package com.example.hooktest;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HookHelper {
    public static void hook() {
        try {
            Class class1 = Class.forName("android.app.ActivityManagerNative");
            Field field1 = class1.getDeclaredField("gDefault");
            field1.setAccessible(true);
            Object gDefault = field1.get(null);

            Class class2 = Class.forName("android.util.Singleton");
            Field field2 = class2.getDeclaredField("mInstance");
            field2.setAccessible(true);
            Object mInstance = field2.get(gDefault);

            Class iActivityManagerInterface = Class.forName("android.app.IActivityManager");
            Object proxy = Proxy.newProxyInstance(
                    Thread.currentThread().getContextClassLoader(),
                    new Class[]{iActivityManagerInterface},
                    new HookHandler(mInstance)
            );
            field2.set(gDefault, proxy);
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

class HookHandler implements InvocationHandler {

    private Object mBase;

    HookHandler(Object base) {
        mBase = base;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.e("xkf", "hook!!!!!");
        return method.invoke(mBase, args);
    }
}
