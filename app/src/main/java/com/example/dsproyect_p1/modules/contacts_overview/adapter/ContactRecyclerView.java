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
import com.example.dsproyect_p1.data.model.Contact;
import java.util.List;

public class ContactRecyclerView extends RecyclerView.Adapter<ContactRecyclerView.ViewHolder> {
  private List<Contact> contactList;
  private onItemClickListener listener;

  public ContactRecyclerView(List<Contact> contacts, onItemClickListener listener) {
    this.contactList = contacts;
    this.listener = listener;
  }

  public interface onItemClickListener {
    void onItemClickContact(Contact contact);
  }

  public void updateData(List<Contact> contacts) {
    this.contactList = contacts;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public ContactRecyclerView.ViewHolder onCreateViewHolder(
      @NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_contact, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Contact contact = contactList.get(position);
    holder.bindData(contact, listener);
  }

  @Override
  public int getItemCount() {
    return contactList.size();
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

    public void bindData(final Contact contact, final onItemClickListener listener) {
      nameContact.setText(contact.getName());
      linearLayout.setOnClickListener(
          view -> {
            if (listener != null) {
              listener.onItemClickContact(contact);
            }
          });
    }
  }
}
