package no.ks.fiks.io.arkiv.model

import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.SystemIDBuilder
import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Journalpost
import java.time.ZonedDateTime
import java.util.*
import kotlin.collections.ArrayList

class JournalpostBuilder : IRegistrering {
    var referanseArkivdel: UUID? = null
        private set
    var journaldato: ZonedDateTime = ZonedDateTime.now()
        private set
    var journalpostnummer: Long? = null
        private set
    var journalsekvensnummer: Long? = null
        private set
    var journalaar: Int = ZonedDateTime.now().year
        private set
    var systemID: SystemIDBuilder? = null
        private set
    var journalposttype: JournalpostType = JournalpostType.INNGAENDE_DOKUMENT
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
    var referanseForelderMappe: SystemIDBuilder? = null
        private set
    var korrespondanseparts: List<KorrespondansepartBuilder>? = null
        private set
    var journalstatus: JournalStatus = JournalStatus.JOURNALFORT
        private set
    var referanseEksternNoekkel: EksternNoekkelBuilder? = null
        private set
    var parts: List<ArkivmeldingPartBuilder> = emptyList()
        private set
    var beskrivelse: String? = null
        private set

    fun systemID(systemID: SystemIDBuilder) = apply { this.systemID = systemID }
    fun journalposttype(journalposttype: JournalpostType) = apply { this.journalposttype = journalposttype }
    fun tittel(tittel: String) = apply { this.tittel = tittel }
    fun opprettetDato(opprettetDato: ZonedDateTime) = apply { this.opprettetDato = opprettetDato }
    fun opprettetAv(opprettetAv: String) = apply { this.opprettetAv = opprettetAv }
    fun arkivertDato(arkivertDato: ZonedDateTime) = apply { this.arkivertDato = arkivertDato }
    fun arkivertAv(arkivertAv: String) = apply { this.arkivertAv = arkivertAv }
    fun referanseForelderMappe(referanseForelderMappe: SystemIDBuilder) = apply { if(referanseArkivdel == null) this.referanseForelderMappe = referanseForelderMappe else throw IllegalArgumentException("ReferanseForelderMappe kan ikke settes i kombinasjon med ReferanseArkivdel") }
    fun referanseArkivdel(referanseArkivdel: UUID) = apply { if(referanseForelderMappe == null) this.referanseArkivdel = referanseArkivdel else throw IllegalArgumentException("ReferanseArkivdel kan ikke settes i kombinasjon med ReferanseForelderMappe") }
    fun korrespondanseparts(korrespondanseparts: List<KorrespondansepartBuilder>) = apply { this.korrespondanseparts = korrespondanseparts }
    fun journalstatus(journalstatus: JournalStatus) = apply { this.journalstatus = journalstatus }
    fun referanseEksternNoekkel(referanseEksternNoekkel: EksternNoekkelBuilder) = apply { this.referanseEksternNoekkel =  referanseEksternNoekkel}
    fun journalaar(journalaar: Int) = apply { this.journalaar = journalaar }
    fun journalsekvensnummer(journalsekvensnummer: Long) = apply { this.journalsekvensnummer = journalsekvensnummer }
    fun journalpostnummer(journalpostnummer: Long) = apply { this.journalpostnummer = journalpostnummer }
    fun journaldato(journaldato: ZonedDateTime) = apply { this.journaldato = journaldato }
    fun parts(parts: List<ArkivmeldingPartBuilder>) = apply { this.parts = parts }
    fun beskrivelse(beskrivelse: String) = apply { this.beskrivelse = beskrivelse }

    override fun buildApiModel() : Journalpost {
        return Journalpost().also {
            it.systemID = systemID?.buildApiModel() ?: throw IllegalStateException(feilmeldingPakrevdFelt("SystemID"))
            it.tittel = checkNotNull(tittel) { feilmeldingPakrevdFelt("Tittel") }
            it.korrespondanseparts.addAll(korrespondanseparts?.map { part -> part.build() }?.toCollection(ArrayList()) ?: emptyList())
            it.referanseEksternNoekkel = referanseEksternNoekkel?.build() ?: throw IllegalStateException(feilmeldingPakrevdFelt("ReferanseEksternNoekkel"))
            it.opprettetDato = opprettetDato
            it.opprettetAv = checkNotNull(opprettetAv) { feilmeldingPakrevdFelt("OpprettetAv") }
            it.arkivertDato = arkivertDato
            it.arkivertAv = checkNotNull(arkivertAv) { feilmeldingPakrevdFelt("ArkivertAv") }
            it.referanseForelderMappe = referanseForelderMappe?.buildApiModel()
            it.referanseArkivdel = referanseArkivdel?.toString()
            it.journalaar = journalaar.toBigInteger()
            it.journalsekvensnummer = journalsekvensnummer?.toBigInteger() ?: throw IllegalStateException(feilmeldingPakrevdFelt("Journalsekvensnummer"))
            it.journalpostnummer = journalpostnummer?.toBigInteger() ?: throw IllegalStateException(feilmeldingPakrevdFelt("Journalpostnummer"))
            it.journalposttype = journalposttype.value
            it.journalstatus = journalstatus.value
            it.journaldato = journaldato
            it.parts.addAll(parts.map { p -> p.buildApiModel() })
            it.beskrivelse = beskrivelse
        }
    }

    private fun feilmeldingPakrevdFelt(prop: String) = "$prop er påkrevd felt for ${Journalpost::class.java.name}"
}
