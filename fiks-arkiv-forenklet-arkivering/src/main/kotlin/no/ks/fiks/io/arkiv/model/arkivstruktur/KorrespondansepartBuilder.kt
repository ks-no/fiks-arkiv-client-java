package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.KorrespondansepartType
import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Korrespondansepart

open class KorrespondansepartBuilder {
    var korrespondansepartType: KorrespondansepartType = KorrespondansepartType.MOTTAKER
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
    var saksbehandler: String? = null
        private set
    var administrativEnhet: String? = null
        private set
    var kontaktperson: String? = null
        private set
    var epostadresse: String? = null
        private set
    var telefonnumre: List<String> = ArrayList()
        private set


    fun korrespondansepartType(korrespondansepartType: KorrespondansepartType) = apply { this.korrespondansepartType = korrespondansepartType }
    fun korrespondansepartNavn(korrespondansepartNavn: String) = apply { this.korrespondansepartNavn = korrespondansepartNavn }
    fun postadresse(postadresse: List<String>) = apply { this.postadresse = postadresse }
    fun postnummer(postnummer: String) = apply { this.postnummer = postnummer }
    fun poststed(poststed: String) = apply { this.poststed = poststed }
    fun land(land: String) = apply { this.land = land }
    fun saksbehandler(saksbehandler: String) = apply { this.saksbehandler = saksbehandler }
    fun administrativEnhet(administrativEnhet: String) = apply { this.administrativEnhet = administrativEnhet }
    fun kontaktperson(kontaktperson: String) = apply { this.kontaktperson = kontaktperson }
    fun epostadresse(epostadresse: String) = apply { this.epostadresse = epostadresse }
    fun telefonnumre(telefonnumre: List<String>) = apply { this.telefonnumre = telefonnumre }

    open fun build(): Korrespondansepart {
        return Korrespondansepart().also {
            it.korrespondanseparttype = korrespondansepartType.value
            it.korrespondansepartNavn = checkNotNull(korrespondansepartNavn) {"KorrespondansepartNavn er påkrevd for Korrespondansepart"}
            it.postadresses.addAll(postadresse)
            it.postnummer = postnummer
            it.poststed = poststed
            it.land = land
            it.saksbehandler = saksbehandler
            it.administrativEnhet = administrativEnhet
            it.kontaktperson = kontaktperson
            it.epostadresse = epostadresse
            it.telefonnummers.addAll(telefonnumre)
        }
    }
}
