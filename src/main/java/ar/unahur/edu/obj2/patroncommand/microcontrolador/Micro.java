package ar.unahur.edu.obj2.patroncommand.microcontrolador;

import java.util.List;

import ar.unahur.edu.obj2.patroncommand.excepciones.FueraDeRangoDeMemoriaException;
import ar.unahur.edu.obj2.patroncommand.operaciones.Operable;



public class Micro implements Programable { // simulador 
    private Integer a; // acumulador A
    private Integer b; // acumulador B
    private Integer pc; // indica la dirección en la memoria de programa de la próxima instrucción a ejecutar. Se incrementa cada vez que el microcontrolador ejecuta una instrucción.
    private Integer[] memoria; // Un área de 1024 valores enteros correspondiente a la memoria de datos. -- direcciones --> addr

   // OTRA FORMA DE HACERLO private List<Integer> memoria = Arrays.asList(new Integer[1024]); //--> creo la lista con un valor fijo 1024

    public Micro() {  // el micro se inicialice vacío
        this.a = 0;
        this.b = 0;
        this.pc = 0;
        this.memoria = new Integer[1024];
    }
    
    @Override
    public Integer getAcumuladorA() { return a; }

    @Override
    public Integer getAcumuladorB() { return b; }

    @Override
    public Integer getAddr(Integer addr) {
         if (addr < 0 || addr >= memoria.length) {
            throw new FueraDeRangoDeMemoriaException();
         }
        return memoria[addr];
        //  return memoria.get(addr); 
    }

    @Override
    public Integer getProgramCounter() { return pc; }

    @Override
    public void incProgramCounter() { pc++; }

    @Override
    public void reset() {
        pc = 0;
        a = 0;
        b = 0;

        // inicializa el contador -> int i = 0
        // es la condición para que siga el bucle --> i < memoria.length
        // incrementa i en 1 cada vez. --> i++
        for (int i = 0; i < memoria.length; i++) {
            memoria[i] = 0;
        }

    // OTRA FORMA DE HACERLO memoria = Arrays.asList(new Integer([1024]));
    }

    @Override
    public void run(List<Operable> operaciones) {
        operaciones.forEach(o -> o.execute(this));
    }

    @Override
    public void setAcumuladorA(Integer value) {
        a = value;        
    }

    @Override
    public void setAcumuladorB(Integer value) {
        b = value;        
    }

    @Override
    public void setAddr(Integer addr) {  // STR addr | Guarda el valor del acumulador A en la posición addr de la memoria de datos.
        if (addr < 0 || addr >= memoria.length) {
            throw new FueraDeRangoDeMemoriaException();
        }
        memoria[addr]= a; // lo armo asi por la utilidad en la operacion STR

    // solucion de la clase con una lista 
        // if (addr < 0 || addr >= memoria.size()) {
        // // armar excepcion 
        // throw new IllegalArgumentException("Direccion fuera de rango");
        // }
        // memoria.set(addr, a); 
    }

    @Override
    public Programable copiar() {
        Micro microNuevo  = new Micro(); // Programable microNuevo  = new Micro();  --> ESTO NO FUNCIONA PORQUE NECESITO USAR LOS ATRIBUTOS DE MICRO
         /// aca copiamos todo antes de ejecutar 
        microNuevo.a = this.a; 
        microNuevo.b = this.b; 
        microNuevo.pc= this.pc;  
        return microNuevo;
    }

    @Override
    public void copiarDesde(Programable microDeRespaldo) {
        this.a = microDeRespaldo.getAcumuladorA();
        this.b = microDeRespaldo.getAcumuladorB();
        this.pc = microDeRespaldo.getProgramCounter();
    }
}
