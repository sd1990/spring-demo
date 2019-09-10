package org.songdan.spring.mybatis.convertor;

/**
 * @author: Songdan
 * @create: 2019-07-23 19:12
 **/
public class ConvertorHolder {

    private static ThreadLocal<Convertor> context = new ThreadLocal<>();

    public static Convertor set(Convertor convertor) {
        Convertor old = context.get();
        context.set(convertor);
        return old;
    }

    public static Convertor get() {
        return context.get();
    }

    public static void remove() {
        context.remove();
    }

}
