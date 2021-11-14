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
Scopul aplicatiei este de a imbunatati atmosfera casei/apartamentului si de a controla volumul de lumina dintr-o incapere, oferind proprietarului posibilitatea de a avea control complet.
### Obiective
* posibilitatea de a prelua date din mediul inconjurator.
* sa se realizeze comunicarea eficienta cu un alt device pentru un anumit scop.(ex: lumina puternica afara inseamna lumina scazuta a becurilor smart).
* posibilitatea de self-review pentru a oferi utilizatorul o experienta completa (exemplu : fereastra a pastrat o temperatura potrivita pentru locul in care este amplasata).
* proiectarea diferitelor imagine pe sticla ferestrei ( exemplu peisaje, poze artistice, etc.).
* existenta unei functii de detectare a nivelul de gaze si fum si sa actioneze corespunzator.
* setarea automata in functie de temperatura incaperii si de input-urile user-ului.
### Grup tinta
* Ca persoana in varsta/cu dizabilitati vreau sa imi pot controla ferestrele din apartament/casa fara prea mult efort.
* Ca parinte vreau sa asigur atmosfera placuta din camera copilului meu chiar si cand nu sunt acasa.
* Ca gamer imi doresc sa am geam avand culori cat mai atractive (RGB) pentru atmosfera din camera.
* Ca angajat imi doresc sa deschid/inchid geamurile sau sa garantez o atmosfera cat mai relaxanta in camera fara sa ma ridic de la birou.
* Ca detinator de animale de companie, vreau sa ma asigur ca animalele de companie stau in conditii bune dpdv al aerului cand sunt plecat de acasa.
* Ca home-owner, vreau sa detin mai mult acces decat alti guests asupra ferestrei si sa ma asigur ca locuinta mea este in siguranta dpdv al nivelului de gaze si fum, atat de afara cat si din incapere.
### Colectarea cerintelor 
  A - screen de login  <br />
  B - screen de register  <br />
  C - extrage informatii despre temperatura din camera si isi modifica pozitia  <br />
  D - autorizarea in aplicatie( exemplu: home-owner, guest)  <br />
  E - baza de date destinata stocarii setarilor curente ale ferestrei  <br />
  F - un user poate seta pozitionarea geamului  <br />
  G - extrage informatii despre climat si vreme dintr-un API in functie de zona geamului  <br />
  H - aplicatia poate trimite un semnal altei aplicatii (ex: smart bulb pentru a anunta ca este destul de multa lumina afara.(exportat prin fisier json))  <br />
  I - extrage informatii dintr-un api despre temperatura medie a camerei  dintr-o zona  <br /> 
  J - face un self-review in functie de informatiile extrase din api  <br />
  K - un camp unde utilizatorul isi pune o imagine care poate fi proiectata  <br />
  L - realizarea unei sectiune care monitorizeaza nivelul de gaze,fum (primit prin json)  <br />
  
### Cerinte functionale:
- autorizarea in aplicatie(ex: home-owner,guest)
- utilizatorul poate seta pozitionarea geamului
- un camp unde utilizatorul isi pune o imagine care poate fi proiectata
- realizarea unei sectiune care monitorizeaza nivelul de gaze,fum.(primit prin json)
- aplicatia poate trimite un semnal altei aplicatii (ex: smart bulb pentru a anunta ca este destul de multa lumina afara.(exportat prin fisier json)) 
- extrage informatii despre temperatura din camera si isi modifica pozitia.
### Cerinte non-functionale:
- screen de login
- screen de register
- baza de date destinata stocarii setarilor curente ale ferestrei
- extrage informatii despre climat si vreme dintr-un API in functie de zona geamului
- extrage informatii dintr-un api despre temperatura medie a camerei dintr-o zona 
- face un self-review in functie de informatiile extrase din api
### Cerinte care tin de securitate, modificarile ferestrei:
- screen de login
- screen de register
- autorizarea in aplicatie(ex: home-owner,guest)
- utilizatorul poate modifica pozitionarea geamului
- extrage informatii despre temperatura din camera si isi modifica pozitia.
- realizarea unei sectiune care monitorizeaza nivelul de gaze,fum.(primit prin json)
### Cerinte care tin de stocarea datelor:
- baza de date pentru stocarea setarilor curente ale geamului
- un camp unde utilizatorul isi poate pune o imagine care o sa fie proiectata
### Cerinte care tin de comunicare:
- aplicatia poate trimite un semnal altei aplicatii (ex: smart bulb pentru a anunta ca este destul de multa lumina afara.(exportat prin fisier json)) 
### Cerinte care tin de procesare:
- extrage informatii despre climat si vreme dintr-un API in functie de zona geamului
- extrage informatii dintr-un API despre temperatura medie a camerei dintr-o zona 
- face un self-review in functie de informatiile extrase din API
- realizarea unei sectiune care monitorizeaza nivelul de gaze,fum.(primit prin json)
- extrage informatii despre temperatura din camera si isi modifica pozitia.

![alt text](https://github.com/AncutaIoan/automate_me/blob/main/graf.png)








