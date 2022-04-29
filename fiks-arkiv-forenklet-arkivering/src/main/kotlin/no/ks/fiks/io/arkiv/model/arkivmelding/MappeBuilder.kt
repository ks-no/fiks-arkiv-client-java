package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.io.arkiv.model.arkivstruktur.*
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.DokumentmediumType
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.SystemIDBuilder
import no.ks.fiks.io.arkiv.v1.client.models.arkivmelding.Mappe
import java.time.ZonedDateTime

/**
 * Mappe er det overordnede objektet for å samle saker i. Det er mulig å ha mapper i mapper.
 */
open class MappeBuilder {
    var systemID: SystemIDBuilder? = null
        private set
    var mappeId: String? = null
        private set
    var referanseForeldermappe: SystemIDBuilder? = null
        private set
    var tittel: String? = null
        private set
    var offentligTittel: String? = null
        private set
    var beskrivelse: String? = null
        private set
    var noekkelord: List<String> = ArrayList()
        private set
    var dokumentmedium: DokumentmediumType? = null
        private set
    var oppbevaringssteder: List<String> = ArrayList()
        private set
    var opprettetDato: ZonedDateTime? = null
        private set
    var opprettetAv: String? = null
        private set
    var avsluttetDato: ZonedDateTime? = null
        private set
    var avsluttetAv: String? = null
        private set
    var referanseArkivdeler: List<String> = ArrayList()
        private set
    var parts: List<PartBuilder> = ArrayList()
        private set
    var kryssreferanser: List<KryssreferanseBuilder> = ArrayList()
        private set
    var merknader: List<MerknadBuilder> = ArrayList()
        private set
    var skjerming: SkjermingBuilder? = null
        private set
    var gradering: GraderingBuilder? = null
        private set
    var klassifikasjoner: List<KlassifikasjonBuilder> = ArrayList()
        private set
    var referanseEksternNoekkel: EksternNoekkelBuilder? = null
        private set
    var registreringer: List<IRegistrering> = emptyList()
        private set
    var mapper: List<MappeBuilder> = emptyList()
        private set

    fun systemID(systemID: SystemIDBuilder) = apply { this.systemID = systemID }
    fun mappeId(mappeId: String) = apply { this.mappeId = mappeId }
    fun referanseForeldermappe(referanseForeldermappe: SystemIDBuilder) = apply { this.referanseForeldermappe = referanseForeldermappe }
    fun tittel(tittel: String) = apply { this.tittel = tittel }
    fun offentligTittel(offentligTittel: String) = apply { this.offentligTittel = offentligTittel }
    fun beskrivelse(beskrivelse: String) = apply { this.beskrivelse =  beskrivelse }
    fun noekkelord(noekkelord: List<String>) = apply { this.noekkelord = noekkelord }
    fun dokumentmedium(dokumentmedium: DokumentmediumType) = apply { this.dokumentmedium = dokumentmedium }
    fun oppbevaringssteder(oppbevaringssteder: List<String>) = apply { this.oppbevaringssteder = oppbevaringssteder }
    fun opprettetDato(opprettetDato: ZonedDateTime) = apply { this.opprettetDato = opprettetDato }
    fun opprettetAv(opprettetAv: String) = apply { this.opprettetAv = opprettetAv }
    fun avsluttetDato(avsluttetDato: ZonedDateTime) = apply { this.avsluttetDato = avsluttetDato }
    fun avsluttetAv(avsluttetAv: String) = apply { this.avsluttetAv = avsluttetAv }
    fun referanseArkivdeler(referanseArkivdeler: List<String>) = apply { this.referanseArkivdeler = referanseArkivdeler }
    fun parts(parts: List<PartBuilder>) = apply { this.parts = parts }
    fun kryssreferanser(kryssreferanser: List<KryssreferanseBuilder>) = apply { this.kryssreferanser = kryssreferanser }
    fun merknader(merknader: List<MerknadBuilder>) = apply { this.merknader = merknader }
    fun skjerming(skjerming: SkjermingBuilder) = apply { this.skjerming = skjerming }
    fun gradering(gradering: GraderingBuilder) = apply { this.gradering = gradering }
    fun klassifikasjoner(klassifikasjoner: List<KlassifikasjonBuilder>) = apply { this.klassifikasjoner = klassifikasjoner }
    fun referanseEksternNoekkel(referanseEksternNoekkel: EksternNoekkelBuilder) = apply { this.referanseEksternNoekkel = referanseEksternNoekkel }
    fun registreringer(registreringer: List<IRegistrering>) = apply { if(mapper.isEmpty()) this.registreringer = registreringer else throw IllegalArgumentException("Det er ikke mulig å registrere både undermapper og registreringer til samme mappe") }
    fun mapper(mapper: List<MappeBuilder>) = apply { if(mapper.isEmpty()) this.mapper = mapper else throw IllegalArgumentException("Det er ikke mulig å registrere både undermapper og registreringer til samme mappe") }

    open fun build(): Mappe {
        return Mappe().also {
            it.systemID = systemID?.build()
            it.mappeID = mappeId
            it.referanseForeldermappe = referanseForeldermappe?.build()
            it.tittel = checkNotNull(tittel) {"Tittel er påkrevd felt for Mappe"}
            it.offentligTittel = offentligTittel
            it.beskrivelse = beskrivelse
            it.noekkelords.addAll(noekkelord)
            it.dokumentmedium = dokumentmedium?.value
            it.oppbevaringssteds.addAll(oppbevaringssteder)
            it.opprettetDato = opprettetDato
            it.opprettetAv = opprettetAv
            it.avsluttetDato = avsluttetDato
            it.avsluttetAv = avsluttetAv
            it.referanseArkivdels.addAll(referanseArkivdeler)
            it.parts.addAll(parts.map { p -> p.build() }.toList())
            it.kryssreferanses.addAll(kryssreferanser.map { k -> k.build() }.toList())
            it.merknads.addAll(merknader.map { m -> m.build() }.toList())
            it.skjerming = skjerming?.build()
            it.gradering = gradering?.build()
            it.klassifikasjons.addAll(klassifikasjoner.map { k -> k.build() }.toList())
            it.referanseEksternNoekkel = referanseEksternNoekkel?.build()
            it.registrerings.addAll(registreringer.map { r -> r.build() }.toList())
            it.mappes.addAll(mapper.map { m -> m.build() }.toList())
        }
    }
}
