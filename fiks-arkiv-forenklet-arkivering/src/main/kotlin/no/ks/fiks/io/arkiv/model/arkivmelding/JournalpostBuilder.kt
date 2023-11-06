package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.opprett.*
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.EksternNoekkel
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.Kode
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.ReferanseTilMappe
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.SystemID
import no.ks.fiks.io.arkiv.model.arkivstruktur.*
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.*
import java.time.LocalDate
import java.time.ZonedDateTime
import java.util.*
import kotlin.collections.ArrayList

/**
 * Journalpost er en spesialisering av registrering og brukes for hendelser som skal inn i journalen, en oversikt over all korrespondanse til og fra organisasjonen, samt interne notater og rapporter. Oftest vil det kun være journalposter registrert i saksmapper.
 * @see http://arkivverket.metakat.no/Objekttype/Index/EAID_EA66ABBF_7124_485c_AB57_BC5159EB8F56
 */
open class JournalpostBuilder : IRegistrering {
    var avskrivningsdato: LocalDate? = null
        private set
    var arkivdel: Kode? = null
        private set
    var journaldato: LocalDate = LocalDate.now()
        private set
    var journalpostnummer: Int? = null
        private set
    var journalsekvensnummer: Int? = null
        private set
    var journalaar: Int = ZonedDateTime.now().year
        private set
    var systemID: SystemID? = null
        private set
    var journalposttype: JournalpostType? = null
        private set
    var tittel: String? = null
        private set
    var opprettetDato: ZonedDateTime = ZonedDateTime.now()
        private set
    var opprettetAv: String? = null
        private set
    var arkivertDato: ZonedDateTime = ZonedDateTime.now()
        private set
    var arkivertAv: String? = null
        private set
    var referanseForelderMappe: ReferanseTilMappe? = null
        private set
    var korrespondanseparts: List<Korrespondansepart> = ArrayList()
        private set
    var journalstatus: JournalStatus? = null
        private set
    var referanseEksternNoekkel: EksternNoekkel? = null
        private set
    var parts: List<Part> = ArrayList()
        private set
    var beskrivelse: String? = null
        private set
    var dokumentetsDato: LocalDate? = null
        private set
    var mottattDato: ZonedDateTime? = null
        private set
    var sendtDato: ZonedDateTime? = null
        private set
    var forfallsdato: LocalDate? = null
        private set
    var offentlighetsvurdertDato: LocalDate? = null
        private set
    var antallVedlegg: Int? = null
        private set
    var utlaantDato: LocalDate? = null
        private set
    var utlaantTil: String? = null
        private set
    var journalenhet: String? = null
        private set
    var dokumentflyt: List<Dokumentflyt> = ArrayList()
        private set
    var presedens: List<Presedens> = ArrayList()
        private set
    var klassifikasjoner: List<Klassifikasjon> = ArrayList()
        private set
    var kryssreferanser: List<Kryssreferanse> = ArrayList()
        private set
    var merknader: List<Merknad> = ArrayList()
        private set
    var oppbevaringssteder: List<String> = ArrayList()
        private set
    var dokumentmedium: DokumentmediumType? = null
        private set
    var forfattere: List<String> = ArrayList()
        private set
    var nokkelord: List<String> = ArrayList()
        private set
    var offentligTittel: String? = null
        private set
    var registreringsID: String? = null
        private set
    var dokumentbeskrivelser: List<Dokumentbeskrivelse> = ArrayList()
        private set
    var gradering: Gradering? = null
        private set
    var skjerming: Skjerming? = null
        private set

    fun avskrivningsdato(avskrivningsdato: LocalDate) = apply { this.avskrivningsdato =  avskrivningsdato }
    fun systemID(systemID: SystemID) = apply { this.systemID = systemID }
    fun journalposttype(journalposttype: JournalpostType) = apply { this.journalposttype = journalposttype }
    fun tittel(tittel: String) = apply { this.tittel = tittel }
    fun opprettetDato(opprettetDato: ZonedDateTime) = apply { this.opprettetDato = opprettetDato }
    fun opprettetAv(opprettetAv: String) = apply { this.opprettetAv = opprettetAv }
    fun arkivertDato(arkivertDato: ZonedDateTime) = apply { this.arkivertDato = arkivertDato }
    fun arkivertAv(arkivertAv: String) = apply { this.arkivertAv = arkivertAv }
    fun referanseForelderMappe(referanseForelderMappe: ReferanseTilMappe) = apply { if(arkivdel == null) this.referanseForelderMappe = referanseForelderMappe else throw IllegalArgumentException("ReferanseForelderMappe kan ikke settes i kombinasjon med ReferanseArkivdel") }
    fun arkivdel(arkivdel: Kode) = apply { if(referanseForelderMappe == null) this.arkivdel = arkivdel else throw IllegalArgumentException("ReferanseArkivdel kan ikke settes i kombinasjon med ReferanseForelderMappe") }
    fun korrespondanseparts(korrespondanseparts: List<Korrespondansepart>) = apply { this.korrespondanseparts = korrespondanseparts }
    fun journalstatus(journalstatus: JournalStatus) = apply { this.journalstatus = journalstatus }
    fun referanseEksternNoekkel(referanseEksternNoekkel: EksternNoekkel) = apply { this.referanseEksternNoekkel =  referanseEksternNoekkel}

