## BDD tesztek készítése

A Cucumber egy viselkedésvezérelt fejlesztési keretrendszer (Behavior Driven Development), melynek segítségével elfogadási teszteket írhatunk.

- A keretrendszer használatához az alábbiakra van szükségünk:
    - Java SE
    - Egy build eszköz:
        - Maven (leglább 3.3.1-es verzió)
        - Gradle
    - Egy fejlesztőkörnyezet:
        - Intellij IDEA
        - Eclipse

- Következő lépésben a meglévő projektünkbe kell felvennünk a pom.xml fájl bővítésével:

    ![cucumber_pom](cucumber_pom.PNG)

    A importálás során fontos megjegyezni, hogy a verziószámoknak meg kell egyezniük.

- Ezek után elkészíthetjük tesztjeinket:
    - A tesztek során konkrét példákat/scenario-kat írunk, hogy mit várunk a folyamat futásától a végrehajtás közben és annak eredményét is meghatározva
    - Ezen scenario-k a .feature fájlban vannak definiálva
    
- Példa a scenario-ra:

    ![cucumber_example](cucumber_example.PNG)

    - A fájl első sora egy kulcsszóval kezdődik (Feature), melyet egy név követ.
    - A következő a már említett scenario, mely egy konkrét példa, amely illusztrálja, hogyan kellene a tesztnek lefutnia.
    - Az utolsó sorok tartalmazzák a Given, When, Then kulcsszavakat, ezek a scenario lépései, melyeket a Cucumber lefuttat.

- Végül, de nem utolsó sorban definiálnunk kell a fentebb leírt lépéseket: 

    ![cucumber_example_dif_1](cucumber_example_def_1.PNG)
    ![cucumber_example_dif_2](cucumber_example_def_2.PNG)

- Ezek után futtathatjuk, melynek eredményéről a képhez hasonlóan visszejelzést kell kapnunk:

    ![cucumber_result](cucumber_result.PNG)

    - Láthathjuk, hogy sikeresen lefutott és elfogadásra került

    