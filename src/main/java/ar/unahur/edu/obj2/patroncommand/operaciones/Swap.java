package ar.unahur.edu.obj2.patroncommand.operaciones;

import ar.unahur.edu.obj2.patroncommand.Programable;

public class Swap implements Operable{

    @Override
    public void execute(Programable micro) {
        Integer valorA = micro.getAcumuladorA();
        Integer valorB = micro.getAcumuladorB();
        micro.setAcumuladorA(valorB);
        micro.setAcumuladorB(valorA);
    }

}
