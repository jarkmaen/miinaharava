# Arkkitehtuurikuvaus

## Rakenne

Koodin pakkausrakenne tällä hetkellä:

<img src="https://github.com/jarkmaen/ot-harjoitustyo/tree/master/dokumentaatio/pakkausrakenne.png">

Pakkaus _miinaharava.ui_ sisältää käyttöliittymään liittyvät luokat ja _miinaharava.logic_ sisältää sovelluslogiikan.

## Päätoiminnallisuudet

#### Pelin aloittaminen

Käyttäjä aloittaa pelin valitsemalla joko helppo, kohtalainen tai vaikea vaikeustason. Kun vaikeustaso on valittu, käyttöliittymä luo pelinäkymän getBoardWindow() metodilla. Pelialustan logiikka luodaan valitun vaikeustason perusteella. Uusi pelinäkymä näytetään käyttäjälle stage.setScene(boardScene) metodilla.

<img src="https://github.com/jarkmaen/ot-harjoitustyo/tree/master/dokumentaatio/sekvenssikaavio.png">