package com.example.uiinterface.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uiinterface.R;
import com.example.uiinterface.entities.Person;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {

    Context context;
    List<Person> personList;
    public PersonAdapter(Context context) {
        this.context = context;
    }

    public void setPersonList(List <Person> list){
        personList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( context ).inflate( R.layout.row_person, parent, false );

        return new MyViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.textViewName.setText( personList.get( position ).getName() );
        holder.textViewEmail.setText( personList.get( position ).getEmail() );
        holder.textViewDate.setText( personList.get( position ).getDate() );

    }

    @Override
    public int getItemCount() {
        if (personList == null) {
            return 0;
        }
        return personList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewName, textViewEmail, textViewDate;


        public MyViewHolder(@NonNull View itemView) {
            super( itemView );

            textViewName = itemView.findViewById( R.id.txt_name );
            textViewEmail = itemView.findViewById( R.id.txt_email );
            textViewDate = itemView.findViewById( R.id.txt_date );


        }
    }
}
