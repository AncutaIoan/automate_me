# Ingineria Programarii
## Team : Automate me
### Contribution : <br />
-[Ancuta Ioan](https://github.com/AncutaIoan) <br />
-[Anita Tudor-Alexandru](https://github.com/AnitaTudor) <br />
-[Olteanu Cosmin-Daniel](https://github.com/OlteanuCosmin) <br />
-[Popescu Maria-Daniela](https://github.com/PandaMaria) <br />
-[Stan Nicolae-Alexandru](https://github.com/Alex693878) <br />

## Document de analiza

### Scop
Scopul aplicatiei este de a imbunatati atmosfera casei/apartamentului, oferind proprietarului posibilitatea de a avea control complet.
### Obiective
* posibilitatea de a prelua date din mediul inconjurator(temperatura, umiditate).Atat din camera in care este pozitionat SmartWindow cat si din climatul de afara.
* posibilitatea de self-review pentru a oferi utilizatorul o experienta completa (exemplu : fereastra a pastrat o temperatura potrivita pentru locul in care este amplasata).
* proiectarea diferitelor imagine pe sticla ferestrei ( exemplu peisaje, poze artistice, etc.).
* existenta unei functii de detectare a nivelul de gaze si fum si sa actioneze corespunzator.
* setarea automata in functie de temperatura incaperii si de temperatura de afara.
* setarea pozitiei SmartWindow de catre utilizator
* autentificarea/inregistrarea utilizatorului in aplicatie 
### Grup tinta
* Ca persoana in varsta/cu dizabilitati vreau sa imi pot controla ferestrele din apartament/casa fara prea mult efort.
* Ca parinte vreau sa asigur atmosfera placuta din camera copilului meu chiar si cand nu sunt acasa.
* Ca gamer imi doresc sa am geam avand culori cat mai atractive (RGB) pentru atmosfera din camera.
* Ca angajat imi doresc sa deschid/inchid geamurile sau sa garantez o atmosfera cat mai relaxanta in camera fara sa ma ridic de la birou.
* Ca detinator de animale de companie, vreau sa ma asigur ca animalele de companie stau in conditii bune dpdv al aerului cand sunt plecat de acasa.
* Ca home-owner, vreau sa detin mai mult acces decat alti guests asupra ferestrei si sa ma asigur ca locuinta mea este in siguranta dpdv al nivelului de gaze si fum, atat de afara cat si din incapere.
### Colectarea cerintelor 
  A - login endpoint  <br />
  B - register endpoint  <br />
  C - extrage informatii despre temperatura din camera si isi modifica pozitia  <br />
  D - baza de date pentru stocarea diferitelor inputuri din mediul inconjurator(exemplu: temperatura in functie de camera,temperatura de afara,nivelul de gaze si fum)<br/>
  E - baza de date destinata stocarii setarilor curente ale ferestrei  <br />
  F - un user poate seta pozitionarea geamului  <br />
  G - extrage informatii despre climat si vreme dintr-un API public in functie de orasul in care se afla geamul  <br />
  H - endpointuri care adauga/updateaza in baza de date  informatiile extrase din API public<br />
  I - extrage informatii dintr-un api despre temperatura dorita a camerei (stocata in baza de date desemnata temperaturii interioare)  <br /> 
  J - face un self-review in functie de informatiile extrase din api(temperatura interioara mentionata) <br />
  K - un camp unde utilizatorul isi pune o imagine care poate fi proiectata  <br />
  L - realizarea unei sectiuni care monitorizeaza nivelul de gaze,fum (gasit in baza de date)  <br />
![alt text](https://github.com/AncutaIoan/automate_me/blob/main/graf.png)

  
### Cerinte functionale:
- un user poate seta pozitionarea geamului 
- un camp unde utilizatorul isi pune o imagine care poate fi proiectata
- realizarea unei sectiuni care monitorizeaza nivelul de gaze,fum (gasit in baza de date)
- extrage informatii despre temperatura din camera si isi modifica pozitia.
### Cerinte non-functionale:
- login endpoint
- register endpoint
- endpointuri care adauga/updateaza in baza de date  informatiile extrase din API public
- baza de date destinata stocarii setarilor curente ale ferestrei
- baza de date pentru stocarea diferitelor inputuri din mediul inconjurator(exemplu: temperatura in functie de camera,temperatura de afara,nivelul de gaze si fum)
- extrage informatii despre climat si vreme dintr-un API public in functie de orasul in care se afla geamul
- extrage informatii dintr-un api despre temperatura dorita a camerei (stocata in baza de date desemnata temperaturii interioare) 
- face un self-review in functie de informatiile extrase din api(temperatura interioara mentinuta)
### Cerinte care tin de securitate, modificarile ferestrei:
- login endpoint
- register endpoint
- un user poate seta pozitionarea geamului 
- extrage informatii despre temperatura din camera si isi modifica pozitia.
- realizarea unei sectiuni care monitorizeaza nivelul de gaze,fum (gasit in baza de date)
### Cerinte care tin de stocarea datelor:
- baza de date destinata stocarii setarilor curente ale ferestrei
- baza de date pentru stocarea diferitelor inputuri din mediul inconjurator(exemplu: temperatura in functie de camera,temperatura de afara,nivelul de gaze si fum)
- endpointuri care adauga/updateaza in baza de date  informatiile extrase din API public
- un camp unde utilizatorul isi poate pune o imagine care o sa fie proiectata

### Cerinte care tin de procesare:
- extrage informatii despre climat si vreme dintr-un API public in functie de orasul in care se afla geamul
- extrage informatii dintr-un api despre temperatura dorita a camerei (stocata in baza de date desemnata temperaturii interioare)
- face un self-review in functie de informatiile extrase din api(temperatura interioara mentinuta)
- realizarea unei sectiuni care monitorizeaza nivelul de gaze,fum (gasit in baza de date)
- extrage informatii despre temperatura din camera si isi modifica pozitia.









