package id.okvi.tp4d.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.okvi.tp4d.R;

public class FotoAdapter extends RecyclerView.Adapter<FotoAdapter.ViewHolder> {

    private Context context;
    private List<String> list;

    public FotoAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_foto, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Picasso.get().load(list.get(position))
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.img_error)
                .into(holder.ivFoto);

        holder.ivFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                View view1 = layoutInflater.inflate(R.layout.image_popup, null);
                ImageView imgPopup = view1.findViewById(R.id.imgPopup);
                imgPopup.setImageDrawable(holder.ivFoto.getDrawable());
                dialogBuilder.setView(view1);
                dialogBuilder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.ivFoto);
        }
    }
}
