package com.sportline.model.entity;

public enum Status {
    IN_STOCK,
    OUT_OF_STOCK,
    PRE_ORDER,
    WILL_BE_SOON;

    public String getTranslated() {
        if (this.equals(IN_STOCK)) {
            return "В наличии";
        } else if (this.equals(OUT_OF_STOCK)) {
            return "Нет в наличии";
        } else if (this.equals(PRE_ORDER)) {
            return "По предзаказу";
        } else if (this.equals(WILL_BE_SOON)) {
            return "Скоро в продаже";
        } else  {
            return "";
        }
    }
}