package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.Korrespondansepart
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.KorrespondansepartType
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.SystemIDBuilder

open class KorrespondansepartBuilder {

    var systemID: SystemIDBuilder? = null
        private set
    var korrespondansepartType: KorrespondansepartType? = null
        private set
    var korrespondansepartNavn: String? = null
        private set
    var postadresse: List<String> = emptyList()
        private set
    var postnummer: String? = null
        private set
    var poststed: String? = null
        private set
    var land: String? = null
        private set
    var epostadresse: String? = null
        private set
    var saksbehandler: String? = null
        private set
    var administrativEnhet: String? = null
        private set
    var kontaktperson: String? = null
        private set
    var telefonnumre: List<String> = ArrayList()
        private set
    var skjermetObjekt: String? = null
        private set
    var personnavn: String? = null
        private set
    var forsendelsesmaate: String? = null
        private set
    var deresReferanse: String? = null
        private set
    var organisasjonid: String? = null
        private set
    var personid: String? = null
        private set

    fun systemID(systemID: SystemIDBuilder) = apply { this.systemID = systemID }
    fun korrespondansepartType(korrespondansepartType: KorrespondansepartType) = apply { this.korrespondansepartType = korrespondansepartType }
    fun korrespondansepartNavn(korrespondansepartNavn: String) = apply { this.korrespondansepartNavn = korrespondansepartNavn }
    fun postadresse(postadresse: List<String>) = apply { this.postadresse = postadresse }
    fun postnummer(postnummer: String) = apply { this.postnummer = postnummer }
    fun poststed(poststed: String) = apply { this.poststed = poststed }
    fun land(land: String) = apply { this.land = land }
    fun epostadresse(epostadresse: String) = apply { this.epostadresse = epostadresse }
    fun saksbehandler(saksbehandler: String) = apply { this.saksbehandler = saksbehandler }
    fun administrativEnhet(administrativEnhet: String) = apply { this.administrativEnhet = administrativEnhet }
    fun kontaktperson(kontaktperson: String) = apply { this.kontaktperson = kontaktperson }
    fun telefonnumre(telefonnumre: List<String>) = apply { this.telefonnumre = telefonnumre }
    fun skjermetObjekt(skjermetObjekt: String) = apply { this.skjermetObjekt = skjermetObjekt }
    fun personnavn(personnavn: String) = apply { this.personnavn = personnavn }
    fun forsendelsesmaate(forsendelsesmaate: String) = apply { this.forsendelsesmaate = forsendelsesmaate }
    fun deresReferanse(deresReferanse: String) = apply { this.deresReferanse = deresReferanse }
    fun organisasjonid(organisasjonid: String) = apply { if(this.personid == null) this.organisasjonid = organisasjonid else throw IllegalStateException("Personid kan ikke settes i kombinasjon med organisasjonsid") }
    fun personid(personid: String) = apply { if(this.organisasjonid == null) this.personid = personid else throw IllegalStateException("Personid kan ikke settes i kombinasjon med organisasjonsid") }

    open fun build(): Korrespondansepart {
        return Korrespondansepart().also {
            it.systemID = systemID?.build()
            it.korrespondanseparttype = korrespondansepartType?.value ?: throw IllegalStateException("Korrespondanseparttype er påkrevd for Korrespondansepart")
            it.korrespondansepartNavn = checkNotNull(korrespondansepartNavn) {"KorrespondansepartNavn er påkrevd for Korrespondansepart"}
            it.postadresses.addAll(postadresse)
            it.postnummer = postnummer
            it.poststed = poststed
            it.land = land
            it.epostadresse = epostadresse
            it.telefonnummers.addAll(telefonnumre)
            it.kontaktperson = kontaktperson
            it.administrativEnhet = administrativEnhet
            it.saksbehandler = saksbehandler
            it.skjermetObjekt = skjermetObjekt
            it.personnavn = personnavn
            it.forsendelsesmaate = forsendelsesmaate
            it.deresReferanse = deresReferanse
            it.organisasjonid = organisasjonid
            it.personid = personid
        }
    }
}
