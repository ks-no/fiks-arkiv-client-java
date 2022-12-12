package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.Gradering
import no.ks.fiks.arkiv.v1.arkivmelding.Kassasjon
import no.ks.fiks.arkiv.v1.arkivmelding.Klasse
import no.ks.fiks.arkiv.v1.arkivmelding.Kryssreferanse
import java.time.ZonedDateTime

open class KlasseBuilder {
    var klasseID: String? = null
        private set
    var tittel: String? = null
        private set
    var beskrivelse: String? = null
        private set
    var nokkelOrd: List<String> = ArrayList()
        private set
    var opprettetDato: ZonedDateTime? = null
        private set
    var opprettetAv: String? = null
        private set
    var avsluttetDato: ZonedDateTime? = null
        private set
    var avsluttetAv: String? = null
        private set
    var kryssreferanse: List<Kryssreferanse> = ArrayList()
        private set
    var kassasjon: Kassasjon? = null
        private set
    var gradering: Gradering? = null
        private set

    fun klasseID(klasseID: String) = apply { this.klasseID = klasseID }
    fun tittel(tittel: String) = apply { this.tittel = tittel }
    fun beskrivelse(beskrivelse: String) = apply { this.beskrivelse = beskrivelse }
    fun nokkelOrd(nokkelOrd: List<String>) = apply { this.nokkelOrd = nokkelOrd }
    fun opprettetDato(opprettetDato: ZonedDateTime) = apply { this.opprettetDato = opprettetDato }
    fun opprettetAv(opprettetAv: String) = apply { this.opprettetAv = opprettetAv }
    fun avsluttetDato(avsluttetDato: ZonedDateTime) = apply { this.avsluttetDato = avsluttetDato }
    fun avsluttetAv(avsluttetAv: String) = apply { this.avsluttetAv = avsluttetAv }
    fun kryssreferanse(kryssreferanse: List<Kryssreferanse>) = apply { this.kryssreferanse = kryssreferanse }
    fun kassasjon(kassasjon: Kassasjon) = apply { this.kassasjon = kassasjon }
    fun gradering(gradering: Gradering) = apply { this.gradering = gradering }

    open fun build() : Klasse {
        return Klasse().also {
            it.klasseID = klasseID
            it.tittel = checkNotNull(tittel) {"Tittel er påkrevd for Klasse"}
            it.beskrivelse = beskrivelse
            it.noekkelords.addAll(nokkelOrd)
            it.opprettetDato = opprettetDato
            it.opprettetAv = opprettetAv
            it.avsluttetDato = avsluttetDato
            it.avsluttetAv = avsluttetAv
            it.kryssreferanses.addAll(kryssreferanse)
            it.kassasjon = kassasjon
            it.gradering = gradering
        }
    }
}
