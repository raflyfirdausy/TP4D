package id.okvi.tp4d.Model;

import java.io.Serializable;

public class DaftarPemohonModel implements Serializable {
    private String
            id_daftar_pemohon,
            id_pemohon,
            no_regis,
            instansi_pemohon,
            alamat_instansi,
            nomer_surat,
            tanggalSurat,
            tanggalMasuk,
            jenis_kegiatan,
            pagu_anggaran,
            instansi,
            tahun_anggaran,
            pelaksanaan_dengan_cara,
            metode_pembayaran,
            lokasi_kegiatan,
            konsultan_perencanaan,
            awal_pekerjaan,
            akhir_pekerjaan,
            rencana_uitzet,
            rencana_pcm,
            rencana_mc,
            rencana_pho,
            perencanaan,
            persiapan,
            pelaksanaan,
            penyerahan_hasil,
            nama_pegawai_pemohon,
            nomor_hp,
            email,
            foto_dokumen;

    public String getId_daftar_pemohon() {
        return id_daftar_pemohon;
    }

    public void setId_daftar_pemohon(String id_daftar_pemohon) {
        this.id_daftar_pemohon = id_daftar_pemohon;
    }

    public String getId_pemohon() {
        return id_pemohon;
    }

    public void setId_pemohon(String id_pemohon) {
        this.id_pemohon = id_pemohon;
    }

    public String getNo_regis() {
        return no_regis;
    }

    public void setNo_regis(String no_regis) {
        this.no_regis = no_regis;
    }

    public String getInstansi_pemohon() {
        return instansi_pemohon;
    }

    public void setInstansi_pemohon(String instansi_pemohon) {
        this.instansi_pemohon = instansi_pemohon;
    }

    public String getAlamat_instansi() {
        return alamat_instansi;
    }

    public void setAlamat_instansi(String alamat_instansi) {
        this.alamat_instansi = alamat_instansi;
    }

    public String getNomer_surat() {
        return nomer_surat;
    }

    public void setNomer_surat(String nomer_surat) {
        this.nomer_surat = nomer_surat;
    }

    public String getTanggalSurat() {
        return tanggalSurat;
    }

    public void setTanggalSurat(String tanggalSurat) {
        this.tanggalSurat = tanggalSurat;
    }

    public String getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(String tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public String getJenis_kegiatan() {
        return jenis_kegiatan;
    }

    public void setJenis_kegiatan(String jenis_kegiatan) {
        this.jenis_kegiatan = jenis_kegiatan;
    }

    public String getPagu_anggaran() {
        return pagu_anggaran;
    }

    public void setPagu_anggaran(String pagu_anggaran) {
        this.pagu_anggaran = pagu_anggaran;
    }

    public String getInstansi() {
        return instansi;
    }

    public void setInstansi(String instansi) {
        this.instansi = instansi;
    }

    public String getTahun_anggaran() {
        return tahun_anggaran;
    }

    public void setTahun_anggaran(String tahun_anggaran) {
        this.tahun_anggaran = tahun_anggaran;
    }

    public String getPelaksanaan_dengan_cara() {
        return pelaksanaan_dengan_cara;
    }

    public void setPelaksanaan_dengan_cara(String pelaksanaan_dengan_cara) {
        this.pelaksanaan_dengan_cara = pelaksanaan_dengan_cara;
    }

    public String getMetode_pembayaran() {
        return metode_pembayaran;
    }

    public void setMetode_pembayaran(String metode_pembayaran) {
        this.metode_pembayaran = metode_pembayaran;
    }

    public String getLokasi_kegiatan() {
        return lokasi_kegiatan;
    }

    public void setLokasi_kegiatan(String lokasi_kegiatan) {
        this.lokasi_kegiatan = lokasi_kegiatan;
    }

    public String getKonsultan_perencanaan() {
        return konsultan_perencanaan;
    }

    public void setKonsultan_perencanaan(String konsultan_perencanaan) {
        this.konsultan_perencanaan = konsultan_perencanaan;
    }

    public String getAwal_pekerjaan() {
        return awal_pekerjaan;
    }

    public void setAwal_pekerjaan(String awal_pekerjaan) {
        this.awal_pekerjaan = awal_pekerjaan;
    }

    public String getAkhir_pekerjaan() {
        return akhir_pekerjaan;
    }

    public void setAkhir_pekerjaan(String akhir_pekerjaan) {
        this.akhir_pekerjaan = akhir_pekerjaan;
    }

    public String getRencana_uitzet() {
        return rencana_uitzet;
    }

    public void setRencana_uitzet(String rencana_uitzet) {
        this.rencana_uitzet = rencana_uitzet;
    }

    public String getRencana_pcm() {
        return rencana_pcm;
    }

    public void setRencana_pcm(String rencana_pcm) {
        this.rencana_pcm = rencana_pcm;
    }

    public String getRencana_mc() {
        return rencana_mc;
    }

    public void setRencana_mc(String rencana_mc) {
        this.rencana_mc = rencana_mc;
    }

    public String getRencana_pho() {
        return rencana_pho;
    }

    public void setRencana_pho(String rencana_pho) {
        this.rencana_pho = rencana_pho;
    }

    public String getPerencanaan() {
        return perencanaan;
    }

    public void setPerencanaan(String perencanaan) {
        this.perencanaan = perencanaan;
    }

    public String getPersiapan() {
        return persiapan;
    }

    public void setPersiapan(String persiapan) {
        this.persiapan = persiapan;
    }

    public String getPelaksanaan() {
        return pelaksanaan;
    }

    public void setPelaksanaan(String pelaksanaan) {
        this.pelaksanaan = pelaksanaan;
    }

    public String getPenyerahan_hasil() {
        return penyerahan_hasil;
    }

    public void setPenyerahan_hasil(String penyerahan_hasil) {
        this.penyerahan_hasil = penyerahan_hasil;
    }

    public String getNama_pegawai_pemohon() {
        return nama_pegawai_pemohon;
    }

    public void setNama_pegawai_pemohon(String nama_pegawai_pemohon) {
        this.nama_pegawai_pemohon = nama_pegawai_pemohon;
    }

    public String getNomor_hp() {
        return nomor_hp;
    }

    public void setNomor_hp(String nomor_hp) {
        this.nomor_hp = nomor_hp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto_dokumen() {
        return foto_dokumen;
    }

    public void setFoto_dokumen(String foto_dokumen) {
        this.foto_dokumen = foto_dokumen;
    }
}
