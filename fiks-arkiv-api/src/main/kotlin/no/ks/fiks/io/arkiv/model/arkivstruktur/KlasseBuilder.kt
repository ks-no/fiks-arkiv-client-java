package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.SystemIDBuilder
import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Klasse
import java.time.ZonedDateTime

open class KlasseBuilder {
    var systemID: SystemIDBuilder? = null
        private set
    var klasseID: String? = null
        private set
    var tittel: String? = null
        private set
    var beskrivelse: String? = null
        private set
    var nokkelOrd: List<String> = ArrayList()
        private set
    var opprettetDato: ZonedDateTime = ZonedDateTime.now()
        private set
    var opprettetAv: String? = null
        private set
    var avsluttetDato: ZonedDateTime? = null
        private set
    var avsluttetAv: String? = null
        private set
    var kryssreferanse: List<KryssreferanseBuilder> = ArrayList()
        private set
    var kassasjon: KassasjonBuilder? = null
        private set
    var gradering: GraderingBuilder? = null
        private set

    fun systemID(systemID: SystemIDBuilder) = apply { this.systemID = systemID }
    fun klasseID(klasseID: String) = apply { this.klasseID = klasseID }
    fun tittel(tittel: String) = apply { this.tittel = tittel }
    fun beskrivelse(beskrivelse: String) = apply { this.beskrivelse = beskrivelse }
    fun nokkelOrd(nokkelOrd: List<String>) = apply { this.nokkelOrd = nokkelOrd }
    fun opprettetDato(opprettetDato: ZonedDateTime) = apply { this.opprettetDato = opprettetDato }
    fun opprettetAv(opprettetAv: String) = apply { this.opprettetAv = opprettetAv }
    fun avsluttetDato(avsluttetDato: ZonedDateTime) = apply { this.avsluttetDato = avsluttetDato }
    fun avsluttetAv(avsluttetAv: String) = apply { this.avsluttetAv = avsluttetAv }
    fun kryssreferanse(kryssreferanse: List<KryssreferanseBuilder>) = apply { this.kryssreferanse = kryssreferanse }
    fun kassasjon(kassasjon: KassasjonBuilder) = apply { this.kassasjon = kassasjon }
    fun gradering(gradering: GraderingBuilder) = apply { this.gradering = gradering }

    open fun build() : Klasse {
        return Klasse().also {
            it.systemID = systemID?.build() ?: throw IllegalStateException("SystemID er påkrevd for Klasse")
            it.klasseID = checkNotNull(klasseID) {"KlasseID er opåkrevd for Klasse"}
            it.tittel = checkNotNull(tittel) {"Tittel er påkrevd for Klasse"}
            it.beskrivelse = beskrivelse
            it.noekkelords.addAll(nokkelOrd)
            it.opprettetDato = opprettetDato
            it.opprettetAv = checkNotNull(opprettetAv) {"OpprettetAv er påkrevd for Klasse"}
            it.avsluttetDato = avsluttetDato
            it.avsluttetAv = avsluttetAv
            it.kryssreferanses.addAll(kryssreferanse.map { k -> k.build() }.toList())
            it.kassasjon = kassasjon?.build()
            it.gradering = gradering?.build()
        }
    }
}
