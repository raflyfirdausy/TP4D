package id.okvi.tp4d.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.okvi.tp4d.Model.DaftarPemohonModel;
import id.okvi.tp4d.R;

public class PetugasBaruAdapter extends RecyclerView.Adapter<PetugasBaruAdapter.ViewHolder> {

    private Context context;
    private List<DaftarPemohonModel> list;

    public PetugasBaruAdapter(Context context, List<DaftarPemohonModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_baru_petugas, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvNomerSurat.setText(list.get(position).getNomer_surat());
        holder.tvTanggal.setText(list.get(position).getTanggal_surat());
        holder.tvInstansiPemohon.setText(list.get(position).getInstansi_pemohon());
        holder.tvJenisKegiatan.setText(list.get(position).getJenis_kegiatan());
        holder.tvPaguAnggaran.setText(list.get(position).getPagu_anggaran());
        holder.tvTahun.setText(list.get(position).getTahun_anggaran());
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
