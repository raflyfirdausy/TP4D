package id.okvi.tp4d.Activity.pemohon;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import id.okvi.tp4d.Helper.Bantuan;
import id.okvi.tp4d.R;

public class BarcodeActivity extends AppCompatActivity {

    private Context context = BarcodeActivity.this;
    private ImageView ivBarcode;
    private TextView tvNoRegis;
    private Button btnKembali;
    private String no_regis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        ivBarcode = findViewById(R.id.ivBarcode);
        tvNoRegis = findViewById(R.id.tvNoRegis);
        btnKembali = findViewById(R.id.btnKembali);

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aksi();
            }
        });

        no_regis = getIntent().getStringExtra("no_regis");
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(no_regis, BarcodeFormat.QR_CODE, 350, 350);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            ivBarcode.setImageBitmap(bitmap);
            tvNoRegis.setText(no_regis);
        } catch (WriterException e) {
            e.printStackTrace();
            new Bantuan(context).toastLong(e.getMessage());
        }
    }

    @Override
    public void onBackPressed() {
        aksi();
    }

    private void aksi() {
        if (getIntent().hasExtra("mode") && getIntent().getStringExtra("mode").equalsIgnoreCase("back")) {
//            new Bantuan(context).toastLong("if");
            finish();
        } else {
//            new Bantuan(context).toastLong("else");
            Intent intent = new Intent(context, PemohonHomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }
}
