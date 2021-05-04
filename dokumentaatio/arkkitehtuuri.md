# Arkkitehtuurikuvaus

## Rakenne

Koodin pakkausrakenne:

<img src="https://raw.githubusercontent.com/jarkmaen/ot-harjoitustyo/master/dokumentaatio/kuvat/pakkausrakenne.png">

Pakkaus _miinaharava.ui_ sisältää käyttöliittymään liittyvät luokat, _miinaharava.logic_ sisältää sovelluslogiikan ja _miinaharava.dao_ sisältää tiedon tallentamisesta vastaavan koodin.

## Päätoiminnallisuudet

#### Pelin aloittaminen

Käyttäjä aloittaa pelin valitsemalla joko helppo, kohtalainen tai vaikea vaikeustason. Kun vaikeustaso on valittu, käyttöliittymä luo pelinäkymän getBoardWindow() metodilla. Pelialustan logiikka luodaan valitun vaikeustason perusteella. Uusi pelinäkymä näytetään käyttäjälle stage.setScene(boardScene) metodilla.

<img src="https://raw.githubusercontent.com/jarkmaen/ot-harjoitustyo/master/dokumentaatio/kuvat/sekvenssikaavio.png">

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
