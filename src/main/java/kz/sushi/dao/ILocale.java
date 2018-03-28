package kz.sushi.dao;

import kz.sushi.dao.entity.Locale;

public interface ILocale extends IBasic<Locale> {
    Locale getLocaleByName(String name);
}
