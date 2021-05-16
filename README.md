## Miinaharava

Helsingin yliopiston Ohjelmistotekniikka-kurssin harjoitustyö. Sovellus on perinteinen miinaharava-peli, jossa tarkoituksena on etsiä kaikki pelialueelle piilotetut miinat.

## Dokumentaatio

[Käyttöohje](https://github.com/jarkmaen/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/jarkmaen/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/jarkmaen/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/jarkmaen/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

## Releaset

[Viikko 5](https://github.com/jarkmaen/ot-harjoitustyo/releases/tag/viikko5)

[Viikko 6](https://github.com/jarkmaen/ot-harjoitustyo/releases/tag/viikko6)

## Komentorivitoiminnot

### Ohjelman suorittaminen

Ohjelma ajetaan komennolla

```
mvn compile exec:java -Dexec.mainClass=miinaharava.Main
```

### Suoritettavan jarin generointi

Komento

```
mvn package
```

luo hakemistoon _target_ suoritettavan jar-tiedoston _Miinaharava-1.0-SNAPSHOT.jar_

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Raportin tulos löytyy _target/site/jacoco/_ -hakemistosta nimellä _index.html_

### JavaDoc

JavaDoc luodaan komennolla

```
mvn javadoc:javadoc
```

Raportin tulos löytyy _target/site/apidocs/_ -hakemistosta nimellä _index.html_

### Checkstyle

Checkstyle tarkistus suoritetaan komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```

Raportin tulos löytyy _target/site/_ -hakemistosta nimellä _checkstyle.html_
