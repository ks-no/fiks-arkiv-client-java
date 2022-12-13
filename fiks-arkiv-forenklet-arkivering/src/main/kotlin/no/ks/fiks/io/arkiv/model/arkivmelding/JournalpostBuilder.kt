package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.*
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.EksternNoekkel
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
open class JournalpostBuilder : IRegistrering<Journalpost>() {
    var journalaar: Int = ZonedDateTime.now().year
        private set
    var journalsekvensnummer: Int? = null
        private set
    var journalpostnummer: Int? = null
        private set
    var journalposttype: JournalpostType? = null
        private set
    var journalstatus: JournalStatus? = null
        private set
    var journaldato: LocalDate = LocalDate.now()
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
    var avskrivningsdato: LocalDate? = null
        private set

    fun avskrivningsdato(avskrivningsdato: LocalDate) = apply { this.avskrivningsdato =  avskrivningsdato }
    fun journalposttype(journalposttype: JournalpostType) = apply { this.journalposttype = journalposttype }
    fun journalstatus(journalstatus: JournalStatus) = apply { this.journalstatus = journalstatus }
    fun journalaar(journalaar: Int) = apply { this.journalaar = journalaar }
    fun journalsekvensnummer(journalsekvensnummer: Int) = apply { this.journalsekvensnummer = journalsekvensnummer }
    fun journalpostnummer(journalpostnummer: Int) = apply { this.journalpostnummer = journalpostnummer }
    fun journaldato(journaldato: LocalDate) = apply { this.journaldato = journaldato }
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

    override fun build() : Journalpost {
        return Journalpost().also {
            build(it)
            it.journalaar = journalaar
            it.journalsekvensnummer = journalsekvensnummer
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
            it.utlaantDato = utlaantDato
            it.utlaantTil = utlaantTil
            it.journalenhet = journalenhet
            it.dokumentflyts.addAll(dokumentflyt)
            it.presedens.addAll(presedens)
            it.avskrivningsdato = avskrivningsdato ?: throw IllegalStateException(feilmeldingPakrevdFelt("avskrivningsdato"))

        }
    }

    private fun feilmeldingPakrevdFelt(prop: String) = "$prop er påkrevd felt for ${Journalpost::class.java.name}"
}
