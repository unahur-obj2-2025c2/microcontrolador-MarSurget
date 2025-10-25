package ar.unahur.edu.obj2.patroncommand.microcontrolador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.unahur.edu.obj2.patroncommand.excepciones.FueraDeRangoDeMemoriaException;

public class MicroTest {
    private Micro microcontrolador  = new Micro();

    // @BeforeAll
    // void setUp(){
    //     microcontrolador.setAcumuladorA(10);
    //     microcontrolador.setAcumuladorB(20);
    //     microcontrolador.setAddr(100);
    // }


    @BeforeEach
    void setUpEach(){
        microcontrolador.reset();
    }


    @Test
    void testGetAcumuladorA() {
        microcontrolador.setAcumuladorA(10);
        assertEquals(10, microcontrolador.getAcumuladorA());
    }

    @Test
    void testGetAcumuladorB() {
        microcontrolador.setAcumuladorB(20);
        assertEquals(20, microcontrolador.getAcumuladorB());
    }

    @Test
    void testGetAddr() {
        microcontrolador.setAcumuladorA(10);
        microcontrolador.setAddr(100);
        assertEquals(10, microcontrolador.getAddr(100));
    }

    @Test
    void testReset() {
        microcontrolador.setAcumuladorA(10);
        microcontrolador.setAcumuladorB(20);
        microcontrolador.setAddr(100);
        assertEquals(10, microcontrolador.getAcumuladorA());
        assertEquals(20, microcontrolador.getAcumuladorB());
        assertEquals(10, microcontrolador.getAddr(100));
        microcontrolador.reset();
        assertEquals(0, microcontrolador.getAcumuladorA());
        assertEquals(0, microcontrolador.getAcumuladorB());
        assertEquals(0, microcontrolador.getAddr(100));
    }
    
    @Test
    void testSetAddrFueraDeRango() {
        FueraDeRangoDeMemoriaException ex = assertThrows(FueraDeRangoDeMemoriaException.class, () -> {
            microcontrolador.setAcumuladorA(10);
            microcontrolador.setAddr(1050);
        });
        assertEquals("La dirección de memoria ingresada esta fuera del rango", ex.getMessage());
    }
    @Test
    void testSetAddrMenorACero() {
        FueraDeRangoDeMemoriaException ex = assertThrows(FueraDeRangoDeMemoriaException.class, () -> {
            microcontrolador.setAcumuladorA(10);
            microcontrolador.setAddr(-10);
        });
        assertEquals("La dirección de memoria ingresada esta fuera del rango", ex.getMessage());
    }

    @Test
    void testGetAddrFueraDeRango() {
        FueraDeRangoDeMemoriaException ex = assertThrows(FueraDeRangoDeMemoriaException.class, () -> {
            microcontrolador.getAddr(1025);
        });
        assertEquals("La dirección de memoria ingresada esta fuera del rango", ex.getMessage());
    }

    @Test
    void testGetAddrFueraMenorACero() {
        FueraDeRangoDeMemoriaException ex = assertThrows(FueraDeRangoDeMemoriaException.class, () -> {
            microcontrolador.getAddr(-10);
        });
        assertEquals("La dirección de memoria ingresada esta fuera del rango", ex.getMessage());
    }

}
