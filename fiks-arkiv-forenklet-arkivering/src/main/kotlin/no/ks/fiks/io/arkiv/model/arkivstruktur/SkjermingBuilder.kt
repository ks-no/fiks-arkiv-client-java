package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.arkiv.v1.arkivmelding.Skjerming
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.SkjermingOpphoererAksjonType
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.TilgangrestriksjonType
import java.time.LocalDate

open class SkjermingBuilder {
    var tilgangsrestriksjon: TilgangrestriksjonType? = null
        private set
    var skjermingshjemmel: String? = null
        private set
    var skjermingOpphoererDato: LocalDate? = null
        private set
    var skjermingOpphoererAksjon: SkjermingOpphoererAksjonType? = null

    fun tilgangsrestriksjon(tilgangsrestriksjon: TilgangrestriksjonType) = apply { this.tilgangsrestriksjon = tilgangsrestriksjon }
    fun skjermingshjemmel(skjermingshjemmel: String) = apply { this.skjermingshjemmel = skjermingshjemmel }
    fun skjermingOpphoererDato(skjermingOpphoererDato: LocalDate) = apply { this.skjermingOpphoererDato = skjermingOpphoererDato }
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
