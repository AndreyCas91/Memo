package ru.geekbrains.memo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.MemoVievHolder> {

    private static final String TAG = "adapter";

    private final List<SimpleNote> simpleNoteList = new ArrayList<>();
    private final MemoAdapterCallback callback;

    public MemoAdapter(MemoAdapterCallback callback) {
        this.callback = callback;
    }

    public void setItems(List<SimpleNote> item){
        simpleNoteList.clear();
        simpleNoteList.addAll(item);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MemoVievHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_memo, parent, false);
        return new MemoVievHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoVievHolder holder, int position) {
        holder.onBind(simpleNoteList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return simpleNoteList.size();
    }

    class MemoVievHolder extends RecyclerView.ViewHolder{

        private final MaterialTextView textView;

        public MemoVievHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_item_memo);
        }

        public void onBind(SimpleNote numb, int position){
            Log.d(TAG, String.valueOf(position));
            textView.setText(numb.getTitle());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                        callback.onItemClicked(getAdapterPosition());
                    }
                }
            });
        }
    }
}
