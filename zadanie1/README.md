# Konečnostavový akceptor

> ### Formálne jazyky
>
> ### Zadanie 1: Regulárny výraz
>
>Úlohou je implementovať konečno-stavový akceptor konkrétneho regulárneho výrazu (bez použitia tzv. regex knižníc),
    ktorý ste dostali pridelený od svojho cvičiaceho. Program načíta zo štandardného vstupu reťazec ukončený znakom nového riadku. 
    Výstupom má byť počet a pozície výskytov podreťazca zodpovedajúceho zadanému regulárnemu výrazu.Výstup môže obsahovať aj
    doplnkové informácie, jeho formát nie je striktne daný. Ak žiadna časť vstupu nezodpovedá danému regulárnemu výrazu,
    program o tom taktiež poskytne informáciu.
>
> Použite službu na vytvorenie projektu v GitLabe. Do repozitára pomocou Gitu nahrajte svoj kód najneskôr do začiatku vášho 5. cvičenia.
>
> Obsah repozitára má mať nasledujúcu štruktúru:
>- **zadanie1/**
>  - **README.txt**
>  - **Makefile**
>  - ***.c**
>  - ***.h**
>
>Nezabudnite predovšetkým na vytvorenie priečinku "zadanie1", keďže ďalšie zadania budú v tom istom repozitári. Súbor README.txt nemá predpísaný formát, ale musí obsahovať konkrétny regulárny výraz, ktorý máte pridelený. Vďaka súboru Makefile by sa mal dať projekt skompilovať príkazom make. Názvy C a H súborov nie sú špecifikované, hlavičkové súbory sú nepovinné.
>
>K zadaniu vytvorte aj jednotkové testy, napr. pomocou knižnice greatest alebo inej. Očakáva sa spolu rádovo 10 volaní funkcií "assert". Viac ako o počet však ide o presnosť - snažte sa otestovať hlavne hraničné situácie, teda napr. neakceptované reťazce, ktoré sa od akceptovaných líšia len jedným znakom (alebo naopak), reťazce s 0 a 1 opakovaním znakov v tranzitívnom uzávere a pod. Pripravte si prostredie tak, aby ste na požiadanie cvičiaceho spustili testy, napr. príkazom make test alebo pomocou IDE. Zadanie je možné odovzdať aj bez testov, no za znížený počet bodov.
>
>Odporúčame tiež kvôli prípadnej kontrole mať pripravené diagramy (prechodový diagram, DKA), ktoré ste použili pri tvorbe.
>
>Celkové hodnotenie projektu: max. 3 body.
>
><sub>  © Copyright 2023 KPI FEI  </sub>	


znenie môjho regexu do zadania : `[c]a|b({c}|b[b])ab`

**UPOZORNENIE!** zadanie je v neštandardnom formáte ktorý sa využíva na škole

štandardný formát : `c?a|b(c*|bb?)ab`

### tabuľka školského zjednodušeného formátu:
```
Regulárny výraz Zjednodušený zápis (používaný na tomto predmete) / Zápis bežný v implementáciách
zoskupenie (postupnosť)	                    (xy)	                    (xy)
alternatíva (x alebo y)	                    x|y	                            x|y
tranzitívny uzáver (0 alebo viac krát)	    {x}	                            (x)*
opakovanie (1 alebo viac krát)	            x{x}	                    (x)+
voliteľnosť (0 alebo 1 krát)	            [x]	                            (x)?
```
