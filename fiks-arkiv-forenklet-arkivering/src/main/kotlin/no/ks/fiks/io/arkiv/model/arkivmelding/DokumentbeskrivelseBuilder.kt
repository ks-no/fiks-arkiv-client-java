package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.opprett.*
import no.ks.fiks.arkiv.v1.arkivstruktur.ElektroniskSignatur
import no.ks.fiks.arkiv.v1.arkivstruktur.Sletting
import no.ks.fiks.arkiv.v1.arkivstruktur.UtfoertKassasjon
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.Kode
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.SystemID
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.*
import java.time.ZonedDateTime

open class DokumentbeskrivelseBuilder {
    var systemID: SystemID? = null
        private set
    var dokumentType: DokumentType? = null
        private set
    var dokumentStatus: DokumentStatus? = null
        private set
    var tittel: String? = null
        private set
    var beskrivelse: String? = null
        private set
    var forfattere: List<String> = ArrayList()
        private set
    var opprettetDato: ZonedDateTime = ZonedDateTime.now()
        private set
    var opprettetAv: String? = null
        private set
    var dokumentmedium: DokumentmediumType? = null
        private set
    var oppbevaringssted: String? = null
        private set
    var arkivdel: Kode? = null
        private set
    var tilknyttetRegistreringSom: TilknyttetRegistreringSomType? = null
        private set
    var dokumentnummer: Int? = null
        private set
    var tilknyttetDato: ZonedDateTime = ZonedDateTime.now()
        private set
    var tilknyttetAv: String? = null
        private set
    var parts: List<Part> = ArrayList()
        private set
    var merknader: List<Merknad> = ArrayList()
        private set
    var kassasjon: Kassasjon? = null
        private set
    var utfoertKassasjon: UtfoertKassasjon? = null
        private set
    var sletting: Sletting? = null
        private set
    var skjerming: Skjerming? = null
        private set
    var gradering: Gradering? = null
        private set
    var elektroniskSignatur: ElektroniskSignatur? = null
        private set
    var dokumentobjekter: List<Dokumentobjekt> = ArrayList()
        private set

    fun systemID(systemID: SystemID) = apply { this.systemID = systemID }
    fun dokumentType(dokumentType: DokumentType) = apply { this.dokumentType = dokumentType }
    fun dokumentStatus(dokumentStatus: DokumentStatus) = apply { this.dokumentStatus = dokumentStatus }
    fun tittel(tittel: String) = apply { this.tittel = tittel }
    fun beskrivelse(beskrivelse: String) = apply { this.beskrivelse = beskrivelse }
    fun forfattere(forfattere: List<String>) = apply { this.forfattere = forfattere }
    fun opprettetDato(opprettetDato: ZonedDateTime) = apply { this.opprettetDato = opprettetDato }
    fun opprettetAv(opprettetAv: String) = apply { this.opprettetAv = opprettetAv }
    fun dokumentmedium(dokumentmedium: DokumentmediumType) = apply { this.dokumentmedium = dokumentmedium }
    fun oppbevaringssted(oppbevaringssted: String) = apply { this.oppbevaringssted = oppbevaringssted }
    fun arkivdel(arkivdel: Kode) = apply { this.arkivdel = arkivdel }
    fun tilknyttetRegistreringSom(tilknyttetRegistreringSom: TilknyttetRegistreringSomType) = apply { this.tilknyttetRegistreringSom = tilknyttetRegistreringSom }
    fun dokumentnummer(dokumentnummer: Int) = apply { this.dokumentnummer = dokumentnummer }
    fun tilknyttetDato(tilknyttetDato: ZonedDateTime) = apply { this.tilknyttetDato = tilknyttetDato }
    fun tilknyttetAv(tilknyttetAv: String) = apply { this.tilknyttetAv = tilknyttetAv }
    fun parts(parts: List<Part>) = apply { this.parts = parts }
    fun merknader(merknader: List<Merknad>) = apply { this.merknader = merknader }
    fun kassasjon(kassasjon: Kassasjon) = apply { this.kassasjon = kassasjon }
    fun utfortKassasjon(utfortKassasjon: UtfoertKassasjon) = apply { this.utfoertKassasjon = utfortKassasjon }
    fun sletting(sletting: Sletting) = apply { this.sletting = sletting }
    fun skjerming(skjerming: Skjerming) = apply { this.skjerming = skjerming }
    fun gradering(gradering: Gradering) = apply { this.gradering = gradering }
    fun elektroniskSignatur(elektroniskSignatur: ElektroniskSignatur) = apply { this.elektroniskSignatur = elektroniskSignatur }
    fun dokumentobjekter(dokumentobjekter: List<Dokumentobjekt>) = apply { this.dokumentobjekter = dokumentobjekter }

    fun build() : Dokumentbeskrivelse {
        return Dokumentbeskrivelse().also {
            it.systemID = systemID
            it.dokumenttype = dokumentType?.value ?: throw IllegalStateException("DokumentType er påkrevd for Dokumentbeskrivelse")
            it.dokumentstatus = dokumentStatus?.value ?: throw IllegalStateException("DokumentStatus er påkrevd for Dokumentbeskrivelse")
            it.tittel = checkNotNull(tittel) {"Tittel er påkrevd for Dokumentbeskrivelse"}
            it.beskrivelse = beskrivelse
            it.forfatters.addAll(forfattere)
            it.opprettetDato = opprettetDato
            it.opprettetAv = opprettetAv
            it.dokumentmedium = dokumentmedium?.value
            it.tilknyttetRegistreringSom = tilknyttetRegistreringSom?.value
            it.dokumentnummer = dokumentnummer
            it.tilknyttetDato = tilknyttetDato
            it.tilknyttetAv = tilknyttetAv
            it.parts.addAll(parts)
            it.merknads.addAll(merknader)
            it.skjerming = skjerming
            it.gradering = gradering
            it.dokumentobjekts.addAll(dokumentobjekter)
        }
    }
}
