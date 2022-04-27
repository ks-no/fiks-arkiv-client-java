package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.io.arkiv.v1.client.models.arkivmelding.Korrespondansepart

class KorrespondansepartBuilder: no.ks.fiks.io.arkiv.model.arkivstruktur.KorrespondansepartBuilder() {

    var deresReferanse: String? = null
        private set
    var forsendelsesmaate: String? = null
        private set
    var organisasjonid: String? = null
        private set
    var personid: String? = null
        private set

    fun deresReferanse(deresReferanse: String) = apply { this.deresReferanse = deresReferanse }
    fun forsendelsesmaate(forsendelsesmaate: String) = apply { this.forsendelsesmaate = forsendelsesmaate }
    fun organisasjonid(organisasjonid: String) = apply { this.organisasjonid = organisasjonid }
    fun personid(personid: String) = apply { this.personid = personid }

    override fun build() : Korrespondansepart {
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
            it.deresReferanse = deresReferanse
            it.forsendelsesmaate = forsendelsesmaate
            it.organisasjonid = organisasjonid
            it.personid = personid
        }
    }
}