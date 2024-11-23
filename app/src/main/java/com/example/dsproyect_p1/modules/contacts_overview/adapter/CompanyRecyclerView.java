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
import com.example.dsproyect_p1.data.model.Company;
import java.util.List;

public class CompanyRecyclerView extends RecyclerView.Adapter<CompanyRecyclerView.ViewHolder> {
  private List<Company> companyList;
  private onItemClickListener listener;

  public CompanyRecyclerView(List<Company> companies, onItemClickListener listener) {
    this.companyList = companies;
    this.listener = listener;
  }

  public interface onItemClickListener {
    void onItemClickCompany(Company company);
  }

  public void updateData(List<Company> companies) {
    this.companyList = companies;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public CompanyRecyclerView.ViewHolder onCreateViewHolder(
      @NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_contact, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Company company = companyList.get(position);
    holder.bindData(company, listener);
  }

  @Override
  public int getItemCount() {
    return companyList.size();
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

    public void bindData(final Company company, final onItemClickListener listener) {
      nameContact.setText(company.getName());
      linearLayout.setOnClickListener(
          view -> {
            if (listener != null) {
              listener.onItemClickCompany(company);
            }
          });
    }
  }
}
