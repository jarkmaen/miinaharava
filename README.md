## Miinaharava

Helsingin yliopiston Ohjelmistotekniikka-kurssin harjoitustyö. Sovellus on perinteinen miinaharava-peli, jossa tarkoituksena on etsiä kaikki pelialueelle piilotetut miinat.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/jarkmaen/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/jarkmaen/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

## Komentorivitoiminnot

### Ohjelman suorittaminen

Ohjelma ajetaan komennolla

```
mvn compile exec:java -Dexec.mainClass=miinaharava.Main
```

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Raportin tulos löytää _target/site/jacoco_ -hakemistosta nimellä _index.html_
