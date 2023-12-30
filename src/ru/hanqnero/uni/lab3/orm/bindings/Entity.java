package ru.hanqnero.uni.lab3.orm.bindings;

import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Entity {
    Table tableName(); // table name
}
