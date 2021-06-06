package com.health.SchoolHealth.util;

import org.assertj.core.util.Lists;

import java.util.Iterator;
import java.util.List;

public class RepositoryUtil {
    public static<T> Iterable<T> iteratorToIterable(Iterator<T> iterator) {
        return () -> iterator;
    }

    public static<T> Iterable<T> listToIterable(List<T> list) {

        return iteratorToIterable(list.iterator());
    }

    public static<T> List<T> iterableToList(Iterable<T> iterable) {
        return  Lists.newArrayList(iterable);
    }
}
