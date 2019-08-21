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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import id.okvi.tp4d.Activity.kajari.KajariActionDisposisiActivity;
import id.okvi.tp4d.Activity.kajari.KajariActionProgressActivity;
import id.okvi.tp4d.Helper.Bantuan;
import id.okvi.tp4d.Helper.Waktu;
import id.okvi.tp4d.Model.DaftarPemohonModel;
import id.okvi.tp4d.R;

public class KajariAdapter extends RecyclerView.Adapter<KajariAdapter.ViewHolder> {

    private Context context;
    private List<DaftarPemohonModel> list;
    private List<DaftarPemohonModel> list_sementara;
    private String mode;

    public KajariAdapter(Context context, List<DaftarPemohonModel> list, String mode) {
        this.context = context;
        this.list = list;
        this.mode = mode;
        this.list_sementara = new ArrayList<>();
        this.list_sementara.addAll(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_baru_kajari, parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.tvNomerSurat.setText(list.get(position).getNomer_surat());
        holder.tvTanggal.setText(list.get(position).getTanggal_surat());
        holder.tvNomerRegistrasi.setText(list.get(position).getNo_regis());
        holder.tvJenisKegiatan.setText("Jenis kegiatan : " + list.get(position).getJenis_kegiatan());
        holder.tvLokasi.setText("Lokasi : " + list.get(position).getLokasi_kegiatan());
        Date tanggalAwal = null, tanggalAkhir = null;
        SimpleDateFormat asli = new SimpleDateFormat("yyyy-mm-dd");
        SimpleDateFormat modify = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        try {
            tanggalAwal = asli.parse(list.get(position).getAwal_pekerjaan());
            tanggalAkhir = asli.parse(list.get(position).getAkhir_pekerjaan());

        } catch (ParseException e) {
            new Bantuan(context).toastLong(e.getMessage());
            e.printStackTrace();
        }

        long timeStampAwal = new Waktu(context).convertToTimeStap(modify.format(tanggalAwal));
        long timeStampAkhir = new Waktu(context).convertToTimeStap(modify.format(tanggalAkhir));

        holder.tvWaktuPengerjaan.setText("Waktu pengerjaan : " + new Waktu(context).getBerapaHari(
                new Date(timeStampAwal), new Date(timeStampAkhir)
        ) + " Hari");

        if (this.mode.equalsIgnoreCase("baru")) {
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
        } else {
            holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, KajariActionProgressActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", list.get(position));
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }

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
