# Übung 1

**Autor**: Fabian Lohoff<br>
**Datum**: 02.10.204

## Aufgabe 1

**Wie kann diese Kommunikationsverbindung nun dennoch mit Hilfe einer zusätzlichen
„Fabrik“-Klasse, welche die dazu notwendige Objekt-Erzeugung übernimmt,
aufgebaut werden? In welchem Package sollte diese zusätzliche Klasse liegen? Bitte
beachten Sie dabei auch die Hinweise bzw. Anforderungen aus den Kommentaren der Klassen, Methoden und des Interfaces. So sollte z.B. auch das Datum der
Erzeugung eines Translator-Objekts gesetzt werden.**

Wir erstellen ein eigenes Factory-Package im control package.
In diesem erstellen wir eine Factory-Klasse, die den Zweck
hat, die GermanTranslator-Klasse zu erzeugen. Dazu benötigt aber das Translator Interface,
welches von GermanTranslator implementiert wird, ein anderes Zugriffsrecht.
Es muss auf public gestellt werden.

----

**Welches Entwurfsmuster (engl.: design pattern) könnte für die Problematik der
Objekt-Erzeugung verwendet werden? Was ist der software-technische Nutzen bei
der Verwendung des Entwurfsmusters? Gratistipp: Hinweise für das korrekte Pattern
finden sie bei unten angegeben Video-Tutorien ;-)**

Das dahinter liegende Design Pattern heißt "Factory Method". Nach diesem
Design Pattern werden Objekte nur aus sogenannten Factory-Klassen erstellt.
Dadurch sind alle Erzeugungen eines Objektes zentral in der Fabrik-Klasse einsehbar.
Dies hilft bei der Strukturierung und Nachvollziehbarkeit des Codes. Obendrein
kann man in diesen Objekt-erzeugenden-Methoden auch gewisse Regeln für die Erzeugung eines
Objektes festlegen.

----

**Wie muss man den Source Code des Interface ggf. anpassen, um mögliche
auftretende Kompilierfehler zu beseitigen?**

Es muss definitiv die Zugriffsrechte geändert werden. Vorgabe war es in der Client-Klasse 
streng gegen das Interface zu programmieren. Dazu brauch diese Klasse aber Zugriff auf das Interface.
Deshalb muss man den Zugriffsoperator auf "public" setzen!

----

## Aufgabe 3

**Was ist der Vorteil einer separaten Test-Klasse?**

Alle Tests in einer seperaten Test-Klase zu bündeln hilft der Strukturiertheit
des Codes. Darüber hinaus kann man so eine Test-Klasse entwerfen, bevor die erste Zeile Code geschrieben wurde.
Der Entwickler kann sich dann ganz an die Tests halten und gegen diese Programmieren. Dies nennt man 
dann Test-Driven-Development.

----

**Was ist bei einem Blackbox-Test der Sinn von Äquivalenzklassen?**

Perfekte Tests decken jeden erdenklichen Fall ab, damit der Code gegen jede Situation
getestet ist. In unserem Beispiel müssten wir aber jede Ganzzahlige Zahl testen. Dies ist eine unendliche Menge und
nicht durchführbar. Deshalb versucht man die Menge an zahlen in disjunkte Teilmengen zu unterteilen, bei denen
man denkt, dass alle Repräsentanten dieser Teilmenge den gleichen Output liefern soll. So kann man eine Menge unendlicher Tests
auf wenige Äquivalenzklassen kürzen. Nun muss nur noch je ein Repräsentant einer Äquivalenzklasse
getestet werden.

-----

**Warum ist ein Blackbox-Test mit JUnit auf der Klasse Client nicht unmittelbar
durchführbar?**

Die Client Klasse soll lediglich die Eingabe des Nutzers in das System repräsentieren.
Es besitzt jedoch selbst keine Funktionalität, die man testen müsste. Diese liegt allein
in der Klasse "GermanTranslator", weshalb es nur Sinn macht, diese direkt zu testen.
