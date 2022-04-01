package no.ks.fiks.io.arkiv.model

import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Journalpost
import java.math.BigInteger
import java.time.ZonedDateTime
import kotlin.collections.ArrayList

class JournalpostBuilder : IRegistrering {
    var journaldato: ZonedDateTime? = null
        private set
    var journalpostnummer: BigInteger? = null
        private set
    var journalsekvensnummer: BigInteger? = null
        private set
    var journalaar: Int? = null
        private set
    var systemID: SystemIDBuilder? = null
        private set
    var journalposttype: JournalPostTypeBuilder? = null
        private set
    var tittel: String? = null
        private set
    var opprettetDato: ZonedDateTime? = null
        private set
    var opprettetAv: String? = null
        private set
    var arkivertDato: ZonedDateTime? = null
        private set
    var arkivertAv: String? = null
        private set
    var referanseForelderMappe: SystemIDBuilder? = null
        private set
    var korrespondanseparts: List<KorrespondansepartBuilder>? = null
        private set
    var journalstatus: JournalstatusBuilder? = null
        private set
    var referanseEksternNoekkel: EksternNoekkelBuilder? = null
        private set

    fun systemID(systemID: SystemIDBuilder) = apply { this.systemID = systemID }
    fun journalposttype(journalposttype: JournalPostTypeBuilder) = apply { this.journalposttype = journalposttype }
    fun tittel(tittel: String) = apply { this.tittel = tittel }
    fun opprettetDato(opprettetDato: ZonedDateTime) = apply { this.opprettetDato = opprettetDato }
    fun opprettetAv(opprettetAv: String) = apply { this.opprettetAv = opprettetAv }
    fun arkivertDato(arkivertDato: ZonedDateTime) = apply { this.arkivertDato = arkivertDato }
    fun arkivertAv(arkivertAv: String) = apply { this.arkivertAv = arkivertAv }
    fun referanseForelderMappe(referanseForelderMappe: SystemIDBuilder) = apply { this.referanseForelderMappe = referanseForelderMappe }
    fun korrespondanseparts(korrespondanseparts: List<KorrespondansepartBuilder>) = apply { this.korrespondanseparts = korrespondanseparts }
    fun journalstatus(journalstatus: JournalstatusBuilder) = apply { this.journalstatus = journalstatus }
    fun referanseEksternNoekkel(referanseEksternNoekkel: EksternNoekkelBuilder) = apply { this.referanseEksternNoekkel =  referanseEksternNoekkel}
    fun journalaar(journalaar: Int) = apply { this.journalaar = journalaar }
    fun journalsekvensnummer(journalsekvensnummer: BigInteger) = apply { this.journalsekvensnummer = journalsekvensnummer }
    fun journalpostnummer(journalpostnummer: BigInteger) = apply { this.journalpostnummer = journalpostnummer }
    fun journaldato(journaldato: ZonedDateTime) = apply { this.journaldato = journaldato }

    override fun buildApiModel() : Journalpost {
        return Journalpost().also {
            it.systemID = systemID?.buildApiModel()
            it.journalposttype = journalposttype?.build()
            it.tittel = tittel
            it.korrespondanseparts.addAll(korrespondanseparts?.map { part -> part.build() }?.toCollection(ArrayList()) ?: emptyList())
            it.journalstatus = journalstatus?.build()
            it.referanseEksternNoekkel = referanseEksternNoekkel?.build()
            it.opprettetDato = opprettetDato
            it.opprettetAv = opprettetAv
            it.arkivertDato = arkivertDato
            it.arkivertAv = arkivertAv
            it.referanseForelderMappe = referanseForelderMappe?.buildApiModel()
            it.journalaar = journalaar?.toBigInteger()
            it.journalsekvensnummer = journalsekvensnummer
            it.journalpostnummer = journalpostnummer
            it.journaldato = journaldato
        }
    }
}
