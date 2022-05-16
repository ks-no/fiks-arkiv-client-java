package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.SkjermingOpphoererAksjonType
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.TilgangrestriksjonType
import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Skjerming
import java.time.ZonedDateTime

open class SkjermingBuilder {
    var tilgangsrestriksjon: TilgangrestriksjonType? = null
        private set
    var skjermingshjemmel: String? = null
        private set
    var skjermingOpphoererDato: ZonedDateTime? = null
        private set
    var skjermingOpphoererAksjon: SkjermingOpphoererAksjonType? = null

    fun tilgangsrestriksjon(tilgangsrestriksjon: TilgangrestriksjonType) = apply { this.tilgangsrestriksjon = tilgangsrestriksjon }
    fun skjermingshjemmel(skjermingshjemmel: String) = apply { this.skjermingshjemmel = skjermingshjemmel }
    fun skjermingOpphoererDato(skjermingOpphoererDato: ZonedDateTime) = apply { this.skjermingOpphoererDato = skjermingOpphoererDato }
    fun skjermingOpphoererAksjon(skjermingOpphoererAksjonType: SkjermingOpphoererAksjonType) = apply { this.skjermingOpphoererAksjon = skjermingOpphoererAksjon }

    open fun build() : Skjerming {
        return Skjerming().also {
            it.tilgangsrestriksjon = tilgangsrestriksjon?.value ?: throw IllegalStateException("Tilgangsrestriksjon er påkrevd for Skjerming")
            it.skjermingshjemmel = checkNotNull(skjermingshjemmel) {"Skjermingshjemmel er påkrevd for Skjerming"}
            it.skjermingOpphoererDato = skjermingOpphoererDato
            it.skjermingOpphoererAksjon = skjermingOpphoererAksjon?.value
        }
    }
}
