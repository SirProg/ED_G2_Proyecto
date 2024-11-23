package com.example.dsproyect_p1.modules.contacts_overview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dsproyect_p1.R;
import com.example.dsproyect_p1.data.model.Person;
import java.util.List;

public class PersonRecyclerView extends RecyclerView.Adapter<PersonRecyclerView.ViewHolder> {
  private List<Person> personList;
  private onItemClickListener listener;

  public PersonRecyclerView(List<Person> persons, onItemClickListener listener) {
    this.personList = persons;
    this.listener = listener;
  }

  public interface onItemClickListener {
    void onItemClickPerson(Person person);
  }

  public void updateData(List<Person> persons) {
    this.personList = persons;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public PersonRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_contact, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Person person = personList.get(position);
    holder.bindData(person, listener);
  }

  @Override
  public int getItemCount() {
    return personList.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    private TextView nameContact;
    private ImageView imageContact;
    private LinearLayout linearLayout;

    public ViewHolder(View view) {
      super(view);
      this.nameContact = itemView.findViewById(R.id.tvNombreContact);
      this.imageContact = itemView.findViewById(R.id.ivContact);
      this.linearLayout = itemView.findViewById(R.id.linearLayoutContact);
    }

    public void bindData(final Person person, final onItemClickListener listener) {
      String name = person.getFirstName() + " " + person.getLastName();
      nameContact.setText(name);
      linearLayout.setOnClickListener(
          view -> {
            if (listener != null) {
              listener.onItemClickPerson(person);
            }
          });
    }
  }
}
