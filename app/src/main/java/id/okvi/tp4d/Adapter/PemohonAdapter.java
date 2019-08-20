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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import id.okvi.tp4d.Activity.kajari.KajariActionProgressActivity;
import id.okvi.tp4d.Model.DaftarPemohonModel;
import id.okvi.tp4d.R;

public class PemohonAdapter extends RecyclerView.Adapter<PemohonAdapter.ViewHolder> {

    private Context context;
    private List<DaftarPemohonModel> list;
    private List<DaftarPemohonModel> list_sementara;
    private String mode;

    public PemohonAdapter(Context context, List<DaftarPemohonModel> list, String mode) {
        this.context = context;
        this.list = list;
        this.mode = mode;
        this.list_sementara = new ArrayList<>();
        this.list_sementara.addAll(list);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_baru_petugas, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvNomerSurat.setText(list.get(position).getNomer_surat());
        holder.tvTanggal.setText(list.get(position).getTanggal_surat());
        holder.tvInstansiPemohon.setText("Instansi Pemohon : " + list.get(position).getInstansi_pemohon());
        holder.tvJenisKegiatan.setText("Jenis Kegiatan : " + list.get(position).getJenis_kegiatan());
        holder.tvPaguAnggaran.setText("Pagu Anggaran : " + list.get(position).getPagu_anggaran());
        holder.tvTahun.setText("Tahun : " + list.get(position).getTahun_anggaran());
        holder.tvNomerRegistrasi.setText("No Registrasi : " + list.get(position).getNo_regis());

        if (mode.equalsIgnoreCase("progress")) {
            holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, KajariActionProgressActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", list.get(position));
                    intent.putExtras(bundle);
                    intent.putExtra("mode", mode);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void cariData(String text) {
        text = text.toLowerCase(Locale.getDefault());
        list.clear();
        if (text.length() == 0) {
            list.addAll(list_sementara);
        } else {
            for (int i = 0; i < list_sementara.size(); i++) {
                if (list_sementara.get(i).getNo_regis().toLowerCase(Locale.getDefault()).contains(text)) {
                    list.add(list_sementara.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomerSurat;
        TextView tvNomerRegistrasi;
        TextView tvTanggal;
        TextView tvInstansiPemohon;
        TextView tvJenisKegiatan;
        TextView tvPaguAnggaran;
        TextView tvTahun;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNomerSurat = itemView.findViewById(R.id.tvNomerSurat);
            tvNomerRegistrasi = itemView.findViewById(R.id.tvNomerRegistrasi);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
            tvInstansiPemohon = itemView.findViewById(R.id.tvInstansiPemohon);
            tvJenisKegiatan = itemView.findViewById(R.id.tvJenisKegiatan);
            tvPaguAnggaran = itemView.findViewById(R.id.tvPaguAnggaran);
            tvTahun = itemView.findViewById(R.id.tvTahun);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }
}
