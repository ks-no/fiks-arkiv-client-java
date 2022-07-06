package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.Klassifikasjonssystem
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.KlassifikasjonsType
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.SystemIDBuilder
import java.time.ZonedDateTime

class KlassifikasjonssystemBuilder {
    var systemID: SystemIDBuilder? = null
        private set
    var klassifikasjonstype: KlassifikasjonsType? = null
        private set
    var tittel: String? = null
        private set
    var beskrivelse: String? = null
        private set
    var opprettetDato: ZonedDateTime? = null
        private set
    var opprettetAv: String? = null
        private set
    var avsluttetDato: ZonedDateTime? = null
        private set
    var avsluttetAv: String? = null
        private set
    var klasse: List<KlasseBuilder> = ArrayList()
        private set

    fun systemID(systemID: SystemIDBuilder) = apply { this.systemID = systemID }
    fun klassifikasjonstype(klassifikasjonstype: KlassifikasjonsType) = apply { this.klassifikasjonstype = klassifikasjonstype }
    fun tittel(tittel: String) = apply { this.tittel = tittel }
    fun beskrivelse(beskrivelse: String) = apply { this.beskrivelse = beskrivelse }
    fun opprettetDato(opprettetDato: ZonedDateTime) = apply { this.opprettetDato = opprettetDato }
    fun opprettetAv(opprettetAv: String) = apply { this.opprettetAv = opprettetAv }
    fun avsluttetDato(avsluttetDato: ZonedDateTime) = apply { this.avsluttetDato = avsluttetDato }
    fun avsluttetAv(avsluttetAv: String) = apply { this.avsluttetAv = avsluttetAv }
    fun klasse(klasse: List<KlasseBuilder>) = apply { this.klasse = klasse }

    fun build() : Klassifikasjonssystem {
        return Klassifikasjonssystem().also {
            it.systemID = systemID?.build()
            it.klassifikasjonstype = klassifikasjonstype?.value
            it.tittel = checkNotNull(tittel) {"Tittel er påkrevd for Klassifikasjonsystem"}
            it.beskrivelse = beskrivelse
            it.opprettetDato = opprettetDato
            it.opprettetAv = opprettetAv
            it.avsluttetDato = avsluttetDato
            it.avsluttetAv = avsluttetAv
            it.klasses.addAll(klasse.map { k -> k.build() }.toList())
            if(klasse.isEmpty()) throw IllegalStateException("En eller flere klasser er påkrevd for klassifikasjonssystem")
        }
    }
}
