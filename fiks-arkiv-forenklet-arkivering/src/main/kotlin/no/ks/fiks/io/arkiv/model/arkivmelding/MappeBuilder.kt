package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.opprett.*
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.EksternNoekkel
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.Kode
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.ReferanseTilMappe
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.SystemID
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.DokumentmediumType
import java.time.ZonedDateTime

/**
 * Mappe er det overordnede objektet for å samle saker i. Det er mulig å ha mapper i mapper men ikke i en arkivmelding.
 */
open class MappeBuilder {
    var systemID: SystemID? = null
        private set
    var mappeId: String? = null
        private set
    var referanseForeldermappe: ReferanseTilMappe? = null
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
    var arkivdel: Kode? = null
        private set
    var parts: List<Part> = ArrayList()
        private set
    var kryssreferanser: List<Kryssreferanse> = ArrayList()
        private set
    var merknader: List<Merknad> = ArrayList()
        private set
    var skjerming: Skjerming? = null
        private set
    var gradering: Gradering? = null
        private set
    var klassifikasjoner: List<Klassifikasjon> = ArrayList()
        private set
    var referanseEksternNoekkel: EksternNoekkel? = null
        private set
    var mappetype: Kode? = null
        private set

    fun systemID(systemID: SystemID) = apply { this.systemID = systemID }
    fun mappeId(mappeId: String) = apply { this.mappeId = mappeId }
    fun referanseForeldermappe(referanseForeldermappe: ReferanseTilMappe) = apply { this.referanseForeldermappe = referanseForeldermappe }
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
    fun arkivdel(arkivdel: Kode) = apply { this.arkivdel = arkivdel }
    fun parts(parts: List<Part>) = apply { this.parts = parts }
    fun kryssreferanser(kryssreferanser: List<Kryssreferanse>) = apply { this.kryssreferanser = kryssreferanser }
    fun merknader(merknader: List<Merknad>) = apply { this.merknader = merknader }
    fun skjerming(skjerming: Skjerming) = apply { this.skjerming = skjerming }
    fun gradering(gradering: Gradering) = apply { this.gradering = gradering }
    fun klassifikasjoner(klassifikasjoner: List<Klassifikasjon>) = apply { this.klassifikasjoner = klassifikasjoner }
    fun referanseEksternNoekkel(referanseEksternNoekkel: EksternNoekkel) = apply { this.referanseEksternNoekkel = referanseEksternNoekkel }
    fun mappetype(mappetype: Kode) = apply { this.mappetype = mappetype }

    open fun build(): Mappe {
        return Mappe().also {
            it.systemID = systemID
            it.mappeID = mappeId
            it.referanseForeldermappe = referanseForeldermappe
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
            it.arkivdel = arkivdel
            it.parts.addAll(parts)
            it.kryssreferanses.addAll(kryssreferanser)
            it.merknads.addAll(merknader)
            it.skjerming = skjerming
            it.gradering = gradering
            it.klassifikasjons.addAll(klassifikasjoner)
            it.referanseEksternNoekkel = referanseEksternNoekkel
            it.mappetype = mappetype
        }
    }
}
