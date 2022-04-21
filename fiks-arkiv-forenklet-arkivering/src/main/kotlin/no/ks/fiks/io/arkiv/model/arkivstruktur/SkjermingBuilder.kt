package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.SkjermingDokumentType
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.SkjermingMetadataType
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.TilgangrestriksjonType
import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Skjerming
import java.time.ZonedDateTime

open class SkjermingBuilder {
    var tilgangsrestriksjon: TilgangrestriksjonType? = null
        private set
    var skjermingshjemmel: String? = null
        private set
    var skjermingMetadataTyper: List<SkjermingMetadataType> = ArrayList()
        private set
    var skjermingDokumentType: SkjermingDokumentType? = null
        private set
    var skjermingsvarighet: Long? = null
        private set
    var skjermingOpphoererDato: ZonedDateTime? = null
        private set

    fun tilgangsrestriksjon(tilgangsrestriksjon: TilgangrestriksjonType) = apply { this.tilgangsrestriksjon = tilgangsrestriksjon }
    fun skjermingshjemmel(skjermingshjemmel: String) = apply { this.skjermingshjemmel = skjermingshjemmel }
    fun skjermingMetadataTyper(skjermingMetadataTyper: List<SkjermingMetadataType>) = apply { this.skjermingMetadataTyper = skjermingMetadataTyper }
    fun skjermingDokumentType(skjermingDokumentType: SkjermingDokumentType) = apply { this.skjermingDokumentType = skjermingDokumentType }
    fun skjermingsvarighet(skjermingsvarighet: Long) = apply { this.skjermingsvarighet = skjermingsvarighet }
    fun skjermingOpphoererDato(skjermingOpphoererDato: ZonedDateTime) = apply { this.skjermingOpphoererDato = skjermingOpphoererDato }

    open fun build() : Skjerming {
        return Skjerming().also {
            it.tilgangsrestriksjon = tilgangsrestriksjon?.value ?: throw IllegalStateException("Tilgangsrestriksjon er påkrevd for Skjerming")
            it.skjermingshjemmel = checkNotNull(skjermingshjemmel) {"Skjermingshjemmel er påkrevd for Skjerming"}
            it.skjermingMetadatas.addAll(skjermingMetadataTyper.map { t -> t.value }.toList().also { typer -> if(typer.isEmpty()) throw IllegalStateException("SkjermingMetadataTyper kan ikke være tom")  })
            it.skjermingDokument = skjermingDokumentType?.value
            it.skjermingsvarighet = skjermingsvarighet?.toBigInteger()
            it.skjermingOpphoererDato = skjermingOpphoererDato
        }
    }
}
