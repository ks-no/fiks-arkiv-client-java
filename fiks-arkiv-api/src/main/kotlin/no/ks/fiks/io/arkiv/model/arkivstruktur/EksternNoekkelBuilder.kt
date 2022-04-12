package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.EksternNoekkel

class EksternNoekkelBuilder {
    var fagsystem: String? = null
        private set
    var noekkel: String? = null
        private set

    fun fagstystem(fagsystem: String) = apply { this.fagsystem = fagsystem }
    fun noekkel(noekkel: String) = apply { this.noekkel = noekkel }

    fun build(): EksternNoekkel {
        return EksternNoekkel().also {
            it.fagsystem = fagsystem
            it.noekkel = noekkel
        }
    }
}
