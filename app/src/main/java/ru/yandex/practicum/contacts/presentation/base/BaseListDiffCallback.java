package ru.yandex.practicum.contacts.presentation.base;

import androidx.recyclerview.widget.DiffUtil;

import org.jetbrains.annotations.NotNull;

public class BaseListDiffCallback<T extends ListDiffInterface<T>> extends DiffUtil.ItemCallback<T> {
    @Override
    public boolean areItemsTheSame(@NotNull T oldItem, @NotNull T newItem) {
        return oldItem.theSameAs(newItem);
    }

    @Override
    public boolean areContentsTheSame(@NotNull T oldItem, @NotNull T newItem) {
        return oldItem.equals(newItem);
    }
}


