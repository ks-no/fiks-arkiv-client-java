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
/opt/jdk-17/bin/java -ea -Didea.test.cyclic.buffer.size=1048576 -javaagent:/snap/intellij-idea-ultimate/353/lib/idea_rt.jar=43053:/snap/intellij-idea-ultimate/353/bin -Dfile.encoding=UTF-8 -classpath /snap/intellij-idea-ultimate/353/lib/idea_rt.jar:/snap/intellij-idea-ultimate/353/plugins/junit/lib/junit5-rt.jar:/snap/intellij-idea-ultimate/353/plugins/junit/lib/junit-rt.jar:/home/audun/ks/fiks-arkiv-client-java/fiks-arkiv-forenklet-arkivering/target/test-classes:/home/audun/ks/fiks-arkiv-client-java/fiks-arkiv-forenklet-arkivering/target/classes:/home/audun/ks/fiks-arkiv-client-java/fiks-arkiv-api/target/classes:/home/audun/.m2/repository/org/codehaus/mojo/jaxb2-maven-plugin/2.5.0/jaxb2-maven-plugin-2.5.0.jar:/home/audun/.m2/repository/org/glassfish/jaxb/jaxb-xjc/2.3.2/jaxb-xjc-2.3.2.jar:/home/audun/.m2/repository/org/glassfish/jaxb/jaxb-runtime/2.3.2/jaxb-runtime-2.3.2.jar:/home/audun/.m2/repository/org/jvnet/staxex/stax-ex/1.8.1/stax-ex-1.8.1.jar:/home/audun/.m2/repository/com/sun/xml/fastinfoset/FastInfoset/1.2.16/FastInfoset-1.2.16.jar:/home/audun/.m2/repository/org/glassfish/jaxb/xsom/2.3.2/xsom-2.3.2.jar:/home/audun/.m2/repository/org/glassfish/jaxb/codemodel/2.3.2/codemodel-2.3.2.jar:/home/audun/.m2/repository/com/sun/xml/bind/external/rngom/2.3.2/rngom-2.3.2.jar:/home/audun/.m2/repository/com/sun/xml/dtd-parser/dtd-parser/1.4.1/dtd-parser-1.4.1.jar:/home/audun/.m2/repository/com/sun/istack/istack-commons-tools/3.0.8/istack-commons-tools-3.0.8.jar:/home/audun/.m2/repository/org/apache/ant/ant/1.10.5/ant-1.10.5.jar:/home/audun/.m2/repository/org/apache/ant/ant-launcher/1.10.5/ant-launcher-1.10.5.jar:/home/audun/.m2/repository/com/sun/xml/bind/external/relaxng-datatype/2.3.2/relaxng-datatype-2.3.2.jar:/home/audun/.m2/repository/org/glassfish/jaxb/jaxb-jxc/2.3.2/jaxb-jxc-2.3.2.jar:/home/audun/.m2/repository/org/glassfish/jaxb/txw2/2.3.2/txw2-2.3.2.jar:/home/audun/.m2/repository/com/thoughtworks/qdox/qdox/2.0-M10/qdox-2.0-M10.jar:/home/audun/.m2/repository/org/apache/maven/maven-plugin-api/3.0.5/maven-plugin-api-3.0.5.jar:/home/audun/.m2/repository/org/sonatype/sisu/sisu-inject-plexus/2.3.0/sisu-inject-plexus-2.3.0.jar:/home/audun/.m2/repository/org/sonatype/sisu/sisu-inject-bean/2.3.0/sisu-inject-bean-2.3.0.jar:/home/audun/.m2/repository/org/sonatype/sisu/sisu-guice/3.1.0/sisu-guice-3.1.0-no_aop.jar:/home/audun/.m2/repository/org/sonatype/sisu/sisu-guava/0.9.9/sisu-guava-0.9.9.jar:/home/audun/.m2/repository/org/apache/maven/maven-core/3.0.5/maven-core-3.0.5.jar:/home/audun/.m2/repository/org/apache/maven/maven-settings/3.0.5/maven-settings-3.0.5.jar:/home/audun/.m2/repository/org/apache/maven/maven-settings-builder/3.0.5/maven-settings-builder-3.0.5.jar:/home/audun/.m2/repository/org/apache/maven/maven-repository-metadata/3.0.5/maven-repository-metadata-3.0.5.jar:/home/audun/.m2/repository/org/apache/maven/maven-model-builder/3.0.5/maven-model-builder-3.0.5.jar:/home/audun/.m2/repository/org/apache/maven/maven-aether-provider/3.0.5/maven-aether-provider-3.0.5.jar:/home/audun/.m2/repository/org/sonatype/aether/aether-spi/1.13.1/aether-spi-1.13.1.jar:/home/audun/.m2/repository/org/sonatype/aether/aether-impl/1.13.1/aether-impl-1.13.1.jar:/home/audun/.m2/repository/org/sonatype/aether/aether-api/1.13.1/aether-api-1.13.1.jar:/home/audun/.m2/repository/org/sonatype/aether/aether-util/1.13.1/aether-util-1.13.1.jar:/home/audun/.m2/repository/org/codehaus/plexus/plexus-interpolation/1.14/plexus-interpolation-1.14.jar:/home/audun/.m2/repository/org/codehaus/plexus/plexus-classworlds/2.4/plexus-classworlds-2.4.jar:/home/audun/.m2/repository/org/codehaus/plexus/plexus-component-annotations/1.5.5/plexus-component-annotations-1.5.5.jar:/home/audun/.m2/repository/org/sonatype/plexus/plexus-sec-dispatcher/1.3/plexus-sec-dispatcher-1.3.jar:/home/audun/.m2/repository/org/sonatype/plexus/plexus-cipher/1.4/plexus-cipher-1.4.jar:/home/audun/.m2/repository/org/apache/maven/maven-artifact/3.0.5/maven-artifact-3.0.5.jar:/home/audun/.m2/repository/org/apache/maven/maven-model/3.0.5/maven-model-3.0.5.jar:/home/audun/.m2/repository/org/apache/maven/skins/maven-fluido-skin/1.6/maven-fluido-skin-1.6.jar:/home/audun/.m2/repository/org/codehaus/plexus/plexus-compiler-api/2.5/plexus-compiler-api-2.5.jar:/home/audun/.m2/repository/org/codehaus/plexus/plexus-utils/3.1.0/plexus-utils-3.1.0.jar:/home/audun/.m2/repository/org/sonatype/plexus/plexus-build-api/0.0.7/plexus-build-api-0.0.7.jar:/home/audun/.m2/repository/com/sun/istack/istack-commons-runtime/3.0.8/istack-commons-runtime-3.0.8.jar:/home/audun/.m2/repository/jakarta/activation/jakarta.activation-api/1.2.1/jakarta.activation-api-1.2.1.jar:/home/audun/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib-jdk8/1.6.10/kotlin-stdlib-jdk8-1.6.10.jar:/home/audun/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib/1.6.10/kotlin-stdlib-1.6.10.jar:/home/audun/.m2/repository/org/jetbrains/annotations/13.0/annotations-13.0.jar:/home/audun/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib-jdk7/1.6.10/kotlin-stdlib-jdk7-1.6.10.jar:/home/audun/.m2/repository/org/jetbrains/kotlin/kotlin-reflect/1.6.10/kotlin-reflect-1.6.10.jar:/home/audun/.m2/repository/io/github/microutils/kotlin-logging-jvm/2.1.21/kotlin-logging-jvm-2.1.21.jar:/home/audun/.m2/repository/org/slf4j/slf4j-api/1.7.32/slf4j-api-1.7.32.jar:/home/audun/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib-common/1.6.10/kotlin-stdlib-common-1.6.10.jar:/home/audun/.m2/repository/javax/xml/bind/jaxb-api/2.3.1/jaxb-api-2.3.1.jar:/home/audun/.m2/repository/javax/activation/javax.activation-api/1.2.0/javax.activation-api-1.2.0.jar:/home/audun/.m2/repository/com/sun/xml/bind/jaxb-impl/3.0.0/jaxb-impl-3.0.0.jar:/home/audun/.m2/repository/com/sun/xml/bind/jaxb-core/3.0.0/jaxb-core-3.0.0.jar:/home/audun/.m2/repository/jakarta/xml/bind/jakarta.xml.bind-api/3.0.0/jakarta.xml.bind-api-3.0.0.jar:/home/audun/.m2/repository/com/sun/activation/jakarta.activation/2.0.0/jakarta.activation-2.0.0.jar:/home/audun/.m2/repository/org/junit/jupiter/junit-jupiter/5.8.2/junit-jupiter-5.8.2.jar:/home/audun/.m2/repository/org/junit/jupiter/junit-jupiter-api/5.8.2/junit-jupiter-api-5.8.2.jar:/home/audun/.m2/repository/org/opentest4j/opentest4j/1.2.0/opentest4j-1.2.0.jar:/home/audun/.m2/repository/org/junit/platform/junit-platform-commons/1.8.2/junit-platform-commons-1.8.2.jar:/home/audun/.m2/repository/org/apiguardian/apiguardian-api/1.1.2/apiguardian-api-1.1.2.jar:/home/audun/.m2/repository/org/junit/jupiter/junit-jupiter-params/5.8.2/junit-jupiter-params-5.8.2.jar:/home/audun/.m2/repository/org/junit/jupiter/junit-jupiter-engine/5.8.2/junit-jupiter-engine-5.8.2.jar:/home/audun/.m2/repository/ch/qos/logback/logback-classic/1.2.10/logback-classic-1.2.10.jar:/home/audun/.m2/repository/ch/qos/logback/logback-core/1.2.10/logback-core-1.2.10.jar:/home/audun/.m2/repository/io/kotest/kotest-runner-junit5-jvm/5.0.0/kotest-runner-junit5-jvm-5.0.0.jar:/home/audun/.m2/repository/io/kotest/kotest-framework-api-jvm/5.0.0/kotest-framework-api-jvm-5.0.0.jar:/home/audun/.m2/repository/org/jetbrains/kotlinx/kotlinx-coroutines-test/1.5.2/kotlinx-coroutines-test-1.5.2.jar:/home/audun/.m2/repository/io/kotest/kotest-common-jvm/5.0.0/kotest-common-jvm-5.0.0.jar:/home/audun/.m2/repository/io/kotest/kotest-framework-engine-jvm/5.0.0/kotest-framework-engine-jvm-5.0.0.jar:/home/audun/.m2/repository/io/github/classgraph/classgraph/4.8.135/classgraph-4.8.135.jar:/home/audun/.m2/repository/com/github/ajalt/mordant/1.2.1/mordant-1.2.1.jar:/home/audun/.m2/repository/com/github/ajalt/colormath/1.2.0/colormath-1.2.0.jar:/home/audun/.m2/repository/org/jetbrains/kotlinx/kotlinx-coroutines-debug/1.5.2/kotlinx-coroutines-debug-1.5.2.jar:/home/audun/.m2/repository/net/java/dev/jna/jna/5.5.0/jna-5.5.0.jar:/home/audun/.m2/repository/net/java/dev/jna/jna-platform/5.5.0/jna-platform-5.5.0.jar:/home/audun/.m2/repository/io/kotest/kotest-framework-discovery-jvm/5.0.0/kotest-framework-discovery-jvm-5.0.0.jar:/home/audun/.m2/repository/io/kotest/kotest-extensions-jvm/5.0.0/kotest-extensions-jvm-5.0.0.jar:/home/audun/.m2/repository/commons-io/commons-io/2.11.0/commons-io-2.11.0.jar:/home/audun/.m2/repository/io/mockk/mockk/1.12.1/mockk-1.12.1.jar:/home/audun/.m2/repository/io/mockk/mockk-dsl-jvm/1.12.1/mockk-dsl-jvm-1.12.1.jar:/home/audun/.m2/repository/io/mockk/mockk-dsl/1.12.1/mockk-dsl-1.12.1.jar:/home/audun/.m2/repository/io/mockk/mockk-common/1.12.1/mockk-common-1.12.1.jar:/home/audun/.m2/repository/io/mockk/mockk-agent-jvm/1.12.1/mockk-agent-jvm-1.12.1.jar:/home/audun/.m2/repository/io/mockk/mockk-agent-api/1.12.1/mockk-agent-api-1.12.1.jar:/home/audun/.m2/repository/io/mockk/mockk-agent-common/1.12.1/mockk-agent-common-1.12.1.jar:/home/audun/.m2/repository/org/objenesis/objenesis/3.1/objenesis-3.1.jar:/home/audun/.m2/repository/net/bytebuddy/byte-buddy/1.11.5/byte-buddy-1.11.5.jar:/home/audun/.m2/repository/net/bytebuddy/byte-buddy-agent/1.11.5/byte-buddy-agent-1.11.5.jar:/home/audun/.m2/repository/io/kotest/kotest-framework-concurrency-jvm/5.0.0/kotest-framework-concurrency-jvm-5.0.0.jar:/home/audun/.m2/repository/org/jetbrains/kotlinx/kotlinx-coroutines-core-jvm/1.5.2/kotlinx-coroutines-core-jvm-1.5.2.jar:/home/audun/.m2/repository/org/junit/platform/junit-platform-engine/1.8.2/junit-platform-engine-1.8.2.jar:/home/audun/.m2/repository/org/junit/platform/junit-platform-suite-api/1.8.2/junit-platform-suite-api-1.8.2.jar:/home/audun/.m2/repository/org/junit/platform/junit-platform-launcher/1.8.2/junit-platform-launcher-1.8.2.jar:/home/audun/.m2/repository/io/kotest/kotest-property-jvm/5.0.0/kotest-property-jvm-5.0.0.jar:/home/audun/.m2/repository/io/kotest/kotest-assertions-shared-jvm/5.0.0/kotest-assertions-shared-jvm-5.0.0.jar:/home/audun/.m2/repository/io/github/java-diff-utils/java-diff-utils/4.11/java-diff-utils-4.11.jar:/home/audun/.m2/repository/com/github/curious-odd-man/rgxgen/1.3/rgxgen-1.3.jar:/home/audun/.m2/repository/io/kotest/kotest-assertions-core-jvm/5.0.0/kotest-assertions-core-jvm-5.0.0.jar:/home/audun/.m2/repository/io/kotest/kotest-assertions-api-jvm/5.0.0/kotest-assertions-api-jvm-5.0.0.jar:/home/audun/.m2/repository/org/jetbrains/kotlinx/kotlinx-coroutines-jdk8/1.5.2/kotlinx-coroutines-jdk8-1.5.2.jar com.intellij.rt.junit.JUnitStarter -ideVersion5 -junit5 no.ks.fiks.io.arkiv.model.ArkivmeldingJavaTest,Brukerhistorie4ProAktivTest
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