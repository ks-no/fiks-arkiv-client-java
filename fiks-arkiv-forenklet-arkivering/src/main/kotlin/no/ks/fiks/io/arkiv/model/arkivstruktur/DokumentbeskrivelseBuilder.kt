package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.*
import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Dokumentbeskrivelse
import java.time.ZonedDateTime

class DokumentbeskrivelseBuilder {
    var systemID: SystemIDBuilder? = null
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
    var referanseArkivDeler: List<String> = ArrayList()
        private set
    var tilknyttetRegistreringSom: TilknyttetRegistreringSomType = TilknyttetRegistreringSomType.HOVEDDOKUMENT
        private set
    var dokumentnummer: Long? = null
        private set
    var tilknyttetDato: ZonedDateTime = ZonedDateTime.now()
        private set
    var tilknyttetAv: String? = null
        private set
    var parts: List<PartBuilder> = ArrayList()
        private set
    var merknader: List<MerknadBuilder> = ArrayList()
        private set
    var kassasjon: KassasjonBuilder? = null
        private set
    var utfoertKassasjon: UtfortKassasjonBuilder? = null
        private set
    var sletting: SlettingBuilder? = null
        private set
    var skjerming: SkjermingBuilder? = null
        private set
    var gradering: GraderingBuilder? = null
        private set
    var elektroniskSignatur: ElektroniskSignaturBuilder? = null
        private set
    var dokumentobjekter: List<DokumentObjektBuilder> = ArrayList()
        private set

    fun systemID(systemID: SystemIDBuilder) = apply { this.systemID = systemID }
    fun dokumentType(dokumentType: DokumentType) = apply { this.dokumentType = dokumentType }
    fun dokumentStatus(dokumentStatus: DokumentStatus) = apply { this.dokumentStatus = dokumentStatus }
    fun tittel(tittel: String) = apply { this.tittel = tittel }
    fun beskrivelse(beskrivelse: String) = apply { this.beskrivelse = beskrivelse }
    fun forfattere(forfattere: List<String>) = apply { this.forfattere = forfattere }
    fun opprettetDato(opprettetDato: ZonedDateTime) = apply { this.opprettetDato = opprettetDato }
    fun opprettetAv(opprettetAv: String) = apply { this.opprettetAv = opprettetAv }
    fun dokumentmedium(dokumentmedium: DokumentmediumType) = apply { this.dokumentmedium = dokumentmedium }
    fun oppbevaringssted(oppbevaringssted: String) = apply { this.oppbevaringssted = oppbevaringssted }
    fun referanseArkivDeler(referanseArkivDeler: List<String>) = apply { this.referanseArkivDeler = referanseArkivDeler }
    fun tilknyttetRegistreringSom(tilknyttetRegistreringSom: TilknyttetRegistreringSomType) = apply { this.tilknyttetRegistreringSom = tilknyttetRegistreringSom }
    fun dokumentnummer(dokumentnummer: Long) = apply { this.dokumentnummer = dokumentnummer }
    fun tilknyttetDato(tilknyttetDato: ZonedDateTime) = apply { this.tilknyttetDato = tilknyttetDato }
    fun tilknyttetAv(tilknyttetAv: String) = apply { this.tilknyttetAv = tilknyttetAv }
    fun parts(parts: List<PartBuilder>) = apply { this.parts = parts }
    fun merknader(merknader: List<MerknadBuilder>) = apply { this.merknader = merknader }
    fun kassasjon(kassasjon: KassasjonBuilder) = apply { this.kassasjon = kassasjon }
    fun utfortKassasjon(utfortKassasjon: UtfortKassasjonBuilder) = apply { this.utfoertKassasjon = utfortKassasjon }
    fun sletting(sletting: SlettingBuilder) = apply { this.sletting = sletting }
    fun skjerming(skjerming: SkjermingBuilder) = apply { this.skjerming = skjerming }
    fun gradering(gradering: GraderingBuilder) = apply { this.gradering = gradering }
    fun elektroniskSignatur(elektroniskSignatur: ElektroniskSignaturBuilder) = apply { this.elektroniskSignatur = elektroniskSignatur }
    fun dokumentobjekter(dokumentobjekter: List<DokumentObjektBuilder>) = apply { this.dokumentobjekter = dokumentobjekter }

    fun build() : Dokumentbeskrivelse {
        return Dokumentbeskrivelse().also {
            it.systemID = systemID?.build() ?: throw IllegalStateException("SystemID er påkrevd for Dokumentbeskrivelse")
            it.dokumenttype = dokumentType?.value ?: throw IllegalStateException("DokumentType er påkrevd for Dokumentbeskrivelse")
            it.dokumentstatus = dokumentStatus?.value ?: throw IllegalStateException("DokumentStatus er påkrevd for Dokumentbeskrivelse")
            it.tittel = checkNotNull(tittel) {"Tittel er påkrevd for Dokumentbeskrivelse"}
            it.beskrivelse = beskrivelse
            it.forfatters.addAll(forfattere)
            it.opprettetDato = opprettetDato
            it.opprettetAv = checkNotNull(opprettetAv) {"OpprettetAv er påkrevd for Dokumentbeskrivelse"}
            it.dokumentmedium = dokumentmedium?.value
            it.oppbevaringssted = oppbevaringssted
            it.referanseArkivdels.addAll(referanseArkivDeler)
            it.tilknyttetRegistreringSom = tilknyttetRegistreringSom.value
            it.dokumentnummer = dokumentnummer?.toBigInteger() ?: throw IllegalStateException("Dokumentnummer er påkrevd for Dokumentbeskrivelse")
            it.tilknyttetDato = tilknyttetDato
            it.tilknyttetAv = checkNotNull(tilknyttetAv)
            it.parts.addAll(parts.map { p -> p.build() }.toList())
            it.merknads.addAll(merknader.map { m -> m.build() }.toList())
            it.kassasjon = kassasjon?.build()
            it.utfoertKassasjon = utfoertKassasjon?.build()
            it.sletting = sletting?.build()
            it.skjerming = skjerming?.build()
            it.gradering = gradering?.build()
            it.elektroniskSignatur = elektroniskSignatur?.build()
            it.dokumentobjekts.addAll(dokumentobjekter.map { d -> d.build() }.toList())
        }
    }
}
