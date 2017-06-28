## Planning Maker
## GSO Killer App

Planning Maker is een Java applicatie gemaakt door Lesley Peters, ter oplevering voor het vak GSO31 aan de Fontys Hogeschool Eindhoven.

Deze applicatie maakt het mogelijk voor gebruikers om een privé agenda aan te maken en te beheren door er taken en afspraken (events) aan toe te voegen.

Daarnaast biedt deze applicatie de mogelijkheid om samen met andere gebruikers "Gedeelde agenda's" aan te maken waardoor ze samen taken en afspraken kunnen maken en beheren.

Om de communicatie tussen de server, clients en agenda hosters mogelijk te maken is er gebruik gemaakt van RMI (pull). Daarnaast gebruikt deze applicatie ook de library "FontysPublisher" (aangeboden door Fontys) die het mogelijk maakt om veranderingen aan een agenda te pushen naar verschillende clients die hierop geabboneerd zijn.

### Om te beginnen
Deze instructies zullen ervoor zorgen dat je een kopie van deze applicatie kunt maken en uitvoeren op je eigen lokale pc voor development en testing doeleinden.

### Voorwaarden
Het volgende heb je nodig om deze applicatie te kunnen aanmaken
```markdown
- Een mysql database (indien nodig de jdbc driver voor het maken van de connectie)
- Een Code Editor (Deze applicatie is gemaakt in NetBeans)
```

### Installatie
Allereerst moet er een mysql database worden aangemaakt
```markdown
Dit kan gedaan worden door het create script "PlanningMakerDatabase.sql" uit te voeren in je eigen mysql database.
```

Als de database aangemaakt is moet in de klasse "Connection" de connection string en de database user + password worden meegegeven, dit kan als volgt gedaan worden:
```markdown
    private final String connectionString = "jdbc:mysql://vserver213.axc.nl:3306/lesleya213_gso?zeroDateTimeBehavior=convertToNull";
    private final String dbUser = "gebruikersnaam";
    private final String dbPass = "wachtwoord";
    
    Allereerst dien je je eigen connection string in te vullen om een connectie te maken naar je eigen database, daarna moet je de gebruikersnaam en wachtwoord meegeven ter verificatie.
```

Nu kan het ip adres van de server ingevoerd worden zodat de clients met de server kunnen verbinden.
Het IP adres kan aangepast worden in de klasse "RegistryManager"
```markdown
verander: 
String ipAddress = "127.0.0.1";
naar het ip adres van de server.

Indien je de applicatie op 1 machiene wilt testen, zorg er dan voor dat de regel (te vinden in de constructor van RegistryManager):
getLocalHostIp();
niet uitgecomment is, deze methode zal je locale ip adres ophalen en gebruiken om een connectie mee te maken.
Wil je de server op een externe pc laten draaien, zorg er dan juist wel voor dat deze regel uitgecomment is:
//getLocalHostIp();
```

Voor het testen van de interfaces word de gehele database leeggemaakt! Houd hier rekening me en zorg dat je hiervoor een apparte test database aanmaakt, verander daarna ook de connection string in de Connection klasse.
```markdown
    //private final String connectionString = "jdbc:mysql://vserver213.axc.nl:3306/lesleya213_gso?zeroDateTimeBehavior=convertToNull";
    private final String connectionString = "jdbc:mysql://vserver213.axc.nl:3306/lesleya213_gso?zeroDateTimeBehavior=convertToNull";
    private final String dbUser = "gebruikersnaam";
    private final String dbPass = "wachtwoord";
    
    Zorg ervoor dat je de normale database connection string uitcomment en de connection string van de test database gebruikt tijdens het testen van de interfaces.
```

### Auteur
- Lesley Peters - Ontwerp & Ontwikkeling van de applicatie

### Support of Contact
Heb je vragen of aanvullingen? Stuur me dan gerust een berichtje naar mijn schoolmail: lesley.peters@student.fontys.nl
