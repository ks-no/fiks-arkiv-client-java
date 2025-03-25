package no.ks.fiks.io.arkiv.model.forenklet

import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.Forsendelsesmaate
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.KorrespondansepartType

class KorrespondansepartForenklet {
    var systemID: String? = null
        private set
    var enhetsidentifikator: Enhetsidentifikator? = null
        private set
    var personid: Personidentifikator? = null
        private set
    var korrespondanseparttype: KorrespondansepartType? = null
        private set
    var navn: String? = null
        private set
    var skjermetKorrespondansepart: Boolean? = null
        private set
    var postadresse: EnkelAdresse? = null
        private set
    var kontaktinformasjonForenklet: KontaktinformasjonForenklet? = null
        private set
    var kontaktperson: String? = null
        private set
    var deresReferanse: String? = null
        private set
    var forsendelsemåte: Forsendelsesmaate? = null
        private set

    fun systemID(systemID: String) = apply { this.systemID = systemID }
    fun enhetsidentifikator(enhetsidentifikator: Enhetsidentifikator) = apply { this.enhetsidentifikator = enhetsidentifikator }
    fun personid(personid: Personidentifikator) = apply { this.personid = personid }
    fun korrespondanseparttype(korrespondanseparttype: KorrespondansepartType) = apply { this.korrespondanseparttype = korrespondanseparttype }
    fun navn(navn: String) = apply { this.navn = navn }
    fun skjermetKorrespondansepart(skjermetKorrespondansepart: Boolean) = apply { this.skjermetKorrespondansepart = skjermetKorrespondansepart }
    fun postadresse(postadresse: EnkelAdresse) = apply { this.postadresse = postadresse }
    fun kontaktinformasjonForenklet(kontaktinformasjonForenklet: KontaktinformasjonForenklet) = apply { this.kontaktinformasjonForenklet = kontaktinformasjonForenklet }
    fun kontaktperson(kontaktperson: String) = apply { this.kontaktperson = kontaktperson }
    fun deresReferanse(deresReferanse: String) = apply { this.deresReferanse = deresReferanse }
    fun forsendelsemaate(forsendelsemåte: Forsendelsesmaate) = apply { this.forsendelsemåte = forsendelsemåte }
}