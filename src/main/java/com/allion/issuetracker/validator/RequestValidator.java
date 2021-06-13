package com.allion.issuetracker.validator;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class RequestValidator {

    public static <E extends Enum<E>> boolean isInEnum(String value, Class<E> enumClass) {
        for (E e : enumClass.getEnumConstants()) {
            if(e.name().equals(value)) { return true; }
        }
        return false;
    }

    public static <E extends Enum<E>>   boolean checkValidEnum(List<String> inputList, Class<E> enumClass ){
        AtomicBoolean existCorrectEnum = new AtomicBoolean(false);
        inputList.forEach(input -> {
        existCorrectEnum.set(isInEnum(input, enumClass));
      });
        return existCorrectEnum.get();
    }
}
