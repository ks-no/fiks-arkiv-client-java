package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.io.arkiv.model.arkivstruktur.PartBuilder
import no.ks.fiks.io.arkiv.v1.client.models.arkivmelding.Part

class ArkivmeldingPartBuilder : PartBuilder() {

    var organisasjonID: OrganisasjonsIDBuilder? = null
        private set
    var personID: PersonIDBuilder? = null
        private set
    var skjermetObjekt: String? = null
        private set
    var personnavn: String? = null
        private set

    fun organisasjonID(organisasjonID: OrganisasjonsIDBuilder) = apply { if(personID == null) this.organisasjonID = organisasjonID else throw IllegalArgumentException("ArkivmeldingPart kan ikke inneholde både personID og organisasjonsID") }
    fun personID(personID: PersonIDBuilder) = apply { if(this.organisasjonID == null) this.personID = personID else throw IllegalArgumentException("ArkivmeldingPart kan ikke inneholde både personID og organisasjonsID") }
    fun skjermetObjekt(skjermetObjekt: String) = apply { this.skjermetObjekt = skjermetObjekt }
    fun personnavn(personnavn: String) = apply { this.personnavn = personnavn }

    override fun build() : Part {
        return Part().also {
            it.organisasjonID = organisasjonID?.buildApiModel()
            it.personID = personID?.buildApiModel()
            it.skjermetObjekt = skjermetObjekt
            it.personnavn = personnavn
        }
    }

    private fun feilmeldingPakrevdFelt(prop: String) = "$prop er påkrevd felt for ${Part::class.java.name}"
}
