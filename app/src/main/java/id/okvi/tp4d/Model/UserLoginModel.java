package id.okvi.tp4d.Model;

import java.io.Serializable;

public class UserLoginModel implements Serializable {
    private String jenis, email;

    public UserLoginModel(String jenis, String email) {
        this.jenis = jenis;
        this.email = email;
    }

    public UserLoginModel() {

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
}
