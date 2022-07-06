package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.arkiv.v1.arkivmelding.EksternNoekkel

class EksternNoekkelBuilder {
    var fagsystem: String? = null
        private set
    var noekkel: String? = null
        private set

    fun fagstystem(fagsystem: String) = apply { this.fagsystem = fagsystem }
    fun noekkel(noekkel: String) = apply { this.noekkel = noekkel }

    fun build(): EksternNoekkel {
        return EksternNoekkel().also {
            it.fagsystem = checkNotNull(fagsystem) {"Fagsystem er påkrevd for EksternNoekkel"}
            it.noekkel = checkNotNull(noekkel) {"Noekkel er påkrevd for EksternNoekkel"}
        }
    }
}
