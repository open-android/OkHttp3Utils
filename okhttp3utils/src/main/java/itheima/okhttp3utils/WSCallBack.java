package itheima.okhttp3utils;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;

/**
 * Created by wschun on 2016/10/7.
 */

public abstract class WSCallBack<T> {

    Type type;

    static Type getSuperclassTypeParameter(Class<?> subclass)
    {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class)
        {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    public WSCallBack()
    {
        type = getSuperclassTypeParameter(getClass());
    }

    public abstract void onFailure(Call call,  Exception e)  ;

    public abstract void onSuccess( T t);

}
