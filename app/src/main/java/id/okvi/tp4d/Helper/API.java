package id.okvi.tp4d.Helper;

public class API {

    public final static String HOST_ROOT = "http://192.168.1.2/tp4d/api/";

    public final static String LOGIN_PEMOHON = HOST_ROOT + "login_pemohon.php";
    public final static String REGISTER_PEMOHON = HOST_ROOT + "register_pemohon.php";
    public final static String INSERT_PEMOHON = HOST_ROOT + "insert_pemohon.php";
    public final static String LOGIN_PETUGAS = HOST_ROOT + "login_petugas.php";
    public final static String LOGIN_KAJARI = HOST_ROOT + "login_kajari.php";
    public final static String GET_KAJARI_BARU = HOST_ROOT + "get_kajari_baru.php";
    public final static String UPDATE_DISPOSISI = HOST_ROOT + "update_disposisi.php";
    public final static String GET_PETUGAS_BARU = HOST_ROOT + "get_petugas_baru.php?disposisi=";
    public final static String PETUGAS_TOLAK_PERMOHONAN = HOST_ROOT + "petugas_tolak_permohonan.php?id_daftar_pemohon=";
}
