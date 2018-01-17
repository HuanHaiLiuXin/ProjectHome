package com.jet.projectone;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 反射的通用工具类
 * <p/>
 * 作者:谭成龙
 * 邮箱:wall0920@163.com
 * 2015/12/18
 */
public final class ReflectionUtils {
    private ReflectionUtils() {
        throw new RuntimeException("工具类禁止实例化");
    }

    /**
     * 用于对类的字段赋值，无视private,project修饰符,无视set/get方法
     *
     * @param c    要反射的类
     * @param args 类的字段名和值 每个字段名和值用英文逗号隔开
     * @return
     *
     * 使用示例:
            RetryManager retryManager = (RetryManager) ReflectionUtils.getInstance(RetryManager.class, "MAX_TAP_TO_RETRY_ATTEMPTS,20", "mTapToRetryEnabled,true", "mMaxTapToRetryAttempts,20", "mTapToRetryAttempts,0");
            Smss sms = (Smss)getInstance(Smss.class, "destID,01201101", "mobile,15810022404", "content,测试数据。");
     */
    public static Object getInstance(Class c, String... args) {
        try {
            Object object = Class.forName(c.getName()).newInstance();
            Class<?> obj = object.getClass();
            Field[] fields = obj.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                for (int j = 0; j < args.length; j++) {
                    String str = args[j];
                    String strs[] = str.split(",");
                    if (strs[0].equals(fields[i].getName())) {
                        fields[i].set(object, strs[1]);
                        break;
                    }
                }
            }
            return object;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 利用递归找一个类的指定方法，如果找不到，去父亲里面找直到最上层Object对象为止。
     *
     * @param clazz      目标类
     * @param methodName 方法名
     * @param classes    方法参数类型数组
     * @return 方法对象
     * @throws Exception
     */
    public static Method getMethod(Class clazz, String methodName,
                                   final Class[] classes) throws Exception {
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(methodName, classes);
        } catch (NoSuchMethodException e) {
            try {
                method = clazz.getMethod(methodName, classes);
            } catch (NoSuchMethodException ex) {
                if (clazz.getSuperclass() == null) {
                    return method;
                } else {
                    method = getMethod(clazz.getSuperclass(), methodName,
                            classes);
                }
            }
        }
        return method;
    }

    /**
     * @param obj        调整方法的对象
     * @param methodName 方法名
     * @param classes    参数类型数组
     * @param objects    参数数组
     * @return 方法的返回值
     *
     * 使用示例:
            invoke(new B(), "printlnA", new Class[] { String.class },new Object[] { "test" });
            invoke(new B(), "printlnB");
     */
    public static Object invoke(final Object obj, final String methodName,
                                final Class[] classes, final Object[] objects) {
        try {
            Method method = getMethod(obj.getClass(), methodName, classes);
            method.setAccessible(true);// 调用private方法的关键一句话
            return method.invoke(obj, objects);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object invoke(final Object obj, final String methodName,
                                final Class[] classes) {
        return invoke(obj, methodName, classes, new Object[]{});
    }

    public static Object invoke(final Object obj, final String methodName) {
        return invoke(obj, methodName, new Class[]{}, new Object[]{});
    }

    /**
     * 将当前Java Bean对象中指定的属性集合以HashMap的形式存储起来:TempData.TEMP_HASHMAP
     *     注意:
     *      属性值的类型只能是 基本类型+String+ICloneable
     *
     * @param object
     * @param fieldNames
     * @return
     *
     * 使用示例:
            List<String> fieldNames = new ArrayList<String>();
            fieldNames.add("name");
            fieldNames.add("weight");
            fieldNames.add("children");
            ReflectionUtils.backupProp(ActivityRef.this,fieldNames);
     */
    public static void backupProp(Object object, List<String> fieldNames) {
        /*TempData.TEMP_HASHMAP.clear();
        Cloner cloner=new Cloner();
        try {
            Class<?> clazz = object.getClass();
            for (String fieldName : fieldNames) {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                Object fieldData = field.get(object);
                Object deepCloneObj =cloner.deepClone(fieldData);
                TempData.TEMP_HASHMAP.put(fieldName, deepCloneObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    /**
     * 将指定  HashMap-->TempData.TEMP_HASHMAP 中存储的值根据其key 赋值 到当前的JAVA BEAN 对象对应属性中.
     *
     * @param object
     *
     * 使用示例:
            ReflectionUtils.restoreProp(ActivityRef.this);
     */
    public static void restoreProp(Object object) {
        /*Cloner cloner=new Cloner();
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                if (TempData.TEMP_HASHMAP.containsKey(fieldName)) {
                    Object mapData = TempData.TEMP_HASHMAP.get(fieldName);
                    Object deepCloneObj =cloner.deepClone(mapData);
                    field.set(object, deepCloneObj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}