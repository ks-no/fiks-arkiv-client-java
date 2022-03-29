package no.ks.fiks.io.arkiv.model

data class Korrespondansepart(val korrespondansepartType: String? = null, val korrespondansepartNavn: String? = null, val organisasjonId: String? = null, val postadresse: List<String> = emptyList(), val postnummer: String? = null, val poststed: String? = null, val saksbehandler: String? = null, val administrativEnhet: String? = null) {
    fun buildApiModel(): no.ks.fiks.io.arkiv.v1.client.models.arkivering.arkivmelding.Korrespondansepart {
        return no.ks.fiks.io.arkiv.v1.client.models.arkivering.arkivmelding.Korrespondansepart().also {
            it.korrespondanseparttype = korrespondansepartType
            it.korrespondansepartNavn = korrespondansepartNavn
            it.organisasjonid = organisasjonId
            it.postadresse.addAll(postadresse)
            it.postnummer = postnummer
            it.poststed = poststed
            it.saksbehandler = saksbehandler
            it.administrativEnhet = administrativEnhet
        }
    }
}
