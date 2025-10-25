package ar.unahur.edu.obj2.patroncommand.invoker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.Micro;
import ar.unahur.edu.obj2.patroncommand.microcontrolador.Programable;
import ar.unahur.edu.obj2.patroncommand.operaciones.Add;
import ar.unahur.edu.obj2.patroncommand.operaciones.Ifnz;
import ar.unahur.edu.obj2.patroncommand.operaciones.Lod;
import ar.unahur.edu.obj2.patroncommand.operaciones.Lodv;
import ar.unahur.edu.obj2.patroncommand.operaciones.Nop;
import ar.unahur.edu.obj2.patroncommand.operaciones.Str;
import ar.unahur.edu.obj2.patroncommand.operaciones.Swap;
import ar.unahur.edu.obj2.patroncommand.operaciones.Whnz;

public class ProgramaTest {
    private Programa prog = new Programa(null);
    private Programable micro = new Micro();

    @BeforeEach
    void setUp(){
        prog.vaciarLista();
        prog.resetar(micro);
    }

    @Test
    void avanzar3PosicionesElProgramCounter(){
        prog.agregarOp(new Nop());
        prog.agregarOp(new Nop());
        prog.agregarOp(new Nop());
        prog.ejecutar(micro);
        assertEquals(3, micro.getProgramCounter());
    }

    @Test
    void sumar20Mas17_YObtenerEnAcumuladorA37(){
        prog.agregarOp(new Lodv(20)); // carga en ac A mientras B es 0
        prog.agregarOp(new Swap()); // invierte los valores, a queda en 0 y b en 20
        prog.agregarOp(new Lodv(17)); // carga en ac A mientras B es 20
        prog.agregarOp(new Add()); // suma los valores y guarda el resultado en A y b queda en 0         
        prog.ejecutar(micro);

        assertEquals(37, micro.getAcumuladorA());
        assertEquals(0, micro.getAcumuladorB());

        assertEquals(4, micro.getProgramCounter());
    }

    @Test
    void sumar2Mas8Mas5_YObtenerEnAcumuladorA15_YEnAcumuladorB0(){
        prog.agregarOp(new Lodv(2)); // carga 2 en ac A mientras B es 0
        prog.agregarOp(new Str(0));  // guarda en la pos 0 el 2
        prog.agregarOp(new Lodv(8)); // carga 8 en a 

        prog.agregarOp(new Swap());  // invierte el A y el b y quedan 0 en a y 8 en b
        prog.agregarOp(new Lodv(5)); // carga 5 ac A
        prog.agregarOp(new Add()); // suma los val y queda el rtdo en a y 0  en b 

        prog.agregarOp(new Swap()); // invierte a y b, qqquue 0 en a y el resultado de la primer suma en b 
        prog.agregarOp(new Lod(0)); // carga 2 en el valor de la pos d memoria 0, es decir el valor 2
        prog.agregarOp(new Add()); // suma 2 +  la suma de 8 + 5, guarda el resultado en a y 0 en b 
        
        prog.ejecutar(micro);

        assertEquals(15, micro.getAcumuladorA());
        assertEquals(0, micro.getAcumuladorB());

        // assertEquals(9, micro.getProgramCounter());
    }

    @Test
    void sumar20Mas17_YObtenerEnAcumuladorA37ConListaOp(){
        prog.agregarOperaciones(List.of(new Lodv(20), new Swap(), new Lodv(17),new Add()));

        prog.ejecutar(micro);

        assertEquals(37, micro.getAcumuladorA());
        assertEquals(0, micro.getAcumuladorB());
        assertEquals(4, micro.getProgramCounter());
    }

    @Test
    void deshacerSwapRestauraAcumuladores(){
        micro.setAcumuladorA(10);
        micro.setAcumuladorB(5);

        prog.agregarOp(new Swap());

        prog.ejecutar(micro);
        
        assertEquals(5, micro.getAcumuladorA());
        assertEquals(10, micro.getAcumuladorB());

        prog.operaciones.getLast().unDo(micro); //deshacer la última

        assertEquals(10, micro.getAcumuladorA());
        assertEquals(5, micro.getAcumuladorB());
    }

    @Test
    void whnzEjecuta_MientrasASeaDistintoDeCero() {
        micro.setAcumuladorA(3);
        micro.setAcumuladorB(0);

        prog.agregarOp(new Whnz(List.of(new Lod(0))));
        prog.ejecutar(micro);

        assertEquals(0, micro.getAcumuladorA());
    }

    @Test
    void ifnzEjecuta_SiAEsDistintoDeCero() {
        micro.setAcumuladorA(5);
        micro.setAcumuladorB(0);

        prog.agregarOp(new Ifnz(List.of(new Swap(),new Lodv(10))));
        prog.ejecutar(micro);

        assertEquals(10, micro.getAcumuladorA());
        assertEquals(5, micro.getAcumuladorB());
    }
    @Test
    void ifnzNoEjecutaCuandoAEsCero() {
        micro.setAcumuladorA(0);
        micro.setAcumuladorB(99);

        prog.agregarOp(new Ifnz(List.of(new Lodv(50), new Add())));
        prog.ejecutar(micro);

        // no debería haber cambiado nada
        assertEquals(0, micro.getAcumuladorA());
        assertEquals(99, micro.getAcumuladorB());
    }


}

