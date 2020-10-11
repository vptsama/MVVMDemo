package com.kirin.mvvmfirsttry;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Create date: 2020/10/11 15:51
 * Create by: chixilun
 * Profile:
 */
public class NoteAdapter extends ListAdapter<Note,NoteAdapter.NoteHolder>{

    private OnItemClickListener mListener;

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    //DiffUtils会分辨list到底有没有变化？好多方法都在listAdapter中实现了
    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item,parent,false);

        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote =getItem(position);
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewPriority.setText(String.valueOf(currentNote.getPriority()));
        holder.textViewDescription.setText(currentNote.getDescription());
    }


    public Note getNoteAt(int position){
        return getItem(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView textViewPriority;
        private TextView textViewDescription;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
            textViewDescription = itemView.findViewById(R.id.text_view_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(mListener != null && position != RecyclerView.NO_POSITION) {
                        mListener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    //为了实现点击更改item，自己定义一个接口
    public interface OnItemClickListener{
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

}
