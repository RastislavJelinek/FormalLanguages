# Prekladač


> Zadanie 3: DSL
> 
> Úlohou je implementovať program, ktorý zo súboru zapísanom v doménovo špecifickom jazyku (DSL) na definíciu stavového automatu vygeneruje jeho implementáciu v jazyku C. Samotný generátor bude napísaný v jazyku Java (potrebná je minimálne verzia 19).
>
> ## Vstup
> 
> Súbor napísaný v DSL obsahuje:
>
> príkazy (commands) - vstupy jednotlivych znakov z klávesnice, ktoré spôsobia prechod do iného stavu,
> príkazy pre reštart (resetCommands) - podmnožina príkazov, ktoré vždy spôsobia prechod do počiatočného stavu,
> udalosti (events) - akcie, ktoré sa automaticky vykonajú pri prechode do daného stavu - konkrétne výpisy znakov na obrazovku,
> stavy (states), voliteľne obsahujúce akcie (actions) a prechody v tvare príkaz => nový_stav.
> Spomínané 4 hlavné časti nemajú pevne stanovené poradie a môžu sa opakovať. Definícia akcií v stave však musí byť pred definíciami prechodov. Počiatočný stav je ten, ktorý sa definuje ako prvý v poradí.
>
> príklad [vstupu (1)](examples/input_01.txt) 
> 
> príklad [vstupu (2)](examples/input_02.txt)
> 
> ## Výstup
> 
> Výstupom generátora má byť súbor so zdrojovým kódom programu v jazyku C. Príklady výstupov sú nasledujúce:
>
> príklad [výstupu (1)](examples/output_01.c) 
> 
> príklad [výstupu (2)](examples/output_02.c)
> 
> ## Podklady
> 
> K dispozícii máte [kostru projektu](examples/base.zip). Zahrnutá gramatika slúži na ukážku, je potrebné ju upraviť.
>
> Podmienky
> Do existujúceho repozitára zadaní nahrajte pomocou Gitu svoj kód najneskôr do začiatku vášho 12. cvičenia. Pridaný obsah má mať nasledujúcu štruktúru:
>
>- zadanie3/
>  - src/
>  - README.txt
>  - pom.xml alebo build.gradle
>
> Nezabudnite predovšetkým na vytvorenie priečinku zadanie3. Súbor README.txt má obsahovať gramatiku DSL v tvare EBNF. Projekt by mal obsahovať konfiguráciu tzv. build nástroja: buď pom.xml (pre Maven) alebo build.gradle, resp. build.gradle.kts (Gradle). Adresárová štruktúra samotných zdrojových súborov by mala zodpovedať konvenciám vybraného kompilačného nástroja.
>
> Je možné meniť kód v kostre projektu (aj výraznejšie), no kód by mal byť stále prehľadný a jednotlivé časti (lexer, parser, generáror) dobre oddelené. Syntaktický analyzátor je potrebné vytvoriť manuálne a má zodpovedať bezkontextovej gramatike dodanej k projektu. Výstupom syntaktickej analýzy má byť vhodná údajová štruktúra (napr. trieda StateMachineDefinition v kostre projektu), z ktorej sa až následne vygeneruje kód v jazyku C, teda generovanie kódu nemá prebiehať priamo počas syntaktickej analýzy.
>
> K zadaniu vytvorte aj testy, napr. pomocou knižnice JUnit alebo inej. Očakáva sa spolu rádovo 5 testov kompletného generátora ako celku, teda páry vstup v DSL - výstup v C (môžete použiť aj príklady z tejto stránky). Okrem toho je vhodné otestovať aj jednotlivé triedy (najmä Lexer a Parser). Viac ako o počet ide o presnosť - snažte sa otestovať aj hraničné (automat bez reštartovacích pravidiel, automat z jedným stavom, prechod do toho istého stavu) a chybné situácie (rovnaké znaky pre dve udalosti a pod.). Pripravte si prostredie tak, aby ste na požiadanie cvičiaceho spustili testy. Zadanie je možné odovzdať aj bez testov, no za znížený počet bodov.
>
> <sub> © Copyright 2023 KPI FEI </sub>	