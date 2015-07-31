package com.shaubert.ui.jumper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;

import java.io.Externalizable;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * Extend your class from {@link Args Bundler} to convert your non-transient fields into
 * {@link android.os.Bundle Bundle} and vice versa. Look at list of supported types: {@link Args.Type Types}.
 */
public class Args {

    public static final String ARGS_CLASS = "__args_class";

    private static class BundlerCache {
        private static Map<Class, Map<Field, Type>> cache = new HashMap<>();

        private static Map<Field, Type> get(Class cls) {
            return cache.get(cls);
        }

        private static void put(Class cls, Field field, Type type) {
            Map<Field, Type> map = cache.get(cls);
            if (map == null) {
                map = new HashMap<>();
                cache.put(cls, map);
            }
            map.put(field, type);
        }
    }

    enum Type {
        BOOLEAN,
        CHAR,
        BYTE,
        SHORT,
        INTEGER,
        FLOAT,
        DOUBLE,
        LONG,
        STRING,
        CHAR_SEQUENCE,
        BOOLEAN_ARRAY,
        CHAR_ARRAY,
        BYTE_ARRAY,
        SHORT_ARRAY,
        INTEGER_ARRAY,
        INTEGER_ARRAY_LIST,
        FLOAT_ARRAY,
        DOUBLE_ARRAY,
        LONG_ARRAY,
        STRING_ARRAY,
        STRING_ARRAY_LIST,
        CHAR_SEQUENCE_ARRAY,
        CHAR_SEQUENCE_ARRAY_LIST,
        BUNDLE,
        PARCELABLE,
        PARCELABLE_ARRAY,
        PARCELABLE_ARRAY_LIST,
        SPARSE_PARCELABLE_ARRAY,
        SERIALIZABLE,
    }

    public static <T extends Args> T fromArgs(Intent intent) {
        if (intent == null) return null;
        return fromArgs(intent.getExtras());
    }

