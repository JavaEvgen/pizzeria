package pizzicato_ibizza.model;

public class Tayte {
    private int tayte_id;
    private String tayte_nimi;
    private double hinta;
    private boolean status;
   
   
   
   
    public Tayte() {
            super();
            this.tayte_id = 0;
            this.tayte_nimi = "";
            this.hinta = 0;
            this.status = true;
    }



    public Tayte(int tayte_id, String tayte_nimi, double hinta, boolean status) {
            super();
            this.tayte_id = tayte_id;
            this.tayte_nimi = tayte_nimi;
            this.hinta = hinta;
            this.status = status;
    }




    public int getTayte_id() {
            return tayte_id;
    }


    public void setTayte_id(int tayte_id) {
            this.tayte_id = tayte_id;
    }


    public String getTayte_nimi() {
            return tayte_nimi;
    }


    public void setTayte_nimi(String tayte_nimi) {
            this.tayte_nimi = tayte_nimi;
    }

   
   

    public double getHinta() {
            return hinta;
    }



    public void setHinta(double hinta) {
            this.hinta = hinta;
    }



    public boolean getStatus() {
            return status;
    }


    public void setStatus(boolean status) {
            this.status = status;
    }



    @Override
    public String toString() {
            return "Tayte [tayte_id=" + tayte_id + ", tayte_nimi=" + tayte_nimi
                            + ", hinta=" + hinta + ", status=" + status + "]";
    }

   
   
}
