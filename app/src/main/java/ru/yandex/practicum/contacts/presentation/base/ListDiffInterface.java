package ru.yandex.practicum.contacts.presentation.base;

public interface ListDiffInterface <E>{
    boolean theSameAs(E element);
    boolean equals(Object o);
}
