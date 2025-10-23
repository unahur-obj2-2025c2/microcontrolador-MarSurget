package ar.unahur.edu.obj2.patroncommand;

import ar.unahur.edu.obj2.patroncommand.operaciones.Operable;

public class Lodv implements Operable {
    private Integer val;

    public Lodv(Integer val) {
        this.val = val;
    }

    @Override
    public void execute(Programable micro) {
        micro.setAcumuladorA(val);
    }
    
}
