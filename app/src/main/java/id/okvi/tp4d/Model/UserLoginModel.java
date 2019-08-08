package id.okvi.tp4d.Model;

import java.io.Serializable;

public class UserLoginModel implements Serializable {
    private String id_pemohon, jenis, email, instansi;

    public UserLoginModel(String id_pemohon, String jenis, String email, String instansi) {
        this.id_pemohon = id_pemohon;
        this.jenis = jenis;
        this.email = email;
        this.instansi = instansi;
    }

    public UserLoginModel(String jenis, String email, String instansi) {
        this.jenis = jenis;
        this.email = email;
        this.instansi = instansi;
    }

    public UserLoginModel(String jenis, String email) {
        this.jenis = jenis;
        this.email = email;
    }

    public UserLoginModel() {

    }

    public String getId_pemohon() {
        return id_pemohon;
    }

    public void setId_pemohon(String id_pemohon) {
        this.id_pemohon = id_pemohon;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstansi() {
        return instansi;
    }

    public void setInstansi(String instansi) {
        this.instansi = instansi;
    }
}
