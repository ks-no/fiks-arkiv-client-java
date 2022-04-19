package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.PartRolleType
import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Part

open class PartBuilder {
    var partID: String? = null
        private set
    var partNavn: String? = null
        private set
    var partRolle: PartRolleType? = null
        private set
    var postadresser: List<String> = emptyList()
        private set
    var postnummer: String? = null
        private set
    var poststed: String? = null
        private set
    var land: String? = null
        private set
    var epostadresse: String? = null
        private set
    var telefonnumre: List<String> = emptyList()
        private set
    var kontaktperson: String? = null
        private set

    fun partID(partID: String) = apply { this.partID = partID }
    fun partNavn(partNavn: String) = apply { this.partNavn = partNavn }
    fun partRolle(partRolle: PartRolleType) = apply { this.partRolle = partRolle }
    fun postAdresser(postadresser: List<String>) = apply { this.postadresser = postadresser }
    fun postnummer(postnummer: String) = apply { this.postnummer = postnummer }
    fun poststed(poststed: String) = apply { this.poststed = poststed }
    fun land(land: String) = apply { this.land = land }
    fun epostadresse(epostadresse: String) = apply { this.epostadresse = epostadresse }
    fun telefonnumre(telefonnumre: List<String>) = apply { this.telefonnumre = telefonnumre }
    fun kontaktperson(kontaktperson: String) = apply { this.kontaktperson = kontaktperson }

    open fun build() : Part {
        return Part().also {
            it.partID = partID
            it.partNavn = checkNotNull(partNavn) { feilmeldingPakrevdFelt("PartNavn") }
            it.partRolle = partRolle?.value ?: throw IllegalStateException(feilmeldingPakrevdFelt("PartRolle"))
            it.postadresses.addAll(postadresser)
            it.postnummer = postnummer
            it.poststed = poststed
            it.land = land
            it.epostadresse = epostadresse
            it.telefonnummers.addAll(telefonnumre)
            it.kontaktperson = kontaktperson
        }
    }

    private fun feilmeldingPakrevdFelt(prop: String) = "$prop er påkrevd felt for ${Part::class.java.name}"
}
