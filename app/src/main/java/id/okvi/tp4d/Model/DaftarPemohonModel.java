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
            tanggal_surat,
            tanggal_masuk,
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
            uitzet_perencanaan,
            p_uitzet_perencanaan,
            pcm_persiapan,
            p_pcm_persiapan,
            mc_pelaksanaan,
            p_mc_pelaksanaan,
            pho_penyerahan_hasil,
            p_pho_penyerahan_hasil,
            nama_pegawai_pemohon,
            nomor_hp,
            email,
            foto_dokumen,
            status,
            disposisi,
            catatan_disposisi,
            hasil_telaah,
            catatan,
            nomor_sprint,
            tanggal_sprint,
            nomor_sprint_pendampingan,
            tanggal_sprint_pendampingan,
            anggota1,
            nik1,
            anggota2,
            nik2,
            anggota3,
            nik3,
            anggota4,
            nik4,
            anggota5,
            nik5,
            anggota6,
            nik6,
            share_lokasi,
            latitude,
            longitude,
            serah_terima;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNomor_sprint_pendampingan() {
        return nomor_sprint_pendampingan;
    }

    public void setNomor_sprint_pendampingan(String nomor_sprint_pendampingan) {
        this.nomor_sprint_pendampingan = nomor_sprint_pendampingan;
    }

    public String getTanggal_sprint_pendampingan() {
        return tanggal_sprint_pendampingan;
    }

    public void setTanggal_sprint_pendampingan(String tanggal_sprint_pendampingan) {
        this.tanggal_sprint_pendampingan = tanggal_sprint_pendampingan;
    }

    public String getP_uitzet_perencanaan() {
        return p_uitzet_perencanaan;
    }

    public void setP_uitzet_perencanaan(String p_uitzet_perencanaan) {
        this.p_uitzet_perencanaan = p_uitzet_perencanaan;
    }

    public String getP_pcm_persiapan() {
        return p_pcm_persiapan;
    }

    public void setP_pcm_persiapan(String p_pcm_persiapan) {
        this.p_pcm_persiapan = p_pcm_persiapan;
    }

    public String getP_mc_pelaksanaan() {
        return p_mc_pelaksanaan;
    }

    public void setP_mc_pelaksanaan(String p_mc_pelaksanaan) {
        this.p_mc_pelaksanaan = p_mc_pelaksanaan;
    }

    public String getP_pho_penyerahan_hasil() {
        return p_pho_penyerahan_hasil;
    }

    public void setP_pho_penyerahan_hasil(String p_pho_penyerahan_hasil) {
        this.p_pho_penyerahan_hasil = p_pho_penyerahan_hasil;
    }

    public String getNomor_sprint() {
        return nomor_sprint;
    }

    public void setNomor_sprint(String nomor_sprint) {
        this.nomor_sprint = nomor_sprint;
    }

    public String getTanggal_sprint() {
        return tanggal_sprint;
    }

    public void setTanggal_sprint(String tanggal_sprint) {
        this.tanggal_sprint = tanggal_sprint;
    }

    public String getAnggota1() {
        return anggota1;
    }

    public void setAnggota1(String anggota1) {
        this.anggota1 = anggota1;
    }

    public String getNik1() {
        return nik1;
    }

    public void setNik1(String nik1) {
        this.nik1 = nik1;
    }

    public String getAnggota2() {
        return anggota2;
    }

    public void setAnggota2(String anggota2) {
        this.anggota2 = anggota2;
    }

    public String getNik2() {
        return nik2;
    }

    public void setNik2(String nik2) {
        this.nik2 = nik2;
    }

    public String getAnggota3() {
        return anggota3;
    }

    public void setAnggota3(String anggota3) {
        this.anggota3 = anggota3;
    }

    public String getNik3() {
        return nik3;
    }

    public void setNik3(String nik3) {
        this.nik3 = nik3;
    }

    public String getAnggota4() {
        return anggota4;
    }

    public void setAnggota4(String anggota4) {
        this.anggota4 = anggota4;
    }

    public String getNik4() {
        return nik4;
    }

    public void setNik4(String nik4) {
        this.nik4 = nik4;
    }

    public String getAnggota5() {
        return anggota5;
    }

    public void setAnggota5(String anggota5) {
        this.anggota5 = anggota5;
    }

    public String getNik5() {
        return nik5;
    }

    public void setNik5(String nik5) {
        this.nik5 = nik5;
    }

    public String getAnggota6() {
        return anggota6;
    }

    public void setAnggota6(String anggota6) {
        this.anggota6 = anggota6;
    }

    public String getNik6() {
        return nik6;
    }

    public void setNik6(String nik6) {
        this.nik6 = nik6;
    }

    public String getShare_lokasi() {
        return share_lokasi;
    }

    public void setShare_lokasi(String share_lokasi) {
        this.share_lokasi = share_lokasi;
    }

    public String getSerah_terima() {
        return serah_terima;
    }

    public void setSerah_terima(String serah_terima) {
        this.serah_terima = serah_terima;
    }

    public String getHasil_telaah() {
        return hasil_telaah;
    }

    public void setHasil_telaah(String hasil_telaah) {
        this.hasil_telaah = hasil_telaah;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getDisposisi() {
        return disposisi;
    }

    public void setDisposisi(String disposisi) {
        this.disposisi = disposisi;
    }

    public String getCatatan_disposisi() {
        return catatan_disposisi;
    }

    public void setCatatan_disposisi(String catatan_disposisi) {
        this.catatan_disposisi = catatan_disposisi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public String getTanggal_surat() {
        return tanggal_surat;
    }

    public void setTanggal_surat(String tanggal_surat) {
        this.tanggal_surat = tanggal_surat;
    }

    public String getTanggal_masuk() {
        return tanggal_masuk;
    }

    public void setTanggal_masuk(String tanggal_masuk) {
        this.tanggal_masuk = tanggal_masuk;
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

    public String getUitzet_perencanaan() {
        return uitzet_perencanaan;
    }

    public void setUitzet_perencanaan(String uitzet_perencanaan) {
        this.uitzet_perencanaan = uitzet_perencanaan;
    }

    public String getPcm_persiapan() {
        return pcm_persiapan;
    }

    public void setPcm_persiapan(String pcm_persiapan) {
        this.pcm_persiapan = pcm_persiapan;
    }

    public String getMc_pelaksanaan() {
        return mc_pelaksanaan;
    }

    public void setMc_pelaksanaan(String mc_pelaksanaan) {
        this.mc_pelaksanaan = mc_pelaksanaan;
    }

    public String getPho_penyerahan_hasil() {
        return pho_penyerahan_hasil;
    }

    public void setPho_penyerahan_hasil(String pho_penyerahan_hasil) {
        this.pho_penyerahan_hasil = pho_penyerahan_hasil;
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
