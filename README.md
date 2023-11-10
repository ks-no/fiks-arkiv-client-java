# fiks-arkiv-client-java
[![MIT Licens](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/ks-no/fiks-arkiv-client-java/blob/master/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/no.ks.fiks/fiks-arkiv-api.svg)](https://search.maven.org/search?q=g:no.ks.fiks%20a:fiks-arkiv-api)
![GitHub last commit](https://img.shields.io/github/last-commit/ks-no/fiks-arkiv-client-java.svg)
![GitHub Release Date](https://img.shields.io/github/release-date/ks-no/fiks-arkiv-client-java.svg)

# Beskrivelse
Kotlin klient for generering av meldinger til [fiks arkiv protokoll](https://www.ks.no/fagomrader/digitalisering/felleslosninger/verktoykasse-plan--og-byggesak/verktoy/sammenhengende-tjenester---integrasjoner/fiks-arkiv/), 
[tjenestedokumentasjon](https://ks-no.github.io/fiks-plattform/tjenester/fiksio/arkiv/)

* Modul fiks-arkiv-api inneholder autogenerert typer, definert i arkiv spesifikasjon [repo](https://github.com/ks-no/fiks-arkiv-specification).
* Modul fiks-arkiv-forenklet-arkivering inneholder buildere for forenklet oppbygging av gyldig arkivmelding. Se [ArkivmeldingBuilder](https://github.com/ks-no/fiks-arkiv-client-java/blob/main/fiks-arkiv-forenklet-arkivering/src/main/kotlin/no/ks/fiks/io/arkiv/model/arkivmelding/ArkivmeldingBuilder.kt)
  * Builder klasser for generering av gyldig arkivmelding
  * ArkivmeldingForenkletUtgaaende bygger på builder struktur, men med utvidet bruk av default verdier for utgående arkivmelding
  * Kodetyper er definert i (no.ks.fiks.io.arkiv.model.metadatakatalog.v2) med utgangspunkt i [kodeliste fra arkivverket](http://arkivverket.metakat.no/Diagram/Index/EAID_CC654F7F_60CA_4240_A003_B6557201F2BC)
  
* XSD skjema er inkludert i jar under schemas.v1. Skjema er definert i eget [repo](https://github.com/ks-no/fiks-arkiv-specification)
* Noark 5 dokumentasjon [Noark 5](https://www.arkivverket.no/forvaltning-og-utvikling/noark-standarden/noark-5/noark5-standarden)

# Brukstilfeller
Ref [Isy Proaktiv](https://github.com/ks-no/fiks-arkiv/wiki/Brukstilfeller#4-isy-proaktiv) er det laget forenklet modell som bygger ArkivmeldingBuilder, se [ArkivmeldingForenkletUtgaaende](https://github.com/ks-no/fiks-arkiv-client-java/blob/main/fiks-arkiv-forenklet-arkivering/src/main/kotlin/no/ks/fiks/io/arkiv/model/forenklet/ArkivmeldingForenkletUtgaaende.kt)
Eks:
```java
SaksbehandlerBuilder saksbehandlerBuilder = new SaksbehandlerBuilder();
saksbehandlerBuilder.navn("Birger Brannmann");
ArkivmeldingBuilder arkivmelding = new ArkivmeldingForenkletUtgaaende()
    .sluttbrukerIdentifikator("ABC")
    .nyUtgaaendeJournalpost(new UtgaaendeJournalpost()
        .avskrivningsdato(LocalDate.now())
        .tittel("Vedtak etter tilsyn")
        .referanseEksternNoekkelForenklet(new EksternNoekkelForenklet()
            .noekkel(UUID.randomUUID().toString())
            .fagstystem("Fagsystem X"))
        .internAvsender(Collections.singletonList(new KorrespondansepartIntern()
            .saksbehandler(saksbehandlerBuilder.build())
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
        .forsendelsemaate("SvarUt")))
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
<arkivmelding xmlns="https://ks-no.github.io/standarder/fiks-protokoll/fiks-arkiv/arkivmelding/v1" xmlns:ns2="https://ks-no.github.io/standarder/fiks-protokoll/fiks-arkiv/metadatakatalog/v1">
  <system>Fagsystem X</system>
  <antallFiler>1</antallFiler>
  <mappe xsi:type="saksmappe" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <tittel>Tilsyn eiendom 21/400</tittel>
    <registrering xsi:type="journalpost">
      <opprettetDato>2022-12-12T13:03:31.963264497+01:00</opprettetDato>
      <opprettetAv>ABC</opprettetAv>
      <arkivertDato>2022-12-12T13:03:31.963274171+01:00</arkivertDato>
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
        <opprettetDato>2022-12-12T13:03:31.966366756+01:00</opprettetDato>
        <tilknyttetRegistreringSom>
          <ns2:kode>H</ns2:kode>
          <ns2:beskrivelse>Hoveddokument</ns2:beskrivelse>
        </tilknyttetRegistreringSom>
        <tilknyttetDato>2022-12-12T13:03:31.96640352+01:00</tilknyttetDato>
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
          <opprettetDato>2022-12-12T13:03:31.968602287+01:00</opprettetDato>
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
        <erBehandlingsansvarlig>false</erBehandlingsansvarlig>
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
        <erBehandlingsansvarlig>false</erBehandlingsansvarlig>
        <korrespondansepartNavn>Birger Brannmann</korrespondansepartNavn>
        <saksbehandler>
          <ns2:navn>Birger Brannmann</ns2:navn>
        </saksbehandler>
      </korrespondansepart>
      <referanseEksternNoekkel>
        <ns2:fagsystem>Fagsystem X</ns2:fagsystem>
        <ns2:noekkel>1ae14424-e951-43e2-8110-4f2329944804</ns2:noekkel>
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
      <journaldato>2022-12-12</journaldato>
      <antallVedlegg>1</antallVedlegg>
      <avskrivningsdato>2022-12-12</avskrivningsdato>
    </registrering>
  </mappe>
</arkivmelding>

```

Før bygg må submodul hentes:
```shell
git submodule  update --init --recursive --remote
```