    @SuppressWarnings("unchecked")
    public static <T extends Args> T fromArgs(Bundle bundle) {
        if (bundle == null) return null;

        String className = bundle.getString(Config.ARGS_CLASS);
        try {
            Class<T> tClass = (Class<T>) Class.forName(className);
            T t = tClass.newInstance();
            t.fromBundle(bundle);
            return t;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void fromBundle(Bundle bundle) {
        if (bundle == null) {
            return;
        }

        Map<Field, Type> fieldTypeMap = getFieldsMap();
        if (fieldTypeMap == null || fieldTypeMap.isEmpty()) {
            return;
        }

        for (Field field : fieldTypeMap.keySet()) {
            try {
                readFromBundle(field, bundle);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Map<Field, Type> getFieldsMap() {
        Class<? extends Args> cls = getClass();
        Map<Field, Type> typeMap = BundlerCache.get(cls);
        if (typeMap != null) {
            return typeMap;
        }

        List<Field> fields = getAllFields(cls);
        for (Field field : fields) {
            if (field.isSynthetic()) continue;
            if (Modifier.isTransient(field.getModifiers())) continue;

            field.setAccessible(true);
            Type type = getType(field.getType());
            BundlerCache.put(cls, field, type);
        }

        return BundlerCache.get(cls);
    }

    private static List<Field> getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<>();
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
        }
        return fields;
    }

    private void readFromBundle(Field field, Bundle bundle) throws IllegalAccessException {
        String name = getBundleEntryName(field);
        if (bundle.containsKey(name)) {
            field.set(this, bundle.get(name));
        }
    }

    @SuppressWarnings("unchecked")
    private void writeToBundle(Field field, Type type, Bundle bundle) throws IllegalAccessException {
        Object value = field.get(this);
        if (value == null) {
            return;
        }

        String name = getBundleEntryName(field);
        switch (type) {
            case BOOLEAN:
                bundle.putBoolean(name, (Boolean) value);
                return;
            case BOOLEAN_ARRAY:
                bundle.putBooleanArray(name, (boolean[]) value);
                return;

            case CHAR:
                bundle.putChar(name, (Character) value);
                return;
            case CHAR_ARRAY:
                bundle.putCharArray(name, (char[]) value);
                return;
            case CHAR_SEQUENCE:
                bundle.putCharSequence(name, (CharSequence) value);
                return;
            case CHAR_SEQUENCE_ARRAY:
                bundle.putCharSequenceArray(name, (CharSequence[]) value);
                return;
            case CHAR_SEQUENCE_ARRAY_LIST:
                bundle.putCharSequenceArrayList(name, (ArrayList<CharSequence>) value);
                return;

            case BYTE:
                bundle.putByte(name, (Byte) value);
                return;
            case BYTE_ARRAY:
                bundle.putByteArray(name, (byte[]) value);
                return;

            case SHORT:
                bundle.putShort(name, (Short) value);
                return;
            case SHORT_ARRAY:
                bundle.putShortArray(name, (short[]) value);
                return;

            case INTEGER:
                bundle.putInt(name, (Integer) value);
                return;
            case INTEGER_ARRAY:
                bundle.putIntArray(name, (int[]) value);
                return;
            case INTEGER_ARRAY_LIST:
                bundle.putIntegerArrayList(name, (ArrayList<Integer>) value);
                return;

            case FLOAT:
                bundle.putFloat(name, (Float) value);
                return;
            case FLOAT_ARRAY:
                bundle.putFloatArray(name, (float[]) value);
                return;

            case DOUBLE:
                bundle.putDouble(name, (Double) value);
                return;
            case DOUBLE_ARRAY:
                bundle.putDoubleArray(name, (double[]) value);
                return;

            case LONG:
                bundle.putLong(name, (Long) value);
                return;
            case LONG_ARRAY:
                bundle.putLongArray(name, (long[]) value);
                return;

            case STRING:
                bundle.putString(name, (String) value);
                return;
            case STRING_ARRAY:
                bundle.putStringArray(name, (String[]) value);
                return;
            case STRING_ARRAY_LIST:
                bundle.putStringArrayList(name, (ArrayList<String>) value);
                return;

            case BUNDLE:
                bundle.putBundle(name, (Bundle) value);
                return;

            case PARCELABLE:
                bundle.putParcelable(name, (Parcelable) value);
                return;
            case PARCELABLE_ARRAY:
                bundle.putParcelableArray(name, (Parcelable[]) value);
                return;
            case PARCELABLE_ARRAY_LIST:
                bundle.putParcelableArrayList(name, (ArrayList<Parcelable>) value);
                return;
            case SPARSE_PARCELABLE_ARRAY:
                bundle.putSparseParcelableArray(name, (SparseArray<Parcelable>) value);
                return;

            case SERIALIZABLE:
                bundle.putSerializable(name, (Serializable) value);
                return;

            default:
                throw new IllegalArgumentException("unable to handle type: " + type);
        }
    }

    private String getBundleEntryName(Field field) {
        return getClass().getSimpleName() + "-" + field.getName();
    }

    private Type getType(Class<?> cls) {
        if (cls.isArray()) {
            Type compType = getType(cls.getComponentType());
            switch (compType) {
                case BOOLEAN:
                    return Type.BOOLEAN_ARRAY;
                case CHAR:
                    return Type.CHAR_ARRAY;
                case BYTE:
                    return Type.BYTE_ARRAY;
                case SHORT:
                    return Type.SHORT_ARRAY;
                case INTEGER:
                    return Type.INTEGER_ARRAY;
                case FLOAT:
                    return Type.FLOAT_ARRAY;
                case DOUBLE:
                    return Type.DOUBLE_ARRAY;
                case LONG:
                    return Type.LONG_ARRAY;
                case STRING:
                    return Type.STRING_ARRAY;
                case CHAR_SEQUENCE:
                    return Type.CHAR_SEQUENCE_ARRAY;
                case PARCELABLE:
                    return Type.PARCELABLE_ARRAY;

                case SPARSE_PARCELABLE_ARRAY:
                    throw new IllegalArgumentException("unsupported type: " + cls);

                default:
                    return Type.SERIALIZABLE;
            }
        }

        if (cls.isPrimitive()) {
            if (cls == int.class) {
                return Type.INTEGER;
            } else if (cls == boolean.class) {
                return Type.BOOLEAN;
            } else if (cls == byte.class) {
                return Type.BYTE;
            } else if (cls == float.class) {
                return Type.FLOAT;
            } else if (cls == double.class) {
                return Type.DOUBLE;
            } else if (cls == long.class) {
                return Type.LONG;
            } else if (cls == short.class) {
                return Type.SHORT;
            } else {
                return Type.SERIALIZABLE;
            }
        } else {
            if (CharSequence.class.isAssignableFrom(cls)) {
                return Type.CHAR_SEQUENCE;
            } else if (Number.class.isAssignableFrom(cls)) {
                if (cls == Integer.class){
                    return Type.INTEGER;
                } else if (cls == Byte.class) {
                    return Type.BYTE;
                } else if (cls == Float.class) {
                    return Type.FLOAT;
                } else if (cls == Double.class) {
                    return Type.DOUBLE;
                } else if (cls == Long.class) {
                    return Type.LONG;
                } else if (cls == Short.class) {
                    return Type.SHORT;
                } else {
                    return Type.SERIALIZABLE;
                }
            } else if (cls == Boolean.class) {
                return Type.BOOLEAN;
            } else {
                if (cls == ArrayList.class) {
                    java.lang.reflect.Type[] typeArguments = ((ParameterizedType) cls.getGenericSuperclass()).getActualTypeArguments();
                    if (typeArguments.length == 0) {
                        return Type.SERIALIZABLE;
                    } else {
                        Class<?> genericClass = (Class<?>) typeArguments[0];
                        Type genType = getType(genericClass);
                        switch (genType) {
                            case INTEGER:
                                return Type.INTEGER_ARRAY_LIST;
                            case STRING:
                                return Type.STRING_ARRAY_LIST;
                            case CHAR_SEQUENCE:
                                return Type.CHAR_SEQUENCE_ARRAY_LIST;
                            case PARCELABLE:
                                return Type.PARCELABLE_ARRAY_LIST;
                            case SERIALIZABLE:
                            default:
                                return Type.SERIALIZABLE;
                        }
                    }
                } else if (cls == SparseArray.class) {
                    java.lang.reflect.Type[] typeArguments = ((ParameterizedType) cls.getGenericSuperclass()).getActualTypeArguments();
                    if (typeArguments.length != 0) {
                        Class<?> genericClass = (Class<?>) typeArguments[0];
                        if (Parcelable.class.isAssignableFrom(genericClass)) {
                            return Type.SPARSE_PARCELABLE_ARRAY;
                        }
                    }
                    throw new IllegalArgumentException("unsupported class: " + cls);
                } else if (cls == Bundle.class) {
                    return Type.BUNDLE;
                } else if (Parcelable.class.isAssignableFrom(cls)) {
                    return Type.PARCELABLE;
                } else if (Serializable.class.isAssignableFrom(cls)) {
                    return Type.SERIALIZABLE;
                } else if (Externalizable.class.isAssignableFrom(cls)) {
                    return Type.SERIALIZABLE;
                } else {
                    throw new IllegalArgumentException("unsupported class: " + cls);
                }
            }
        }
    }

    public Bundle toBundle() {
        return toBundle(null);
    }

    public Bundle toBundle(Bundle out) {
        if (out == null) {
            out = new Bundle();
        }

        Map<Field, Type> fieldTypeMap = getFieldsMap();
        if (fieldTypeMap == null || fieldTypeMap.isEmpty()) {
            return out;
        }

        for (Map.Entry<Field, Type> entry : fieldTypeMap.entrySet()) {
            try {
                writeToBundle(entry.getKey(), entry.getValue(), out);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        out.putString(ARGS_CLASS, getClass().getName());

        return out;
    }

}