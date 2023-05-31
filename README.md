## Spielbeschreibung

Das Projekt Digitaler Alltag verfolgt das Ziel durch eine spielerische Weise Kindern und Jugendlichen ein Strombewusstsein zu vermitteln. Dabei greift das Projektteam auf eine Arcade-Spiel zurück, welches eine angepasste Form von den bekannten Spiele " Space Invaders" sein sollte. Durch die Hardware-Komponente in Form einer Kurbel wird der Spieler animiert Strom zu erzeugen und durch die im Spiel erreichten Checkpoints zu erfahren wie viel Strom mit der Kurbel bis zu diesem Punkt erzeugt wurde und welche Alltagsgegenstände für wie lange damit betrieben oder geladen werden können.

## Hardwarekomponenten

Die Hardwarekomponenten sind im SAD unter [Hardwarebeschreibung](https://gitlab.fhnw.ch/ip12-22vt/ip12-22vt_digitaleralltag/docu/-/blob/main/software(sad)/Hardwarebeschreibung.adoc)  dokumentiert. Das Dokument dient auch als Anleitung für Wartungen am Spiel.


## Systeminformationen**

**RaspberryPi:  Model B** 

**Betriebssystem: OS based on Debian 11**

**Programmiersprache: Java 17** 

**Datenbank: MariaDB 10.5**


## Anweisungen für die Installation

### Aufsetzen Betriebssystem auf Raspberry Pi.

**Materialliste:**

- Raspberry Pi,
- Micro SD card, minimally 16Gb b
- PC (oder anderer Raspberry Pi) mit einem SD-Kartensteckplatz (eventuell benötigen Sie einen SD-Kartenadapter)
- Stromversorgung (5V, 2 or 3A)

Auf der SD-Karte wird das Betriebssystem gespeichert. Auf der Raspberry Pi-Website finden Sie auf der Download-Seite das Imager-Tool. Wählen Sie die Version für Ihren Computer aus, laden Sie sie herunter und installieren Sie sie.
https://www.raspberrypi.com/software/

1. Klicken Sie bei "Operating System" auf "CHOOSE OS"
2. Wählen Sie "Raspberry Pi OS (other)"
3. Wählen Sie "Raspberry Pi OS Full (32-bit)"
4. Stecken Sie Ihre SD-Karte in Ihren Computer oder in den SD-Kartenadapter 
5. Klicken Sie bei "SD-Karte" auf "CHOOSE SD CARD"
6. Wählen Sie Ihre SD-Karte aus.
7. Klicken Sie danach auf "WRITE"

Das Betriebssystem wird nun auf die SD-Karte geladen.

8. Stecken Sie nun die SD-Karte in den Raspberry Pi. 
9. Folgen Sie der Installationanweisung.

Alternativ können Sie auch das CrowPi Image herunterladen. Zur Installation können Sie auch das Imager-Tool von Raspberry Pi nutzen.
https://github.com/Pi4J/pi4j-example-crowpi/releases

Java wird mit der Vollversion des Betriebssystem installiert. Kontrollieren Sie welche Version installiert ist. Öffnen Sie ein Terminal und geben Sie java -version ein.

Password Raspberry Pi: picade


### Installation Git auf Entwickler-PC 

Installlieren sie Git auf ihrem PC 

**Windows**

Laden Sie die Installations-Datei herunter.
https://git-scm.com/download/win. Folgen Sie den Anweisungen des Installationsprogramms.

**Linux**
Geben Sie folgendes Kommando ein:

$ sudo dnf install git-all

oder 

$ sudo apt install git-all

**macOS**

Schauen Sie ob Git bereits installiert ist. 

$ git --version 

Falls es noch nicht installiert ist können Sie die Installations-Datei hier herunterladen https://git-scm.com/download/mac. Folgen Sie den Anweisungen des Installationsprogramms.



Herunterladen Git-Repository https://gitlab.fhnw.ch/ip12-22vt/ip12-22vt_digitaleralltag/galactic-energies

Verbindung Entwickler-PC mit Raspberry Pi durch IP-Adresse vom Raspberry Pi 

Die Ip-Adresse muss im pom.xml file hinterlegt werden

```
 <profiles>
        <!-- Transfer and run JAR with dependencies on remote Raspberry -->
        <profile>
            <id>remote-run</id>
            <activation>
                <property>
                <name>example.remote.host</name>
                </property>
            </activation>
            <build>
                <plugins>
```

### Anleitung für Anpassungen am System 

Die Anleitung für Anpassungen am System ist im SAD unter folgendem Link zu finden: [Anleitung für Anpassungen am System](https://gitlab.fhnw.ch/ip12-22vt/ip12-22vt_digitaleralltag/docu/-/blob/main/software(sad)/Anleitung%20für%20Anpassungen%20am%20System.adoc)

### Deployment-Anleitung 

Die Anleitung für das Deployment finden sie unter folgendem Link: [Deployment-Anleitung.](https://gitlab.fhnw.ch/ip12-22vt/ip12-22vt_digitaleralltag/docu/-/blob/main/software(sad)/src/07.1_DeploymentInstrucions.adoc)
Sie beinhaltet zudem Anweisungen für die Neugenerierung des Systems aus Quell- und Konfigurationsdateien.
## Dateiliste
### Klassen
***collision***

    AseroidHandler 

***components***

    ArrowsComponent
    AsteroidComponent
    BackgroundStarsViewComponent
    CheckpointComponent
    DashboardComponent
    LifeComponent
    RocketComponent

***controllers***

    AsteroidController
    CheckpointController
    GameOverController
    MovementController
    PowerController
    RocketController
    SpeedController
    ViewController

***data***

    DBConnection
    PowerInput

***enums***

    GalacticEnergiesType

***events***

    GameEvent

***factories***

    GalacticEnergiesFactory
    LoadingSceneFactory

***kurbel***

    Kurbel

***menu***

    IntroScene
    MainMenu

***Config***

***View***

## Fehlerbehebung

## Bekannte Bugs

## Danksagung und Credits

Wir möchten uns herzlich bei Ihnen für Ihr Interesse an unserem Projekt bedanken. Diese Readme-Datei dient dazu, Ihnen einen Überblick über unser Projekt zu geben und Ihnen bei der Verwendung und dem Verständnis unserer Software zu helfen.

Unser Entwicklerteam hat hart daran gearbeitet, um dieses Projekt zu realisieren, und wir sind stolz auf das Ergebnis, das wir erreicht haben. Doch wir möchten betonen, dass dieses Projekt ohne die Unterstützung und Hilfe von vielen Menschen nicht möglich gewesen wäre.

Zuallererst möchten wir uns bei unseren Dozierenden bedanken. Ihre fachkundige Anleitung, Ihre wertvollen Ratschläge und Ihre Bereitschaft, uns bei unseren Fragen zu unterstützen, haben uns enorm geholfen. Sie haben uns ermutigt, unsere Fähigkeiten weiterzuentwickeln und neue Wege zu erkunden. Ohne Ihre Unterstützung wären wir nicht in der Lage gewesen, dieses Projekt erfolgreich abzuschließen.

Ein besonderer Dank geht auch an das Pi4J-Team. Die von Ihnen entwickelte Plattform und die bereitgestellten Werkzeuge waren von unschätzbarem Wert für die Umsetzung unseres Projekts. Ihre fortlaufende Weiterentwicklung von Pi4J hat uns die Möglichkeit gegeben, die volle Leistungsfähigkeit der zugrunde liegenden Hardware auszuschöpfen und unsere Ideen in die Tat umzusetzen.

Darüber hinaus möchten wir uns bei all den Menschen bedanken, die uns bei der Testphase unterstützt und wertvolles Feedback gegeben haben. Ihre konstruktiven Anmerkungen haben dazu beigetragen, unser Projekt zu verbessern und uns auf mögliche Fehler oder Schwachstellen aufmerksam zu machen.

Vielen herzlichen Dank!

## Kontaktinformationen:
Jan Rudolf 
jan.rudolf@students.fhnw.ch

Rinor Shala	
rinor.shala@students.fhnw.ch	

Andreas Löffel	
andreas.loeffel@students.fhnw.ch

Ayse Soy	
ayse.soy@students.fhnw.ch

Benaja Kuhn	
benaja.kuhn@students.fhnw.ch

Simon Wildi	
simon.wildi@students.fhnw.ch

Jasmin Bajwa
jasmin.bajwa@students.fhnw.ch
