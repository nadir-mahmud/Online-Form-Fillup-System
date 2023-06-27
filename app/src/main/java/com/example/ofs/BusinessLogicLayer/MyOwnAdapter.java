package com.example.ofs.BusinessLogicLayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ofs.entity.Attendance;

import java.util.List;
import com.example.ofs.R;

public class MyOwnAdapter extends RecyclerView.Adapter<MyOwnAdapter.MyOwnHolder> {

    private Context mContext;
    private List<Attendance> Attendances;
    private RecyclerviewClickListener listener;


    public MyOwnAdapter(Context mContext, List<Attendance> attendances, RecyclerviewClickListener listener) {
        this.mContext = mContext;
        Attendances = attendances;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyOwnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row,parent,false);
        MyOwnHolder holder = new MyOwnHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyOwnHolder holder, int position) {
        Attendance list = Attendances.get(position);
        holder.studentId.setText(list.getStudentId());
        holder.attendance.setText(list.getAttendance());

    }

    @Override
    public int getItemCount() {
        return Attendances.size();
    }

    public class MyOwnHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView studentId,attendance;


        public MyOwnHolder(View itemView) {
            super(itemView);
            studentId =(TextView)itemView.findViewById(R.id.textView);
            attendance =(TextView)itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onclick(view, getAdapterPosition());
        }
    }

    public interface RecyclerviewClickListener{
        void onclick(View view, int postition);
    }

    }

