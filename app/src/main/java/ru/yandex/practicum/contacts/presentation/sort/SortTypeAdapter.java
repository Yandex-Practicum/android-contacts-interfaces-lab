package ru.yandex.practicum.contacts.presentation.sort;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.yandex.practicum.contacts.R;
import ru.yandex.practicum.contacts.databinding.ItemSortBinding;
import ru.yandex.practicum.contacts.presentation.base.BaseListDiffCallback;
import ru.yandex.practicum.contacts.presentation.sort.model.SortType;
import ru.yandex.practicum.contacts.presentation.sort.model.SortTypeUI;

public class SortTypeAdapter extends RecyclerView.Adapter<SortTypeAdapter.ViewHolder> {

    private final AsyncListDiffer<SortTypeUI> differ = new AsyncListDiffer<>(
            this,
            new BaseListDiffCallback<SortTypeUI>()
    );

    private OnClickListener clickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemSortBinding binding = ItemSortBinding.inflate(inflater, parent, false);
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

    public void submitList(List<SortTypeUI> list) {
        differ.submitList(list);
    }

    public void setClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemSortBinding binding;
        private SortTypeUI data;

        public ViewHolder(@NonNull ItemSortBinding binding, OnClickListener clickListener) {
            super(binding.getRoot());
            this.binding = binding;

            itemView.setOnClickListener(v -> {
                if (clickListener != null && data != null) {
                    clickListener.onSortTypeItemClick(data);
                }
            });
        }

        public void bind(SortTypeUI data) {
            this.data = data;
            binding.text.setText(getStringRes(data.getSortType()));
            binding.selected.setVisibility(data.isSelected() ? View.VISIBLE : View.GONE);
        }

        private int getStringRes(SortType sortType) {
            switch (sortType) {
                case BY_SURNAME:
                    return R.string.sort_by_surname;
                case BY_NAME:
                default:
                    return R.string.sort_by_name;
            }
        }
    }

    public interface OnClickListener {
        void onSortTypeItemClick(SortTypeUI sortType);
    }
}