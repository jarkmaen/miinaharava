package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {

    Maksukortti kortti;
    Kassapaate kassa;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
        kassa = new Kassapaate();
    }

    @Test
    public void luotuKassaOlemassa() {
        assertTrue(kassa != null);
    }

    @Test
    public void onkoKassanAloitusRahamaaraOikea() {
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void onkoMyytyjaLounaitaAlussaNolla() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty() + kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void edullinenLounasKateisellaOikeaSaldoKassassa() {
        kassa.syoEdullisesti(240);
        assertEquals(100240, kassa.kassassaRahaa());
    }

    @Test
    public void maukasLounasKateisellaOikeaSaldoKassassa() {
        kassa.syoMaukkaasti(400);
        assertEquals(100400, kassa.kassassaRahaa());
    }

    @Test
    public void myytyjenEdullistenLounaidenMaaraKasvaaYhdella() {
        kassa.syoEdullisesti(240);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void myytyjenMaukkaidenLounaidenMaaraKasvaaYhdella() {
        kassa.syoMaukkaasti(400);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiMuttaKateinenEiRiita() {
        kassa.syoEdullisesti(230);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void syoMaukkaastiMuttaKateinenEiRiita() {
        kassa.syoMaukkaasti(390);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void edullistenLounaidenMaaraPysyySamanaJosKateinenEiRiita() {
        kassa.syoEdullisesti(230);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void maukkaidenLounaidenMaaraPysyySamanaJosKateinenEiRiita() {
        kassa.syoMaukkaasti(390);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void edullinenLounasKortillaOikeaSaldoKassassa() {
        assertEquals(true, kassa.syoEdullisesti(kortti));
    }

    @Test
    public void maukasLounasKortillaOikeaSaldoKassassa() {
        assertEquals(true, kassa.syoMaukkaasti(kortti));
    }

    @Test
    public void myytyjenEdullistenLounaidenMaaraKasvaaYhdellaKortilla() {
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void myytyjenMaukkaidenLounaidenMaaraKasvaaYhdellaKortilla() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiMuttaKortillaEiOleTarpeeksiRahaa() {
        Maksukortti testikortti = new Maksukortti(150);
        kassa.syoEdullisesti(testikortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void syoMaukkaastiMuttaKortillaEiOleTarpeeksiRahaa() {
        Maksukortti testikortti = new Maksukortti(150);
        kassa.syoMaukkaasti(testikortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void edullistenLounaidenMaaraPysyySamanaKunKortillaEiVoiMaksaa() {
        Maksukortti testikortti = new Maksukortti(150);
        kassa.syoEdullisesti(testikortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void maukkaidenLounaidenMaaraPysyySamanaKunKortillaEiVoiMaksaa() {
        Maksukortti testikortti = new Maksukortti(150);
        kassa.syoMaukkaasti(testikortti);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void palautaFalseJosRahatEivatRiitaKortillaEdulliseen() {
        Maksukortti testikortti = new Maksukortti(150);
        assertEquals(false, kassa.syoEdullisesti(testikortti));
    }

    @Test
    public void palautaFalseJosRahatEivatRiitaKortillaMaukkaaseen() {
        Maksukortti testikortti = new Maksukortti(150);
        assertEquals(false, kassa.syoMaukkaasti(testikortti));
    }

    @Test
    public void kassanRahamaaraEiMuutuKunOstaaKortilla() {
        kassa.syoEdullisesti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void lisaaRahaaKassaan() {
        kassa.lataaRahaaKortille(kortti, 500);
        assertEquals(100500, kassa.kassassaRahaa());
    }

    @Test
    public void kassaanEiVoiLisataNegatiivistaSummaa() {
        kassa.lataaRahaaKortille(kortti, -500);
        assertEquals(100000, kassa.kassassaRahaa());
    }
}
