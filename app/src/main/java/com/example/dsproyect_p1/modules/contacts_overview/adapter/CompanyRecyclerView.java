package com.example.dsproyect_p1.modules.contacts_overview.adapter;

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
import com.example.dsproyect_p1.data.model.Company;
import com.example.dsproyect_p1.modules.company_details.view.CompanyDetailsActivity;
import java.util.List;

public class CompanyRecyclerView extends RecyclerView.Adapter<CompanyRecyclerView.ViewHolder> {
  private Context context;
  private List<Company> companyList;
  final CompanyRecyclerView.onItemClickListener listener;

  public interface onItemClickListener{
    void onItemClick(Company company);
  }

  public CompanyRecyclerView(Context context, List<Company> companies, CompanyRecyclerView.onItemClickListener listener) {
    this.context = context;
    this.companyList = companies;
    this.listener = listener;
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
    holder.nameContact.setText(company.getName());
    holder.linearLayout.setOnClickListener(
        v -> {
          Intent intent = new Intent(context, CompanyDetailsActivity.class);
          intent.putExtra("PERSON_ID", company.getId().toString());
          context.startActivity(intent);
        });

  }

  @Override
  public int getItemCount() {
    return companyList.size();
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

    public void binData(final Company company){
      nameContact.setText(company.getName());
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          listener.onItemClick(company);
        }
      });
    }

    public TextView getTextView() {
      return nameContact;
    }
  }
}
