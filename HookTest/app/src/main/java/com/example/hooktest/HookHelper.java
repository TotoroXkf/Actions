package com.example.hooktest;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HookHelper {
    public static void hook(Class<Activity> activityClass, Activity activity) {
        try {
            Field field = activityClass.getDeclaredField("mInstrumentation");
            field.setAccessible(true);
            Object mInstrumentation = field.get(activity);
            MyInstrumentation myInstrumentation = new MyInstrumentation((Instrumentation) mInstrumentation);
            field.set(activity, myInstrumentation);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

class MyInstrumentation extends Instrumentation{
    Instrumentation base;

    MyInstrumentation(Instrumentation base) {
        this.base = base;
    }

    public Instrumentation.ActivityResult execStartActivity(
            Context who,
            IBinder contextThread,
            IBinder token,
            Activity target,
            Intent intent,
            int requestCode,
            Bundle options) {
        Log.e("xkf", "hook startActivity");
        Class instrumentationClass = Instrumentation.class;
        Class[] p = {
                Context.class,
                IBinder.class,
                IBinder.class,
                Activity.class,
                Intent.class,
                int.class,
                Bundle.class
        };
        Object[] v = {
                who,
                contextThread,
                token,
                target,
                intent,
                requestCode,
                options
        };
        try {
            Method method = instrumentationClass.getDeclaredMethod("execStartActivity", p);
            return (Instrumentation.ActivityResult) method.invoke(base, v);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}