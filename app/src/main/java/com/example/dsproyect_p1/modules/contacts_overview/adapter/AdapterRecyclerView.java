package com.example.dsproyect_p1.modules.contacts_overview.view.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.dsproyect_p1.modules.contact_details.ContactDetails;
import java.util.List;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder> {
  private Context context;
  private List<Person> personList;

  public AdapterRecyclerView(Context context, List<Person> persons) {
    this.context = context;
    this.personList = persons;
  }

  @NonNull
  @Override
  public AdapterRecyclerView.ViewHolder onCreateViewHolder(
      @NonNull ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_contact, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Person person = personList.get(position);
    holder.nameContact.setText(person.getFirstName() + " " + person.getLastName());
    holder.linearLayout.setOnClickListener(
        v -> {
          Intent intent = new Intent(context, ContactDetails.class);
          intent.putExtra("PERSON_ID", person.getId().toString());
          context.startActivity(intent);
        });
  }

  @Override
  public int getItemCount() {
    return personList.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    Context contextViewHolder;
    private TextView nameContact;
    private ImageView imagenContact;
    private LinearLayout linearLayout;

    public ViewHolder(View view) {
      super(view);
      this.contextViewHolder = view.getContext();
      this.nameContact = itemView.findViewById(R.id.tvNombreContact);
      this.imagenContact = itemView.findViewById(R.id.ivContact);
      this.linearLayout = itemView.findViewById(R.id.linearLayoutContact);
    }

    public TextView getTextView() {
      return nameContact;
    }
  }
}
