## UX Testing:

- <u>Előny:</u>
  - A program használatához kapunk segítséget hogyan kell játszani.
  - A játék beállításai egyértelműek.
  - Könnyű elindítani a játékot.
  - Kapunk figyelmeztetést az eseményekről.
  - Jól látni, hogy a kijelölt jégtáblán éppen mi történik.
  - A karakterek adatai meg vannak jelenítve részletesen.
  - Jól meglehet különböztetni a karaktertípusokat.
  - Végig tudjuk böngészni az összes jégtáblát a további információkért.
- <u>Hátrány:</u>
  - A medve beleolvad a háttérbe, ezért nehéz észrevenni.
  - Figyelmeztetés után nem látszik, hogy a felhasználó hol esett a vízbe.
  - Miután vesztettél nem ad lehetőséget, hogy újraindítsd a játékot.
  - A hóréteg eltávolítása ásó nélkül nem egyértelmű.
  - A fehér táblán nem látszik jól az iglu.

## Stress testing:

- <u>Maximális beállításon:</u>

<img src="https://cdn.discordapp.com/attachments/837219059228475392/842335958477438976/unknown.png" alt="img" style="zoom:80%;" />

<img src="https://cdn.discordapp.com/attachments/837219059228475392/842336023248764948/unknown.png" alt="img" style="zoom: 33%;" />

Maximális beállítások mellett is rendesen működik az alkalmazás, nem észleltünk semmilyen hibát.

- Minimális beállításon:

<img src="https://cdn.discordapp.com/attachments/837219059228475392/842336357522079774/unknown.png" alt="img" style="zoom: 80%;" />

<img src="https://cdn.discordapp.com/attachments/837219059228475392/842336400078274570/unknown.png" alt="img" style="zoom: 50%;" />

A program továbbra is rendesen működik, eltérést nem tapasztaltunk.

- Legkisebb pálya, legtöbb medve beállítás:

![img](https://cdn.discordapp.com/attachments/837219059228475392/842336677091475456/unknown.png)

<img src="https://cdn.discordapp.com/attachments/837219059228475392/842336732431253524/unknown.png" alt="img" style="zoom:50%;" />

A kis pályának és a sok medvének köszönhetően a játék játszhatatlanná válik. A medvék egymáson vannak és nem látszik, hogy egy jégtáblán éppen hány medve van.

## Recovery testing:

Az alkalmazás hiba esetén nem tud visszatölteni, viszont van lehetőségünk egy korábban elmentett verzió visszatöltésére.

A program logolást végez, emiatt ha szeretnék, akkor azokból a lépésekből újrajátszható.

## Maintainability Testing:

Új karakterekkel bővíthető:

```
public abstract class Karakter implements Serializable, JatekVezerlo
```

- Például: sarkkutató, eszkimó

Új NPC-vel bővíthető:

```
public abstract class Npc implements Serializable, JatekVezerlo
```

- Például: medve

Új tárggyal bővíthető:

```
public abstract class Dolog implements Serializable, JatekVezerlo 
```

- Például étel, lapát ..

Új építménnyel bővíthető:

```
public interface Epitmeny extends Serializable, JatekVezerlo 
```

- Például: iglu, sátor

Köszönhetően annak, hogy absztrakciókat és interfészeket használ könnyen bővíthető a program.

