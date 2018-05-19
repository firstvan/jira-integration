# Jira-integration

Egy egyszeru kis program, ami a lok�lisan telep�tett jir�hoz kapcsol�dik,
�s issueval, illetve worklogokkal kapcsolatos muveleteket lehet v�grehajtani.

A program egy egyetemi beadand�hoz k�sz�lt, a clean-code hi�nya �s k�dol�si pontatlans�gok jog�t fenntartom! (Legfobb ok: idohi�ny)

Az alkalmaz�shoz tesztek is k�sz�ltek.

#### Version

1.0

#### Prerequisites:
* [Apache Maven](https://maven.apache.org)
* Java-jdk
* Wildfly alkalmaz�sszerver

#### Usage

Deployolhat� war k�sz�t�se:
```sh
    $ mvn clean install
```

Tesztek futtat�sa:
```sh
    $ mvn test
```

K�dlefedetts�g gener�l�s:
```sh
    $ mvn site
```

A cobertura �ltal gener�lt index.html megtal�lhat� a k�vetkezo helyen: project_basedir/target/staging.
A jacoco �ltal gener�lt index.html megtal�lhat� a k�vetkezo helyen: project_basedir/target/staging/jacoco.