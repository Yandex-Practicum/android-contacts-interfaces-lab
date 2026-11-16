package ru.yandex.practicum.contacts.presentation.filter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.yandex.practicum.contacts.databinding.ItemFilterBinding;
import ru.yandex.practicum.contacts.presentation.base.BaseListDiffCallback;
import ru.yandex.practicum.contacts.presentation.filter.model.FilterContactTypeUi;

public class FilterContactTypeAdapter extends RecyclerView.Adapter<FilterContactTypeAdapter.ViewHolder> {

    private final AsyncListDiffer<FilterContactTypeUi> differ = new AsyncListDiffer<>(
            this,
            new BaseListDiffCallback<FilterContactTypeUi>()
    );

    private OnClickListener clickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemFilterBinding binding = ItemFilterBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(differ.getCurrentList().get(position));
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }

    public void submitList(List<FilterContactTypeUi> list) {
        differ.submitList(list);
    }

    public void setClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemFilterBinding binding;
        private FilterContactTypeUi data;

        public ViewHolder(@NonNull ItemFilterBinding binding, OnClickListener clickListener) {
            super(binding.getRoot());
            this.binding = binding;

            itemView.setOnClickListener(v -> {
                if (clickListener != null && data != null) {
                    clickListener.onFilterTypeItemClick(data);
                }
            });
        }

        public void bind(FilterContactTypeUi data) {
            this.data = data;
            // Используем getContactType() для получения типа контакта
            binding.text.setText(data.getContactType().name());
            binding.selected.setChecked(data.isSelected());
        }
    }

    public interface OnClickListener {
        void onFilterTypeItemClick(FilterContactTypeUi type);
    }
}