package com.example.time4class;

public class Jadwal {
    String Matkul, ruangan, dosen, waktumulai, waktuselesai, hari, email;

    public Jadwal() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatkul() {
        return Matkul;
    }

    public void setMatkul(String matkul) {
        Matkul = matkul;
    }

    public String getRuangan() {
        return ruangan;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }

    public String getDosen() {
        return dosen;
    }

    public void setDosen(String dosen) {
        this.dosen = dosen;
    }

    public String getWaktumulai() {
        return waktumulai;
    }

    public void setWaktumulai(String waktumulai) {
        this.waktumulai = waktumulai;
    }

    public String getWaktuselesai() {
        return waktuselesai;
    }

    public void setWaktuselesai(String waktuselesai) {
        this.waktuselesai = waktuselesai;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }
}
