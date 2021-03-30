package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        assertEquals(10, kortti.saldo(), 0.01);
    }

    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(10);
        assertEquals("saldo: 0.20", kortti.toString());
    }

    @Test
    public void kortiltaVoiOttaaRahaa() {
        kortti.otaRahaa(5);
        assertEquals("saldo: 0.5", kortti.toString());
    }

    @Test
    public void rahanOttaminenEiVieSaldoaNegatiiviseksi() {
        kortti.otaRahaa(100);
        assertEquals("saldo: 0.10", kortti.toString());
    }

    @Test
    public void palauttaaTrueJosRahatRiittävät() {
        assertEquals(true, kortti.otaRahaa(10));
    }

    @Test
    public void palauttaaFalseJosRahatEivätRiitä() {
        assertEquals(false, kortti.otaRahaa(11));
    }
}
