package no.ks.fiks.io.arkiv.model

data class EksternNoekkel(val fagsystem: String, val noekkel: String) {
    fun buildApiModel(): no.ks.fiks.io.arkiv.v1.client.models.arkivering.arkivmelding.EksternNoekkel {
        return no.ks.fiks.io.arkiv.v1.client.models.arkivering.arkivmelding.EksternNoekkel().also {
            it.fagsystem = fagsystem
            it.noekkel = noekkel
        }
    }
}
