package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.PartRolleType
import no.ks.fiks.io.arkiv.v1.client.models.arkivmelding.Part

class PartBuilder {

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
    var organisasjonID: OrganisasjonsIDBuilder? = null
        private set
    var personID: PersonIDBuilder? = null
        private set
    var skjermetObjekt: String? = null
        private set
    var personnavn: String? = null
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
    fun organisasjonID(organisasjonID: OrganisasjonsIDBuilder) = apply { if(personID == null) this.organisasjonID = organisasjonID else throw IllegalArgumentException("ArkivmeldingPart kan ikke inneholde både personID og organisasjonsID") }
    fun personID(personID: PersonIDBuilder) = apply { if(this.organisasjonID == null) this.personID = personID else throw IllegalArgumentException("ArkivmeldingPart kan ikke inneholde både personID og organisasjonsID") }
    fun skjermetObjekt(skjermetObjekt: String) = apply { this.skjermetObjekt = skjermetObjekt }
    fun personnavn(personnavn: String) = apply { this.personnavn = personnavn }

    fun build() : Part {
        return Part().also {
            it.partID = partID
            it.partNavn = checkNotNull(partNavn) { feilmeldingPakrevdFelt("PartNavn") }
            it.partRolle = partRolle?.value
            it.postadresses.addAll(postadresser)
            it.postnummer = postnummer
            it.poststed = poststed
            it.land = land
            it.epostadresse = epostadresse
            it.telefonnummers.addAll(telefonnumre)
            it.kontaktperson = kontaktperson
            it.organisasjonID = organisasjonID?.buildApiModel()
            it.personID = personID?.buildApiModel()
            it.skjermetObjekt = skjermetObjekt
            it.personnavn = personnavn
        }
    }

    private fun feilmeldingPakrevdFelt(prop: String) = "$prop er påkrevd felt for ${Part::class.java.name}"
}
