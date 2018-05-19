# Jira-integration

Egy egyszerű kis webalkalmazás, ami a lokálisan telepített jirához kapcsolódik,
és issueval, illetve worklogokkal kapcsolatos műveleteket lehet végrehajtani.

A program egy egyetemi beadandóhoz készült, a clean-code hiánya és kódolási pontatlanságok jogát fenntartom! (Legfőbb ok: időhiány)

Az alkalmazáshoz tesztek is készültek.

#### Version

1.0

#### Prerequisites:
* [Apache Maven](https://maven.apache.org)
* Java-jdk
* Wildfly alkalmazásszerver

#### Usage

Deployolható war készítése:
```sh
    $ mvn clean install
```

Tesztek futtatása:
```sh
    $ mvn test
```

Kódlefedettség generálás:
```sh
    $ mvn site
```

A cobertura által generált index.html megtalálható a következő helyen: project_basedir/target/staging.
A jacoco által generált index.html megtalálható a következő helyen: project_basedir/target/staging/jacoco.
