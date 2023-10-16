package br.com.javaweekrocketseat.todolist.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Utils {

    // Nesse método ele vai fazer a mescla do o que atualizamos com o que já existe
    public static void copyNonNullProperties(Object source, Object target) {

        BeanUtils.copyProperties(source, target, getNullpropertyNames(source));
    }

    // Nesse método ele vai pegar tudo que temos de value null no obj
    public static String[] getNullpropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);

        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();

        for (PropertyDescriptor pd: pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
