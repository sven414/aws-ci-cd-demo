# Vecka 2: Skapa ett 'Book API'

## Beskrivning

Detta projekt handlar om att skapa ett enkelt CRUD (Create, Read, Update, Delete) API i Java. Målet är att hantera
'Book'-relaterade uppgifter, vilket ger de studerande praktisk erfarenhet av att utveckla och interagera med ett REST
API. Projektet involverar att använda Spring Boot för att förenkla utvecklingsprocessen och en H2-databas för
att lagra uppgifter. Vi kommer att följa Uncle Bob's standard för Clean Code och använda en service layer (facade) för
enkelhet vid unittester.

## Mål

- Utveckla ett REST API i Java med Spring Boot.
- Implementera CRUD-operationer för 'Book'-uppgifter.
- Använda H2-databas för datalagring.
- Skapa enkel, men tydlig dokumentation för API:ets användning.
- Följa Clean Code-principer.

## Förkunskaper

- Grundläggande kunskaper i Java.
- Förståelse för Objektorienterad Programmering (OOP).
- Grundläggande förståelse för REST API:er och CRUD-operationer.
- Grundläggande kunskaper i Spring Boot.

## Färdigheter som utvecklas

- Förmågan att skapa och hantera ett REST API i Java.
- Förståelse för databasinteraktioner i Java-applikationer.
- Kunskap i att använda Spring Boot och H2-databasen för att utveckla en fullstack-applikation.
- Implementering av Clean Code-principer.

## Steg-för-Steg Instruktioner

1. **Konfiguration:** Konfigurera `application.properties` för att använda H2-databasen.
2. **Book Entity:** Skapa en `Book`-entitetsklass.
3. **Book Repository:** Implementera `BookRepository`-gränssnittet för databasoperationer.
4. **Book Service:** Implementera `BookService` för att hantera 'Book'-affärslogik.
5. **Book Controller:** Implementera `BookController` för att hantera API-anrop.
6. **Testning:** Utför enhetstester för att säkerställa att alla CRUD-operationer fungerar som förväntat.
7. **HTTP Anrop:** Skapa en `generated-requests.http`-fil med korrekta HTTP-anrop för att testa API:et.

## Ni ska leverera

- Fullständigt implementerat och fungerande CRUD API.
- En `generated-requests.http`-fil med korrekta HTTP-anrop för att testa API:et.
- Ifylld `personal-reflection.md`-fil.

## Bedömningskriterier

- **Funktionalitet:** API:et ska korrekt utföra alla CRUD-operationer.
- **Kodkvalitet:** Koden ska följa Clean Code-principer och vara lättförståelig.
- **Databasinteraktion:** Effektiv och korrekt användning av H2-databasen.
- **Testning:** Korrekt implementering av enhetstester som verifierar API:ets funktionalitet.

## Kursmål som testas

- Den studerande kan bygga ett API som ger svarsmeddelande och svarskoder.

Lycka till och ha kul!
