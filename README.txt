Papacica Elena Daniela
323CB
						Tema 1 - POO


	Pentru rezolvarea temei am parcurs urmatorii pasi:

	1) Citirea datelor din fisier folosind o instanta a clasei HomeworkReader din suportul pentru tema.


	2) Instantierea planificatorului de procese si apelarea metodei corespondente tipului de scheduler necesar.
	Clasa corespondenta planificatorului se numeste SchedulerList si contine cate o metoda pentru fiecare tip de scheduler(Random, RoundRobin, Weighted).
	Prin apelarea constructorului pentru scheduler se seteaza si campul schedulerType. Se apeleaza apoi din main metoda din scheduler care alege din cele 3 metode corespondente fiecarui tip(metoda getScheduler), in functie de campul schedulerType.
		Descrierea fiecarui tip de scheduler:
	Fiecare scheduler are un algoritm pentru alegerea unui proces dintre cele disponibile pentru fiecare numar in parte care urmeaza sa fie procesat. 
	a) Random Scheduler selecteaza un numar aleator mai mic decat numarul de procese disponibile. Se alege apoi procesul cu indexul corespondent numarului aleator ales.
	b) Round Robin Scheduler foloseste fiecare proces pe rand in ordinea data. Cand toate procesele au fost utilizate, se ia de la inceput alegerea proceselor.
	c) Weighted Scheduler calculeaza cmmdc-ul cotelor proceselor disponibile, apoi calculeaza procentul pe care ar trebui sa-l aiba fiecare proces. Fiecare proces va fi utilizat de atatea ori cat reprezinta numarul corespondent procentului, la o tura. Procedeul de utilizare a fiecarui proces conform proportiilor se repeta pana la terminarea numerelor ce trebuie sa fie procesate.


	3) Utilizarea cache-ului
	Inca de la instantierea claseic SchedulerList in main, este setat atat tipul de planificator utilizat cat si tipul de cache, deoarece constructorul de SchedulerList instantiaza campul mySchedulerCache. Instantierea tipului de cache se realizeaza utilizand Factory. Asadar exista o clasa abstracta Cache si 3 clasa care extind clasa Cache(NoCache, LruCache si LfuCache). Metoda getCacheType din clasa CacheFactory alege sa instantieze unul din cele 3 tipuri.
		Descrierea fiecarui tip de cache:
	Fiecare din cele 3 tipuri de cache apeleaza metoda getProcess din clasa ProcessList sau cauta in liniile de cache daca acel proces impreuna cu numarul de procesat au mai fost apelate pentru a intoarce direct rezultatul obtinut data trecuta. Pentru stocarea liniilor de cache(continand procesul, numarul procesat, rezultatul, numarul de utilizari si ultimul moment in care a fost utilizat) se foloseste un vector cu instante al clasei CacheStructure.

	a) No Cache - de fapt nu utilizeaza cache, motiv pentru care apeleaza direct metoda getProcess.

	b) si c) Atat LruCache cat si LfuCache stocheaza in linii de cache rezultatele. Cand procesul impreuna cu numarul de procesat se gasesc in liniile de cache, rezultatul este intors direct. Daca perechea nu se gaseste in cache, aceasta este introdusa iar rezultatul este calculat si introdus in cache de asemenea. Daca cache-ul nu e plin se continua ocuparea sa cu noua informatie. Diferenta dintre cele 2, LfuCache si LruCache consta in momentul cand se doreste introducerea unei noi perechi (proces, numar de procesat) si cache-ul este plin. 
	LruCache foloseste un contor de timp si asociaza fiecarei linii de cache ultimul moment in care a fost utlizat(fie cand a fost introdus sau cand s-a extras rezultatulde la linia respectiva). La introducerea unei noi informatii, aceasta este asezata in locul celei mai vechi utlizate.
	LfuCache asociaza in schimb fiecarei linii frecventa utilizarilor. Cand se doreste introducerea unei linii noi in cache-ul plin, aceasta se va pune in locul celei mai putin utilizate.


	4) Apelarea proceselor
	Fiecare tip de cache, atunci cand nu gaseste perechea (numar de procesat, numele procesului) in cache, este nevoit sa calculeze pur si simplu rezultatul prin apelarea metodei getPorcess din clasa ProcessList(in cazul NoCache, getProcess este apelat direct de fiecare data).
	Clasa ProcessList are implementata cate o metoda pentru fiecare tip de proces(CheckPrima, Factorial, Square...etc) si o metoda care alege din aceasta lista pe baza numelui de proces primit ca parametru. Metoda aceasta returneaza un rezultat din clasa Output.
	Clasa Output contine tripletul (procesul ales, rezultatul, string-ul "FromCache"/"Computed") si este utila pentru efectuarea afisarii finale in fisier. In cazul in care rezultatul este generat in urma proceselor, stringul va fi "Computed" iar cand rezultatul este preluat direct din cache, acesta va fi "FromCache".


	5) Afisarea fiecarei linii de output in fisier




