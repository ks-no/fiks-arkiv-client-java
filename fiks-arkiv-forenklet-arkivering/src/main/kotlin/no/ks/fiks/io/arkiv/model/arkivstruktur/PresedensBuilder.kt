package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.PresedensStatusType
import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Presedens
import java.time.ZonedDateTime

class PresedensBuilder {
    var presedensDato: ZonedDateTime? = null
        private set
    var opprettetDato: ZonedDateTime? = null
        private set
    var opprettetAv: String? = null
        private set
    var tittel: String? = null
        private set
    var beskrivelse: String? = null
        private set
    var presedensHjemmel: String? = null
        private set
    var rettskildefaktor: String? = null
        private set
    var presedensGodkjentDato: ZonedDateTime? = null
        private set
    var presedensGodkjentAv: String? = null
        private set
    var avsluttetDato: ZonedDateTime? = null
        private set
    var avsluttetAv: String? = null
        private set
    var presedensStatus: PresedensStatusType? = null
        private set

    fun presedensDato(presedensDato: ZonedDateTime) = apply { this.presedensDato = presedensDato }
    fun opprettetDato(opprettetDato: ZonedDateTime) = apply { this.opprettetDato = opprettetDato }
    fun opprettetAv(opprettetAv: String) = apply { this.opprettetAv = opprettetAv }
    fun tittel(tittel: String) = apply { this.tittel = tittel }
    fun beskrivelse(beskrivelse: String) = apply { this.beskrivelse = beskrivelse }
    fun presedensHjemmel(presedensHjemmel: String) = apply { this.presedensHjemmel = presedensHjemmel }
    fun rettskildefaktor(rettskildefaktor: String) = apply { this.rettskildefaktor = rettskildefaktor }
    fun presedensGodkjentDato(presedensGodkjentDato: ZonedDateTime) = apply { this.presedensGodkjentDato = presedensGodkjentDato }
    fun presedensGodkjentAv(presedensGodkjentAv: String) = apply { this.presedensGodkjentAv = presedensGodkjentAv }
    fun avsluttetDato(avsluttetDato: ZonedDateTime) = apply { this.avsluttetDato = avsluttetDato }
    fun avsluttetAv(avsluttetAv: String) = apply { this.avsluttetAv = avsluttetAv }
    fun presedensStatus(presedensStatus: PresedensStatusType) = apply { this.presedensStatus = presedensStatus }

    fun build() : Presedens {
        return Presedens().also {
            it.presedensDato = checkNotNull(presedensDato) {"PresedensDato er påkrevd for Presedens"}
            it.opprettetDato = checkNotNull(opprettetDato) {"OpprettetDato er påkrevd for Presedens"}
            it.opprettetAv = checkNotNull(opprettetAv) {"OpprettetAv er påkrevd for Presedens"}
            it.tittel = checkNotNull(tittel) {"Tittel er påkrevd for Presedens"}
            it.beskrivelse = beskrivelse
            it.presedensHjemmel = presedensHjemmel
            it.rettskildefaktor = checkNotNull(rettskildefaktor) {"Rettskildefaktor er påkrevd for Presedens"}
            it.presedensGodkjentDato = presedensGodkjentDato
            it.presedensGodkjentAv = presedensGodkjentAv
            it.avsluttetDato = avsluttetDato
            it.avsluttetAv = avsluttetAv
            it.presedensStatus = presedensStatus?.value
        }
    }
}
