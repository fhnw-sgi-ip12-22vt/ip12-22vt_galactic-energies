**Digitaler Alltag**

Das Projekt Digitaler Alltag verfolgt das Ziel durch eine spielerische Weise Kindern und Jugendlichen ein Strombewusstsein zu vermitteln. Dabei greift das Projektteam auf eine Arcade-Spiel zurück, welches eine angepasste Form von den bekannten Spiele " Space Invaders" sein sollte. Durch die Hardware-Komponente in Form einer Kurbel wird der Spieler animiert Strom zu erzeugen und durch die im Spiel erreichten Checkpoints zu erfahren wie viel Strom mit der Kurbel bis zu diesem Punkt erzeugt wurde und welche Alltagsgegenstände für wie lange damit betrieben oder geladen werden können.

**Komponenten**

RaspberryPi, 
Button,  
Kurbel,  
Strommesser,
Holzbox


**Systeminformationen**

RaspberryPi: Version 

Betriebssystem: Linux

Programmiersprache: Java 

Datenbank: MariaDB


**Anweisungen für die Installation**

Aufsetzen Betriebssystem Raspberry Pi.

Installation Git auf Entwickler-PC 

Herunterladen Git-Reposatory https://gitlab.fhnw.ch/ip12-22vt/ip12-22vt_digitaleralltag/galactic-energies

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
