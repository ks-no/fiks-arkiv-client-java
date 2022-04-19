# fiks-arkiv-client-java
[![MIT Licens](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/ks-no/fiks-arkiv-client-java/blob/master/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/no.ks.fiks/fiks-arkiv-api.svg)](https://search.maven.org/search?q=g:no.ks.fiks%20a:fiks-arkiv-api)
![GitHub last commit](https://img.shields.io/github/last-commit/ks-no/fiks-arkiv-client-java.svg)
![GitHub Release Date](https://img.shields.io/github/release-date/ks-no/fiks-arkiv-client-java.svg)

Kotlin klient for generering av meldinger til [Fiks-Arkiv](https://www.ks.no/fagomrader/digitalisering/felleslosninger/verktoykasse-plan--og-byggesak/verktoy/sammenhengende-tjenester---integrasjoner/fiks-arkiv/), 
[tjenestedokumentasjon](https://ks-no.github.io/fiks-plattform/tjenester/fiksio/arkiv/)


Modeller for forenklet generering av gyldige arkivmeldinger. 
Typer er definert med utgangspunkt i [kodeliste fra arkivverket](http://arkivverket.metakat.no/Diagram/Index/EAID_CC654F7F_60CA_4240_A003_B6557201F2BC)
XSD skjema er inkludert i jar under schemas.v1. Skjema er definert i eget [repo](https://github.com/ks-no/fiks-arkiv-specification)

Før bygg må submodul hentes:
```shell
git submodule  update --init --recursive --remote
```