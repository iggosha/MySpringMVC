package com.sportline.exc.custom;


import com.sportline.exc.GlobalAppException;

public class CustomNotFoundException extends GlobalAppException {

    public CustomNotFoundException(String objectName, Long id) {
        super(404, "Объект " + objectName + " с идентификатором " + id + " не найден");
    }
}