## CI build

- Github Actions segítségével létrehoztuk a mavenes CI buildet és beépítettük a SonarCloud szolgáltatást.
  - Ehhez megváltoztattuk a pom.xml fájlt:
  
    ![image-20210513154122853](image-20210513154122853.png)
  
- Ezáltal minden egyes pull-request-nél mielőtt mergeljük a master ágra lebuildeli a beküldött kódot.

  - Előnye, hogy így ellenőrizni tudjuk, hogy valóban működik és nem pusholunk olyan kódot master ágra ami eltöri a rendszert.

- A buildben lévő lépések:

  ![image-20210513154632242](image-20210513154632242.png)

  SonarCloud:

  ![image-20210513155019210](image-20210513155019210.png)

  

