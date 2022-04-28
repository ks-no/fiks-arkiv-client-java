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
```java
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
Resultat fra oppbygging av arkivmelding:
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<arkivmelding xmlns="http://www.arkivverket.no/standarder/noark5/arkivmelding/v2" xmlns:ns2="http://www.arkivverket.no/standarder/noark5/metadatakatalog/v2" xmlns:ns3="http://www.arkivverket.no/standarder/noark5/arkivstruktur">
    <system>Fagsystem X</system>
    <meldingId>06db37a0-c827-4193-b373-f4c5f4f1add2</meldingId>
    <tidspunkt>2022-04-28T12:21:51.842558391+02:00</tidspunkt>
    <antallFiler>1</antallFiler>
    <mappe xsi:type="saksmappe" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
        <tittel>Tilsyn eiendom 21/400</tittel>
        <registrering xsi:type="journalpost">
            <opprettetDato>2022-04-28T12:21:51.808217413+02:00</opprettetDato>
            <opprettetAv>ABC</opprettetAv>
            <arkivertDato>2022-04-28T12:21:51.808290714+02:00</arkivertDato>
            <dokumentbeskrivelse>
                <dokumenttype>
                    <ns2:kode>KORR</ns2:kode>
                    <ns2:beskrivelse>Faktura</ns2:beskrivelse>
                </dokumenttype>
                <dokumentstatus>
                    <ns2:kode>F</ns2:kode>
                    <ns2:beskrivelse>Dokumentet er ferdigstilt</ns2:beskrivelse>
                </dokumentstatus>
                <tittel>Vedtak</tittel>
                <opprettetDato>2022-04-28T12:21:51.811935028+02:00</opprettetDato>
                <tilknyttetRegistreringSom>
                    <ns2:kode>H</ns2:kode>
                    <ns2:beskrivelse>Hoveddokument</ns2:beskrivelse>
                </tilknyttetRegistreringSom>
                <tilknyttetDato>2022-04-28T12:21:51.812008351+02:00</tilknyttetDato>
                <dokumentobjekt>
                    <versjonsnummer>1</versjonsnummer>
                    <variantformat>
                        <ns2:kode>P</ns2:kode>
                        <ns2:beskrivelse>Produksjonsformat</ns2:beskrivelse>
                    </variantformat>
                    <format>
                        <ns2:kode>RA-PDF</ns2:kode>
                        <ns2:beskrivelse>PDF/A - ISO 19005-1:2005</ns2:beskrivelse>
                    </format>
                    <opprettetDato>2022-04-28T12:21:51.818820604+02:00</opprettetDato>
                    <filnavn>vedtak.pdf</filnavn>
                    <referanseDokumentfil>/en/path</referanseDokumentfil>
                </dokumentobjekt>
            </dokumentbeskrivelse>
            <tittel>Vedtak etter tilsyn</tittel>
            <korrespondansepart>
                <korrespondanseparttype>
                    <ns2:kode>EM</ns2:kode>
                    <ns2:beskrivelse>Mottaker</ns2:beskrivelse>
                </korrespondanseparttype>
                <korrespondansepartNavn>Mons Mottaker</korrespondansepartNavn>
                <organisasjonid>12345678901</organisasjonid>
                <postadresse>Gate 1</postadresse>
                <postadresse>Andre adresselinje</postadresse>
                <postadresse>Tredje adresselinje</postadresse>
                <postnummer>3801</postnummer>
                <poststed>Bø</poststed>
                <forsendelsesmaate>SvarUt</forsendelsesmaate>
            </korrespondansepart>
            <korrespondansepart>
                <korrespondanseparttype>
                    <ns2:kode>IA</ns2:kode>
                    <ns2:beskrivelse>Intern avsender</ns2:beskrivelse>
                </korrespondanseparttype>
                <korrespondansepartNavn>Birger Brannmann</korrespondansepartNavn>
                <saksbehandler>Birger Brannmann</saksbehandler>
            </korrespondansepart>
            <referanseEksternNoekkel>
                <ns3:fagsystem>Fagsystem X</ns3:fagsystem>
                <ns3:noekkel>06db37a0-c827-4193-b373-f4c5f4f1add2</ns3:noekkel>
            </referanseEksternNoekkel>
            <journalaar>2022</journalaar>
            <journalposttype>
                <ns2:kode>U</ns2:kode>
                <ns2:beskrivelse>Utgående dokument</ns2:beskrivelse>
            </journalposttype>
            <journalstatus>
                <ns2:kode>J</ns2:kode>
                <ns2:beskrivelse>Journalført</ns2:beskrivelse>
            </journalstatus>
            <journaldato>2022-04-28+02:00</journaldato>
            <antallVedlegg>1</antallVedlegg>
        </registrering>
    </mappe>
</arkivmelding>

```


- Modeller for forenklet generering av gyldige arkivmeldinger.
- Typer er definert med utgangspunkt i [kodeliste fra arkivverket](http://arkivverket.metakat.no/Diagram/Index/EAID_CC654F7F_60CA_4240_A003_B6557201F2BC)
- XSD skjema er inkludert i jar under schemas.v1. Skjema er definert i eget [repo](https://github.com/ks-no/fiks-arkiv-specification)
- Noark 5 dokumentasjon [Noark 5](https://www.arkivverket.no/forvaltning-og-utvikling/noark-standarden/noark-5/noark5-standarden)




Før bygg må submodul hentes:
```shell
git submodule  update --init --recursive --remote
```