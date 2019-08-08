package id.okvi.tp4d.Helper;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bantuan {
    private final static String expression = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";
    private Context context;

    public Bantuan(Context context) {
        this.context = context;
    }

    public void toastShort(String pesan) {
        Toast.makeText(context, pesan, Toast.LENGTH_SHORT).show();
    }

    public void toastLong(String pesan) {
        Toast.makeText(context, pesan, Toast.LENGTH_LONG).show();
    }

    public void alertDialogDebugging(String pesan) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("Info Debugging")
                .setMessage(pesan)
                .setPositiveButton(android.R.string.yes, null)
                .setCancelable(false)
                .show();
    }

    public void alertDialogPeringatan(String pesan) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("Peringatan")
                .setMessage(pesan)
                .setPositiveButton(android.R.string.yes, null)
                .setCancelable(false)
                .show();
    }

    public void alertDialogInformasi(String pesan) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("Informasi")
                .setMessage(pesan)
                .setPositiveButton(android.R.string.yes, null)
                .setCancelable(false)
                .show();
    }

    public String getVideoId(String videoUrl) {
        if (videoUrl == null || videoUrl.trim().length() <= 0) {
            return null;
        }
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(videoUrl);
        try {
            if (matcher.find())
                return matcher.group();
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
