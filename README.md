# fiks-arkiv-client-java
[![MIT Licens](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/ks-no/fiks-arkiv-client-java/blob/master/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/no.ks.fiks/fiks-arkiv-api.svg)](https://search.maven.org/search?q=g:no.ks.fiks%20a:fiks-arkiv-api)
![GitHub last commit](https://img.shields.io/github/last-commit/ks-no/fiks-arkiv-client-java.svg)
![GitHub Release Date](https://img.shields.io/github/release-date/ks-no/fiks-arkiv-client-java.svg)

# Beskrivelse
Kotlin klient for generering av meldinger til [Fiks-Arkiv](https://www.ks.no/fagomrader/digitalisering/felleslosninger/verktoykasse-plan--og-byggesak/verktoy/sammenhengende-tjenester---integrasjoner/fiks-arkiv/), 
[tjenestedokumentasjon](https://ks-no.github.io/fiks-plattform/tjenester/fiksio/arkiv/)

Modul fiks-arkiv-api inneholder autogenerert typer, definert i arkiv spesifikasjon [repo](https://github.com/ks-no/fiks-arkiv-specification).
Modul fiks-arkiv-forenklet-arkivering inneholder buildere for forenklet oppbygging av gyldig arkivmelding. Se [ArkivmeldingBuilder](https://github.com/ks-no/fiks-arkiv-client-java/blob/main/fiks-arkiv-forenklet-arkivering/src/main/kotlin/no/ks/fiks/io/arkiv/model/arkivmelding/ArkivmeldingBuilder.kt)

# Brukstilfeller
Ref [Isy Proaktiv](https://github.com/ks-no/fiks-arkiv/wiki/Brukstilfeller#4-isy-proaktiv) er det laget forenklet modell som bygger ArkivmeldingBuilder, se [ArkivmeldingForenkletUtgaaende](https://github.com/ks-no/fiks-arkiv-client-java/blob/main/fiks-arkiv-forenklet-arkivering/src/main/kotlin/no/ks/fiks/io/arkiv/model/forenklet/ArkivmeldingForenkletUtgaaende.kt)
Eks:
```
ArkivmeldingBuilder arkivmelding = new ArkivmeldingForenkletUtgaaende()
    .sluttbrukerIdentifikator("ABC")
    .nyUtgaaendeJournalpost(new UtgaaendeJournalpost()
            .tittel("Vedtak etter tilsyn")
            .referanseEksternNoekkelForenklet(new EksternNoekkelForenklet()
                    .noekkel(UUID.randomUUID().toString())
                    .fagstystem("Fagsystem X"))
            .internAvsender(Collections.singletonList(new KorrespondansepartIntern()
                    .saksbehandler("Birger Brannmann")
                    .referanseSaksbehandler("60577438-1f97-4c5f-b254-aa758c8786c4")))
            .mottakere(Collections.singletonList(new KorrespondansepartForenklet()
                    .navn("Mons Mottaker")
                    .personid(new Personidentifikator()
                            .personidentifikatorLandkode("NO")
                            .personidentifikatorNr("12345678901"))
                    .postadresse(new EnkelAdresse()
                            .adresselinje1("Gate 1")
                            .adresselinje2("Andre adresselinje")
                            .adresselinje3("Tredje adresselinje")
                            .postnr("3801")
                            .poststed("Bø"))
                    .forsendelsemåte("SvarUt")))
            .hoveddokument(new ForenkletDokument()
                    .tittel("Vedtak")
                    .filnavn("vedtak.pdf")
                    .referanseDokumentFil("/en/path")))
    .referanseSaksmappeForenklet(new SaksmappeForenklet().tittel("Tilsyn eiendom 21/400"))
    .byggArkivmelding();
```


- Modeller for forenklet generering av gyldige arkivmeldinger.
- Typer er definert med utgangspunkt i [kodeliste fra arkivverket](http://arkivverket.metakat.no/Diagram/Index/EAID_CC654F7F_60CA_4240_A003_B6557201F2BC)
- XSD skjema er inkludert i jar under schemas.v1. Skjema er definert i eget [repo](https://github.com/ks-no/fiks-arkiv-specification)
- Noark 5 dokumentasjon [Noark 5](https://www.arkivverket.no/forvaltning-og-utvikling/noark-standarden/noark-5/noark5-standarden)




Før bygg må submodul hentes:
```shell
git submodule  update --init --recursive --remote
```