package id.okvi.tp4d.Adapter;

import android.annotation.SuppressLint;
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

import id.okvi.tp4d.Activity.PetugasActionPermohonanActivity;
import id.okvi.tp4d.Model.DaftarPemohonModel;
import id.okvi.tp4d.R;

public class PetugasBaruAdapter extends RecyclerView.Adapter<PetugasBaruAdapter.ViewHolder> {

    private Context context;
    private List<DaftarPemohonModel> list;
    private String mode = "baru";

    public PetugasBaruAdapter(Context context, List<DaftarPemohonModel> list, String mode) {
        this.context = context;
        this.list = list;
        this.mode = mode;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_baru_petugas, parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvNomerSurat.setText(list.get(position).getNomer_surat());
        holder.tvTanggal.setText(list.get(position).getTanggal_surat());
        holder.tvInstansiPemohon.setText("Instansi Pemohon : " + list.get(position).getInstansi_pemohon());
        holder.tvJenisKegiatan.setText("Jenis Kegiatan : " + list.get(position).getJenis_kegiatan());
        holder.tvPaguAnggaran.setText("Pagu Anggaran : " + list.get(position).getPagu_anggaran());
        holder.tvTahun.setText("Tahun : " + list.get(position).getTahun_anggaran());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PetugasActionPermohonanActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", list.get(position));
                intent.putExtras(bundle);
                intent.putExtra("mode", mode);
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
        TextView tvInstansiPemohon;
        TextView tvJenisKegiatan;
        TextView tvPaguAnggaran;
        TextView tvTahun;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNomerSurat = itemView.findViewById(R.id.tvNomerSurat);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
            tvInstansiPemohon = itemView.findViewById(R.id.tvInstansiPemohon);
            tvJenisKegiatan = itemView.findViewById(R.id.tvJenisKegiatan);
            tvPaguAnggaran = itemView.findViewById(R.id.tvPaguAnggaran);
            tvTahun = itemView.findViewById(R.id.tvTahun);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }
}
