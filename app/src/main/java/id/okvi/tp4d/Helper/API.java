package id.okvi.tp4d.Helper;

public class API {

    public final static String HOST_ROOT = "https://okvi.000webhostapp.com/api/";
//    public final static String HOST_ROOT = "http://192.168.1.5/tp4d/api/";

    public final static String LOGIN_PEMOHON = HOST_ROOT + "login_pemohon.php";
    public final static String REGISTER_PEMOHON = HOST_ROOT + "register_pemohon.php";
    public final static String INSERT_PEMOHON = HOST_ROOT + "insert_pemohon.php";
    public final static String LOGIN_PETUGAS = HOST_ROOT + "login_petugas.php";
    public final static String LOGIN_KAJARI = HOST_ROOT + "login_kajari.php";
    public final static String GET_KAJARI_BARU = HOST_ROOT + "get_kajari_baru.php";
    public final static String UPDATE_DISPOSISI = HOST_ROOT + "update_disposisi.php";
    public final static String GET_PETUGAS_BARU = HOST_ROOT + "get_petugas_baru.php?disposisi=";
    public final static String PETUGAS_TOLAK_PERMOHONAN = HOST_ROOT + "petugas_tolak_permohonan.php?id_daftar_pemohon=";
    public final static String PETUGAS_TERIMA_PERMOHONAN = HOST_ROOT + "petugas_terima_permohonan.php";
    public final static String GET_PETUGAS_TOLAK = HOST_ROOT + "get_petugas_tolak.php?disposisi=";
    public final static String GET_PETUGAS_PROGRESS = HOST_ROOT + "get_petugas_progress.php?disposisi=";
    public final static String GET_PETUGAS_SELESAI = HOST_ROOT + "get_petugas_selesai.php?disposisi=";
    public final static String UPDATE_PROSES_TAHAP_PERTAMA = HOST_ROOT + "update_proses_tahap_pertama.php";
    public final static String UPLOAD_DOKUMEN = HOST_ROOT + "upload_dokumen.php";
    public final static String GET_FOTO_DOKUMENTASI = HOST_ROOT + "get_foto_dokumentasi.php?id_daftar_pemohon=";
    public final static String UPDATE_PROSES_LANJUTAN = HOST_ROOT + "update_proses_lanjutan.php";
    public final static String GET_KAJARI_PROGRESS = HOST_ROOT + "get_kajari_progress.php";
    public final static String GET_KAJARI_SELESAI = HOST_ROOT + "get_kajari_selesai.php";
    public final static String GET_KAJARI_TOLAK = HOST_ROOT + "get_kajari_tolak.php";
    public final static String GET_PEMOHON_PROGRESS = HOST_ROOT + "get_pemohon_progress.php?id_pemohon=";
    public final static String GET_PEMOHON_SELESAI = HOST_ROOT + "get_pemohon_selesai.php?id_pemohon=";
}
