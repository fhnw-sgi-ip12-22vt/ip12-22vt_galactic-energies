**Digitaler Alltag**

Das Projekt Digitaler Alltag verfolgt das Ziel durch eine spielerische Weise Kindern und Jugendlichen ein Strombewusstsein zu vermitteln. Dabei greift das Projektteam auf eine Arcade-Spiel zurück, welches eine angepasste Form von den bekannten Spiele " Space Invaders" sein sollte. Durch die Hardware-Komponente in Form einer Kurbel wird der Spieler animiert Strom zu erzeugen und durch die im Spiel erreichten Checkpoints zu erfahren wie viel Strom mit der Kurbel bis zu diesem Punkt erzeugt wurde und welche Alltagsgegenstände für wie lange damit betrieben oder geladen werden können.

**Komponenten**

Raspberry Pi, 
Button,  
Kurbel,  
Strommesser,
Holzbox, Stromkabel Pi


**Systeminformationen**

RaspberryPi: Version 

Betriebssystem: Linux

Programmiersprache: Java 

Datenbank: MariaDB


**Anweisungen für die Installation**

Aufsetzen Betriebssystem auf Raspberry Pi.

Materialliste:

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

Java wird mit der Vollversion des Betriebssystem installiert. Kontrollieren Sie welche Version installiert ist. Öffnen Sie ein Terminal und geben Sie java -version ein.

Installation Git auf Entwickler-PC 

Herunterladen Git-Repository https://gitlab.fhnw.ch/ip12-22vt/ip12-22vt_digitaleralltag/galactic-energies

Verbindung Entwickler-PC mit Raspberry Pi durch IP-Adresse vom Raspberry Pi 

Die Ip-Adresse muss im pom.xml file hinterlegt werden 

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




**Dateiliste**


**Klassen**

***collision:***
 AsteroidHandler

***components:***
ArrowsComponent,
AsteroidComponent,
BackgroundStarsViewComponent,
CheckpointComponent,
DashboardComponent,
LifeComponent,
RocketComponent

***controllers:***
AsteroidController,
CheckpointController,
LevelController,
MovementControllerDEV,
MovementControllerJoyStick,
PowerController,
RocketController,
SpeedController,
ViewController

***data:***
DBConnection,
PowerInput,
SelectTest

***enums:***
GalacticEnergiesType,
GameStates,

***events:***
GameEvent

***factories:***
GalacticEnergiesFactory

***kurbel:***
KurbelTest
Kurbel

Config
View

**Changelog**

**Fehlerbehebung**

**Bekannte Bugs**

**Danksagung und Credits**

**Kontaktinformationen:**
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
