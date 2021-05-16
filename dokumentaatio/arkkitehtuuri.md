# Arkkitehtuurikuvaus

## Rakenne

Koodin pakkausrakenne:

<img src="https://raw.githubusercontent.com/jarkmaen/ot-harjoitustyo/master/dokumentaatio/kuvat/pakkausrakenne.png">

Pakkaus _miinaharava.ui_ sisältää käyttöliittymään liittyvät luokat, _miinaharava.logic_ sisältää sovelluslogiikan ja _miinaharava.dao_ sisältää tiedon tallentamisesta vastaavan koodin.

## Käyttölittyymä

Peli sisältää neljä eri näkymää
- Päävalikko
- Pelilauta
- Voitit ruutu
- Hävisit ruutu

Jokainen näistä on toteutettu Scene oliona, jotka voidaan sijoittaa sovelluksen Stageen. Käyttöliittymä on rakennettu luokassa _miinaharava.ui.MinesweeperUI_.

## Päätoiminnallisuudet

#### Pelin aloittaminen

Käyttäjä aloittaa pelin valitsemalla joko helppo, kohtalainen tai vaikea vaikeustason. Kun vaikeustaso on valittu, käyttöliittymä luo pelinäkymän getBoardWindow() metodilla. Pelialustan logiikka luodaan valitun vaikeustason perusteella. Uusi pelinäkymä näytetään käyttäjälle stage.setScene(boardScene) metodilla.

<img src="https://raw.githubusercontent.com/jarkmaen/ot-harjoitustyo/master/dokumentaatio/kuvat/sekvenssikaavio1.png">

#### Ajan tallentaminen

Pelin päätyttyä voittoon pelaajalla on mahdollisuus tallentaa aikansa. Voitto ruudussa on teksikenttä mihin syötetään oma nimi ja nappi mikä tallentaa tiedot ylös. Nappia painettua käyttöliittymä kutsuu sovelluslogiikan metodia createTime joka saa paremetrinä vaikeustason, pelaajan nimen, kuinka monta minuuttia kului ja kuinka monta sekunttia kului. TimeService luo uuden Time-olion ja tallentaa sen kutsumalla TimeDAO:n metodia create. Päävalikko päivittää uudet ajat listoille kun pelaaja palaa siihen.

<img src="https://raw.githubusercontent.com/jarkmaen/ot-harjoitustyo/master/dokumentaatio/kuvat/sekvenssikaavio2.png">

## Tietojen pysyväistallennus

Pakkauksen _miinaharava.dao_ luokka _FileTimeDAO_ huolehtii tietojen tallentamisesta tiedostoon. Luokka noudattaa DAO-suunnittelumallia.

#### Tiedosto

Sovellus tallentaa selvitysajat tekstitiedostoon.

Sovelluksen juuressa sijaitseva konfiguraatiotiedosto _config.properties_ määrittelee tiedoston nimen.

Sovellus tallentaa tiedot tässä formaatissa:

```
EASY,Jarkko,0,48
```

Ensin valittu vaikeustaso, syötetty nimi, kuinka monta minuuttia kului ja kuinka monta sekunttia kului.
