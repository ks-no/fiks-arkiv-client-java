package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.MerknadsType
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.SystemIDBuilder
import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Merknad
import java.time.ZonedDateTime

open class MerknadBuilder {
    var systemID: SystemIDBuilder? = null
        private set
    var merknadstekst: String? = null
        private set
    var merknadstype: MerknadsType? = null
        private set
    var merknadsDato: ZonedDateTime = ZonedDateTime.now()
        private set
    var merknadRegistrertAv: String? = null
        private set
    var skjermetObjekt: String? = null
        private set


    fun systemID(systemID: SystemIDBuilder) = apply { this.systemID = systemID }
    fun merknadstekst(merknadstekst: String) = apply { this.merknadstekst = merknadstekst }
    fun merknadstype(merknadstype: MerknadsType) = apply { this.merknadstype = merknadstype }
    fun merknadRegistrertAv(merknadRegistrertAv: String) = apply { this.merknadRegistrertAv = merknadRegistrertAv }
    fun skjermetObjekt(skjermetObjekt: String) = apply { this.skjermetObjekt = skjermetObjekt }

    open fun build() : Merknad {
        return Merknad().also {
            it.systemID = systemID?.build() ?: throw IllegalStateException("SystemID er påkrevd for Klasse")
            it.merknadstekst = checkNotNull(merknadstekst) {"Merknadstekst er påkrevd for Klasse"}
            it.merknadstype = merknadstype?.value
            it.merknadsdato = merknadsDato
            it.merknadRegistrertAv = checkNotNull(merknadRegistrertAv) {"MerknadRegistrertAv er påkrevd for klasse"}
            it.skjermetObjekt = skjermetObjekt
        }
    }
}