    /**
     * Viser året journalposten ble opprettet Kilde: Registreres automatisk når journalposten opprettes Kommentar: (ingen) M013 journalaar
     */
    fun journalaar(journalaar: Int) = apply { this.journalaar = journalaar }
    fun journalsekvensnummer(journalsekvensnummer: Int) = apply { this.journalsekvensnummer = journalsekvensnummer }
    fun journalpostnummer(journalpostnummer: Int) = apply { this.journalpostnummer = journalpostnummer }
    fun journaldato(journaldato: LocalDate) = apply { this.journaldato = journaldato }
    fun parts(parts: List<Part>) = apply { this.parts = parts }
    fun beskrivelse(beskrivelse: String) = apply { this.beskrivelse = beskrivelse }
    fun dokumentetsDato(dokumentetsDato: LocalDate) = apply { this.dokumentetsDato = dokumentetsDato }
    fun mottattDato(mottattDato: ZonedDateTime) = apply { this.mottattDato = mottattDato }
    fun sendtDato(sendtDato: ZonedDateTime) = apply { this.sendtDato = sendtDato }
    fun forfallsdato(forfallsdato: LocalDate) = apply { this.forfallsdato = forfallsdato }
    fun offentlighetsvurdertDato(offentlighetsvurdertDato: LocalDate) = apply { this.offentlighetsvurdertDato = offentlighetsvurdertDato }
    fun antallVedlegg(antallVedlegg: Int) = apply { this.antallVedlegg = antallVedlegg }
    fun utlaantDato(utlaantDato: LocalDate) = apply { this.utlaantDato = utlaantDato }
    fun utlaantTil(utlaantTil: String) = apply { this.utlaantTil = utlaantTil }
    fun journalenhet(journalenhet: String) = apply { this.journalenhet = journalenhet }
    fun dokumentflyt(dokumentflyt: List<Dokumentflyt>) = apply { this.dokumentflyt = dokumentflyt }
    fun presedens(presedens: List<Presedens>) = apply { this.presedens = presedens }
    fun klassifikasjoner(klassifikasjoner: List<Klassifikasjon>) = apply { this.klassifikasjoner = klassifikasjoner }
    fun kryssreferanser(kryssreferanser: List<Kryssreferanse>) = apply { this.kryssreferanser = kryssreferanser }
    fun merknader(merknader: List<Merknad>) = apply { this.merknader = merknader }
    fun oppbevaringssteder(oppbevaringssteder: List<String>) = apply { this.oppbevaringssteder = oppbevaringssteder }
    fun dokumentmedium(dokumentmedium: DokumentmediumType) = apply { this.dokumentmedium = dokumentmedium }
    fun forfattere(forfattere: List<String>) = apply { this.forfattere = forfattere }
    fun nokkelord(nokkelord: List<String>) = apply { this.nokkelord = nokkelord }
    fun offentligTittel(offentligTittel: String) = apply { this.offentligTittel = offentligTittel }
    fun registreringsID(registreringsID: String) = apply { this.registreringsID = registreringsID }
    fun dokumentbeskrivelser(dokumentbeskrivelser: List<Dokumentbeskrivelse>) = apply { this.dokumentbeskrivelser = dokumentbeskrivelser }
    fun gradering(gradering: Gradering) = apply { this.gradering = gradering }
    fun skjerming(skjerming: Skjerming) = apply { this.skjerming = skjerming }

    override fun build() : Journalpost {
        return Journalpost().also {
            it.avskrivningsdato = avskrivningsdato ?: throw IllegalStateException(feilmeldingPakrevdFelt("avskrivningsdato"))
            it.systemID = systemID
            it.opprettetDato = opprettetDato
            it.opprettetAv = opprettetAv
            it.arkivertDato = arkivertDato
            it.arkivertAv = arkivertAv
            it.referanseForelderMappe = referanseForelderMappe
            it.arkivdel = arkivdel
            it.journalsekvensnummer = journalsekvensnummer
            it.parts.addAll(parts)

            it.skjerming = skjerming
            it.gradering = gradering
            it.dokumentbeskrivelses.addAll(dokumentbeskrivelser)
            it.registreringsID = registreringsID
            it.tittel = checkNotNull(tittel) { feilmeldingPakrevdFelt("Tittel") }
            it.offentligTittel = offentligTittel
            it.beskrivelse = beskrivelse
            it.noekkelords.addAll(nokkelord)
            it.forfatters.addAll(forfattere)
            it.dokumentmedium = dokumentmedium?.value
            it.merknads.addAll(merknader)
            it.kryssreferanses.addAll(kryssreferanser)
            it.korrespondanseparts.addAll(korrespondanseparts )
            it.klassifikasjons.addAll(klassifikasjoner)
            it.referanseEksternNoekkel = referanseEksternNoekkel ?: throw IllegalStateException(feilmeldingPakrevdFelt("ReferanseEksternNoekkel"))

            it.journalaar = journalaar
            it.journalpostnummer = journalpostnummer
            it.journalposttype = journalposttype?.value ?: throw IllegalStateException(feilmeldingPakrevdFelt("Journalposttype"))
            it.journalstatus = journalstatus?.value ?: throw IllegalStateException(feilmeldingPakrevdFelt("Journalstatus"))
            it.journaldato = journaldato
            it.dokumentetsDato = dokumentetsDato
            it.mottattDato = mottattDato
            it.sendtDato = sendtDato
            it.forfallsdato = forfallsdato
            it.offentlighetsvurdertDato = offentlighetsvurdertDato
            it.antallVedlegg = antallVedlegg
            it.journalenhet = journalenhet
            it.dokumentflyts.addAll(dokumentflyt)
            it.presedens.addAll(presedens)
        }
    }

    private fun feilmeldingPakrevdFelt(prop: String) = "$prop er påkrevd felt for ${Journalpost::class.java.name}"
}
