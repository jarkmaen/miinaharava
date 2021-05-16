# Testausdokumentti

Ohjelmaa on testattu JUnit testeillä sekä manuaalisesti järjestemätasolla sovelluksen kehityksen aikana.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Paketin _miinaharava.logic_ luokkia testaa luokan _BoardTest.java_ yksikkötestit. Integraatiotesti _TimeServiceTest.java_ testaa _TimeService:n_ toimintoja.

### DAO-luokat

DAO-luokkaa on testattu JUnitin TemporaryFolder-ruleja hyödyntäen.

### Testauskattavuus

Sovelluksen testaus rivikattavuus on 89% ja haarautumakattavuus 95%.

<img src="https://raw.githubusercontent.com/jarkmaen/ot-harjoitustyo/master/dokumentaatio/kuvat/testikattavuus.PNG">
