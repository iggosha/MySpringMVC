package com.sportline.exc;


public class CustomNotFoundException extends GlobalAppException {

    public CustomNotFoundException(String objectName, Long id) {
        super(404, "Объект " + objectName + " с идентификатором " + id + " не найден");
    }
}