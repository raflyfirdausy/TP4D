package id.okvi.tp4d.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.okvi.tp4d.Activity.KajariActionDisposisiActivity;
import id.okvi.tp4d.Model.DaftarPemohonModel;
import id.okvi.tp4d.R;

public class KajariBaruAdapter extends RecyclerView.Adapter<KajariBaruAdapter.ViewHolder> {

    private Context context;
    private List<DaftarPemohonModel> list;

    public KajariBaruAdapter(Context context, List<DaftarPemohonModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_baru, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.tvNomerSurat.setText(list.get(position).getNomer_surat());
        holder.tvTanggal.setText(list.get(position).getTanggal_surat());
        holder.tvNomerRegistrasi.setText(list.get(position).getNo_regis());
        holder.tvJenisKegiatan.setText(list.get(position).getJenis_kegiatan());
        holder.tvLokasi.setText(list.get(position).getLokasi_kegiatan());
        holder.tvWaktuPengerjaan.setText("ngko slur");

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, KajariActionDisposisiActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", list.get(position));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomerSurat;
        TextView tvTanggal;
        TextView tvNomerRegistrasi;
        TextView tvJenisKegiatan;
        TextView tvLokasi;
        TextView tvWaktuPengerjaan;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNomerSurat = itemView.findViewById(R.id.tvNomerSurat);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
            tvNomerRegistrasi = itemView.findViewById(R.id.tvNomerRegistrasi);
            tvJenisKegiatan = itemView.findViewById(R.id.tvJenisKegiatan);
            tvLokasi = itemView.findViewById(R.id.tvLokasi);
            tvWaktuPengerjaan = itemView.findViewById(R.id.tvWaktuPengerjaan);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }
}
