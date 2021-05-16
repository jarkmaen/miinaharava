# Käyttöohje

Lataa tiedosto [Miinaharava.jar](https://github.com/jarkmaen/ot-harjoitustyo/releases/tag/viikko5)

## Konfigurointi

Ohjelman käynnistyshakemistossa on konfiguraatiotiedosto _config.properties_, joka määrittelee aikojen tallennukseen käytetyn tiedoston. Tiedoston muoto on seuraava:

```
timeFile=times.txt
```

Jos ohjelma ei löydä kyseistä tiedostoa, se generoi sen itse.

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar Miinaharava.jar
```

## Pelaaminen

Sovellus alkaa päävalikosta. Valikossa voit valita vaikeustason ja aloittaa pelin. Voit nähdä myös kaikki selvitysaikasi klikkaamalla pudotusvalikkoa. (PR = Personal Records)

<img src="https://raw.githubusercontent.com/jarkmaen/ot-harjoitustyo/master/dokumentaatio/kuvat/paavalikko.PNG">

Peli näkymässä voit alkaa raivaamaan ruutuja vasemmalla klikkauksella. Oikea klikkaus pudottaa lipun ruudun päälle.

<img src="https://raw.githubusercontent.com/jarkmaen/ot-harjoitustyo/master/dokumentaatio/kuvat/pelilauta.PNG">

Jos löydät kaikki turvalliset ruudut, niin peli päättyy ja siirryt voittonäkymään. Voit syöttää nimesi ja tallentaa aikasi listoille, tai palata takaisin päävalikkoon ilman tallentamista.

<img src="https://raw.githubusercontent.com/jarkmaen/ot-harjoitustyo/master/dokumentaatio/kuvat/voitit.PNG">

