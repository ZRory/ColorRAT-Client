package eu.aragonapp.colorrat.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.prefs.Preferences;

public class WinRegistry {

    private static Preferences userRoot = Preferences.userRoot();
    private static Class<? extends Preferences> userClass = userRoot.getClass();

    public static final int HKEY_CURRENT_USER = 0x80000001;
    public static final int KEY_ALL_ACCESS = 0xf003f;

    private static Method regSetValueEx = null;
    private static Method regCloseKey = null;
    private static Method regOpenKey = null;

    static {
        try {
            regOpenKey = userClass.getDeclaredMethod("WindowsRegOpenKey", int.class, byte[].class, int.class);
            regOpenKey.setAccessible(true);

            regCloseKey = userClass.getDeclaredMethod("WindowsRegCloseKey", int.class);
            regCloseKey.setAccessible(true);

            regSetValueEx = userClass.getDeclaredMethod("WindowsRegSetValueEx", int.class, byte[].class, byte[].class);
            regSetValueEx.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeStringValue(String key, String valueName, String value) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        writeStringValue(userRoot, HKEY_CURRENT_USER, key, valueName, value);
    }

    private static void writeStringValue(Preferences root, int hkey, String key, String valueName, String value) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        int[] handles = (int[]) regOpenKey.invoke(root, new Object[]{hkey, toCstr(key), KEY_ALL_ACCESS});

        regSetValueEx.invoke(root, handles[0], toCstr(valueName), toCstr(value));
        regCloseKey.invoke(root, handles[0]);
    }

    private static byte[] toCstr(String str) {
        byte[] result = new byte[str.length() + 1];

        for (int i = 0; i < str.length(); i++)
            result[i] = (byte) str.charAt(i);

        result[str.length()] = 0;
        return result;
    }

}